package com.izhuantou.service.impl.manager;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.izhuantou.damain.ManagerUser;
import com.izhuantou.damain.ManagerUserToRoles;
import com.izhuantou.damain.Menu;
import com.izhuantou.damain.RolesToMenu;
import com.izhuantou.dao.ManagerUserToRolesMapper;
import com.izhuantou.dao.MenuMapper;
import com.izhuantou.dao.RolesToMenuMapper;
import com.izhuantou.service.api.managerMenu.MenuService;
import com.izhuantou.service.impl.BaseServiceImpl;

@Service("menuService")
public class MenuServiceImpl extends BaseServiceImpl<Menu> implements MenuService {

    @Autowired
    private MenuMapper menuMapper;
    @Autowired
    private ManagerUserToRolesMapper userToRolesMapper;
    @Autowired
    private RolesToMenuMapper rolesToMenuMapper;

    public List<Menu> queryMenuByParentId(String parentId) {
	Menu menu = new Menu();
	menu.setParentId(parentId);
	return this.menuMapper.select(menu);
    }

    public List<Menu> queryMenuByUserName(ManagerUser managerUser) {
	List<Menu> menuList = null;
	// 获取该用户的所有权限
	List<String> menuOidList = new ArrayList<String>();

	ManagerUserToRoles userToRoles = new ManagerUserToRoles();
	userToRoles.setUserOid(managerUser.getOid());
	// 获取用户角色列表
	List<ManagerUserToRoles> list = this.userToRolesMapper.select(userToRoles);
	if (list != null && list.size() > 0) {
	    // 获取该用户的所有角色
	    List<String> rolesList = new ArrayList<String>();
	    for (ManagerUserToRoles managerUserToRoles : list) {
		rolesList.add(managerUserToRoles.getRolesOid());
	    }
	    if (rolesList != null && rolesList.size() > 0) {

		for (String rolesOid : rolesList) {
		    RolesToMenu rolesToMenu = new RolesToMenu();
		    rolesToMenu.setRolesOid(rolesOid);
		    // 获取该角色所有的权限
		    List<RolesToMenu> rolesToMenuList = this.rolesToMenuMapper.select(rolesToMenu);
		    if (rolesToMenuList != null && rolesToMenuList.size() > 0) {
			for (RolesToMenu menu : rolesToMenuList) {
			    menuOidList.add(menu.getMenuOid());
			}
		    }
		}
	    }
	}

	// 根据菜但ID获取菜单树
	if (menuOidList != null && menuOidList.size() > 0) {
	    ArrayList<String> resultList = new ArrayList<String>();
	    resultList.addAll(menuOidList);
	    // 根据3级菜单获取2级菜单
	    List<String> twoOid = this.menuMapper.queryParentIdByOid(menuOidList);
	    // 去重 获取所有2级菜单
	    List<String> twoLeaveList = this.removal(twoOid);
	    resultList.addAll(twoLeaveList);
	    // 根据2级菜单获取所有1级菜单
	    List<String> oneOid = this.menuMapper.queryParentIdByOid(twoLeaveList);
	    // 去重 获取所有一级菜单
	    List<String> oneLeaveList = this.removal(oneOid);
	    resultList.addAll(oneLeaveList);
	    // 获取所以菜单详情
	    menuList = this.menuMapper.queryDetailByOid(resultList);

	}
	return menuList;
    }

    /**
     * 赋值到另一个List
     * 
     * @param list
     * @return
     */
    public List<String> removal(List<String> list) {
	List<String> resultlist = new ArrayList<String>();
	Iterator<String> it = list.iterator();
	while (it.hasNext()) {
	    String itnext = (String) it.next();
	    if (resultlist.contains(itnext)) {
		it.remove();
	    } else {
		resultlist.add(itnext);
	    }
	}
	return resultlist;
    }

}
