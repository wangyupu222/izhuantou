package com.izhuantou.service.api.managerMenu;

import java.util.List;

import com.izhuantou.damain.manager.ManagerUser;
import com.izhuantou.damain.manager.Menu;
import com.izhuantou.damain.manager.TreeMenu;
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

    /**
     * 返回用户具有权限的菜单树
     * 
     * @param oid
     */
    public List<TreeMenu> getTree(String oid);

}
