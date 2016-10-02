package me.zzx.oa.action;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import me.zzx.oa.dto.Pager;
import me.zzx.oa.manager.PersonManager;
import me.zzx.oa.model.Person;
import me.zzx.oa.util.SystemException;

@Component("personAction")
@Scope("prototype")
public class PersonAction extends ActionSupport implements ModelDriven<Pager> {

	private static final long serialVersionUID = 5359031626351802898L;

	private PersonManager personManager;
	
	private Pager pager = new Pager();
	
	private int orgId;
	
	private Person person;

	@Override
	public String execute() throws Exception {
		personManager.searchPersons(pager);
		return "index";
	}

	public String addInput() {
		return "add_input";
	}
	
	public String updateInput() {
		person = personManager.find(person.getId());
		return "update_input";
	}
	
	public String save() throws SystemException {
		personManager.add(person, orgId);
		return "pub_add_success";
	}
	
	public String update() {
		personManager.update(person, orgId);
		return "pub_update_success";
	}
	
	public String delete() {
		personManager.delete(person);
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

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public PersonManager getPersonManager() {
		return personManager;
	}

	@Resource
	public void setPersonManager(PersonManager personManager) {
		this.personManager = personManager;
	}

	public int getOrgId() {
		return orgId;
	}

	public void setOrgId(int orgId) {
		this.orgId = orgId;
	}

}
