package me.zzx.oa.action;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import me.zzx.oa.dto.Pager;
import me.zzx.oa.manager.ModuleManager;
import me.zzx.oa.manager.RoleManager;
import me.zzx.oa.manager.UserManager;
import me.zzx.oa.model.AccessControlList;
import me.zzx.oa.util.SystemException;

@Component("aclAction")
@Scope("prototype")
public class AclAction extends ActionSupport implements ModelDriven<Pager> {
	
	private static final long serialVersionUID = -4978827185616250799L;

	private ModuleManager moduleManager;
	
	private UserManager userManager;
	
	private RoleManager roleManager;
	
	private Pager pager = new Pager();
	
	private AccessControlList acl;
	
	//打开ACL授权界面 
	@Override
	public String execute() throws Exception {
		if(AccessControlList.TYPE_ROLE.equals(acl.getPrincipalType())) {
			ActionContext.getContext().put("role", roleManager.find(acl.getPrincipalId()));
		} else if (AccessControlList.TYPE_USER.equals(acl.getPrincipalType())) {
			ActionContext.getContext().put("user", userManager.find(acl.getPrincipalId()));
		} else {
			throw new SystemException("error.principalType.invalid");
		}
		
		pager.setOffset(0);
		pager.setPagesize(Integer.MAX_VALUE);
		moduleManager.searchModules(0, pager);
		
		return "index";
	}

	public AccessControlList getAcl() {
		return acl;
	}

	public void setAcl(AccessControlList acl) {
		this.acl = acl;
	}

	@Override
	public Pager getModel() {
		return pager;
	}

	public ModuleManager getModuleManager() {
		return moduleManager;
	}

	@Resource
	public void setModuleManager(ModuleManager moduleManager) {
		this.moduleManager = moduleManager;
	}

	public UserManager getUserManager() {
		return userManager;
	}

	@Resource
	public void setUserManager(UserManager userManager) {
		this.userManager = userManager;
	}

	public RoleManager getRoleManager() {
		return roleManager;
	}

	@Resource
	public void setRoleManager(RoleManager roleManager) {
		this.roleManager = roleManager;
	}

	public Pager getPager() {
		return pager;
	}

	public void setPager(Pager pager) {
		this.pager = pager;
	}
	
}
