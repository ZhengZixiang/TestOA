package me.zzx.oa.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * ������ʷ��Ϣ��
 * @author pc
 *
 */
@Entity
@Table(name="t_approveinfo")
public class ApproveInfo {
	
	//������ʷ��ϢID
	private int id;
	
	//�������
	private String comment;
	
	//����ʱ��
	private Date approveTime;
	
	//������
	private User approver;
	
	//���������ļ�
	private Document document;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Date getApproveTime() {
		return approveTime;
	}

	public void setApproveTime(Date approveTime) {
		this.approveTime = approveTime;
	}

	@ManyToOne(fetch=FetchType.EAGER,
			cascade={CascadeType.MERGE}
			)
	public User getApprover() {
		return approver;
	}

	public void setApprover(User approver) {
		this.approver = approver;
	}

	@ManyToOne(fetch=FetchType.EAGER,
			cascade={CascadeType.MERGE}
			)
	public Document getDocument() {
		return document;
	}

	public void setDocument(Document document) {
		this.document = document;
	}
	
}
