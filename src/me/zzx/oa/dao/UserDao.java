package me.zzx.oa.dao;

import java.util.List;

import me.zzx.oa.dto.Pager;
import me.zzx.oa.model.Role;
import me.zzx.oa.model.User;
import me.zzx.oa.model.UserRoleMapping;

public interface UserDao {

	public void save(User user);

	public User load(int id);
	
	public User load(String username);

	public void delete(User user);

	public void update(User user);

	public void addOrUpdateUserRoleMapping(int userId, int roleId, int orderNo);

	public void deleteUserRoleMapping(int userId, int roleId);

	public UserRoleMapping findUserRoleMapping(int userId, int roleId);

	public void searchUserRoleMappings(int userId, Pager pager);
	
	public List<Role> searchSameUser(int userId);

}
