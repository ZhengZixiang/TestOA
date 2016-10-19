package me.zzx.oa.tag;

import java.io.StringWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import freemarker.template.Configuration;
import freemarker.template.Template;
import me.zzx.oa.manager.FormManager;
import me.zzx.oa.model.Form;
import me.zzx.oa.util.FreemarkerUtil;

@Service("dynamicFormFunction")
public class DynamicFormFunction {

	private static FormManager formManager;
	
	private static String defaultTemplate = "document_form.ftl";
	
	public static String form(int workflowId) {
		try {
			Form form = formManager.findFormByWorkflow(workflowId);
			if(form == null) {
				return null;
			}
			Configuration cfg = FreemarkerUtil.getCfg();
			
			Template template = null;
			if(form.getTemplate() == null || form.getTemplate().trim().equals("")) {
				template = cfg.getTemplate(defaultTemplate);
			} else {
				template = cfg.getTemplate(form.getTemplate());
			}
			
			Writer out = new StringWriter();
			
			//数据模型
			Map<String, Form> map = new HashMap<> ();
			map.put("form", form);
			
			//模板引擎解释模板
			template.process(map, out);
			
			return out.toString();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public FormManager getFormManager() {
		return formManager;
	}

	@Resource
	public void setFormManager(FormManager formManager) {
		DynamicFormFunction.formManager = formManager;
	}
	
}
