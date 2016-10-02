package me.zzx.oa.dao;

import me.zzx.oa.dto.Pager;
import me.zzx.oa.model.Person;

public interface PersonDao {
	
	public void searchPersons(int orgId, Pager pager);
	
	public void searchPersons(Pager pager);
	
	public Person load(int id);
	
	public void save(Person person);
	
	public void update(Person person);
	
	public void delete(Person person);
}
