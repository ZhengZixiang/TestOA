package me.zzx.oa.manager;

import me.zzx.oa.dto.Pager;
import me.zzx.oa.model.User;
import me.zzx.oa.model.UserRoleMapping;

public interface UserManager {
	public void add(User user, int personId);
	
	public void delete(int id);
	
	public void update(User user, int personId);
	
	public User find(int id);
	
	public UserRoleMapping findUserRoleMapping(int userId, int roleId);
	
	/**
	 * ��ѯ�û���ӵ�еĽ�ɫ��Ϣ
	 * @param userId
	 * @param roleId
	 * @param orderNo
	 */	
	public void searchUserRoleMappings(int userId, Pager pager);

	/**
	 * ��ӻ��߸��½�ɫ��Ϣ������û��Ѿ�ӵ�иý�ɫ������������ȼ�
	 * ������û������µĽ�ɫ���������ȼ�
	 * @param userId
	 * @param roleId
	 */
	public void addOrUpdateUserRoleMapping(int userId, int roleId, int orderNo);
	
	/**
	 * ɾ���û�����Ľ�ɫ����
	 * @param userId
	 * @param roleId
	 */
	public void deleteUserRoleMapping(int userId, int roleId);
	
	public User login(String username, String password);
}
