package me.zzx.oa.action;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import me.zzx.oa.manager.FormManager;
import me.zzx.oa.manager.WorkflowManager;
import me.zzx.oa.model.FieldInput;
import me.zzx.oa.model.FieldItem;
import me.zzx.oa.model.FieldType;
import me.zzx.oa.model.Form;
import me.zzx.oa.model.FormField;
import me.zzx.oa.model.Workflow;

@Component("formAction")
@Scope("prototype")
public class FormAction extends ActionSupport {

	private static final long serialVersionUID = -3553490461165970530L;

	private Form form;
	
	private FormField field;
	
	private FieldInput input;
	
	private FieldType type;
	
	private List<FieldItem> items;
	
	private FormManager formManager;
	
	private WorkflowManager workflowManager;
	
	private Workflow workflow;
	
	/**
	 * form管理主界面，显示所有流程信息
	 */
	public String execute() throws Exception {
		ActionContext.getContext().put("workflows", workflowManager.searchWorkflows());
		return "index";
	}
	
	/**
	 * 打开某个流程表单定义界面
	 * @return
	 * @throws Exception
	 */
	public String addFormInput() throws Exception {
		workflow = workflowManager.find(workflow.getId());
		form = formManager.findFormByWorkflow(workflow.getId());
		return "add_form_input";
	}
	
	/**
	 * 添加流程表单
	 * @return
	 * @throws Exception
	 */
	public String addForm() throws Exception {
		workflow = workflowManager.find(workflow.getId());
		form.setWorkflow(workflow);
		formManager.addForm(form);
		return "pub_add_success";
	}
	
	public String addItemInput() throws Exception {
		field = formManager.findField(field.getId());
		ActionContext.getContext().put("field", field);
		return "add_item_input";
	}
	
	/**
	 * 添加表单域的条目信息
	 * @return
	 * @throws Exception
	 */
	public String addItem() throws Exception {
		List<FieldItem> temp = new ArrayList<>();
		for(FieldItem item : items) {
			if(item != null && item.getLabel() != null && item.getValue() != null && !item.getLabel().trim().equals("") && !item.getValue().trim().equals("")) {
				temp.add(item);
			}
		}
		formManager.updateFieldItems(field.getId(), temp);
		return "pub_add_success";
	}
	
	public String addFieldInput() throws Exception {
		ActionContext ac = ActionContext.getContext();
		form = formManager.findForm(form.getId());
		ac.put("types", formManager.searchFieldTypes());
		ac.put("inputs", formManager.searchFieldInputs());
		return "add_field_input";
	}
	
	public String addField() throws Exception {
		formManager.addField(field, form.getId(), type.getId(), input.getId());
		return "pub_add_success";
	}
	
	public String deleteField() throws Exception {
		formManager.deleteField(field.getId());
		return "pub_delete_success";
	}

	public Form getForm() {
		return form;
	}

	public void setForm(Form form) {
		this.form = form;
	}

	public FormField getField() {
		return field;
	}

	public void setField(FormField field) {
		this.field = field;
	}

	public FieldInput getInput() {
		return input;
	}

	public void setInput(FieldInput input) {
		this.input = input;
	}

	public List<FieldItem> getItems() {
		return items;
	}

	public void setItems(List<FieldItem> items) {
		this.items = items;
	}

	public FormManager getFormManager() {
		return formManager;
	}

	@Resource
	public void setFormManager(FormManager formManager) {
		this.formManager = formManager;
	}

	public WorkflowManager getWorkflowManager() {
		return workflowManager;
	}

	@Resource
	public void setWorkflowManager(WorkflowManager workflowManager) {
		this.workflowManager = workflowManager;
	}

	public Workflow getWorkflow() {
		return workflow;
	}

	public void setWorkflow(Workflow workflow) {
		this.workflow = workflow;
	}

	public FieldType getType() {
		return type;
	}

	public void setType(FieldType type) {
		this.type = type;
	}
	
}
