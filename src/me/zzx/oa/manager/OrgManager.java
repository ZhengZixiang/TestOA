package me.zzx.oa.manager;

import me.zzx.oa.model.Organization;
import me.zzx.oa.dto.Pager;

public interface OrgManager {
	
	/**
	 * ��ӻ�����Ϣ
	 * @param org
	 * @param parentId
	 */
	public void addOrg(Organization org, int parentId);
	
	public void deleteOrg(int id);
	
	public void updateOrg(Organization org, int parentId);
	
	public Organization findOrg(int id);
	
	/**
	 * ���һ����б���� parentId Ϊ0�� ����Ҷ��������б�
	 * @param parentId
	 * @return
	 */
	public void listByPager(int parentId, Pager pager);
}
