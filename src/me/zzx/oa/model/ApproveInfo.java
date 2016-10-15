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
 * 审批历史信息类
 * @author pc
 *
 */
@Entity
@Table(name="t_approveinfo")
public class ApproveInfo {
	
	//审批历史信息ID
	private int id;
	
	//审批意见
	private String comment;
	
	//审批时间
	private Date approveTime;
	
	//审批者
	private User approver;
	
	//所审批的文件
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
