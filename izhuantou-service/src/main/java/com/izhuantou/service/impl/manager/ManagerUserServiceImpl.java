package com.izhuantou.service.impl.manager;

import java.util.List;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.izhuantou.common.bean.Pagination;
import com.izhuantou.common.utils.StringUtil;
import com.izhuantou.damain.manager.ManagerUser;
import com.izhuantou.damain.manager.Password;
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
		if (managerUser.getPassWord().equals(password.getOldPassWard())) {
		    // 表示输入的就密码是正确的，则进行新密码的修改
		    managerUser.setPassWord(password.getNewPassward());
		    this.managerUserMapper.updateByPrimaryKey(managerUser);
		    flag = true;
		}
	    }
	} catch (Exception e) {
	    e.getMessage();
	}
	return flag;
    }

    @Override
    public ManagerUser getUser(String oid) {
	ManagerUser user = new ManagerUser();
	user.setOid(oid);
	return this.queryOne(user);
    }

    @Override
    public Pagination<ManagerUser> queryPageListByPage(Integer currentPage, ManagerUser managerUse) {
	Pagination<ManagerUser> pagination = new Pagination<ManagerUser>();
	try {
	    // 设置每页显示条数
	    pagination.setPageSize(10);

	    // 获取 总条数
	    Integer count = this.managerUserMapper.selectCount(managerUse);
	    pagination.setTotalNumber(count);
	    // 获取 总页数
	    if (count % pagination.getPageSize() == 0) {
		pagination.setTotalPage(count / pagination.getPageSize());
	    } else {
		pagination.setTotalPage((count / pagination.getPageSize().intValue() + 1));
	    }
	    // 获取分页数据
	    List<ManagerUser> list = this.queryByPage(currentPage, 10, managerUse);
	    pagination.setData(list);
	} catch (Exception e) {
	    e.getMessage();
	}
	return pagination;
    }

    @Override
    public boolean addManagerUser(ManagerUser managerUser, String roles) {
	boolean flag = false;
	if (managerUser != null && StringUtils.isNotBlank(managerUser.getOid())) {
	    // 修改用户信息
	    flag = true;
	} else {
	    // 添加用户
	    flag = true;
	}
	return flag;
    }

    @Override
    public boolean updateManagerUserstatus(String oid) {
	boolean flag = false;
	if (StringUtils.isNotBlank(oid)) {
	    flag = true;
	}
	return false;
    }

    @Override
    public String resetPwd(String oid) {
	// 密码规则 (numbe+A+a+@) = 8
	String pwd = StringUtil.genRandomPwd(8);
	ManagerUser managerUser = new ManagerUser();
	managerUser.setOid(oid);
	ManagerUser user = this.queryOne(managerUser);
	if (user != null) {
	    // TODO 这里需要进行加密操作
	    user.setPassWord(pwd + "@");
	    return pwd + "@";
	} else {
	    return "用不不存在";
	}

    }

    @Override
    public String updateUserLock(ManagerUser user) {
	String flag = "操作失败";
	// 锁定
	ManagerUser managerUser = new ManagerUser();
	managerUser.setOid(user.getOid());
	ManagerUser queryUser = this.queryOne(managerUser);
	if (queryUser != null) {
	    queryUser.setStatus(user.getStatus());
	    this.updateBySelective(queryUser);
	    flag = "操作成功";
	}
	return flag;
    }
}
