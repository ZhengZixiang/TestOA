package me.zzx.oa.dto;

import java.util.List;

import me.zzx.oa.model.Organization;

public class Pager {
	private int total;
	private List<Organization> orgs;
	private int offset = 0;
	private int pagesize = 10;
	
	public List<Organization> getOrgs() {
		return orgs;
	}
	
	public void setOrgs(List<Organization> orgs) {
		this.orgs = orgs;
	}
	
	public int getTotal() {
		return total;
	}
	
	public void setTotal(int total) {
		this.total = total;
	}

	public int getOffset() {
		return offset;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}

	public int getPagesize() {
		return pagesize;
	}

	public void setPagesize(int pagesize) {
		this.pagesize = pagesize;
	}
	
}
