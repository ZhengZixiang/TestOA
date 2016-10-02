package me.zzx.oa.manager;

import me.zzx.oa.dto.Pager;
import me.zzx.oa.model.Person;

public interface PersonManager {

	/**
	 * 得到所有人员信息
	 */
	public void searchPersons(Pager pager);
	
	/**
	 * 搜索特定机构人员信息
	 * @param orgId 机构ID
	 * @param pager 分页模型
	 */
	public void searchPersons(int orgId, Pager pager);
	
	/**
	 * 查找特定人员的信息
	 * @param id
	 */
	public Person find(int id);
	
	/**
	 * 添加人员
	 * @param person 人员
	 * @param orgId 所属机构
	 */
	public void add(Person person, int orgId);
	
	/**
	 * 删除人员
	 * @param person
	 */
	public void delete(Person person);
	
	/**
	 * 更新人员
	 * @param person
	 */
	public void update(Person person, int orgId);
}
