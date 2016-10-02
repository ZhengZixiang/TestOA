package me.zzx.oa.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="t_acl")
public class AccessControlList {

	public static final String TYPE_ROLE = "role";
	
	public static final String TYPE_USER = "user";
	
	public static final int ACL_YES = 1;
	
	public static final int ACL_NO = 0;
	
	public static final int ACL_NEUTRAL = -1;
	
	private int id;
	
	//主体类型：角色 用户
	private String principalType;
	
	//主体标识
	private int principalId;
	
	//资源标识
	private int resourceId;
	
	//授权状态，用后四位标识CRUD操作
	private int aclState;
	
	//表示是否继承，0表示不继承，-1表示继承
	private int aclTriState;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Column(nullable=false)
	public String getPrincipalType() {
		return principalType;
	}

	public void setPrincipalType(String principalType) {
		this.principalType = principalType;
	}

	@Column(nullable=false)
	public int getPrincipalId() {
		return principalId;
	}

	public void setPrincipalId(int principalId) {
		this.principalId = principalId;
	}

	@Column(nullable=false)
	public int getResourceId() {
		return resourceId;
	}

	public void setResourceId(int resourceId) {
		this.resourceId = resourceId;
	}

	@Column(nullable=false)
	public int getAclState() {
		return aclState;
	}

	public void setAclState(int aclState) {
		this.aclState = aclState;
	}

	@Column(nullable=false)
	public int getAclTriState() {
		return aclTriState;
	}

	public void setAclTriState(int aclTriState) {
		this.aclTriState = aclTriState;
	}
	
	/**
	 * acl实例跟主体和资源相关联
	 * 针对此实例进行授权某种操作是否被允许
	 * @param permission PermissionType
	 * @param accepted true 表示允许，false 表示不允许
	 */
	public void setPermission(int permission, boolean accepted) {
		int temp = 1;
		temp = temp << permission;
		if(accepted) {
			this.aclState |= temp;
		} else {
			this.aclState &= ~temp;
		}
	}
	
	/**
	 * 获取授权信息
	 * @param permission
	 * @return 授权标识，
	 */
	public int getPermission(int permission) {
		//如果继承，则返回未定的授权信息
		if(aclTriState == 0xFFFFFFFF) {
			return ACL_NEUTRAL;
		}
		int temp = 1;
		temp = temp << permission;
		temp &= aclState;
		if(temp != 0)
			return ACL_YES;
		else 
			return ACL_NO;
	}
	
	/**
	 * 设置本授权是否继承
	 * @param yes true表示继承，false表示不继承
	 */
	public void setExtends(boolean yes) {
		if(yes) {
			aclTriState = 0xFFFFFFFF;
		} else {
			aclTriState = 0;
		}
	}
}
