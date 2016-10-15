package me.zzx.oa.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="t_form")
/**
 * 表单类
 * @author pc
 *
 */
public class Form {
	private int id;
	
	/**
	 * 对应的工作流
	 */
	private Workflow workflow;
	
	/**
	 * 表单模板
	 */
	private String template;
	
	/**
	 * 包含的表单域
	 */
	private List<FormField> fields;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@ManyToOne(fetch=FetchType.EAGER,
			cascade={CascadeType.MERGE}
			)	
	public Workflow getWorkflow() {
		return workflow;
	}

	public void setWorkflow(Workflow workflow) {
		this.workflow = workflow;
	}

	public String getTemplate() {
		return template;
	}

	public void setTemplate(String template) {
		this.template = template;
	}


	@OneToMany(fetch=FetchType.LAZY,
			cascade={CascadeType.ALL},
			mappedBy="form"
			)
	public List<FormField> getFields() {
		return fields;
	}

	public void setFields(List<FormField> fields) {
		this.fields = fields;
	}
	
}
