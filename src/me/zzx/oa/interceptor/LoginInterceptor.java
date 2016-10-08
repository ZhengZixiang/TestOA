package me.zzx.oa.interceptor;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;

import me.zzx.oa.model.User;

public class LoginInterceptor extends MethodFilterInterceptor {

	private static final long serialVersionUID = -7119226609919960418L;

	@Override
	protected String doIntercept(ActionInvocation invoker ) throws Exception {
		User user = (User) ActionContext.getContext().getSession().get("login");
		if(null == user) {
			return "login";
		}
		return invoker.invoke();
	}

}
