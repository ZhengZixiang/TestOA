package me.zzx.oa.model;

import java.util.Date;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapKeyColumn;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

@Entity
@Table(name="t_document")
public class Document {
	
	public static final String STATUS_START = "新建";
	
	public static final String STATUS_END = "结束";

	//公文ID
	private int id;
	
	//公文标题
	private String title;
	
	//公文描述
	private String description;
		
	//公文附件
	private byte[] content;
	
	//公文创建者
	private User creator;
	
	//公文创建时间
	private Date createTime;
	
	//公文对应的流程
	private Workflow workflow;

	//公文状态
	private String status;
	
	//流程实例标识
	private long processInstanceId;
	
	//表单动态属性
	private Map<String, DocumentProperty> props;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Type(type="binary")
	@Column(length=99999999)
	public byte[] getContent() {
		return content;
	}

	public void setContent(byte[] content) {
		this.content = content;
	}

	@ManyToOne(fetch=FetchType.EAGER,
			cascade={CascadeType.MERGE}
			)
	public User getCreator() {
		return creator;
	}

	public void setCreator(User creator) {
		this.creator = creator;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@ManyToOne(fetch=FetchType.EAGER,
			cascade={CascadeType.MERGE})
	public Workflow getWorkflow() {
		return workflow;
	}

	public void setWorkflow(Workflow workflow) {
		this.workflow = workflow;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public long getProcessInstanceId() {
		return processInstanceId;
	}

	public void setProcessInstanceId(long processInstanceId) {
		this.processInstanceId = processInstanceId;
	}

	@ElementCollection
	@CollectionTable(name="t_docprop", joinColumns=@JoinColumn(name="document_id"))
	@MapKeyColumn(name="prop_name")
	public Map<String, DocumentProperty> getProps() {
		return props;
	}

	public void setProps(Map<String, DocumentProperty> props) {
		this.props = props;
	}
	
}
