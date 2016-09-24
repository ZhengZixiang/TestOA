package me.zzx.oa.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class SystemExceptionInterceptor extends AbstractInterceptor {

	private static final long serialVersionUID = -4116605184493836044L;

	private static Log log = LogFactory.getLog(SystemExceptionInterceptor.class);
	
	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		String result = invocation.invoke();
		if(result.equals("error")) {
			log.warn(ActionContext.getContext().getValueStack().findValue("exception.message"));
		}
		return null;
	}

}
