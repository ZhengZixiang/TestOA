package me.zzx.oa.manager;

import java.util.List;

import me.zzx.oa.dto.Record;
import me.zzx.oa.model.Module;

public interface AclManager {
	/**
	 * 授权
	 * @param principalType
	 * @param principalId
	 * @param ResourceId
	 * @param permission
	 * @param accpeted
	 */
	public void addOrUpdatePermission(String principalType, int principalId, int resourceId, int permission, boolean accepted);
	
	/**
	 * 取消授权
	 * @param principalType
	 * @param principalId
	 * @param resourceId
	 */
	public void deletePermission(String principalType, int principalId, int resourceId);
	
	/**
	 * 添加或更新用户的继承特性
	 * @param userId
	 * @param resourceId
	 * @param yes
	 */
	public void addOrUpdateExtends(int userId, int resourceId, boolean yes);
	
	/**
	 * 认证，判断用户是否拥有某模块的某操作的权限
	 * @param userId 用户ID
	 * @param resourceId 资源序列号
	 * @param permission CRUD权限
	 * @return 允许true/不允许false
	 */
	public boolean hasPermission(int userId, int resourceId, int permission);
	
	/**
	 * 认证，判断用户是否拥有某模块的某操作的权限
	 * @param userId 用户ID
	 * @param resourceId 资源唯一标识
	 * @param permission CRUD权限
	 * @return 允许true/不允许false
	 */
	public boolean hasPermission(int userId, String resourceSn, int permission);
	
	/**
	 * 搜索用户拥有读取权限的模块列表，用于形成导航菜单
	 * @param userId 用户ID
	 * @return 模块列表
	 */
	public List<Module> searchModules(int userId);
	
	/**
	 * 根据主体类型和主体标识查找主体的授权记录
	 * @param principalType
	 * @param principalId
	 * @return
	 */
	public List<Record> searchAclRecord(String principalType, int principalId);
}
