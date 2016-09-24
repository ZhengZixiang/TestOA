package me.zzx.oa.action;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import me.zzx.oa.manager.OrgManager;
import me.zzx.oa.model.Orgnization;

@Component("orgAction")
@Scope("prototype")
public class OrgAction extends ActionSupport {
	
	private static final long serialVersionUID = 820088832067353574L;
	private OrgManager orgManager;
	private List<Orgnization> orgs;
	private int id;
	private int parentId;
	private String name;
	private String description;
	
	@Override
	public String execute() throws Exception {
		orgs = orgManager.findOrgs(parentId);
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

	public List<Orgnization> getOrgs() {
		return orgs;
	}

	public void setOrgs(List<Orgnization> orgs) {
		this.orgs = orgs;
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
	
}
