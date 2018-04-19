package com.izhuantou.service.api.managerMenu;

import java.util.List;

import com.izhuantou.damain.ManagerUser;
import com.izhuantou.damain.Password;
import com.izhuantou.service.api.BaseService;

public interface ManagerUserService extends BaseService<ManagerUser> {

    /**
     * 获取用户列表
     * 
     * @return
     */
    public List<ManagerUser> queryManagerUserAll();

    /**
     * 用户登录效验
     * 
     * @param user
     * @return
     */
    public ManagerUser queryUser(ManagerUser user);

    /**
     * 修改密码
     * 
     * @param updatePassward
     * @return
     */
    public Boolean updatePassword(Password password);

}
