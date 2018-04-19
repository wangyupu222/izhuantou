package com.izhuantou.service.impl.manager;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.izhuantou.damain.Roles;
import com.izhuantou.dao.RolesMapper;
import com.izhuantou.service.api.managerMenu.RolesService;
import com.izhuantou.service.impl.BaseServiceImpl;

@Service("rolesService")
public class RolesServiceImpl extends BaseServiceImpl<Roles> implements RolesService {

    @Autowired
    private RolesMapper rolesMapper;

    public List<Roles> queryRolesByParentId(String parentId) {
	Roles roles = new Roles();
	roles.setParentId(parentId);
	return this.rolesMapper.select(roles);
    }

}
