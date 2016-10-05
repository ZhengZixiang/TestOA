package me.zzx.oa.dto;

public class Record {
	private int resourceId;
	
	private int cState;
	
	private int rState;
	
	private int uState;
	
	private int dState;
	
	private int aclTriState;
	
	public Record(int resourceId, int aclState, int aclTriState) {
		this.resourceId = resourceId;
		this.cState = aclState & 1;
		this.rState = aclState & 2;
		this.uState = aclState & 4;
		this.dState = aclState & 8;
		this.aclTriState = aclTriState;
	}

	public int getcState() {
		return cState;
	}

	public void setcState(int cState) {
		this.cState = cState;
	}

	public int getrState() {
		return rState;
	}

	public void setrState(int rState) {
		this.rState = rState;
	}

	public int getuState() {
		return uState;
	}

	public void setuState(int uState) {
		this.uState = uState;
	}

	public int getdState() {
		return dState;
	}

	public void setdState(int dState) {
		this.dState = dState;
	}

	public int getAclTriState() {
		return aclTriState;
	}

	public void setAclTriState(int aclTriState) {
		this.aclTriState = aclTriState;
	}

	public int getResourceId() {
		return resourceId;
	}

	public void setResourceId(int resourceId) {
		this.resourceId = resourceId;
	}

}
