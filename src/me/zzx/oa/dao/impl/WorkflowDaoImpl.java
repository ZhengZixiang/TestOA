package me.zzx.oa.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import me.zzx.oa.dao.WorkflowDao;
import me.zzx.oa.model.Workflow;

@Repository("workflowDao")
public class WorkflowDaoImpl implements WorkflowDao {
	
	private HibernateTemplate hibernateTemplate;

	@Override
	public void save(Workflow workflow) {
		hibernateTemplate.save(workflow);
	}

	@Override
	public void delete(Workflow workflow) {
		hibernateTemplate.delete(workflow);
	}

	@Override
	public Workflow load(int id) {
		return hibernateTemplate.load(Workflow.class, id);
	}
	
	@Override
	public Workflow loadByName(String processName) {
		return hibernateTemplate.getSessionFactory().getCurrentSession()
			.createQuery("from Workflow w where w.name = :var", Workflow.class)
			.setParameter("var", processName)
			.getSingleResult();
	}

	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}

	@Resource
	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}

	@Override
	public List<Workflow> searchWorkflows() {
		return hibernateTemplate.getSessionFactory().getCurrentSession()
				.createQuery("from Workflow", Workflow.class)
				.getResultList();
	}
}
