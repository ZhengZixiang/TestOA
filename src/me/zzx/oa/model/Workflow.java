package me.zzx.oa.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="t_workflow")
public class Workflow {

	//����ID
	private int id;
	
	//��������
	private String name;
	
	//�����ļ�
	private byte[] processFile;
	
	//����ͼƬ
	private String processImagePath;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	@Column(unique=true)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(length=99999999)
	public byte[] getProcessFile() {
		return processFile;
	}

	public void setProcessFile(byte[] processFile) {
		this.processFile = processFile;
	}

	@Column(length=999)
	public String getProcessImagePath() {
		return processImagePath;
	}

	public void setProcessImagePath(String processImagePath) {
		this.processImagePath = processImagePath;
	}
	
}
