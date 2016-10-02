package me.zzx.oa.manager;

import me.zzx.oa.dto.Pager;
import me.zzx.oa.model.Role;

public interface RoleManager {
	public void add(Role role);
	
	public void delete(int id);
	
	public void update(Role role);
	
	public Role find(int id);
	
	public void searchRoles(Pager pager);
}
