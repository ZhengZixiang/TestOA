package me.zzx.oa.manager;

import java.util.List;

import me.zzx.oa.model.Module;

public interface AclManager {
	/**
	 * ��Ȩ
	 * @param principalType
	 * @param principalId
	 * @param ResourceId
	 * @param permission
	 * @param accpeted
	 */
	public void addOrUpdatePermission(String principalType, int principalId, int resourceId, int permission, boolean accepted);
	
	/**
	 * ȡ����Ȩ
	 * @param principalType
	 * @param principalId
	 * @param resourceId
	 */
	public void deletePermission(String principalType, int principalId, int resourceId);
	
	/**
	 * ��ӻ�����û��ļ̳�����
	 * @param userId
	 * @param resourceId
	 * @param yes
	 */
	public void addOrUpdateUserExtends(int userId, int resourceId, boolean yes);
	
	/**
	 * ��֤���ж��û��Ƿ�ӵ��ĳģ���ĳ������Ȩ��
	 * @param userId �û�ID
	 * @param resourceId ��Դ��ʶ
	 * @param permission CRUDȨ��
	 * @return ����true/������false
	 */
	public boolean hasPermission(int userId, int resourceId, int permission);
	
	/**
	 * �����û�ӵ�ж�ȡȨ�޵�ģ���б������γɵ����˵�
	 * @param userId �û�ID
	 * @return ģ���б�
	 */
	public List<Module> searchModules(int userId);
}
