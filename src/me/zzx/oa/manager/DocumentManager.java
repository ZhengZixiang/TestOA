package me.zzx.oa.manager;

import java.util.List;

import me.zzx.oa.dto.Pager;
import me.zzx.oa.model.ApproveInfo;
import me.zzx.oa.model.Document;

public interface DocumentManager {
	/**
	 * ��ӹ���
	 * @param document
	 * @param workflowId
	 * @param createrId
	 */
	public void add(Document document, int workflowId, int createrId);
	
	/**
	 * ���������Ϣ
	 * @param info
	 */
	public void addApproveInfo(ApproveInfo info, int documentId, int approverId);
	
	/**
	 * ���ҹ���
	 * @param id
	 * @return
	 */
	public Document find(int id);
	
	/**
	 * ���¹���
	 * @param document ����
	 */
	public void update(Document document);
	
	/**
	 * ɾ������
	 * @param id ����ID
	 */
	public void delete(int id);
	
	/**
	 * ��ѯ�û������Ĺ����б�
	 * @param userId �û�ID
	 * @param pager 
	 * @return
	 */
	public void searchCreatedDocuments(int userId, Pager pager);
	
	/**
	 * ��ѯ���ĵ�������Ϣ
	 * @param documentId
	 * @return
	 */
	public void searchApproveInfo(int documentId, Pager pager);
	
	/**
	 * ��ѯ���û������Ĺ����б�
	 * @param userId
	 * @return
	 */
	public List<Object> searchApprovingDocuments(String username);
	
	/**
	 * ��ѯ�û��������Ĺ����б�
	 * @param userId
	 * @return
	 */
	public void searchApprovedDocuments(int userId, Pager pager);

}
