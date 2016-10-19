package me.zzx.oa.dao;

import java.util.List;

import me.zzx.oa.model.FieldInput;
import me.zzx.oa.model.FieldType;
import me.zzx.oa.model.Form;
import me.zzx.oa.model.FormField;

public interface FormDao {

	public Form loadFormByWorkflow(int workflowId);

	public void saveForm(Form form);

	public void deleteForm(Form form);

	public Form loadForm(int formId);

	public FieldType loadFieldType(int fieldTypeId);

	public FieldInput loadFieldInput(int fieldInputId);

	public void saveField(FormField field);

	public void deleteField(FormField field);

	public List<Form> searchForms();

	public FormField loadField(int fieldId);

	public void updateField(FormField field);

	public List<FieldInput> searchFieldInputs();

	public List<FieldType> searchFieldTypes();

}
