package me.zzx.oa.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="t_fieldtype")
/**
 * 表单类型
 * @author pc
 *
 */
public class FieldType {

	private int id;
	
	/**
	 * 类型名称，如字符串、整型
	 */
	private String name;
	
	/**
	 * 对应的Java类型，如String、Integer
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
