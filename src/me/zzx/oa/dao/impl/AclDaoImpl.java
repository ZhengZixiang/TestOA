package me.zzx.oa.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import me.zzx.oa.dao.AclDao;
import me.zzx.oa.model.AccessControlList;

@Repository("aclDao")
public class AclDaoImpl implements AclDao {

	private HibernateTemplate hibernateTemplate;
	
	@Override
	public AccessControlList find(String principalType, int principalId, int resourceId) {
		AccessControlList acl = new AccessControlList();
		acl.setPrincipalType(principalType);
		acl.setPrincipalId(principalId);
		acl.setResourceId(resourceId);
		return (AccessControlList) hibernateTemplate.findByExample(acl);
	}

	@Override
	public void update(AccessControlList acl) {
		hibernateTemplate.update(acl);
	}

	@Override
	public void save(AccessControlList acl) {
		hibernateTemplate.save(acl);
	}

	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}

	@Resource
	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}

	@Override
	public void delete(AccessControlList acl) {
		hibernateTemplate.delete(acl);
	}

	@Override
	public List<AccessControlList> findByExample(AccessControlList acl) {
		return hibernateTemplate.findByExample(acl);
	}

}

