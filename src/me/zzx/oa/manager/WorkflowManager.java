package me.zzx.oa.manager;

import java.io.File;
import java.util.List;
import java.util.Map;

import me.zzx.oa.model.Workflow;

public interface WorkflowManager {
	/**
	 * ��������
	 * @param processFile
	 * @param processImage
	 * @throws Exception 
	 */
	public void deployProcessDefinition(String processName, File processFile, String processImagePath) throws Exception;
	
	/**
	 * ��ѯ����
	 * @param id
	 * @return
	 */
	public Workflow find(int id);
	
	/**
	 * ɾ������
	 * @param id
	 */
	public void delete(int id);
	
	/**
	 * ��ѯ�����б�
	 * @return
	 */
	public List<Workflow> searchWorkflows();
	
	/**
	 * ������һ��������
	 * @param processInstanceId ����ʵ��ID
	 * @return
	 */
	public Map<String, String> searchNextSteps(long processInstanceId);
	
	/**
	 * 
	 * @param username �û���
	 * @param transitionName ��ѡ�����һ�������ID
	 * @param processInstanceId ����ʵ��ID
	 * @return 
	 */
	public String flowToNextStep(String username, String stepId, long processInstanceId);
}
