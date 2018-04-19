package com.izhuantou.service.impl.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.izhuantou.damain.RolesToMenu;
import com.izhuantou.dao.RolesToMenuMapper;
import com.izhuantou.service.api.managerMenu.RolesToMenuService;
import com.izhuantou.service.impl.BaseServiceImpl;

@Service("rolesToMenuService")
public class RolesToMenuServiceImpl extends BaseServiceImpl<RolesToMenu> implements RolesToMenuService {

    @Autowired
    private RolesToMenuMapper rolesToMenuMapper;

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

}
