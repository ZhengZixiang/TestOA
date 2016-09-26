package me.zzx.oa.action;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import me.zzx.oa.dto.Pager;
import me.zzx.oa.manager.OrgManager;
import me.zzx.oa.model.Orgnization;

@Component("orgAction")
@Scope("prototype")
public class OrgAction extends ActionSupport implements ModelDriven<Pager>{
	
	private static final long serialVersionUID = 820088832067353574L;
	private OrgManager orgManager;
	private int id;
	private int parentId;
	private String name;
	private String description;
	private Pager pager = new Pager();
	
	@Override
	public String execute() throws Exception {
		orgManager.findOrgs(parentId, pager);
		
		if(parentId != 0) {
			Orgnization org = orgManager.findOrg(parentId);
			Orgnization parent = org.getParent();
			if(parent != null) 
				//Struts2 request 传参最好的方法
				ActionContext.getContext().put("ppid", parent.getId());
		}
		return "index";
	}

	public String addInput() {
		return "add_input";
	}
	
	public String updateInput() {
		Orgnization org = orgManager.findOrg(id);
		name = org.getName();
		description = org.getDescription();
		return "update_input";
	}
	
	public String save() {
		Orgnization org = new Orgnization();
		org.setName(name);
		org.setDescription(description);
		orgManager.addOrg(org, parentId);
		return "pub_add_success";
	}
	
	public String delete() throws Exception {
		orgManager.deleteOrg(id);
		return "pub_delete_success";
	}

	public String update() {
		Orgnization org = orgManager.findOrg(id);
		org.setName(name);
		org.setDescription(description);
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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
	
}
