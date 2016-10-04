package me.zzx.oa.manager.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import me.zzx.oa.dao.ModuleDao;
import me.zzx.oa.dto.Pager;
import me.zzx.oa.manager.ModuleManager;
import me.zzx.oa.model.Module;
import me.zzx.oa.util.SystemException;

@Service("moduleManager")
public class ModuleManagerImpl implements ModuleManager {

	private ModuleDao moduleDao;
	
	@Override
	public void add(Module module, int parentId) {
		if(parentId != 0) {
			module.setParent(moduleDao.load(parentId));
		}
		moduleDao.save(module);
	}

	@Override
	public void delete(int id) throws SystemException {
		Module module = moduleDao.load(id);
		if(module.getChildren().size() > 0) {
			throw new SystemException("exception.module.delete");
		}
		moduleDao.delete(module);
	}

	@Override
	public void update(Module module, int parentId) {
		if(parentId != 0) {
			module.setParent(moduleDao.load(parentId));
		}
		moduleDao.update(module);
	}

	@Override
	public Module find(int id) {
		return moduleDao.load(id);
	}

	@Override
	public void searchModules(int parentId, Pager pager) {
		moduleDao.searchModules(parentId, pager);
	}
	
	public ModuleDao getModuleDao() {
		return moduleDao;
	}

	@Resource
	public void setModuleDao(ModuleDao moduleDao) {
		this.moduleDao = moduleDao;
	}

}
