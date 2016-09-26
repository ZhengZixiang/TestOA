package me.zzx.oa.manager;

import me.zzx.oa.model.Orgnization;
import me.zzx.oa.dto.Pager;

public interface OrgManager {
	
	/**
	 * ��ӻ�����Ϣ
	 * @param org
	 * @param parentId
	 */
	public void addOrg(Orgnization org, int parentId);
	
	public void deleteOrg(int id);
	
	public void updateOrg(Orgnization org, int parentId);
	
	public Orgnization findOrg(int id);
	
	/**
	 * ���һ����б���� parentId Ϊ0�� ����Ҷ��������б�
	 * @param parentId
	 * @return
	 */
	public Pager findOrgs(int parentId, Pager pager);
}
