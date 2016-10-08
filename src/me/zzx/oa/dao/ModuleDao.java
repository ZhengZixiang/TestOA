package me.zzx.oa.dao;

import me.zzx.oa.dto.Pager;
import me.zzx.oa.model.Module;

public interface ModuleDao {
	public void save(Module module);
	
	public Module load(int id);

	public Module loadByName(String name);
	
	public Module loadBySn(String sn);
	
	public void delete(Module module);

	public void update(Module module);

	public void searchModules(int parentId, Pager pager);
}
