package me.zzx.oa.dao;

import me.zzx.oa.dto.Pager;

public interface PagerDao {
	public void searchPaginated(String hql, Pager pager);

	public void searchPaginated(String hql, Object value, Pager pager);
	
	public void searchPaginated(String hql, Object [] values, Pager pager);
}
