package me.zzx.oa.dao;

import java.util.List;

import me.zzx.oa.model.AccessControlList;

public interface AclDao {

	public AccessControlList find(String principalType, int principalId, int resourceId);

	public void update(AccessControlList acl);

	public void save(AccessControlList acl);

	public void delete(AccessControlList acl);

	public List<AccessControlList> findByExample(AccessControlList acl);
}
