package com.izhuantou.admin.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.izhuantou.admin.ims.annotation.SystemControllerLog;
import com.izhuantou.common.bean.OpResult;
import com.izhuantou.damain.manager.Roles;
import com.izhuantou.damain.vo.RolesAndUserDTO;
import com.izhuantou.damain.vo.RolesDTO;
import com.izhuantou.service.api.managerMenu.RolesService;
import com.izhuantou.service.api.managerMenu.RolesToMenuService;

@Controller
@RequestMapping(value = "roles", produces = "application/json;charset=UTF-8")
public class RolesController {
    private static final Logger logger = LoggerFactory.getLogger(RolesController.class);

    @Autowired
    private RolesService rolesService;
    @Autowired
    private RolesToMenuService rolesToMenuService;

    // 获取用户分组的一级菜单
    @RequestMapping(value = "/list")
    @ResponseBody
    public OpResult getRolesList() {
	try {
	    List<RolesDTO> list = this.rolesService.queryRoleTree();
	    return OpResult.getSuccessResult(list);
	} catch (Exception e) {
	    return OpResult.getFailedResult(null);
	}
    }

    // 新增角色or叫色添加权限
    @SystemControllerLog(description = "新增角色or角色添加权限")
    @RequestMapping(value = "/addroles")
    @ResponseBody
    public OpResult addRoels(Roles roles, String arr) {
	try {
	    String message = this.rolesToMenuService.addRoles(roles, arr);
	    return OpResult.getSuccessResult("操作成功");
	} catch (Exception e) {
	    return OpResult.getSuccessResult("操作失败");
	}
    }

    // 根据一级角色获取所有的二级角色并且获取具有该角色的所有用户
    @RequestMapping(value = "/getrolesanduser")
    @ResponseBody
    public OpResult getRolesAndUser(String oid) {
	try {
	    List<RolesAndUserDTO> list = this.rolesService.getRolesAndUser(oid);
	    return OpResult.getSuccessResult(list);
	} catch (Exception e) {
	    return OpResult.getSuccessResult(null);
	}
    }

    // 获取所有角色分组的父节点

    @RequestMapping(value = "/parentlist")
    @ResponseBody
    public OpResult getparentList() {
	try {
	    List<Roles> list = this.rolesService.queryRolesByParentId("0");
	    return OpResult.getSuccessResult(list);
	} catch (Exception e) {
	    return OpResult.getSuccessResult("获取失败");
	}
    }

}
