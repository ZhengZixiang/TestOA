package me.zzx.oa.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import me.zzx.oa.dao.FormDao;
import me.zzx.oa.model.FieldInput;
import me.zzx.oa.model.FieldType;
import me.zzx.oa.model.Form;
import me.zzx.oa.model.FormField;

@Repository("formDao")
public class FormDaoImpl implements FormDao {
	
	private HibernateTemplate hibernateTemplate;

	public void saveOrUpdate(Form form) {
		hibernateTemplate.saveOrUpdate(form);
	}
	
	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}

	@Resource
	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}

	@Override
	public Form loadFormByWorkflow(int workflowId) {
		String hql = "from Form f where f.workflow.id = :var";
		return (Form) hibernateTemplate.getSessionFactory().getCurrentSession()
				.createQuery(hql)
				.setParameter("var", workflowId)
				.getSingleResult();
	}

	@Override
	public void saveForm(Form form) {
		hibernateTemplate.saveOrUpdate(form);
	}

	@Override
	public void deleteForm(Form form) {
		hibernateTemplate.delete(form);
	}

	@Override
	public Form loadForm(int formId) {
		return hibernateTemplate.getSessionFactory().getCurrentSession().createQuery("from Form f where f.id = :var", Form.class)
		.setParameter("var", formId).getSingleResult();
	}

	@Override
	public FieldType loadFieldType(int fieldTypeId) {
		return hibernateTemplate.getSessionFactory().getCurrentSession().createQuery("from FieldType ft where ft.id = :var", FieldType.class)
		.setParameter("var", fieldTypeId).getSingleResult();
	}

	@Override
	public FieldInput loadFieldInput(int fieldInputId) {
		return hibernateTemplate.getSessionFactory().getCurrentSession().createQuery("from FieldInput fi where fi.id = :var", FieldInput.class)
		.setParameter("var", fieldInputId).getSingleResult();
	}

	@Override
	public void saveField(FormField field) {
		hibernateTemplate.saveOrUpdate(field);
	}

	@Override
	public void deleteField(FormField field) {
		hibernateTemplate.delete(field);
	}

	@Override
	public List<Form> searchForms() {
		return hibernateTemplate
				.getSessionFactory().getCurrentSession()
				.createQuery("from Form", Form.class)
				.getResultList();
	}

	@Override
	public FormField loadField(int fieldId) {
		return hibernateTemplate.load(FormField.class, fieldId);
	}

	@Override
	public void updateField(FormField field) {
		hibernateTemplate.saveOrUpdate(field);
	}

	@Override
	public List<FieldInput> searchFieldInputs() {
		return hibernateTemplate
				.getSessionFactory().getCurrentSession()
				.createQuery("from FieldInput", FieldInput.class)
				.getResultList();
	}

	@Override
	public List<FieldType> searchFieldTypes() {
		return hibernateTemplate
				.getSessionFactory().getCurrentSession()
				.createQuery("from FieldType", FieldType.class)
				.getResultList();
	}

}

