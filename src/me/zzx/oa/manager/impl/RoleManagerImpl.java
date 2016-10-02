package me.zzx.oa.manager.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import me.zzx.oa.dao.RoleDao;
import me.zzx.oa.dto.Pager;
import me.zzx.oa.manager.RoleManager;
import me.zzx.oa.model.Role;

@Service("roleManager")
public class RoleManagerImpl implements RoleManager {

	private RoleDao roleDao;
	
	@Override
	public void add(Role role) {
		roleDao.save(role);
	}

	@Override
	public void delete(int id) {
		Role role = roleDao.load(id);
		roleDao.delete(role);
	}

	@Override
	public void update(Role role) {
		roleDao.update(role);
	}

	@Override
	public Role find(int id) {
		return roleDao.load(id);
	}

	@Override
	public void searchRoles(Pager pager) {
		roleDao.searchRoles(pager);
	}

	public RoleDao getRoleDao() {
		return roleDao;
	}

	@Resource
	public void setRoleDao(RoleDao roleDao) {
		this.roleDao = roleDao;
	}
}
