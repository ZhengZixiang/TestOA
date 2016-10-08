package me.zzx.oa.action;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import me.zzx.oa.dto.Pager;
import me.zzx.oa.manager.ModuleManager;
import me.zzx.oa.model.Module;

@Component("moduleAction")
@Scope("prototype")
public class ModuleAction extends ActionSupport implements ModelDriven<Pager>{

	private static final long serialVersionUID = -4681397224554936995L;

	private Module module;
	
	private int parentId;
	
	private ModuleManager moduleManager;

	private Pager pager = new Pager();
	
	@Override
	public String execute() throws Exception {
		
		moduleManager.searchModules(parentId, pager);
		
		if(parentId != 0) {
			Module temp = moduleManager.find(parentId);
			Module parent = temp.getParent();
			if(parent != null) 
				ActionContext.getContext().put("ppid", parent.getId());
		}
		
		if(pager.isSelect()) {
			return "select";
		}
		return "index";
	}

	public String addInput() {
		return "add_input";
	}
	
	public String updateInput() {
		module = moduleManager.find(module.getId());
		return "update_input";
	}
	
	public String save() {
		moduleManager.add(module, parentId);
		return "pub_add_success";
	}
	
	public String delete() throws Exception {
		moduleManager.delete(module.getId());
		return "pub_delete_success";
	}

	public String update() {
		moduleManager.update(module, parentId);
		return "pub_update_success";
	}
	
	public ModuleManager getmoduleManager() {
		return moduleManager;
	}

	@Resource(name="moduleManager")
	public void setModuleManager(ModuleManager moduleManager) {
		this.moduleManager = moduleManager;
	}

	public int getParentId() {
		return parentId;
	}

	public void setParentId(int parentId) {
		this.parentId = parentId;
	}

	public Pager getPager() {
		return pager;
	}

	public void setPager(Pager pager) {
		this.pager = pager;
	}

	@Override
	public Pager getModel() {
		return pager;
	}

	public Module getModule() {
		return module;
	}

	public void setModule(Module module) {
		this.module = module;
	}
	
}
