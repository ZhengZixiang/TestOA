package me.zzx.oa.action;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import java.util.Date;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import me.zzx.oa.dto.Pager;
import me.zzx.oa.manager.DocumentManager;
import me.zzx.oa.manager.WorkflowManager;
import me.zzx.oa.model.ApproveInfo;
import me.zzx.oa.model.Document;
import me.zzx.oa.model.User;
import me.zzx.oa.util.FileUtil;

@Component("documentAction")
@Scope("prototype")
public class DocumentAction extends ActionSupport implements ModelDriven<Pager>{
	
	private static final long serialVersionUID = 358352324966077082L;

	private Document document;
	
	private ApproveInfo info;
	
	private Pager pager = new Pager();
	
	private DocumentManager documentManager;
	
	private WorkflowManager workflowManager;

	private String transitionName;
	
	private int workflowId;
	
	private File attachment;
	
	private String attachmentFileName;
	
	private InputStream is;
	
	private String stepId;
	
	@Override
	public String execute() throws Exception {
		User user = (User) ActionContext.getContext().getSession().get("login");
		documentManager.searchCreatedDocuments(user.getId(), pager);
		return "index";
	}
	
	/**
	 * �������б�
	 * @return
	 * @throws Exception
	 */
	public String approvedList() throws Exception {
		User user = (User) ActionContext.getContext().getSession().get("login");
		documentManager.searchApprovedDocuments(user.getId(), pager);
		return "approved_list";
	}
	
	/**
	 * �������б�
	 * @return
	 * @throws Exception
	 */
	public String approvingList() throws Exception {
		User user = (User) ActionContext.getContext().getSession().get("login");
		pager.setList(documentManager.searchApprovingDocuments(user.getUsername()));
		return "approving_list";
	}
	
	/**
	 * �ĵ���������
	 * @return
	 * @throws Exception
	 */
	public String approveInput() throws Exception {
		return "approve_input";
	}
	
	/**
	 * ����
	 * @return
	 * @throws Exception
	 */
	public String approve() throws Exception {
		User user = (User) ActionContext.getContext().getSession().get("login");
		info.setApproveTime(new Date());
		documentManager.addApproveInfo(info, document.getId(), user.getId());
		return "pub_add_success";
	}
	
	/**
	 * ���ҵĹ��Ļ��ߴ����ĵ���ύ���ɴ��ύ����
	 * ����������У��г���һ�����п�ѡ�Ĳ����û�ѡ��
	 * ����һ�������ύ��ϵͳ�����û�ѡ��������һ����
	 * ��
	 * @return
	 * @throws Exception
	 */
	public String submitInput() throws Exception {
		Document doc = documentManager.find(document.getId());
		Map<String, String>  steps = workflowManager.searchNextSteps(doc.getProcessInstanceId());
		ActionContext.getContext().put("steps", steps);
		return "submit_input";
	}
	
	/**
	 * �û�ѡ��һ�������ύ
	 * @return
	 * @throws Exception
	 */
	public String submit() throws Exception {
		User user = (User) ActionContext.getContext().getSession().get("login");
		Document doc = documentManager.find(document.getId());
		String status = workflowManager.flowToNextStep(user.getUsername(), stepId, doc.getProcessInstanceId());
		doc.setStatus(status);
		documentManager.update(doc);
		return "pub_add_success";
	}
	
	/**
	 * �鿴����������ʷ
	 * @return
	 * @throws Exception
	 */
	public String approveHistory() throws Exception {
		documentManager.searchApproveInfo(document.getId(), pager);
		return "approve_history";
	}
	
	/**
	 * ɾ��
	 */
	public String delete() throws Exception {
		documentManager.delete(document.getId());
		return "pub_delete_success";
	}
	
	/**
	 * ��ӹ���ʱ��ѡ������
	 * @return
	 * @throws Exception
	 */
	public String selectWorkflow() throws Exception {
		ActionContext.getContext().put("workflows", workflowManager.searchWorkflows());
		return "select_list";
	}
	
	/**
	 * �򿪹�����ӽ���
	 * @return
	 * @throws Exception
	 */
	public String addInput() throws Exception {
		return "add_input";
	}
	
	/**
	 * ִ����Ӳ���
	 * @return
	 * @throws Exception
	 */
	public String add() throws Exception {
		User user = (User) ActionContext.getContext().getSession().get("login");
		
		//ʹ��multipart/form-data��������ʱ����file�������������������http�����ļ�ת��Ϊ����ʽ���޷�������ȡ
		//������Spring�ṩ�Ĺ�������ж�ȡ�����ԭʼ��request
		HttpServletRequest request = ServletActionContext.getRequest();
		MultipartResolver resolver = new CommonsMultipartResolver(request.getSession().getServletContext());
		MultipartHttpServletRequest	mRequest = resolver.resolveMultipart(request);
		
		String title = mRequest.getParameter("document.title");
		String description = mRequest.getParameter("document.description");
		document.setTitle(title);
		document.setDescription(description);
		
		//��ͨ���Լ�д���ļ��������ȡ�ļ��Ķ���������
		byte[] b = FileUtil.getByteArray(attachment);
		document.setContent(b);
		
		documentManager.add(document, workflowId, user.getId());
		return "pub_add_success";
	}

	/**
	 * ���ع���
	 * @return
	 * @throws Exception
	 */
	public String download() throws Exception {
		this.document = documentManager.find(document.getId());
		is = new ByteArrayInputStream(document.getContent());
		attachmentFileName = new String(document.getTitle().getBytes(), "ISO8859-1");
		return "download";
	}
	
	public Document getDocument() {
		return document;
	}

	public void setDocument(Document document) {
		this.document = document;
	}

	public DocumentManager getDocumentManager() {
		return documentManager;
	}

	@Resource
	public void setDocumentManager(DocumentManager documentManager) {
		this.documentManager = documentManager;
	}

	public WorkflowManager getWorkflowManager() {
		return workflowManager;
	}

	@Resource
	public void setWorkflowManager(WorkflowManager workflowManager) {
		this.workflowManager = workflowManager;
	}

	@Override
	public Pager getModel() {
		return this.pager;
	}

	public ApproveInfo getInfo() {
		return info;
	}

	public void setInfo(ApproveInfo info) {
		this.info = info;
	}

	public Pager getPager() {
		return pager;
	}

	public void setPager(Pager pager) {
		this.pager = pager;
	}

	public String getTransitionName() {
		return transitionName;
	}

	public void setTransitionName(String transitionName) {
		this.transitionName = transitionName;
	}

	public int getWorkflowId() {
		return workflowId;
	}

	public void setWorkflowId(int workflowId) {
		this.workflowId = workflowId;
	}

	public File getAttachment() {
		return attachment;
	}

	public void setAttachment(File attachment) {
		this.attachment = attachment;
	}

	public String getAttachmentFileName() {
		return attachmentFileName;
	}

	public void setAttachmentFileName(String attachmentFileName) {
		this.attachmentFileName = attachmentFileName;
	}

	public InputStream getIs() {
		return is;
	}

	public void setIs(InputStream is) {
		this.is = is;
	}

	public String getStepId() {
		return stepId;
	}

	public void setStepId(String stepId) {
		this.stepId = stepId;
	}

}
