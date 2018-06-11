package com.izhuantou.service.impl.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.izhuantou.common.utils.StringUtil;
import com.izhuantou.damain.manager.Roles;
import com.izhuantou.damain.manager.RolesToMenu;
import com.izhuantou.dao.RolesToMenuMapper;
import com.izhuantou.service.api.managerMenu.RolesService;
import com.izhuantou.service.api.managerMenu.RolesToMenuService;
import com.izhuantou.service.impl.BaseServiceImpl;

@Service("rolesToMenuService")
public class RolesToMenuServiceImpl extends BaseServiceImpl<RolesToMenu> implements RolesToMenuService {

    @Autowired
    private RolesToMenuMapper rolesToMenuMapper;
    @Autowired
    private RolesService rolesService;

    public Boolean distributionMenu(String str) {
	if (StringUtils.isNotEmpty(str)) {
	    String[] array = str.split(",");
	    String rolesOid = array[0];
	    if (array.length > 0) {
		for (int i = 1; i < array.length; i++) {
		    RolesToMenu rolesToMenu = new RolesToMenu();
		    rolesToMenu.setRolesOid(rolesOid);
		    rolesToMenu.setMenuOid(array[i]);
		    this.rolesToMenuMapper.insert(rolesToMenu);
		}
	    }
	    return true;
	} else {
	    return false;
	}

    }

    @Override
    public String addRoles(Roles roles, String arr) {
	if (roles != null) {
	    if (StringUtils.isNotEmpty(roles.getOid())) {
		// 设置权限
		if (StringUtils.isNotEmpty(arr)) {
		    String rolesOid = roles.getOid();
		    String message = this.insertRoles(rolesOid, arr);
		    return message;
		}
	    } else {
		// 添加角色并授予相应权限
		String oid = StringUtil.getUUID();
		roles.setIsParent(1);
		roles.setOid(oid);
		roles.setStatus(0);
		this.rolesService.save(roles);
		String message = this.insertRoles(oid, arr);
		return message;
	    }
	} else {
	    return "操作失败";
	}
	return null;
    }

    /**
     * 角色设置权限写库操作
     * 
     * @param oid
     * @param arr
     */
    public String insertRoles(String oid, String arr) {
	if (arr.contains(",")) {
	    String[] array = arr.split(",");
	    if (array.length > 0) {
		for (int i = 1; i < array.length; i++) {
		    RolesToMenu rolesToMenu = new RolesToMenu();
		    rolesToMenu.setRolesOid(oid);
		    rolesToMenu.setMenuOid(array[i]);
		    this.save(rolesToMenu);
		}
	    }
	} else {
	    RolesToMenu rolesToMenu = new RolesToMenu();
	    rolesToMenu.setRolesOid(oid);
	    rolesToMenu.setMenuOid(arr);
	    this.save(rolesToMenu);

	}
	return "操作成功";
    }

}
