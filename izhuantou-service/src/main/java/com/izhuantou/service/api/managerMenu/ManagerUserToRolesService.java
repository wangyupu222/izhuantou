package com.izhuantou.service.api.managerMenu;

import com.izhuantou.damain.manager.ManagerUserToRoles;
import com.izhuantou.service.api.BaseService;

public interface ManagerUserToRolesService extends BaseService<ManagerUserToRoles> {

    /**
     * 用户角色设置
     * 
     * @param str
     * @return
     */
    public Boolean distributionRole(String str);
}
