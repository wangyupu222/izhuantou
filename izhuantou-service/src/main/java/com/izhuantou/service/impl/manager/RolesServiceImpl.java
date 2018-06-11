package com.izhuantou.service.impl.manager;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.izhuantou.damain.manager.ManagerUser;
import com.izhuantou.damain.manager.Roles;
import com.izhuantou.damain.vo.RolesAndUserDTO;
import com.izhuantou.damain.vo.RolesDTO;
import com.izhuantou.dao.ManagerUserMapper;
import com.izhuantou.dao.ManagerUserToRolesMapper;
import com.izhuantou.dao.RolesMapper;
import com.izhuantou.service.api.managerMenu.RolesService;
import com.izhuantou.service.impl.BaseServiceImpl;

@Service("rolesService")
public class RolesServiceImpl extends BaseServiceImpl<Roles> implements RolesService {

    @Autowired
    private RolesMapper rolesMapper;
    @Autowired
    private ManagerUserToRolesMapper managerUserToRolesMapper;
    @Autowired
    private ManagerUserMapper managerUserMapper;

    public List<Roles> queryRolesByParentId(String parentId) {
	Roles roles = new Roles();
	roles.setParentId(parentId);
	return this.rolesMapper.select(roles);
    }

    @Override
    public List<RolesDTO> queryRoleTree() {
	List<RolesDTO> list = new ArrayList<RolesDTO>();

	// 获取所有的一级菜单
	List<Roles> rolesList = this.queryRolesByParentId("0");
	if (rolesList != null && rolesList.size() > 0) {
	    for (Roles roles : rolesList) {
		RolesDTO rolesDTO = new RolesDTO();
		rolesDTO.setOid(roles.getOid());
		rolesDTO.setName(roles.getName());
		List<Roles> type = this.queryRolesByParentId(roles.getOid());
		rolesDTO.setRoleList(type);
		list.add(rolesDTO);
	    }
	    return list;
	} else {
	    return null;
	}
    }

    @Override
    public List<RolesAndUserDTO> getRolesAndUser(String oid) {
	List<RolesAndUserDTO> returnList = new ArrayList<>();
	List<Roles> rolesList = this.queryRolesByParentId(oid);
	if (rolesList != null && rolesList.size() > 0) {
	    for (Roles roles : rolesList) {
		RolesAndUserDTO rolesDTO = new RolesAndUserDTO();
		rolesDTO.setOid(roles.getOid());
		rolesDTO.setName(roles.getName());
		List<String> userOidList = this.getUserOidByRolesOid(roles.getOid());
		if (userOidList != null && userOidList.size() > 0) {
		    List<ManagerUser> userList = this.managerUserMapper.getUserListByOidList(userOidList);
		    rolesDTO.setUserList(userList);
		}
		returnList.add(rolesDTO);
	    }
	}
	return returnList;
    }

    public List<String> getUserOidByRolesOid(String oid) {
	return this.managerUserToRolesMapper.getUserOidByRolesOid(oid);

    }

}
