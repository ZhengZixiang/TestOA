package me.zzx.oa.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
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
@Table(name="t_organization")
public class Organization {
	
	private int id;
	
	private String name;
	
	private String sn;
	
	private String description;
	
	private Organization parent;
	
	private Set<Organization> children = new HashSet<Organization>();
	
	private List<Person> persons = new ArrayList<Person>();
	
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

	//多对一双向关联切勿将cascade设成ALL！
	@ManyToOne(cascade={CascadeType.MERGE},
			fetch=FetchType.EAGER
			)
	public Organization getParent() {
		return parent;
	}

	public void setParent(Organization parent) {
		this.parent = parent;
	}

	//LazyCollection属性设置成EXTRA指定了当如果查询数据的个数时候，只会发出一条 count(*)的语句，提高性能
	@OneToMany(mappedBy="parent",
			cascade={CascadeType.ALL},
			fetch=FetchType.LAZY
			)
	@LazyCollection(LazyCollectionOption.EXTRA)
	public Set<Organization> getChildren() {
		return children;
	}

	public void setChildren(Set<Organization> children) {
		this.children = children;
	}

	@OneToMany(mappedBy="org",
			cascade={CascadeType.ALL},
			fetch=FetchType.LAZY
			)
	@LazyCollection(LazyCollectionOption.EXTRA)
	public List<Person> getPersons() {
		return persons;
	}

	public void setPersons(List<Person> persons) {
		this.persons = persons;
	}
	
}
