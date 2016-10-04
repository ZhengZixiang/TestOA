package me.zzx.oa.dao.impl;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import me.zzx.oa.dao.PagerDao;
import me.zzx.oa.dao.RoleDao;
import me.zzx.oa.dto.Pager;
import me.zzx.oa.model.Role;

@Repository("roleDao")
public class RoleDaoImpl implements RoleDao {

	private PagerDao pagerDao;
	
	private HibernateTemplate hibernateTemplate;
	
	@Override
	public void save(Role role) {
		hibernateTemplate.save(role);
	}

	@Override
	public Role load(int id) {
		return hibernateTemplate.load(Role.class, id);
	}

	@Override
	public Role loadByName(String name) {
		Session session = hibernateTemplate.getSessionFactory().getCurrentSession();
		return session.createQuery("from Role r where r.name = ?", Role.class)
			.setParameter(0, name)
			.getSingleResult();
	}
	
	@Override
	public void delete(Role role) {
		hibernateTemplate.delete(role);
	}

	@Override
	public void update(Role role) {
		hibernateTemplate.update(role);
	}

	@Override
	public void searchRoles(Pager pager) {
		pagerDao.searchPaginated("from Role", pager);
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
