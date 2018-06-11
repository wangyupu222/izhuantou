package com.izhuantou.service.api.managerMenu;

import com.izhuantou.damain.manager.Roles;
import com.izhuantou.damain.manager.RolesToMenu;
import com.izhuantou.service.api.BaseService;

public interface RolesToMenuService extends BaseService<RolesToMenu> {

    /**
     * 根据角色设置权限
     * 
     * @param str
     * @return
     */
    public Boolean distributionMenu(String str);

    /**
     * 添加角色，配置角色权限
     * 
     * @param roles
     * @param arr
     */
    public String addRoles(Roles roles, String arr);

}
