package com.izhuantou.service.api.managerMenu;

import com.izhuantou.damain.RolesToMenu;
import com.izhuantou.service.api.BaseService;

public interface RolesToMenuService extends BaseService<RolesToMenu> {

    /**
     * 根据角色设置权限
     * 
     * @param str
     * @return
     */
    public Boolean distributionMenu(String str);

}
