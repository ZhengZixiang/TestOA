package me.zzx.oa.manager;

import java.util.List;

import me.zzx.oa.model.FieldInput;
import me.zzx.oa.model.FieldItem;
import me.zzx.oa.model.FieldType;
import me.zzx.oa.model.Form;
import me.zzx.oa.model.FormField;

public interface FormManager {

	public void updateFieldItems(int fieldId, List<FieldItem> items);

	public void addForm(Form form);
	
	public void deleteForm(int formId);
	
	public List<Form> searchForms();

	public Form findFormByWorkflow(int workflowId);
	
	public FormField findField(int fieldId);

	public void addField(FormField field, int formId, int fieldTypeId, int fieldInputId);

	public void deleteField(int id);

	public List<FieldType> searchFieldTypes();
	
	public List<FieldInput> searchFieldInputs();

	public Form findForm(int id);

}
