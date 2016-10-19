package me.zzx.oa.util;

import freemarker.cache.ClassTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.TemplateExceptionHandler;

public class FreemarkerUtil {
	private static Configuration cfg = new Configuration(Configuration.VERSION_2_3_22);
	
	static {
		//定义加载模板位置，从类路径中，相对于工具类所在路径加载
		cfg.setTemplateLoader(new ClassTemplateLoader(FreemarkerUtil.class, "template"));
		
		//设置对象包装器
		cfg.setObjectWrapper(new DefaultObjectWrapper(Configuration.VERSION_2_3_22));
		
		//设置异常处理器
		cfg.setTemplateExceptionHandler(TemplateExceptionHandler.IGNORE_HANDLER);
	}

	public static Configuration getCfg() {
		return cfg;
	}

	public static void setCfg(Configuration cfg) {
		FreemarkerUtil.cfg = cfg;
	}
	
}
