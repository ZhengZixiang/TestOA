package me.zzx.oa.tag;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import me.zzx.oa.manager.AclManager;

@Service("permissionFunction")
public class PermissionFunction{

	private static AclManager aclManager;
	
	public static boolean hasPermission(int userId, String resourceSn, int permission) {
		return aclManager.hasPermission(userId, resourceSn, permission);
	}

	public AclManager getAclManager() {
		return aclManager;
	}

	@Resource
	public void setAclManager(AclManager aclManager) {
		PermissionFunction.aclManager = aclManager;
	}
	
}
