package com.izhuantou.service.impl.manager;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.izhuantou.common.bean.Pagination;
import com.izhuantou.common.utils.StringUtil;
import com.izhuantou.damain.manager.ManagerUser;
import com.izhuantou.damain.manager.UserGroup;
import com.izhuantou.damain.vo.GroupDTO;
import com.izhuantou.service.api.managerMenu.ManagerUserService;
import com.izhuantou.service.api.managerMenu.UserGroupService;
import com.izhuantou.service.impl.BaseServiceImpl;

@Service("userGroupService")
public class UserGroupServiceImpl extends BaseServiceImpl<UserGroup> implements UserGroupService {
    @Autowired
    private ManagerUserService managerUserService;

    @Override
    public List<UserGroup> getUserGroupParentList() {
	UserGroup userGroup = new UserGroup();
	userGroup.setIsParent(0);
	List<UserGroup> list = this.queryListByWhere(userGroup);
	if (list != null && list.size() > 0) {
	    for (UserGroup group : list) {
		UserGroup usergroup = new UserGroup();
		usergroup.setParentId(group.getOid());
		int count = this.queryCountByWhere(usergroup);
		group.setStatus(count + "");
	    }
	}
	return list;
    }

    @Override
    public List<UserGroup> getUserGroupList(String oid) {
	UserGroup userGroup = new UserGroup();
	userGroup.setParentId(oid);
	List<UserGroup> list = this.queryListByWhere(userGroup);
	if (list != null && list.size() > 0) {
	    for (UserGroup group : list) {
		ManagerUser user = new ManagerUser();
		user.setDuty(group.getName());
		int count = this.managerUserService.queryCountByWhere(user);
		group.setStatus(count + "");
	    }
	}
	return list;
    }

    @Override
    public Pagination<ManagerUser> queryPageListByoid(Integer currentPage, String oid) {
	UserGroup userGroup = new UserGroup();
	userGroup.setOid(oid);
	UserGroup group = this.queryOne(userGroup);
	ManagerUser user = new ManagerUser();
	if (group != null) {
	    user.setDuty(group.getName());
	    return this.managerUserService.queryPageListByPage(currentPage, user);
	} else {
	    return null;
	}
    }

    @Override
    public List<GroupDTO> getUserGroupTree() {
	List<GroupDTO> returnList = new ArrayList<GroupDTO>();
	// 获取所有的一级菜单
	UserGroup userGroup = new UserGroup();
	userGroup.setIsParent(0);
	List<UserGroup> list = this.queryListByWhere(userGroup);
	if (list != null && list.size() > 0) {
	    for (UserGroup usergroup : list) {
		GroupDTO groupDTO = new GroupDTO();
		groupDTO.setOid(usergroup.getOid());
		groupDTO.setName(usergroup.getName());
		UserGroup typeGroup = new UserGroup();
		typeGroup.setParentId(usergroup.getOid());
		List<UserGroup> typeList = this.queryListByWhere(typeGroup);
		groupDTO.setUserGroupList(typeList);
		returnList.add(groupDTO);
	    }
	}
	return returnList;
    }

    @Override
    public boolean addGroup(String oid, String name) {
	boolean flag = false;
	if (!oid.equals("0") && StringUtils.isNotBlank(name)) {
	    // 表示在已有的一级菜单上添加二级菜单
	    UserGroup userGroup = new UserGroup();
	    userGroup.setIsParent(1);
	    userGroup.setName(name);
	    userGroup.setOid(StringUtil.getUUID());
	    userGroup.setParentId(oid);
	    userGroup.setStatus("0");
	    this.save(userGroup);
	    flag = true;
	} else if (StringUtils.isNotBlank(name)) {
	    // 表示添加一级菜单
	    UserGroup userGroup = new UserGroup();
	    userGroup.setIsParent(0);
	    userGroup.setName(name);
	    userGroup.setOid(StringUtil.getUUID());
	    userGroup.setParentId("0");
	    userGroup.setStatus("0");
	    this.save(userGroup);
	    flag = true;
	}
	return flag;
    }

}
