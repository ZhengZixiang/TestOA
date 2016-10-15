package me.zzx.oa.model;

import javax.persistence.Embeddable;

@Embeddable
/**
 * 可供选择元素
 * @author pc
 *
 */
public class FieldItem {
	
	/**
	 * 文本
	 */
	private String label;
	
	/**
	 * 值
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
