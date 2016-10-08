package me.zzx.oa.action;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import me.zzx.oa.manager.AclManager;
import me.zzx.oa.model.Module;
import me.zzx.oa.model.User;

@Component("indexAction")
public class IndexAction extends ActionSupport {

	private static final long serialVersionUID = 1L;

	private AclManager aclManager;
	
	public String outlook() throws Exception {
		User user = (User) ActionContext.getContext().getSession().get("login");
		List<Module> modules = aclManager.searchModules(user.getId());
		ActionContext.getContext().put("modules", modules);
		return "outlook";
	}
	
	public String main() throws Exception {
		return "main";
	}

	public AclManager getAclManager() {
		return aclManager;
	}

	@Resource
	public void setAclManager(AclManager aclManager) {
		this.aclManager = aclManager;
	}
	
}
