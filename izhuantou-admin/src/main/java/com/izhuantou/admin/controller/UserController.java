package com.izhuantou.admin.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.izhuantou.admin.ims.annotation.SystemControllerLog;
import com.izhuantou.common.bean.OpResult;
import com.izhuantou.common.bean.Pagination;
import com.izhuantou.damain.manager.ManagerUser;
import com.izhuantou.damain.manager.Password;
import com.izhuantou.damain.manager.TreeMenu;
import com.izhuantou.damain.webp2p.WebP2pFourAmounts;
import com.izhuantou.service.api.managerMenu.ManagerUserService;
import com.izhuantou.service.api.managerMenu.MenuService;
import com.izhuantou.service.api.p2p.FourNumService;

/**
 * 用户相关controller
 * 
 * @author fucheng
 * @date 2018-05-28
 *
 */
@Controller
@RequestMapping(value = "user", produces = "application/json;charset=UTF-8")
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    // 用户session key
    public static final String KEY_USER = "ims_user";
    @Autowired
    private MenuService menuService;
    @Autowired
    private FourNumService fourNumberService;
    @Autowired
    private ManagerUserService managerUserService;

    // 登录方法
    @SystemControllerLog(description = "登录系统")
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    @ResponseBody
    public OpResult login(ManagerUser user, HttpServletRequest request, HttpServletResponse response, Model view) {

	if (user.getUserName() != null && user.getPassWord() != null) {
	    // 基于shiro框架实现认证操作
	    Subject subject = SecurityUtils.getSubject();
	    // 参数封装,密码要进行加密操作
	    // user.setPassWard(new UtilsMD5().Md5(user.getPassWard()));
	    UsernamePasswordToken token = new UsernamePasswordToken(user.getUserName(), user.getPassWord());
	    // realm认证
	    try {
		subject.login(token);
		ManagerUser principalUser = (ManagerUser) subject.getPrincipal();
		HttpSession session = request.getSession();
		session.setAttribute(KEY_USER, user);
		logger.info("{} 登入系统成功!", user.getName());
		return OpResult.getSuccessResult(principalUser.getOid());
	    } catch (Exception e) {
		return OpResult.getFailedResult("账号或密码错误");
	    }
	} else {
	    return OpResult.getFailedResult("账号或密码错误");
	}
    }

    // 首页展示
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String goIndex() {
	return "index";
    }

    // 展示用户具有权限的菜单树

    @RequestMapping(value = "/menutree", method = RequestMethod.GET)
    @ResponseBody
    public OpResult getTree(String oid) {
	List<TreeMenu> returnList = menuService.getTree(oid);
	System.out.println(returnList);
	return OpResult.getSuccessResult(returnList);
    }

    /**
     * 工作台数据展示
     * 
     * @return OpResult
     */

    @RequestMapping(value = "/dataStatistics", method = RequestMethod.GET)
    @ResponseBody
    public OpResult findFourNum() {

	WebP2pFourAmounts fourAmounts = this.fourNumberService.findFourNum();
	return OpResult.getSuccessResult(fourAmounts);
    }

    /**
     * 根据oid获取ManagerUser详细信息
     * 
     * @param oid
     * @return
     */

    @RequestMapping(value = "/usermessage", method = RequestMethod.GET)
    @ResponseBody
    public OpResult getUserMessage(String oid) {
	try {
	    ManagerUser user = this.managerUserService.getUser(oid);
	    // 保密数据处理
	    user.setPassWord(null);
	    return OpResult.getSuccessResult(user);
	} catch (Exception e) {
	    return OpResult.getFailedResult("系统异常");
	}
    }

    /**
     * 密码修改
     * 
     * @param password
     * @return
     */
    @SystemControllerLog(description = "修改密码")
    @RequestMapping(value = "/updatePassword")
    @ResponseBody
    public OpResult updatePassword(Password password) {
	try {
	    Boolean flag = this.managerUserService.updatePassword(password);
	    if (flag) {
		return OpResult.getSuccessResult("修改成功");
	    } else {
		return OpResult.getSuccessResult("原密码错误");
	    }
	} catch (Exception e) {
	    return OpResult.getSuccessResult("修改失败");
	}
    }

    /**
     * 分页获取所有用户信息
     * 
     * @param currentPage
     * @return
     */
    @SystemControllerLog(description = "分页获取用户信息")
    @RequestMapping(value = "/userlist", method = RequestMethod.GET)
    @ResponseBody
    public OpResult queryPageListByPage(@RequestParam(value = "page", defaultValue = "1") Integer currentPage) {
	try {
	    Pagination<ManagerUser> page = managerUserService.queryPageListByPage(currentPage, null);
	    return OpResult.getSuccessResult(page);
	} catch (Exception e) {
	    return OpResult.getFailedResult(null);
	}
    }

    @SystemControllerLog(description = "添加用户")
    @RequestMapping(value = "/adduser")
    @ResponseBody
    public OpResult addManagerUser(ManagerUser managerUser, String roles) {
	try {
	    boolean flag = this.managerUserService.addManagerUser(managerUser, roles);
	    if (flag) {
		return OpResult.getSuccessResult("修改成功");
	    } else {
		return OpResult.getSuccessResult("修改失败");
	    }
	} catch (Exception e) {
	    return OpResult.getSuccessResult("修改失败");
	}
    }

    @SystemControllerLog(description = "删除用户")
    @RequestMapping(value = "/deleteuser")
    @ResponseBody
    public OpResult deleteManagerUser(String oid) {
	try {
	    boolean flag = this.managerUserService.updateManagerUserstatus(oid);
	    if (flag) {
		return OpResult.getSuccessResult("删除成功");
	    } else {
		return OpResult.getSuccessResult("删除失败");
	    }
	} catch (Exception e) {
	    return OpResult.getSuccessResult("删除失败");
	}
    }

    // 重置密码
    @SystemControllerLog(description = "重置密码")
    @RequestMapping(value = "/resetpwd")
    @ResponseBody
    public OpResult resetPwd(String oid) {
	try {
	    String newPwd = this.managerUserService.resetPwd(oid);
	    return OpResult.getSuccessResult(newPwd);
	} catch (Exception e) {
	    return OpResult.getSuccessResult("重置失败");
	}
    }

    // 锁定用户
    @SystemControllerLog(description = "锁定用户")
    @RequestMapping(value = "/lockuser")
    @ResponseBody
    public OpResult updateUserLock(ManagerUser user) {
	String message = null;
	try {
	    message = this.managerUserService.updateUserLock(user);
	    return OpResult.getSuccessResult(message);
	} catch (Exception e) {
	    return OpResult.getSuccessResult(message);
	}
    }

}
