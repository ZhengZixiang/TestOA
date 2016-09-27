package me.zzx.oa.manager.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import me.zzx.oa.dao.OrgDao;
import me.zzx.oa.dto.Pager;
import me.zzx.oa.manager.OrgManager;
import me.zzx.oa.model.Organization;
import me.zzx.oa.util.SystemException;

@Service("orgManager")
public class OrgManagerImpl implements OrgManager{
	
	private OrgDao orgDao;

	@Override
	public void addOrg(Organization org, int parentId) {
		orgDao.save(org, parentId);
	}

	@Override
	public void deleteOrg(int id) throws SystemException {
		Organization org = orgDao.load(id);
		
		//判断是否存在子机构，若存在，则不允许删除
		if(org.getChildren().size() > 0) {
			throw new SystemException("exception.org.delete");
		}		
		orgDao.delete(org);
	}

	@Override
	public void updateOrg(Organization org, int parentId) {
		if(parentId != 0) {
			org.setParent(orgDao.load(parentId));
		}
		orgDao.update(org);
	}

	@Override
	public Organization findOrg(int id) {
		return orgDao.load(id);
	}

	@Override
	public void listByPager(int parentId, Pager pager) {
		orgDao.searchOrgs(parentId, pager);
	}

	public OrgDao getOrgDao() {
		return orgDao;
	}

	@Resource
	public void setOrgDao(OrgDao orgDao) {
		this.orgDao = orgDao;
	}
	
}
