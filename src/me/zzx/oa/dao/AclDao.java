package me.zzx.oa.dao;

import java.util.List;

import me.zzx.oa.dto.Record;
import me.zzx.oa.model.AccessControlList;

public interface AclDao {

	public AccessControlList find(String principalType, int principalId, int resourceId);

	public void update(AccessControlList acl);

	public void save(AccessControlList acl);

	public void delete(AccessControlList acl);

	public List<Record> searchAclRecord(String principalType, int principalId);

	public List<AccessControlList> findByPrincipal(String principalType, int principalId);
}
