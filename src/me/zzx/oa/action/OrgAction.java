package me.zzx.oa.action;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import me.zzx.oa.dto.Pager;
import me.zzx.oa.manager.OrgManager;
import me.zzx.oa.model.Organization;

@Component("orgAction")
@Scope("prototype")
public class OrgAction extends ActionSupport implements ModelDriven<Pager>{
	
	private static final long serialVersionUID = 820088832067353574L;
	
	private Organization org;
	
	private int parentId;
	
	private OrgManager orgManager;

	private Pager pager = new Pager();
	
	@Override
	public String execute() throws Exception {
		orgManager.listByPager(parentId, pager);
		
		if(parentId != 0) {
			Organization temp = orgManager.findOrg(parentId);
			Organization parent = temp.getParent();
			if(parent != null) 
				//Struts2 request 传参最好的方法
				ActionContext.getContext().put("ppid", parent.getId());
		}
		
		if(pager.isSelect()) {
			return "select";
		}
		return "index";
	}

	public String addInput() {
		return "add_input";
	}
	
	public String updateInput() {
		org = orgManager.findOrg(org.getId());
		return "update_input";
	}
	
	public String save() {
		orgManager.addOrg(org, parentId);
		return "pub_add_success";
	}
	
	public String delete() throws Exception {
		orgManager.deleteOrg(org.getId());
		return "pub_delete_success";
	}

	public String update() {
		orgManager.updateOrg(org, parentId);
		return "pub_update_success";
	}
	
	public OrgManager getOrgManager() {
		return orgManager;
	}

	@Resource(name="orgManager")
	public void setOrgManager(OrgManager orgManager) {
		this.orgManager = orgManager;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public int getParentId() {
		return parentId;
	}

	public void setParentId(int parentId) {
		this.parentId = parentId;
	}

	public Pager getPager() {
		return pager;
	}

	public void setPager(Pager pager) {
		this.pager = pager;
	}

	@Override
	public Pager getModel() {
		return pager;
	}

	public Organization getOrg() {
		return org;
	}

	public void setOrg(Organization org) {
		this.org = org;
	}
	
}
