package me.zzx.oa.dao;

import me.zzx.oa.dto.Pager;
import me.zzx.oa.model.Role;

public interface RoleDao {

	public void save(Role role);

	public Role load(int id);

	public void delete(Role role);

	public void update(Role role);

	public void searchRoles(Pager pager);

	public Role loadByName(String name);

}
