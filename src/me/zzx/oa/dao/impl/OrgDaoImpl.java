package me.zzx.oa.dao.impl;

import javax.annotation.Resource;

import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import me.zzx.oa.dao.OrgDao;
import me.zzx.oa.dao.PagerDao;
import me.zzx.oa.dto.Pager;
import me.zzx.oa.model.Organization;
import me.zzx.oa.util.SystemException;

@Repository("orgDao")
public class OrgDaoImpl implements OrgDao {

	private PagerDao pagerDao;
	private HibernateTemplate hibernateTemplate;
	
	@Override
	public void save(Organization org, int parentId) {
		if(parentId != 0) {
			org.setParent(hibernateTemplate.load(Organization.class, parentId));
		}
		hibernateTemplate.save(org);
		int id = (org.getId());
		if(parentId != 0) org.setSn(org.getParent().getSn() + "_" + id);
		else org.setSn("" + id);
		hibernateTemplate.update(org);	
	}

	@Override
	public void delete(Organization org) {
		hibernateTemplate.delete(org);
	}

	@Override
	public void update(Organization org) {
		hibernateTemplate.update(org);
	}

	@Override
	public Organization load(int id) {
		return hibernateTemplate.load(Organization.class, id);
	}

	@Override
	public void searchOrgs(int parentId, Pager pager) throws SystemException {
		if(parentId == 0) {
			pagerDao.searchPaginated("from Organization o where o.parent is null", pager);
		} else {
			pagerDao.searchPaginated("from Organization o where o.parent.id = ?", parentId, pager);
		}
	}

	public PagerDao getPagerDao() {
		return pagerDao;
	}

	@Resource
	public void setPagerDao(PagerDao pagerDao) {
		this.pagerDao = pagerDao;
	}

	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}

	@Resource
	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}

}
