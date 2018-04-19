package com.izhuantou.service.impl.manager;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.izhuantou.damain.ManagerUser;
import com.izhuantou.damain.Password;
import com.izhuantou.damain.mongo.ManagerErrorLog;
import com.izhuantou.damain.mongo.ManagerOperateLog;
import com.izhuantou.dao.ManagerUserMapper;
import com.izhuantou.service.api.managerMenu.ManagerUserService;
import com.izhuantou.service.impl.BaseServiceImpl;

@Service("managerUserService")
public class ManagerUserServiceImpl extends BaseServiceImpl<ManagerUser> implements ManagerUserService {

    @Autowired
    private ManagerUserMapper managerUserMapper;

    public List<ManagerUser> queryManagerUserAll() {
	ManagerOperateLog operateLog = new ManagerOperateLog();

	try {
	    ManagerUser managerUser = new ManagerUser();
	    managerUser.setStatus(0);
	    List<ManagerUser> list = managerUserMapper.select(managerUser);
	    operateLog.setId(UUID.randomUUID().toString());
	    operateLog.setName("获取用户列表");
	    operateLog.setManagerName("张天强");
	    operateLog.setIpAddress("192.168.37.161");
	    return list;
	} catch (Exception e) {
	    ManagerErrorLog errorLog = new ManagerErrorLog();
	    errorLog.setId(UUID.randomUUID().toString());
	    errorLog.setMessage(e.getMessage());
	    errorLog.setMethodName("ManagerUserServiceImpl/queryManagerUserAll");
	    errorLog.setName("获取全部用户列表");
	    return null;
	}
    }

    public ManagerUser queryUser(ManagerUser user) {
	ManagerUser queryUser = null;

	try {
	    queryUser = managerUserMapper.selectOne(user);
	} catch (Exception e) {
	    e.printStackTrace();
	}

	return queryUser;
    }

    public Boolean updatePassword(Password password) {
	Boolean flag = false;
	try {
	    ManagerUser managerUser = this.managerUserMapper.selectByPrimaryKey(password.getOid());
	    if (managerUser != null) {
		// 表示该对象 存在
		if (managerUser.getPassWard().equals(password.getOldPassWard())) {
		    // 表示输入的就密码是正确的，则进行新密码的修改
		    managerUser.setPassWard(password.getNewPassward());
		    this.managerUserMapper.updateByPrimaryKey(managerUser);
		    flag = true;
		}
	    }
	} catch (Exception e) {
	    e.getMessage();
	}
	return flag;
    }

}
