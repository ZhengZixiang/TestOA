package me.zzx.oa.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="t_fieldinput")
/**
 * 表单输入形式
 * @author pc
 *
 */
public class FieldInput {
	
	private int id;

	/**
	 * 页面表单元素的名称，如：
	 * 文本输入框、下拉框
	 */
	private String name;
	
	/**
	 * 针对页面表单元素HTML呈现模板
	 * 通过freemarker实现
	 */
	private String template;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTemplate() {
		return template;
	}

	public void setTemplate(String template) {
		this.template = template;
	}
	
	
}
