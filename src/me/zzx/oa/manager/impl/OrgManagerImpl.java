package me.zzx.oa.manager.impl;

import java.util.List;

import javax.annotation.Resource;
import javax.persistence.Query;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate5.HibernateCallback;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Service;

import me.zzx.oa.dto.Pager;
import me.zzx.oa.manager.OrgManager;
import me.zzx.oa.model.Orgnization;
import me.zzx.oa.util.SystemException;

@Service("orgManager")
public class OrgManagerImpl implements OrgManager{
	
	private HibernateTemplate hibernateTemplate;

	@Override
	public void addOrg(Orgnization org, int parentId) {
		if(parentId != 0) {
			org.setParent(hibernateTemplate.load(Orgnization.class, parentId));
		}
		hibernateTemplate.save(org);
		int id = (org.getId());
		if(parentId != 0) org.setSn(org.getParent().getSn() + "_" + id);
		else org.setSn("" + id);
		hibernateTemplate.update(org);
	}

	@Override
	public void deleteOrg(int id) throws SystemException {
		Orgnization org = hibernateTemplate.load(Orgnization.class, id);
		
		//判断是否存在子机构，若存在，则不允许删除
		if(org.getChildren().size() > 0) {
			throw new SystemException("exception.org.delete");
		}
		
		hibernateTemplate.delete(org);
	}

	@Override
	public void updateOrg(Orgnization org, int parentId) {
		if(parentId != 0) {
			org.setParent(hibernateTemplate.load(Orgnization.class, parentId));
		}
		hibernateTemplate.update(org);
	}

	@Override
	public Orgnization findOrg(int id) {
		return hibernateTemplate.load(Orgnization.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Pager findOrgs(int parentId, Pager pager) {
		
		String countHql;
		
		//如果 parentId 为0，则查找顶级机构列表
		if(parentId == 0) {
			countHql = "select count(*) from Orgnization o where o.parent is null";
		} else {
			countHql = "select count(*) from Orgnization o where o.parent.id is " + parentId;
		}
		
		long total = ((List<Long>) hibernateTemplate.find(countHql)).get(0);
		
		List<Orgnization> orgs = (List<Orgnization>) hibernateTemplate.execute(
			new HibernateCallback() {
				@Override
				public Object doInHibernate(Session session) throws HibernateException {
					String selectHql;
					if(parentId == 0) {
						selectHql = " from Orgnization o where o.parent is null";
					} else {
						selectHql = " from Orgnization o where o.parent.id = " + parentId;
					}
					Query query = session.createQuery(selectHql)
							.setMaxResults(pager.getPagesize())
							.setFirstResult(pager.getOffset());
					List<Orgnization> orgs = query.getResultList();
					return orgs;
				}				
			}
		);
		
		pager.setTotal((int)total);
		pager.setOrgs(orgs);
		return pager;
	}

	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}

	@Resource
	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}

}
