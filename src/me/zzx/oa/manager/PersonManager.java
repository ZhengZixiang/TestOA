package me.zzx.oa.manager;

import me.zzx.oa.dto.Pager;
import me.zzx.oa.model.Person;

public interface PersonManager {

	/**
	 * �õ�������Ա��Ϣ
	 */
	public void searchPersons(Pager pager);
	
	/**
	 * �����ض�������Ա��Ϣ
	 * @param orgId ����ID
	 * @param pager ��ҳģ��
	 */
	public void searchPersons(int orgId, Pager pager);
	
	/**
	 * �����ض���Ա����Ϣ
	 * @param id
	 */
	public Person find(int id);
	
	/**
	 * �����Ա
	 * @param person ��Ա
	 * @param orgId ��������
	 */
	public void add(Person person, int orgId);
	
	/**
	 * ɾ����Ա
	 * @param person
	 */
	public void delete(Person person);
	
	/**
	 * ������Ա
	 * @param person
	 */
	public void update(Person person, int orgId);
}
