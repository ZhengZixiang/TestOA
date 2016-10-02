package me.zzx.oa.manager.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import me.zzx.oa.dao.PersonDao;
import me.zzx.oa.dao.UserDao;
import me.zzx.oa.dto.Pager;
import me.zzx.oa.manager.UserManager;
import me.zzx.oa.model.User;
import me.zzx.oa.model.UserRoleMapping;
import me.zzx.oa.util.SystemException;

@Service("userManager")
public class UserManagerImpl implements UserManager {

	private UserDao userDao;
	
	private PersonDao personDao;
	
	@Override
	public void add(User user, int personId) throws SystemException {
		if(personId == 0) {
			throw new SystemException("exception.user.add");
		}
		user.setPerson(personDao.load(personId));
		userDao.save(user);
	}

	@Override
	public void delete(int id) {
		userDao.delete(userDao.load(id));
	}

	@Override
	public void update(User user, int personId) {
		if(personId == 0) {
			throw new SystemException("exception.user.update");
		}
		user.setPerson(personDao.load(personId));
		userDao.update(user);
	}

	@Override
	public User find(int id) {
		return userDao.load(id);
	}

	@Override
	public void searchUserRoleMappings(int userId, Pager pager) {
		userDao.searchUserRoleMappings(userId, pager);
	}

	@Override
	public void addOrUpdateUserRoleMapping(int userId, int roleId, int orderNo) {
		userDao.addOrUpdateUserRoleMapping(userId, roleId, orderNo);
	}

	@Override
	public void deleteUserRoleMapping(int userId, int roleId) {
		userDao.deleteUserRoleMapping(userId, roleId);
	}

	@Override
	public User login(String username, String password) throws SystemException {
		User user = userDao.load(username);
		if(user == null) throw new SystemException("error.username");
		if(!password.equals(user.getPassword())) throw new SystemException("error.password");
		return user;
	}

	public UserDao getUserDao() {
		return userDao;
	}

	@Resource
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	public PersonDao getPersonDao() {
		return personDao;
	}

	@Resource
	public void setPersonDao(PersonDao personDao) {
		this.personDao = personDao;
	}

	@Override
	public UserRoleMapping findUserRoleMapping(int userId, int roleId) {
		return userDao.findUserRoleMapping(userId, roleId);
	}

}
