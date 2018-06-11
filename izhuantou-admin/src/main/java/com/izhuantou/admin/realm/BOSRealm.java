package com.izhuantou.admin.realm;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import com.izhuantou.common.utils.StringUtil;
import com.izhuantou.damain.manager.ManagerUser;
import com.izhuantou.service.api.managerMenu.ManagerUserService;

/**
 * 自定义Realm
 * 
 * @author fucheng
 * @Date 2018-05-23
 *
 */
public class BOSRealm extends AuthorizingRealm {
    @Autowired
    private ManagerUserService managerUserService;

    // 认证
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
	UsernamePasswordToken passwordToken = (UsernamePasswordToken) token;
	String name = passwordToken.getUsername();
	if (StringUtil.isNotEmpty(name)) {
	    // 查询数据库
	    ManagerUser user = new ManagerUser();
	    user.setUserName(name);
	    ManagerUser managerUser = managerUserService.queryUser(user);
	    // 简单认证对象信息
	    if (managerUser != null) {
		SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(managerUser, managerUser.getPassWord(),
			this.getName());
		return info;
	    } else {
		return null;
	    }

	} else {
	    return null;
	}
    }

    // 授权方法
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
	// 获取当前登录的用户
	ManagerUser User = (ManagerUser) principals.getPrimaryPrincipal();
	// TODO 根据当前登录的用户查询数据库,为用户授权
	SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
	info.addStringPermission("");
	info.addRole("");
	return info;
    }

}
