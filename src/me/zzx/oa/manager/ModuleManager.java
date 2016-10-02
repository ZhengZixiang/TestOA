package me.zzx.oa.manager;

import me.zzx.oa.dto.Pager;
import me.zzx.oa.model.Module;

public interface ModuleManager {
	/**
	 * 添加模块信息，如果父模块的ID为0，则添加顶级模块
	 * @param module
	 * @param parentId
	 */
	public void add(Module module, int parentId);
	
	/**
	 * 删除模块
	 * @param id
	 */
	public void delete(int id);
	
	/**
	 * 更新模块
	 * @param module
	 * @param parentId
	 */
	public void update(Module module, int parentId);
	
	/**
	 * 查询特定模块
	 * @param moduleId
	 * @return
	 */
	public Module find(int id);
	
	/**
	 * 分页查询模块列表
	 * @param parentId
	 * @param pager
	 */
	public void searchModules(int parentId, Pager pager);
	
}
