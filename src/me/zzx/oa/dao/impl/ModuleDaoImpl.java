package me.zzx.oa.dao.impl;

import javax.annotation.Resource;

import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import me.zzx.oa.dao.ModuleDao;
import me.zzx.oa.dao.PagerDao;
import me.zzx.oa.dto.Pager;
import me.zzx.oa.model.Module;

@Repository("moduleDao")
public class ModuleDaoImpl implements ModuleDao {

	private PagerDao pagerDao;
	
	private HibernateTemplate hibernateTemplate;
	
	@Override
	public void save(Module module) {
		hibernateTemplate.save(module);
	}

	@Override
	public Module load(int id) {
		return hibernateTemplate.load(Module.class, id);
	}

	@Override
	public void delete(Module module) {
		hibernateTemplate.delete(module);
	}

	@Override
	public void update(Module module) {
		hibernateTemplate.update(module);
	}

	@Override
	public void searchModules(int parentId, Pager pager) {
		if(parentId == 0) {
			pagerDao.searchPaginated("from Module m where m.parent.id is null", pager);
		} else {
			pagerDao.searchPaginated("from Module m where m.parent.id = ?", parentId, pager);
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
