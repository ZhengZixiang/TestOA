package me.zzx.oa.manager.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import me.zzx.oa.dao.OrgDao;
import me.zzx.oa.dao.PersonDao;
import me.zzx.oa.dto.Pager;
import me.zzx.oa.manager.PersonManager;
import me.zzx.oa.model.Organization;
import me.zzx.oa.model.Person;
import me.zzx.oa.util.SystemException;

@Service("personManager")
public class PersonManagerImpl implements PersonManager {
	
	private PersonDao personDao;
	
	private OrgDao orgDao;
	
	@Override
	public void searchPersons(Pager pager) {
		personDao.searchPersons(pager);
	}

	@Override
	public void searchPersons(int orgId, Pager pager) {
		personDao.searchPersons(orgId, pager);
	}

	@Override
	public Person find(int id) {
		return personDao.load(id);
	}

	@Override
	public void add(Person person, int orgId) throws SystemException {
		if(orgId == 0) {
			throw new SystemException("error.person.add");
		} else {
			Organization org = orgDao.load(orgId);
			person.setOrg(org);
			personDao.save(person);
		}
	}

	@Override
	public void delete(Person person) {
		personDao.delete(person);
	}

	public PersonDao getPersonDao() {
		return personDao;
	}

	@Resource
	public void setPersonDao(PersonDao personDao) {
		this.personDao = personDao;
	}

	public OrgDao getOrgDao() {
		return orgDao;
	}
	
	@Resource
	public void setOrgDao(OrgDao orgDao) {
		this.orgDao = orgDao;
	}

	@Override
	public void update(Person person, int orgId) {
		if(orgId == 0) {
			throw new SystemException("error.person.add");
		} else {
			Organization org = orgDao.load(orgId);
			person.setOrg(org);
			personDao.update(person);
		}
	}
}
