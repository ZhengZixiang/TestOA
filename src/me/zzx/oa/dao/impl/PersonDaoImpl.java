package me.zzx.oa.dao.impl;

import javax.annotation.Resource;

import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import me.zzx.oa.dao.PagerDao;
import me.zzx.oa.dao.PersonDao;
import me.zzx.oa.dto.Pager;
import me.zzx.oa.model.Person;

@Repository("personDao")
public class PersonDaoImpl implements PersonDao {

	private PagerDao pagerDao;
	private HibernateTemplate hibernateTemplate;
	
	@Override
	public void searchPersons(int orgId, Pager pager) {
		String hql = "from Person p where p.org.id = ?";
		pagerDao.searchPaginated(hql, orgId, pager);
	}

	@Override
	public void searchPersons(Pager pager) {
		String hql = "from Person p";
		pagerDao.searchPaginated(hql, pager);
	}

	@Override
	public Person load(int id) {
		return hibernateTemplate.load(Person.class, id);
	}

	@Override
	public void save(Person person) {
		hibernateTemplate.save(person);
	}

	@Override
	public void update(Person person) {
		hibernateTemplate.update(person);
	}
	
	@Override
	public void delete(Person person) {
		hibernateTemplate.delete(person);
	}

	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}

	@Resource
	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}

	public PagerDao getPagerDao() {
		return pagerDao;
	}

	@Resource
	public void setPagerDao(PagerDao pagerDao) {
		this.pagerDao = pagerDao;
	}

}
