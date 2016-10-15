package me.zzx.oa.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import me.zzx.oa.manager.WorkflowManager;
import me.zzx.oa.model.Workflow;
import me.zzx.oa.util.FileUtil;
import me.zzx.oa.util.SystemException;

@Component("workflowAction")
@Scope("prototype")
public class WorkflowAction extends ActionSupport {

	private static final long serialVersionUID = -484509163005437415L;
	
	private WorkflowManager workflowManager;
	
	private File processFile;
	
	private File processImage;
	
	private String processFileFileName;
	
	private String processFileContentType;
	
	private String processImageFileName;
	
	private String processImageContentType;
	
	private Workflow workflow;

	@Override
	public String execute() throws Exception {
		ActionContext.getContext().put("workflows", workflowManager.searchWorkflows());
		return "index";
	}
	
	public String add() throws Exception {
		//判断文件是否为空
		if(processFile == null || processImage == null) {
			throw new SystemException("error.file.null");
		}
		String uploadPath = ServletActionContext.getServletContext().getRealPath("/upload/");
		int index = processFileFileName.indexOf(".bpmn");
		String processName = processFileFileName.substring(0, index);
		//部署流程
		workflowManager.deployProcessDefinition(processName, processFile, uploadPath + processImageFileName);
		//存储图片
		saveImage(uploadPath);
		
		return "add_success";
	}
	
	@SuppressWarnings("resource")
	private void saveImage(String uploadPath) throws IOException {
		File uploadPathFile = new File(uploadPath);
		if(!uploadPathFile.exists()) uploadPathFile.mkdirs();
		long length = processFile.length(); 
		FileInputStream fis = new FileInputStream(processImage);
		File file = new File(uploadPath, processImageFileName);
		FileOutputStream fos = new FileOutputStream(file);
		if(length > Integer.MAX_VALUE) {
			throw new SystemException("error.file.toobig");
		}
		int bufferSize = 1024;
		byte [] b = new byte[bufferSize];
		int i = 0;
		while((i = fis.read(b)) != -1) {
			fos.write(b, 0, i);
		}
		fis.close();
		fos.close();
	}
	
	public String delete() throws Exception {
		return "pub_delete_success";
	}

	//打开查看流程定义代码窗口
	public String viewCode() throws Exception {
		workflow = workflowManager.find(workflow.getId());
		byte[] b = workflow.getProcessFile();
		String code = new String(new String(b).getBytes(), "utf-8");
		ActionContext.getContext().put("code", code);
		return "view_code";
	}
	
	//打开查看流程定义图片窗口
	public String viewImage() throws Exception {
		workflow = workflowManager.find(workflow.getId());
		return "view_image";
	}
	
	//查看图片
	public String showImage() throws Exception {
		workflow = workflowManager.find(workflow.getId());
		String imagePath = workflow.getProcessImagePath();
		File image = new File(imagePath);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("image/jpg");
		response.getOutputStream().write(FileUtil.getByteArray(image));
		return NONE;
	}
	
	public WorkflowManager getWorkflowManager() {
		return workflowManager;
	}

	@Resource
	public void setWorkflowManager(WorkflowManager workflowManager) {
		this.workflowManager = workflowManager;
	}

	public File getProcessFile() {
		return processFile;
	}

	public void setProcessFile(File processFile) {
		this.processFile = processFile;
	}

	public File getProcessImage() {
		return processImage;
	}

	public void setProcessImage(File processImage) {
		this.processImage = processImage;
	}

	public String getProcessFileFileName() {
		return processFileFileName;
	}

	public void setProcessFileFileName(String processFileFileName) {
		this.processFileFileName = processFileFileName;
	}

	public String getProcessFileContentType() {
		return processFileContentType;
	}

	public void setProcessFileContentType(String processFileContentType) {
		this.processFileContentType = processFileContentType;
	}

	public String getProcessImageFileName() {
		return processImageFileName;
	}

	public void setProcessImageFileName(String processImageFileName) {
		this.processImageFileName = processImageFileName;
	}

	public String getProcessImageContentType() {
		return processImageContentType;
	}

	public void setProcessImageContentType(String processImageContentType) {
		this.processImageContentType = processImageContentType;
	}

	public Workflow getWorkflow() {
		return workflow;
	}

	public void setWorkflow(Workflow workflow) {
		this.workflow = workflow;
	}

}
