package me.zzx.oa.action;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import me.zzx.oa.manager.UserManager;
import me.zzx.oa.model.User;

@Component("loginAction")
public class LoginAction extends ActionSupport {

	private static final long serialVersionUID = 8636575981165510763L;

	private UserManager userManager;
	
	private String password;
	
	private String username;

	public String execute() throws Exception {
		User user = userManager.login(username, password);
		ActionContext.getContext().getSession().put("login", user);
		return "back_index";
	}

	public UserManager getUserManager() {
		return userManager;
	}

	@Resource
	public void setUserManager(UserManager userManager) {
		this.userManager = userManager;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
}
