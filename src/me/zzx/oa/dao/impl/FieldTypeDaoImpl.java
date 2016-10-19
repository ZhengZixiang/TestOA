package me.zzx.oa.dao.impl;

import javax.annotation.Resource;

import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import me.zzx.oa.dao.FieldTypeDao;
import me.zzx.oa.model.FieldType;

@Repository("typeDao")
public class FieldTypeDaoImpl implements FieldTypeDao {

	private HibernateTemplate hibernateTemlate;
	
	@Override
	public void save(FieldType type) {
		hibernateTemlate.save(type);
	}

	public HibernateTemplate getHibernateTemlate() {
		return hibernateTemlate;
	}

	@Resource
	public void setHibernateTemlate(HibernateTemplate hibernateTemlate) {
		this.hibernateTemlate = hibernateTemlate;
	}

}
