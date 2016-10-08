package me.zzx.oa.manager.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import me.zzx.oa.dao.AclDao;
import me.zzx.oa.dao.ModuleDao;
import me.zzx.oa.dao.UserDao;
import me.zzx.oa.dto.Record;
import me.zzx.oa.manager.AclManager;
import me.zzx.oa.model.AccessControlList;
import me.zzx.oa.model.Module;
import me.zzx.oa.model.PermissionType;
import me.zzx.oa.model.Role;

@Service("aclManager")
public class AclManagerImpl implements AclManager {

	private AclDao aclDao;
	
	private UserDao userDao;
	
	private ModuleDao moduleDao;

	@Override
	public void addOrUpdatePermission(String principalType, int principalId, int resourceId, int permission, boolean accepted) {
		AccessControlList acl = this.find(principalType, principalId, resourceId);
		
		if(acl != null) {
			acl.setPermission(permission, accepted);
			aclDao.update(acl);
			return;
		}
		
		acl = new AccessControlList();
		acl.setPrincipalType(principalType);
		acl.setPrincipalId(principalId);
		acl.setResourceId(resourceId);
		acl.setPermission(permission, accepted);
		aclDao.save(acl);
	}
	
	@Override
	public void addOrUpdateExtends(int userId, int resourceId, boolean yes) {
		AccessControlList acl = this.find(AccessControlList.TYPE_USER, userId, resourceId);
		
		if(acl != null) {
			acl.setExtends(yes);
			aclDao.update(acl);
			return;
		}
		
		acl = new AccessControlList();
		acl.setPrincipalType(AccessControlList.TYPE_USER);
		acl.setPrincipalId(userId);
		acl.setResourceId(resourceId);
		acl.setExtends(yes);
		aclDao.save(acl);
	}
	
	private AccessControlList find(String principalType, int principalId, int resourceId) {
		return aclDao.find(principalType, principalId, resourceId);
	}
	
	@Override
	public void deletePermission(String principalType, int principalId, int resourceId) {
		aclDao.delete(this.find(principalType, principalId, resourceId));
	}

	@Override
	public boolean hasPermission(int userId, int resourceId, int permission) {
		AccessControlList acl = this.find(AccessControlList.TYPE_USER, userId, resourceId);
		
		if(acl != null) {
			int temp = acl.getPermission(permission);
			
			//�����ȷ����Ȩ
			if(temp != AccessControlList.ACL_NEUTRAL) {
				return temp == AccessControlList.ACL_YES? true : false;
			}
		}
		
		//���ݽ�ɫ���ȼ����������û��Ľ�ɫ��Ȩ
		List<Role> roles = userDao.searchSameUser(userId);
		for(Role role : roles) {
			acl = this.find(AccessControlList.TYPE_ROLE, role.getId(), resourceId);
			if(acl != null)
				return acl.getPermission(permission) == AccessControlList.ACL_YES? true : false;
		}
		
		return false;
	}

	
	@Override
	public boolean hasPermission(int userId, String resourceSn, int permission) {
		Module module = this.moduleDao.loadBySn(resourceSn);
		return this.hasPermission(userId, module.getId(), permission);
	}

	@Override
	public List<Module> searchModules(int userId) {
		Map<Integer, AccessControlList> map = new HashMap<Integer, AccessControlList>();
		
		//���ݽ�ɫ���ȼ������û�ӵ�еĽ�ɫ
		List<Role> roles = userDao.searchSameUser(userId);
		
		//���λ�ý�ɫ����Ȩ�б�
		for(int i = roles.size() - 1; i > -1; i--) {
			List<AccessControlList> acls = this.findByPrincipal(AccessControlList.TYPE_ROLE, roles.get(i).getId());
			for(AccessControlList acl : acls) {
				map.put(acl.getResourceId(), acl);
			}
		}
		
		//����ֱ�������û�����Ȩ�б�(���Ǽ̳еĻ�����Ӧ�ð����ڸ��б��У�
		List<AccessControlList> acls = this.findByPrincipal(AccessControlList.TYPE_USER, userId);
		for(AccessControlList acl : acls) {
			map.put(acl.getResourceId(), acl);
		}
		
		//�ѻ���û���������Ȩ���û�ֱ����Ȩ�ͽ�ɫ��Ȩ
		List<Integer> delete = new ArrayList<Integer>();
		for(Entry<Integer, AccessControlList> entry : map.entrySet()) {
			AccessControlList acl = entry.getValue();
			if(acl.getPermission(PermissionType.READ) == AccessControlList.ACL_NO) {
				delete.add(entry.getKey());
			}
		}
		
		for(int i : delete) map.remove(i);
		
		List<Module> modules = new ArrayList<Module>();
		//�����Ȩ�б�Ϊ�գ��򷵻�0���ȵ�ģ���б�
		if(map.isEmpty()) return modules;
		
		//�ѻ���û�������ӵ�ж�ȡȨ�޵���Ȩ����һ�����ģ��
		for(int i : map.keySet()) {
			modules.add(moduleDao.load(i));
		}
		
		return modules;
	}
	
	private List<AccessControlList> findByPrincipal(String principalType, int principalId) {
		return aclDao.findByPrincipal(principalType, principalId);
	}

	@Override
	public List<Record> searchAclRecord(String principalType, int principalId) {
		return aclDao.searchAclRecord(principalType, principalId);
	}
	
	public AclDao getAclDao() {
		return aclDao;
	}
	
	@Resource
	public void setAclDao(AclDao aclDao) {
		this.aclDao = aclDao;
	}

	public UserDao getUserDao() {
		return userDao;
	}

	@Resource
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	public ModuleDao getModuleDao() {
		return moduleDao;
	}

	@Resource
	public void setModuleDao(ModuleDao moduleDao) {
		this.moduleDao = moduleDao;
	}

}
