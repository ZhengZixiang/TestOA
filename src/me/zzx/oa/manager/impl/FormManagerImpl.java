package me.zzx.oa.manager.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import me.zzx.oa.dao.FormDao;
import me.zzx.oa.manager.FormManager;
import me.zzx.oa.model.FieldInput;
import me.zzx.oa.model.FieldItem;
import me.zzx.oa.model.FieldType;
import me.zzx.oa.model.Form;
import me.zzx.oa.model.FormField;

@Service("formManager")
public class FormManagerImpl implements FormManager {
	
	private FormDao formDao;
	
	@Override
	public void updateFieldItems(int fieldId, List<FieldItem> items) {
		FormField field = formDao.loadField(fieldId);
		field.setItems(items);
		formDao.updateField(field);
	}

	@Override
	public void addForm(Form form) {
		formDao.saveForm(form);
	}

	@Override
	public Form findFormByWorkflow(int workflowId) {
		return formDao.loadFormByWorkflow(workflowId);
	}

	public FormDao getFormDao() {
		return formDao;
	}

	@Resource
	public void setFormDao(FormDao formDao) {
		this.formDao = formDao;
	}

	@Override
	public void deleteForm(int formId) {
		Form form = formDao.loadForm(formId);
		formDao.deleteForm(form);
	}

	@Override
	public List<Form> searchForms() {
		return formDao.searchForms();
	}

	@Override
	public FormField findField(int fieldId) {
		return formDao.loadField(fieldId);
	}

	@Override
	public void deleteField(int id) {
		FormField field = formDao.loadField(id);
		formDao.deleteField(field);
	}

	@Override
	public void addField(FormField field, int formId, int fieldTypeId, int fieldInputId) {
		Form form = formDao.loadForm(formId);
		FieldType type = formDao.loadFieldType(fieldTypeId);
		FieldInput input = formDao.loadFieldInput(fieldInputId);
		field.setForm(form);
		field.setFieldType(type);
		field.setFieldInput(input);
		formDao.saveField(field);
	}

	@Override
	public List<FieldType> searchFieldTypes() {
		return formDao.searchFieldTypes();
	}

	@Override
	public List<FieldInput> searchFieldInputs() {
		return formDao.searchFieldInputs();
	}
	
	public Form findForm(int formId) {
		return formDao.loadForm(formId);
	}

}
