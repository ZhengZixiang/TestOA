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
	
	//�������ͣ���ɫ �û�
	private String principalType;
	
	//�����ʶ
	private int principalId;
	
	//��Դ��ʶ
	private int resourceId;
	
	//��Ȩ״̬���ú���λ��ʶCRUD����
	private int aclState;
	
	//��ʾ�Ƿ�̳У�0��ʾ���̳У�-1��ʾ�̳�
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
	 * aclʵ�����������Դ�����
	 * ��Դ�ʵ��������Ȩĳ�ֲ����Ƿ�����
	 * @param permission PermissionType
	 * @param accepted true ��ʾ����false ��ʾ������
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
	 * ��ȡ��Ȩ��Ϣ
	 * @param permission
	 * @return ��Ȩ��ʶ��
	 */
	public int getPermission(int permission) {
		//����̳У��򷵻�δ������Ȩ��Ϣ
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
	 * ���ñ���Ȩ�Ƿ�̳�
	 * @param yes true��ʾ�̳У�false��ʾ���̳�
	 */
	public void setExtends(boolean yes) {
		if(yes) {
			aclTriState = 0xFFFFFFFF;
		} else {
			aclTriState = 0;
		}
	}
}
