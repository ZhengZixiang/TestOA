package me.zzx.oa.manager;

import java.util.List;

import me.zzx.oa.dto.Pager;
import me.zzx.oa.model.ApproveInfo;
import me.zzx.oa.model.Document;

public interface DocumentManager {
	/**
	 * 添加公文
	 * @param document
	 * @param workflowId
	 * @param createrId
	 */
	public void add(Document document, int workflowId, int createrId);
	
	/**
	 * 添加审批信息
	 * @param info
	 */
	public void addApproveInfo(ApproveInfo info, int documentId, int approverId);
	
	/**
	 * 查找公文
	 * @param id
	 * @return
	 */
	public Document find(int id);
	
	/**
	 * 更新公文
	 * @param document 公文
	 */
	public void update(Document document);
	
	/**
	 * 删除公文
	 * @param id 公文ID
	 */
	public void delete(int id);
	
	/**
	 * 查询用户创建的公文列表
	 * @param userId 用户ID
	 * @param pager 
	 * @return
	 */
	public void searchCreatedDocuments(int userId, Pager pager);
	
	/**
	 * 查询公文的审批信息
	 * @param documentId
	 * @return
	 */
	public void searchApproveInfo(int documentId, Pager pager);
	
	/**
	 * 查询待用户审批的公文列表
	 * @param userId
	 * @return
	 */
	public List<Object> searchApprovingDocuments(String username);
	
	/**
	 * 查询用户已审批的公文列表
	 * @param userId
	 * @return
	 */
	public void searchApprovedDocuments(int userId, Pager pager);

}
