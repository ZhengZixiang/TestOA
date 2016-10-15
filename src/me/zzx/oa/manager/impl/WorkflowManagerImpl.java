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
		
		//若流程不存在，则创建和部署流程，并保存到数据库
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
		
		//若流程已存在，则更新数据库
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
		
		//根据流程实例ID获得对应的任务列表
		List<Task> tasks = taskService.createTaskQuery().processInstanceId(Long.toString(processInstanceId)).list();
		
		//获取当前任务节点
		Task task = tasks.get(0);
		
		//获得当前任务对应的当前流程的流程定义
		ProcessDefinitionEntity pde = (ProcessDefinitionEntity) ((RepositoryServiceImpl)repositoryService).getDeployedProcessDefinition(task.getProcessDefinitionId());
		
		//获得流程定义下所有活动节点
		List<ActivityImpl> activities = pde.getActivities();
		
		//获得执行实例ID
		String excId = task.getExecutionId();
		
		//获得执行实例
		ExecutionEntity execution = (ExecutionEntity) runtimeService.createExecutionQuery().executionId(excId).singleResult();
		
		//根据执行实例获得当前活动ID
		String activitiId = execution.getActivityId();
		
		//遍历所有活动节点，与当前活动ID相同，则获得当前活动实例并获得所有出口
		for(ActivityImpl activiti : activities) {
			if(activiti.getId().equals(activitiId))
				transitions = activiti.getOutgoingTransitions();
		}
		
		//从各个出口flow获得终点，从而获得下一步流向的节点名字及其ID，放到map中存储
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
