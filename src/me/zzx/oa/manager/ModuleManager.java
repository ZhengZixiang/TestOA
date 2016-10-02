package me.zzx.oa.manager;

import me.zzx.oa.dto.Pager;
import me.zzx.oa.model.Module;

public interface ModuleManager {
	/**
	 * ���ģ����Ϣ�������ģ���IDΪ0������Ӷ���ģ��
	 * @param module
	 * @param parentId
	 */
	public void add(Module module, int parentId);
	
	/**
	 * ɾ��ģ��
	 * @param id
	 */
	public void delete(int id);
	
	/**
	 * ����ģ��
	 * @param module
	 * @param parentId
	 */
	public void update(Module module, int parentId);
	
	/**
	 * ��ѯ�ض�ģ��
	 * @param moduleId
	 * @return
	 */
	public Module find(int id);
	
	/**
	 * ��ҳ��ѯģ���б�
	 * @param parentId
	 * @param pager
	 */
	public void searchModules(int parentId, Pager pager);
	
}
