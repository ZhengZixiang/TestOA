package me.zzx.oa.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
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
@Table(name="t_orgnization")

public class Orgnization {
	
	private int id;
	
	private String name;
	
	private String sn;
	
	private String description;
	
	private Orgnization parent;
	
	private Set<Orgnization> children = new HashSet<Orgnization>();
	
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

	public String getSn() {
		return sn;
	}

	public void setSn(String sn) {
		this.sn = sn;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@ManyToOne(cascade={CascadeType.ALL},
			fetch=FetchType.EAGER
			)
	public Orgnization getParent() {
		return parent;
	}

	public void setParent(Orgnization parent) {
		this.parent = parent;
	}

	//LazyCollection属性设置成EXTRA指定了当如果查询数据的个数时候，只会发出一条 count(*)的语句，提高性能
	@OneToMany(mappedBy="parent",
			cascade={CascadeType.ALL},
			fetch=FetchType.LAZY
			)
	@LazyCollection(LazyCollectionOption.EXTRA)
	public Set<Orgnization> getChildren() {
		return children;
	}

	public void setChildren(Set<Orgnization> children) {
		this.children = children;
	}
	
	
}
