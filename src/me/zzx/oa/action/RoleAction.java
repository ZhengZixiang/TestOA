package me.zzx.oa.action;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import me.zzx.oa.dto.Pager;
import me.zzx.oa.manager.RoleManager;
import me.zzx.oa.model.Role;

@Component("roleAction")
@Scope("prototype")
public class RoleAction extends ActionSupport implements ModelDriven<Pager> {

	private static final long serialVersionUID = 8904790648500115894L;

	private RoleManager roleManager;
	
	private Pager pager = new Pager();
	
	private Role role;

	@Override
	public String execute() throws Exception {
		roleManager.searchRoles(pager);
		return "index";
	}

	public String addInput() throws Exception {
		return "add_input";
	}
	
	public String save() throws Exception {
		roleManager.add(role);
		return "pub_add_success";
	}
	
	public String delete() throws Exception {
		roleManager.delete(role.getId());
		return "pub_delete_success";
	}
	
	public RoleManager getRoleManager(){
		return roleManager;
	}

	@Resource
	public void setRoleManager(RoleManager roleManager) {
		this.roleManager = roleManager;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	@Override
	public Pager getModel() {
		return pager;
	}

	public Pager getPager() {
		return pager;
	}

	public void setPager(Pager pager) {
		this.pager = pager;
	}
	
}
