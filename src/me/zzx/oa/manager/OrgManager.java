package me.zzx.oa.manager;

import me.zzx.oa.model.Orgnization;
import me.zzx.oa.dto.Pager;

public interface OrgManager {
	
	/**
	 * 添加机构信息
	 * @param org
	 * @param parentId
	 */
	public void addOrg(Orgnization org, int parentId);
	
	public void deleteOrg(int id);
	
	public void updateOrg(Orgnization org, int parentId);
	
	public Orgnization findOrg(int id);
	
	/**
	 * 查找机构列表，如果 parentId 为0， 则查找顶级机构列表
	 * @param parentId
	 * @return
	 */
	public Pager findOrgs(int parentId, Pager pager);
}
