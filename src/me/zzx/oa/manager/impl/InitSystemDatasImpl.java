package me.zzx.oa.manager.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.stereotype.Service;

import me.zzx.oa.dao.AclDao;
import me.zzx.oa.dao.ModuleDao;
import me.zzx.oa.dao.OrgDao;
import me.zzx.oa.dao.PersonDao;
import me.zzx.oa.dao.RoleDao;
import me.zzx.oa.dao.UserDao;
import me.zzx.oa.interceptor.SystemExceptionInterceptor;
import me.zzx.oa.manager.InitSystemDatas;
import me.zzx.oa.model.AccessControlList;
import me.zzx.oa.model.Module;
import me.zzx.oa.model.Organization;
import me.zzx.oa.model.PermissionType;
import me.zzx.oa.model.Person;
import me.zzx.oa.model.Role;
import me.zzx.oa.model.User;
import me.zzx.oa.util.SystemException;

@Service("initSystemDatas")
public class InitSystemDatasImpl implements InitSystemDatas {

	private ModuleDao moduleDao;
	
	private RoleDao roleDao;
	
	private AclDao aclDao;
	
	private OrgDao orgDao;
	
	private PersonDao personDao;
	
	private UserDao userDao;
	
	private Log log = LogFactory.getLog(SystemExceptionInterceptor.class);
	
	private String fileName;
	
	@Override
	public void addOrUpdateInitDatas(String xmlFilePath) {
		String filePath = null;
		if(xmlFilePath == null || xmlFilePath.trim().equals("")) {
			filePath = fileName;
		} else {
			filePath = xmlFilePath;
		}
		
		SAXReader reader = new SAXReader();
		try {
			Document document = reader.read(Thread.currentThread().getContextClassLoader().getResourceAsStream(filePath));
					
			importModules(document.selectNodes("//Modules/Module"), null);
			importRolesAndAcl(document.selectNodes("//Roles/Role"));
			importOrgAndPerson(document.selectNodes("//Organizations/Org"), null);
		} catch (DocumentException e) {
			e.printStackTrace();
			throw new SystemException("error.datas.init");
		}
	}

	private void importOrgAndPerson(List selectNodes, Organization parent) {
		for(Object o : selectNodes) {
			Element e = (Element) o;
			Organization org = new Organization();
			org.setName(e.attributeValue("name"));
			orgDao.save(org, parent == null? 0 : parent.getId());
			
			List list = e.selectNodes("Person");
			for(Object obj : list) {
				Element personEle = (Element) obj;
				Person person = new Person();
				person.setName(personEle.attributeValue("name"));
				person.setOrg(org);
				personDao.save(person);
				
				User user = new User();
				user.setUsername(personEle.attributeValue("username"));
				user.setPassword(personEle.attributeValue("password"));
				user.setPerson(person);
				userDao.save(user);
				
				String roles = personEle.attributeValue("roles");
				String [] rolesArray = roles.split(",");
				for(int i = 0; i < rolesArray.length; i++) {
					Role role = roleDao.loadByName(rolesArray[i]);
					userDao.addOrUpdateUserRoleMapping(user.getId(), role.getId(), i + 1);
				}
			}
			
			importOrgAndPerson(e.selectNodes("Org"), org);
		}
	}

	private void importRolesAndAcl(List selectNodes) {
		for(Object o : selectNodes) {
			Element e = (Element) o;
			Role role = new Role();
			role.setName(e.attributeValue("name"));
			roleDao.save(role);
			
			List list = e.selectNodes("Acl");
			for(Object obj : list) {
				Element aclEle = (Element) obj;
				int moduleId = moduleDao.loadByName(aclEle.attributeValue("module")).getId();
				AccessControlList acl = new AccessControlList();
				acl.setPrincipalType(AccessControlList.TYPE_ROLE);
				acl.setPrincipalId(role.getId());
				acl.setResourceId(moduleId);
				
				if("true".equals(aclEle.attributeValue("C"))) acl.setPermission(PermissionType.CREATE, true);
				if("true".equals(aclEle.attributeValue("R"))) acl.setPermission(PermissionType.READ, true);
				if("true".equals(aclEle.attributeValue("U"))) acl.setPermission(PermissionType.UPDATE, true);
				if("true".equals(aclEle.attributeValue("D"))) acl.setPermission(PermissionType.DELETE, true);
				
				aclDao.save(acl);
			}
		}
	}

	private void importModules(List selectNodes, Module parent) {
		for(Object o : selectNodes) {
			Element e = (Element) o;
			Module module = new Module();
			module.setName(e.attributeValue("name"));
			module.setSn(e.attributeValue("sn"));
			module.setUrl(e.attributeValue("url"));
			module.setOrderNo(Integer.parseInt(e.attributeValue("orderNo")));
			module.setParent(parent);
			moduleDao.save(module);
			log.info("µ¼ÈëÄ£¿é¡¾" + module.getName() + "¡¿");
			importModules(e.selectNodes("Module"), module);
		}
	}

	public ModuleDao getModuleDao() {
		return moduleDao;
	}

	@Resource
	public void setModuleDao(ModuleDao moduleDao) {
		this.moduleDao = moduleDao;
	}

	public RoleDao getRoleDao() {
		return roleDao;
	}

	@Resource
	public void setRoleDao(RoleDao roleDao) {
		this.roleDao = roleDao;
	}

	public AclDao getAclDao() {
		return aclDao;
	}

	@Resource
	public void setAclDao(AclDao aclDao) {
		this.aclDao = aclDao;
	}

	public OrgDao getOrgDao() {
		return orgDao;
	}
	
	@Resource
	public void setOrgDao(OrgDao orgDao) {
		this.orgDao = orgDao;
	}

	public PersonDao getPersonDao() {
		return personDao;
	}

	@Resource
	public void setPersonDao(PersonDao personDao) {
		this.personDao = personDao;
	}

	public UserDao getUserDao() {
		return userDao;
	}

	@Resource
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

}
