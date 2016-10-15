package me.zzx.oa.manager;

import java.io.File;
import java.util.List;
import java.util.Map;

import me.zzx.oa.model.Workflow;

public interface WorkflowManager {
	/**
	 * 部署流程
	 * @param processFile
	 * @param processImage
	 * @throws Exception 
	 */
	public void deployProcessDefinition(String processName, File processFile, String processImagePath) throws Exception;
	
	/**
	 * 查询流程
	 * @param id
	 * @return
	 */
	public Workflow find(int id);
	
	/**
	 * 删除流程
	 * @param id
	 */
	public void delete(int id);
	
	/**
	 * 查询流程列表
	 * @return
	 */
	public List<Workflow> searchWorkflows();
	
	/**
	 * 搜索下一步的流向
	 * @param processInstanceId 流程实例ID
	 * @return
	 */
	public Map<String, String> searchNextSteps(long processInstanceId);
	
	/**
	 * 
	 * @param username 用户名
	 * @param transitionName 所选择的下一步流向的ID
	 * @param processInstanceId 流程实例ID
	 * @return 
	 */
	public String flowToNextStep(String username, String stepId, long processInstanceId);
}
