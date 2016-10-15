package me.zzx.oa.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="t_fieldtype")
/**
 * ������
 * @author pc
 *
 */
public class FieldType {

	private int id;
	
	/**
	 * �������ƣ����ַ���������
	 */
	private String name;
	
	/**
	 * ��Ӧ��Java���ͣ���String��Integer
	 */
	private String type;

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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
}
