package me.zzx.oa.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="t_fieldinput")
/**
 * ��������ʽ
 * @author pc
 *
 */
public class FieldInput {
	
	private int id;

	/**
	 * ҳ���Ԫ�ص����ƣ��磺
	 * �ı������������
	 */
	private String name;
	
	/**
	 * ���ҳ���Ԫ��HTML����ģ��
	 * ͨ��freemarkerʵ��
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
