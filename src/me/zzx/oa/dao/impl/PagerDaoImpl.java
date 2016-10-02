package me.zzx.oa.dao.impl;

import javax.annotation.Resource;
import javax.persistence.Query;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate5.HibernateCallback;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import me.zzx.oa.dao.PagerDao;
import me.zzx.oa.dto.Pager;
import me.zzx.oa.util.SystemException;

@Repository("pagerDao")
public class PagerDaoImpl implements PagerDao {

	private HibernateTemplate hibernateTemplate;
	
	@Override
	public void searchPaginated(String hql, Pager pager) {
		this.searchPaginated(hql, null, pager);
	}
	
	@Override
	public void searchPaginated(String hql, Object value, Pager pager) {
		this.searchPaginated(hql, new Object []{value}, pager);
	}
	
	/**
	 * ����HQL�����з�ҳ��ѯ
	 * @param hql HQL���
	 * @param values HQL�����б�
	 * @param pager Pager��ģ��
	 */
	@Override
	public void searchPaginated(String hql, Object[] values, Pager pager) throws SystemException {
		//��ȡ�ܼ�¼��
		String countHql = this.getCountHql(hql);
		
		hibernateTemplate.execute(
			new HibernateCallback <Object>() {

				@SuppressWarnings("unchecked")
				@Override
				public Object doInHibernate(Session session) throws HibernateException {
					Query countQuery = session.createQuery(countHql);
					Query selectQuery = session.createQuery(hql)
							.setFirstResult(pager.getOffset())
							.setMaxResults(pager.getPagesize());
					if(values != null && values.length > 0) {
						for(int i = 0; i < values.length; i++) {
							countQuery.setParameter(i, values[i]);
							selectQuery.setParameter(i, values[i]);
						}
					}
					pager.setTotal((int)((long)countQuery.getSingleResult()));
					pager.setList(selectQuery.getResultList());
					return null;
				}
		});
	}		
	
	private String getCountHql(String hql) throws SystemException {
		int index = hql.indexOf("from");
		if(index != -1) {
			String countHql = " select count(*) " + hql.substring(index);
			return countHql;
		} else {
			throw new SystemException("exception.hql.invalid");
		}
		
	}

	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}

	@Resource
	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}

}
