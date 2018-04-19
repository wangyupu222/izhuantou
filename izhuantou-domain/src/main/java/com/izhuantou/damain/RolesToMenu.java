package com.izhuantou.damain;

import javax.persistence.Table;

/**
 * 角色对菜单实体类
 *
 * @author fucheng
 * @date 2018-01-23
 */
@Table(name = "tb_roles_menu")
public class RolesToMenu extends BasePojo {
    /** 
    *  
    */
    private static final long serialVersionUID = 1L;
    private String rolesOid;
    private String menuOid;

    public String getRolesOid() {
	return rolesOid;
    }

    public void setRolesOid(String rolesOid) {
	this.rolesOid = rolesOid;
    }

    public String getMenuOid() {
	return menuOid;
    }

    public void setMenuOid(String menuOid) {
	this.menuOid = menuOid;
    }

    @Override
    public String toString() {
	return "RolesToMenu [rolesOid=" + rolesOid + ", menuOid=" + menuOid + "]";
    }

}
