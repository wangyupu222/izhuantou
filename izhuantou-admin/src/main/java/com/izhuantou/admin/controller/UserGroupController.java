package com.izhuantou.admin.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.izhuantou.admin.ims.annotation.SystemControllerLog;
import com.izhuantou.common.bean.OpResult;
import com.izhuantou.common.bean.Pagination;
import com.izhuantou.damain.manager.ManagerUser;
import com.izhuantou.damain.manager.UserGroup;
import com.izhuantou.damain.vo.GroupDTO;
import com.izhuantou.service.api.managerMenu.UserGroupService;

/**
 * 用户组相关的controller
 * 
 * @author fucheng
 * @data 2018-05-30
 *
 */
@Controller
@RequestMapping(value = "group", produces = "application/json;charset=UTF-8")
public class UserGroupController {
    private static final Logger logger = LoggerFactory.getLogger(UserGroupController.class);

    @Autowired
    private UserGroupService userGroupService;

    // 获取用户分组的一级菜单
    @SystemControllerLog(description = "获取用户分组")
    @RequestMapping(value = "/parentlist")
    @ResponseBody
    public OpResult getUserGroupParentList(@RequestParam(value = "page", defaultValue = "1") Integer currentPage) {
	try {
	    List<UserGroup> list = this.userGroupService.getUserGroupParentList();
	    return OpResult.getSuccessResult(list);
	} catch (Exception e) {
	    return OpResult.getFailedResult(null);
	}
    }

    // 根据oid获取所有子菜单
    @SystemControllerLog(description = "获取用户分组")
    @RequestMapping(value = "/list")
    @ResponseBody
    public OpResult getUserGroupList(String oid) {
	try {
	    List<UserGroup> list = this.userGroupService.getUserGroupList(oid);
	    return OpResult.getSuccessResult(list);
	} catch (Exception e) {
	    return OpResult.getFailedResult(null);
	}
    }

    // 根据子菜单的oid分页获取下属的用户
    @SystemControllerLog(description = "获取分页所属用户")
    @RequestMapping(value = "/pageUserlist")
    @ResponseBody
    public OpResult getGroupUserListByOid(@RequestParam(value = "page", defaultValue = "1") Integer currentPage,
	    String oid) {
	try {
	    Pagination<ManagerUser> page = this.userGroupService.queryPageListByoid(currentPage, oid);
	    return OpResult.getSuccessResult(page);
	} catch (Exception e) {
	    return OpResult.getFailedResult("系统异常");
	}
    }

    // 根据oid获取所有子菜单
    @SystemControllerLog(description = "获取子菜单")
    @RequestMapping(value = "/tree")
    @ResponseBody
    public OpResult getUserGroupTree() {
	try {
	    List<GroupDTO> list = this.userGroupService.getUserGroupTree();
	    return OpResult.getSuccessResult(list);
	} catch (Exception e) {
	    return OpResult.getFailedResult(null);
	}
    }

    // 添加部门
    @SystemControllerLog(description = "添加部门")
    @RequestMapping(value = "/addgroup")
    @ResponseBody
    public OpResult addGroup(@RequestParam(value = "oid", defaultValue = "0") String oid, String name) {
	try {
	    boolean flag = this.userGroupService.addGroup(oid, name);
	    if (flag) {
		return OpResult.getSuccessResult("添加成功");
	    } else {
		return OpResult.getSuccessResult("添加失败");
	    }
	} catch (Exception e) {
	    return OpResult.getSuccessResult("添加失败");
	}
    }

}
