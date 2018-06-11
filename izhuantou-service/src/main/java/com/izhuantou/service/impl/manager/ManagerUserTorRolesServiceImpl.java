package com.izhuantou.service.impl.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.izhuantou.damain.manager.ManagerUserToRoles;
import com.izhuantou.dao.ManagerUserToRolesMapper;
import com.izhuantou.service.api.managerMenu.ManagerUserToRolesService;
import com.izhuantou.service.impl.BaseServiceImpl;

@Service("userToRolesService")
public class ManagerUserTorRolesServiceImpl extends BaseServiceImpl<ManagerUserToRoles>
	implements ManagerUserToRolesService {

    @Autowired
    private ManagerUserToRolesMapper userToRolesMapper;

    public Boolean distributionRole(String str) {
	if (StringUtils.isNotEmpty(str)) {
	    String[] Array = str.split(",");
	    String userOid = Array[0];
	    if (Array.length > 0) {
		for (int i = 1; i < Array.length; i++) {
		    // 循环将用对应关系添加进用户对角色的中间表,建立多对多关系映射
		    ManagerUserToRoles userToRoles = new ManagerUserToRoles();
		    userToRoles.setUserOid(userOid);
		    userToRoles.setRolesOid(Array[i]);
		    this.userToRolesMapper.insert(userToRoles);
		}
	    }
	    return true;
	} else {
	    return false;
	}
    }
}
