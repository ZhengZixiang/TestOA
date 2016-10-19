package me.zzx.oa.util;

import freemarker.cache.ClassTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.TemplateExceptionHandler;

public class FreemarkerUtil {
	private static Configuration cfg = new Configuration(Configuration.VERSION_2_3_22);
	
	static {
		//�������ģ��λ�ã�����·���У�����ڹ���������·������
		cfg.setTemplateLoader(new ClassTemplateLoader(FreemarkerUtil.class, "template"));
		
		//���ö����װ��
		cfg.setObjectWrapper(new DefaultObjectWrapper(Configuration.VERSION_2_3_22));
		
		//�����쳣������
		cfg.setTemplateExceptionHandler(TemplateExceptionHandler.IGNORE_HANDLER);
	}

	public static Configuration getCfg() {
		return cfg;
	}

	public static void setCfg(Configuration cfg) {
		FreemarkerUtil.cfg = cfg;
	}
	
}
