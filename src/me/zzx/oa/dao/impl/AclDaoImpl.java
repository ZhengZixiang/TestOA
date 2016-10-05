package me.zzx.oa.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import me.zzx.oa.dao.AclDao;
import me.zzx.oa.dto.Record;
import me.zzx.oa.model.AccessControlList;

@Repository("aclDao")
public class AclDaoImpl implements AclDao {

	private HibernateTemplate hibernateTemplate;
	
	@Override
	public AccessControlList find(String principalType, int principalId, int resourceId) {
		Session session = hibernateTemplate.getSessionFactory().getCurrentSession();
		return session.createQuery("from AccessControlList acl where (acl.principalType = ?) and (acl.principalId = ? ) and (acl.resourceId = ?)", AccessControlList.class)
			.setParameter(0, principalType)
			.setParameter(1, principalId)
			.setParameter(2, resourceId)
			.getSingleResult();
	}

	@Override
	public void update(AccessControlList acl) {
		hibernateTemplate.update(acl);
	}

	@Override
	public void save(AccessControlList acl) {
		hibernateTemplate.save(acl);
	}
	
	@Override
	public List<Record> searchAclRecord(String principalType, int principalId) {
		String hql = "select new me.zzx.oa.dto.Record(resourceId, aclState, aclTriState) "
				+ "from AccessControlList acl "
				+ "where principalType = :var1 and principalId = :var2 ";
		
		Session session = hibernateTemplate.getSessionFactory().getCurrentSession();
		List<Record> list = session.createQuery(hql, Record.class)
				.setParameter("var1", principalType)
				.setParameter("var2", principalId)
				.getResultList();
		return list;
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

