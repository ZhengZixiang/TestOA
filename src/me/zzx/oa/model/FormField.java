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
 * ����
 * @author pc
 *
 */
public class FormField {

	private int id;
	
	/**
	 * ��������
	 */
	private String fieldName;
	
	/**
	 * �����ǩ(��ʾ�������ݣ�
	 */
	private String fieldLabel;

	/**
	 * ����������ʽ
	 */
	private FieldInput fieldInput;
	
	/**
	 * ������������
	 */
	private FieldType fieldType;
	
	/**
	 * ��������������������Ŀ
	 * ���������box
	 */
	private List<FieldItem> items;
	
	/**
	 * �����Ӧ�ı�
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

	//�������ӳ��
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
