package me.zzx.oa.dao.impl;

import javax.annotation.Resource;

import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import me.zzx.oa.dao.FieldInputDao;
import me.zzx.oa.model.FieldInput;

@Repository("inputDao")
public class FieldInputDaoImpl implements FieldInputDao {

	private HibernateTemplate hibernateTemplate;
	
	@Override
	public void save(FieldInput input) {
		hibernateTemplate.save(input);
	}

	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}

	@Resource
	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}

}
