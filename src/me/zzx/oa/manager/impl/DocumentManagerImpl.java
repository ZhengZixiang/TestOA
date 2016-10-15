package me.zzx.oa.manager.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.springframework.stereotype.Service;

import me.zzx.oa.dao.DocumentDao;
import me.zzx.oa.dao.UserDao;
import me.zzx.oa.dao.WorkflowDao;
import me.zzx.oa.dto.Pager;
import me.zzx.oa.manager.DocumentManager;
import me.zzx.oa.model.ApproveInfo;
import me.zzx.oa.model.Document;
import me.zzx.oa.model.Workflow;

@Service("documentManager")
public class DocumentManagerImpl implements DocumentManager {

	private DocumentDao documentDao;
	
	private UserDao userDao;
	
	private WorkflowDao workflowDao;
	
	private RuntimeService runtimeService;
	
	private RepositoryService repositoryService;
	
	private TaskService taskService;

	@Override
	public void add(Document document, int workflowId, int createrId) {
		//���湫����Ϣ���־û�ʵ��
		Workflow workflow = workflowDao.load(workflowId);
		document.setWorkflow(workflowDao.load(workflowId));
		document.setCreator(userDao.load(createrId));
		document.setStatus(Document.STATUS_START);
		document.setCreateTime(new Date());
		documentDao.save(document);
		
		//��������ʵ��ID��Ϊҵ��Key
		String businessKey = Integer.toString(document.getId());
		
		//��������ʵ������һ���������������ƣ��ڶ���������ҵ��ID������ID��
		ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(workflow.getName(), businessKey);
	
		//���������ö�Ӧ������ʵ��������
		document.setProcessInstanceId(Long.parseLong(processInstance.getId()));
		documentDao.update(document);
	}

	@Override
	public Document find(int id) {
		return documentDao.load(id);
	}

	@Override
	public void update(Document document) {
		documentDao.update(document);
	}

	@Override
	public void delete(int id) {
		//���ع���
		Document doc = this.find(id);
		
		//ɾ��������Ϣ
		documentDao.delete(doc);
		
		//ɾ������ʱ����ʵ�����ڶ���������ɾ��ԭ��
		runtimeService.deleteProcessInstance(Long.toString(doc.getProcessInstanceId()), "");
	}

	@Override
	public void searchCreatedDocuments(int userId, Pager pager) {
		documentDao.searchCreatedDocuments(userId, pager);
	}

	@Override
	public void searchApproveInfo(int documentId, Pager pager) {
		documentDao.searchApproveInfo(documentId, pager);
	}

	@Override
	public List<Object> searchApprovingDocuments(String username) {
		List<Task> tasks = taskService.createTaskQuery()
								.taskCandidateOrAssigned(username).list();
		List<Object> list = new ArrayList<Object> ();
		
		for(Task task : tasks) {
			list.add(documentDao.loadByProcessInstanceId(Long.parseLong(task.getProcessInstanceId())));
		}
		return list;
	}

	@Override
	public void searchApprovedDocuments(int userId, Pager pager) {
		documentDao.searchApprovedDocuments(userId, pager);
	}
	
	@Override
	public void addApproveInfo(ApproveInfo info, int documentId, int approverId) {
		info.setDocument(documentDao.load(documentId));
		info.setApprover(userDao.load(approverId));
		documentDao.saveApproveInfo(info);
	}

	public DocumentDao getDocumentDao() {
		return documentDao;
	}

	@Resource
	public void setDocumentDao(DocumentDao documentDao) {
		this.documentDao = documentDao;
	}

	public UserDao getUserDao() {
		return userDao;
	}

	@Resource
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}	

	public WorkflowDao getWorkflowDao() {
		return workflowDao;
	}

	@Resource
	public void setWorkflowDao(WorkflowDao workflowDao) {
		this.workflowDao = workflowDao;
	}

	public RuntimeService getRuntimeService() {
		return runtimeService;
	}

	@Resource
	public void setRuntimeService(RuntimeService runtimeService) {
		this.runtimeService = runtimeService;
	}

	public RepositoryService getRepositoryService() {
		return repositoryService;
	}

	@Resource
	public void setRepositoryService(RepositoryService repositoryService) {
		this.repositoryService = repositoryService;
	}

	public TaskService getTaskService() {
		return taskService;
	}

	@Resource
	public void setTaskService(TaskService taskService) {
		this.taskService = taskService;
	}

}
