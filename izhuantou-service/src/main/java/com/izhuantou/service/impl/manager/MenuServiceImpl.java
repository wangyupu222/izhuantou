package com.izhuantou.service.impl.manager;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.izhuantou.damain.manager.ManagerUser;
import com.izhuantou.damain.manager.ManagerUserToRoles;
import com.izhuantou.damain.manager.Menu;
import com.izhuantou.damain.manager.RolesToMenu;
import com.izhuantou.damain.manager.TreeMenu;
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

    @Override
    public List<TreeMenu> getTree(String oid) {
	// 以及菜单的集合
	List<TreeMenu> returnList = new ArrayList<>();
	if (StringUtils.isNotBlank(oid)) {
	    // 获取用户角色
	    ManagerUserToRoles userToRoles = new ManagerUserToRoles();
	    userToRoles.setUserOid(oid);
	    List<ManagerUserToRoles> list = this.userToRolesMapper.select(userToRoles);
	    // 获取所有的角色oid
	    List<String> rolesOidList = new ArrayList<String>();
	    if (list != null && list.size() > 0) {
		for (ManagerUserToRoles managerUserToRoles : list) {
		    rolesOidList.add(managerUserToRoles.getRolesOid());
		}
	    }
	    // 获取角色的所有权限
	    List<String> menuOidList = new ArrayList<String>();
	    for (String rolesOid : rolesOidList) {
		RolesToMenu rolesToMenu = new RolesToMenu();
		rolesToMenu.setRolesOid(rolesOid);
		List<RolesToMenu> rolesToMenuList = this.rolesToMenuMapper.select(rolesToMenu);
		if (rolesToMenuList != null && rolesToMenuList.size() > 0) {
		    for (RolesToMenu menu : rolesToMenuList) {
			menuOidList.add(menu.getMenuOid());
		    }
		}
	    }
	    // 封装数据对比使用
	    if (menuOidList != null && menuOidList.size() > 0) {
		// 类比对象
		List<TreeMenu> type = new ArrayList<>();
		for (String menuOid : menuOidList) {
		    // 获取三级菜单详情
		    Menu menuThree = menuMapper.queryMenuByOid(menuOid);
		    if (menuThree != null) {
			// 获取二级菜单详情
			Menu menuTwo = menuMapper.queryMenuByOid(menuThree.getParentId());
			if (menuTwo != null) {
			    if (menuTwo.getParentId().equals("0")) {
				// 表示该菜单树是一个2级菜单树
				if (returnList != null && returnList.size() > 0) {
				    for (TreeMenu treeMenu : returnList) {
					if (menuTwo.getName().equals(treeMenu.getTitle())) {
					    // 表示该一级菜单已经存在,直接添加2级菜单
					    TreeMenu treeMenu2 = new TreeMenu();
					    treeMenu2.setOid(menuThree.getOid());
					    treeMenu2.setTitle(menuThree.getName());
					    treeMenu2.setName("systemmanager");
					    treeMenu2.setJump(menuThree.getPageUrl());
					    treeMenu.getMenuList().add(treeMenu2);
					    type.clear();
					} else {
					    type.add(treeMenu);
					}
				    }
				}
				if (returnList.size() == type.size()) {
				    // 表示该一级菜单不存在，添加进1，2级菜单
				    TreeMenu menu = new TreeMenu();
				    menu.setOid(menuTwo.getOid());
				    menu.setTitle(menuTwo.getName());
				    menu.setJump(menuTwo.getPageUrl());
				    menu.setName("systemmanager");
				    menu.setIcon("layui-icon-set");
				    TreeMenu menu2 = new TreeMenu();
				    menu2.setOid(menuThree.getOid());
				    menu2.setTitle(menuThree.getName());
				    menu2.setJump(menuThree.getPageUrl());
				    menu2.setName("systemmanager");
				    menu.getMenuList().add(menu2);
				    returnList.add(menu);
				    type.clear();
				}

			    } else {
				// 表示该菜单树是一个3级菜单树
				// 获取一级菜单详情
				Menu menuOne = menuMapper.queryMenuByOid(menuTwo.getParentId());
				if (menuOne != null && returnList != null && returnList.size() > 0) {
				    for (TreeMenu treeMenu : returnList) {
					if (menuOne.getName().equals(treeMenu.getTitle())) {
					    // 表示该一级菜单已经存在,获取一级菜单下的二级菜单
					    List<TreeMenu> list2 = treeMenu.getMenuList();
					    if (list2 != null && list2.size() > 0) {
						// 临时对象
						List<TreeMenu> typetwo = new ArrayList<>();
						for (TreeMenu treeMenu2 : list2) {
						    if (menuTwo.getName().equals(treeMenu2.getTitle())) {
							// 表示该二级菜单已经存在，添加进三菜单
							TreeMenu threeMenu = new TreeMenu();
							// 菜单名称
							threeMenu.setOid(menuThree.getOid());
							threeMenu.setTitle(menuThree.getName());
							threeMenu.setJump(menuThree.getPageUrl());
							threeMenu.setName("accountManagement");
							treeMenu2.getMenuList().add(threeMenu);
							type.clear();
						    } else {
							typetwo.add(treeMenu2);
						    }
						}
						if (list2.size() == typetwo.size()) {
						    // 表示该二级菜单不存在，添加进2，3菜单
						    TreeMenu MenuTwo = new TreeMenu();
						    MenuTwo.setOid(menuTwo.getOid());
						    MenuTwo.setTitle(menuTwo.getName());
						    MenuTwo.setJump(menuTwo.getPageUrl());
						    menuTwo.setName("userNum");
						    TreeMenu threeMenu = new TreeMenu();
						    threeMenu.setOid(menuThree.getOid());
						    threeMenu.setTitle(menuThree.getName());
						    threeMenu.setJump(menuThree.getPageUrl());
						    threeMenu.setName("accountManagement");
						    MenuTwo.getMenuList().add(threeMenu);
						    treeMenu.getMenuList().add(MenuTwo);
						    typetwo.clear();
						}
					    }
					} else {
					    type.add(treeMenu);
					}
				    }
				}
				if (returnList.size() == type.size()) {
				    // 表示该一级菜单不存在，添加进1，2，3级菜单
				    TreeMenu oneMenu = new TreeMenu();
				    oneMenu.setOid(menuOne.getOid());
				    oneMenu.setTitle(menuOne.getName());
				    oneMenu.setJump(menuOne.getPageUrl());
				    oneMenu.setName("systemmanager");
				    oneMenu.setIcon("layui-icon-set");
				    TreeMenu MenuTwo = new TreeMenu();
				    MenuTwo.setOid(menuTwo.getOid());
				    MenuTwo.setTitle(menuTwo.getName());
				    MenuTwo.setJump(menuTwo.getPageUrl());
				    menuTwo.setName("userNum");
				    TreeMenu threeMenu = new TreeMenu();
				    threeMenu.setOid(menuThree.getOid());
				    threeMenu.setTitle(menuThree.getName());
				    threeMenu.setJump(menuThree.getPageUrl());
				    threeMenu.setName("accountManagement");
				    MenuTwo.getMenuList().add(threeMenu);
				    oneMenu.getMenuList().add(MenuTwo);
				    returnList.add(oneMenu);
				    type.clear();
				}
			    }
			}
		    }

		}
	    }
	    return returnList;
	} else {
	    return null;
	}
    }

}
