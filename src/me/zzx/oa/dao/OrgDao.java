package me.zzx.oa.dao;

import me.zzx.oa.dto.Pager;
import me.zzx.oa.model.Organization;

public interface OrgDao {
	public void save(Organization org, int parentId);
	
	public void delete(Organization org);
	
	public void update(Organization org);
	
	public Organization load(int id);
	
	public void searchOrgs(int parentId, Pager pager);
}
