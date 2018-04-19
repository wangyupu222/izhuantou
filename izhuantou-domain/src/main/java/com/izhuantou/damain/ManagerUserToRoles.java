package com.izhuantou.damain;

import javax.persistence.Table;

/**
 * 用户对角色实体类
 *
 * @author fucheng
 * @date 2018-01-23
 */
@Table(name = "tb_manager_user_roles")
public class ManagerUserToRoles extends BasePojo {
    /** 
    *  
    */
    private static final long serialVersionUID = 1L;

    private String userOid;
    private String rolesOid;

    public String getUserOid() {
	return userOid;
    }

    public void setUserOid(String userOid) {
	this.userOid = userOid;
    }

    public String getRolesOid() {
	return rolesOid;
    }

    public void setRolesOid(String rolesOid) {
	this.rolesOid = rolesOid;
    }

    @Override
    public String toString() {
	return "ManagerUserToRoles [userOid=" + userOid + ", rolesOid=" + rolesOid + "]";
    }

}
