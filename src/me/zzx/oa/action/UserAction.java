package me.zzx.oa.action;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import me.zzx.oa.dto.Pager;
import me.zzx.oa.manager.PersonManager;
import me.zzx.oa.manager.RoleManager;
import me.zzx.oa.manager.UserManager;
import me.zzx.oa.model.Person;
import me.zzx.oa.model.Role;
import me.zzx.oa.model.User;

@Component("userAction")
@Scope("prototype")
public class UserAction extends ActionSupport implements ModelDriven<Pager> {
	
	private static final long serialVersionUID = 3640876663966351326L;
	
	private UserManager userManager;
	
	private PersonManager personManager;
	
	private RoleManager roleManager;
	
	private Pager pager = new Pager();
	
	private User user;
	
	private Role role;
	
	private Person person;
	
	private int orderNo;
	
	@Override
	public String execute() throws Exception {
		personManager.searchPersons(pager);
		return "index";
	}

	public String addInput() throws Exception {
		return "add_input";
	}
	
	public String updateInput() throws Exception {
		user = userManager.find(user.getId());
		return "update_input";
	}
	
	public String update() throws Exception {
		userManager.update(user, person.getId());
		return "pub_update_success";
	}
	
	public String save() throws Exception {
		userManager.add(user, person.getId());
		return "pub_add_success";
	}
	
	public String delete() throws Exception {
		userManager.delete(user.getId());
		return "pub_delete_success";
	}
	
	public String listURMapping() throws Exception {
		int userId = user.getId();
		userManager.searchUserRoleMappings(userId, pager);
		user = userManager.find(userId);
		return "list_ur_mapping";
	}
	
	public String addURMappingInput() throws Exception {
		roleManager.searchRoles(pager);
		return "add_ur_mapping_input";
	}
	
	public String saveURMapping() throws Exception {
		userManager.addOrUpdateUserRoleMapping(user.getId(), role.getId(), orderNo);
		return "pub_add_success";
	}
	
	public String deleteURMapping() throws Exception {
		userManager.deleteUserRoleMapping(user.getId(), role.getId());
		return "pub_delete_success";
	}
	
	@Override
	public Pager getModel() {
		return pager;
	}

	public Pager getPager() {
		return pager;
	}

	public void setPager(Pager pager) {
		this.pager = pager;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public UserManager getUserManager() {
		return userManager;
	}

	@Resource
	public void setUserManager(UserManager userManager) {
		this.userManager = userManager;
	}

	public PersonManager getPersonManager() {
		return personManager;
	}

	@Resource
	public void setPersonManager(PersonManager personManager) {
		this.personManager = personManager;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public RoleManager getRoleManager() {
		return roleManager;
	}

	@Resource
	public void setRoleManager(RoleManager roleManager) {
		this.roleManager = roleManager;
	}

	public int getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(int orderNo) {
		this.orderNo = orderNo;
	}

}
