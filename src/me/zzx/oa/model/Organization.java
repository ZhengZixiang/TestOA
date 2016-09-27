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

public class Organization {
	
	private int id;
	
	private String name;
	
	private String sn;
	
	private String description;
	
	private Organization parent;
	
	private Set<Organization> children = new HashSet<Organization>();
	
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

	//���һ˫���������cascade���ALL��
	@ManyToOne(cascade={CascadeType.MERGE},
			fetch=FetchType.EAGER
			)
	public Organization getParent() {
		return parent;
	}

	public void setParent(Organization parent) {
		this.parent = parent;
	}

	//LazyCollection�������ó�EXTRAָ���˵������ѯ���ݵĸ���ʱ��ֻ�ᷢ��һ�� count(*)����䣬�������
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
	
	
}
