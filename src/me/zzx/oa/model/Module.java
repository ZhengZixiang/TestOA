package me.zzx.oa.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity
@Table(name="t_module")
public class Module {
	private int id;
	
	private String name;
	
	//模块编号
	private String sn;
	
	//模块入口地址
	private String url;
	
	//模块排序号
	private int orderNo;

	private Module Parent;
	
	private List<Module> Children;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Column(nullable=false, unique=true)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(nullable=false, unique=true)
	public String getSn() {
		return sn;
	}

	public void setSn(String sn) {
		this.sn = sn;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public int getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(int orderNo) {
		this.orderNo = orderNo;
	}

	@ManyToOne(cascade={CascadeType.MERGE},
			fetch=FetchType.EAGER
			)
	public Module getParent() {
		return Parent;
	}

	public void setParent(Module parent) {
		Parent = parent;
	}

	@OneToMany(mappedBy="parent",
			cascade={CascadeType.ALL},
			fetch=FetchType.LAZY
			)
	@LazyCollection(LazyCollectionOption.EXTRA)
	public List<Module> getChildren() {
		return Children;
	}

	public void setChildren(List<Module> children) {
		Children = children;
	}
	
}
