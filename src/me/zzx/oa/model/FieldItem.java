package me.zzx.oa.model;

import javax.persistence.Embeddable;

@Embeddable
/**
 * �ɹ�ѡ��Ԫ��
 * @author pc
 *
 */
public class FieldItem {
	
	/**
	 * �ı�
	 */
	private String label;
	
	/**
	 * ֵ
	 */
	private String value;

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
}
