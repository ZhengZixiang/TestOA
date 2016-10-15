package me.zzx.oa.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="t_formfield")
/**
 * 表单域
 * @author pc
 *
 */
public class FormField {

	private int id;
	
	/**
	 * 表单域名称
	 */
	private String fieldName;
	
	/**
	 * 表单域标签(提示输入内容）
	 */
	private String fieldLabel;

	/**
	 * 表单域输入形式
	 */
	private FieldInput fieldInput;
	
	/**
	 * 表单域输入类型
	 */
	private FieldType fieldType;
	
	/**
	 * 额外参数，表单域的输入条目
	 * 如下拉框和box
	 */
	private List<FieldItem> items;
	
	/**
	 * 表单域对应的表单
	 */
	private Form form;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public String getFieldLabel() {
		return fieldLabel;
	}

	public void setFieldLabel(String fieldLabel) {
		this.fieldLabel = fieldLabel;
	}

	@ManyToOne(fetch=FetchType.EAGER,
			cascade={CascadeType.MERGE}
			)	
	public FieldInput getFieldInput() {
		return fieldInput;
	}

	public void setFieldInput(FieldInput fieldInput) {
		this.fieldInput = fieldInput;
	}

	@ManyToOne(fetch=FetchType.EAGER,
			cascade={CascadeType.MERGE}
			)
	public FieldType getFieldType() {
		return fieldType;
	}

	public void setFieldType(FieldType fieldType) {
		this.fieldType = fieldType;
	}

	//组件集合映射
	@ElementCollection
	@CollectionTable(
			name="t_fielditem",
			joinColumns=@JoinColumn(name="field_id")
			)
	public List<FieldItem> getItems() {
		return items;
	}

	public void setItems(List<FieldItem> items) {
		this.items = items;
	}

	@ManyToOne(fetch=FetchType.EAGER,
			cascade={CascadeType.MERGE}
			)	
	public Form getForm() {
		return form;
	}

	public void setForm(Form form) {
		this.form = form;
	}
	
}
