package com.izhuantou.service.api.managerMenu;

import java.util.List;

import com.izhuantou.damain.ManagerUser;
import com.izhuantou.damain.Menu;
import com.izhuantou.service.api.BaseService;

public interface MenuService extends BaseService<Menu> {

    /**
     * 获取所有菜单
     * 
     * @param parentId
     * @return
     */
    public List<Menu> queryMenuByParentId(String parentId);

    /**
     * 根据用户获取权限菜单树
     * 
     * @param managerUser
     * @return
     */
    public List<Menu> queryMenuByUserName(ManagerUser managerUser);

}
