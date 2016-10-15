package me.zzx.oa.manager.impl;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.impl.RepositoryServiceImpl;
import org.activiti.engine.impl.persistence.entity.ExecutionEntity;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.impl.pvm.PvmActivity;
import org.activiti.engine.impl.pvm.PvmTransition;
import org.activiti.engine.impl.pvm.process.ActivityImpl;
import org.activiti.engine.task.Task;
import org.springframework.stereotype.Service;

import me.zzx.oa.dao.WorkflowDao;
import me.zzx.oa.manager.WorkflowManager;
import me.zzx.oa.model.Document;
import me.zzx.oa.model.Workflow;
import me.zzx.oa.util.FileUtil;

@Service("workflowManager")
public class WorkflowManagerImpl implements WorkflowManager {

	private WorkflowDao workflowDao;
	
	private RepositoryService repositoryService;
	
	private TaskService taskService;
	
	private RuntimeService runtimeService;
	
	@Override
	public void deployProcessDefinition(String processName, File processFile, String processImagePath) throws Exception {
		
		Workflow workflow = workflowDao.loadByName(processName);
		byte [] processFileBytes = FileUtil.getByteArray(processFile);
		
		//�����̲����ڣ��򴴽��Ͳ������̣������浽���ݿ�
		if(null == workflow) {
			FileInputStream fis = new FileInputStream(processFile);
			repositoryService.createDeployment().addInputStream(processName + ".bpmn", fis).deploy();
			workflow = new Workflow();
			workflow.setName(processName);
			workflow.setProcessFile(processFileBytes);
			workflow.setProcessImagePath(processImagePath);
			workflowDao.save(workflow);
			return;
		}
		
		//�������Ѵ��ڣ���������ݿ�
		workflow.setName(processName);
		workflow.setProcessFile(processFileBytes);
		workflow.setProcessImagePath(processImagePath);
		workflowDao.save(workflow);
	}

	@Override
	public Workflow find(int id) {
		return workflowDao.load(id);
	}

	@Override
	public void delete(int id) {
		Workflow workflow = workflowDao.load(id);
		String deploymentId = repositoryService
				.createDeploymentQuery()
				.deploymentName(workflow.getName())
				.singleResult()
				.getId();
		repositoryService.deleteDeployment(deploymentId, true);
		workflowDao.delete(workflow);
	}

	@Override
	public List<Workflow> searchWorkflows() {
		return workflowDao.searchWorkflows();
	}

	@Override
	public Map<String, String> searchNextSteps(long processInstanceId) {
		List<PvmTransition> transitions = new ArrayList<PvmTransition>();
		Map<String, String> steps = new HashMap<> ();
		
		//��������ʵ��ID��ö�Ӧ�������б�
		List<Task> tasks = taskService.createTaskQuery().processInstanceId(Long.toString(processInstanceId)).list();
		
		//��ȡ��ǰ����ڵ�
		Task task = tasks.get(0);
		
		//��õ�ǰ�����Ӧ�ĵ�ǰ���̵����̶���
		ProcessDefinitionEntity pde = (ProcessDefinitionEntity) ((RepositoryServiceImpl)repositoryService).getDeployedProcessDefinition(task.getProcessDefinitionId());
		
		//������̶��������л�ڵ�
		List<ActivityImpl> activities = pde.getActivities();
		
		//���ִ��ʵ��ID
		String excId = task.getExecutionId();
		
		//���ִ��ʵ��
		ExecutionEntity execution = (ExecutionEntity) runtimeService.createExecutionQuery().executionId(excId).singleResult();
		
		//����ִ��ʵ����õ�ǰ�ID
		String activitiId = execution.getActivityId();
		
		//�������л�ڵ㣬�뵱ǰ�ID��ͬ�����õ�ǰ�ʵ����������г���
		for(ActivityImpl activiti : activities) {
			if(activiti.getId().equals(activitiId))
				transitions = activiti.getOutgoingTransitions();
		}
		
		//�Ӹ�������flow����յ㣬�Ӷ������һ������Ľڵ����ּ���ID���ŵ�map�д洢
		for(PvmTransition pv : transitions) {
			PvmActivity pa = pv.getDestination();
			steps.put((String) pa.getProperty("name"), pa.getId());
		}
		return steps;
	}

	@Override
	public String flowToNextStep(String username, String stepId, long processInstanceId) {
		List<Task> tasks = taskService.createTaskQuery().processInstanceId(Long.toString(processInstanceId)).list();
		Task task = tasks.get(0);
		task.setAssignee(username);
		taskService.complete(task.getId());
		tasks = taskService.createTaskQuery().processInstanceId(Long.toString(processInstanceId)).list();
		if(0 == tasks.size()) return Document.STATUS_END;
		Task nextTask = tasks.get(0);
		return nextTask.getName();
	}

	public WorkflowDao getWorkflowDao() {
		return workflowDao;
	}

	@Resource
	public void setWorkflowDao(WorkflowDao workflowDao) {
		this.workflowDao = workflowDao;
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

	public RuntimeService getRuntimeService() {
		return runtimeService;
	}

	@Resource
	public void setRuntimeService(RuntimeService runtimeService) {
		this.runtimeService = runtimeService;
	}

}
