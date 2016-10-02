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
	 * 查询用户所拥有的角色信息
	 * @param userId
	 * @param roleId
	 * @param orderNo
	 */	
	public void searchUserRoleMappings(int userId, Pager pager);

	/**
	 * 添加或者更新角色信息，如果用户已经拥有该角色，则更新其优先级
	 * 否则给用户分配新的角色并设置优先级
	 * @param userId
	 * @param roleId
	 */
	public void addOrUpdateUserRoleMapping(int userId, int roleId, int orderNo);
	
	/**
	 * 删除用户分配的角色关联
	 * @param userId
	 * @param roleId
	 */
	public void deleteUserRoleMapping(int userId, int roleId);
	
	public User login(String username, String password);
}
