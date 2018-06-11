package com.izhuantou.service.api.managerMenu;

import java.util.List;

import com.izhuantou.common.bean.Pagination;
import com.izhuantou.damain.manager.ManagerUser;
import com.izhuantou.damain.manager.Password;
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

    /**
     * 获取用户详细信息
     * 
     * @param oid
     * @return
     */
    public ManagerUser getUser(String oid);

    /**
     * 分页获取用户所有信息
     * 
     * @param currentPage
     * @return
     */
    public Pagination<ManagerUser> queryPageListByPage(Integer currentPage, ManagerUser managerUser);

    /**
     * 添加,修改用户
     * 
     * @param managerUser
     * @param roles
     * @return
     */
    public boolean addManagerUser(ManagerUser managerUser, String roles);

    /**
     * 删除用户
     * 
     * @param oid
     * @return
     */
    public boolean updateManagerUserstatus(String oid);

    /**
     * 重置密码
     * 
     * @param oid
     * @return
     */
    public String resetPwd(String oid);

    /**
     * 锁定,解锁用户
     * 
     * @param user
     * @return
     */
    public String updateUserLock(ManagerUser user);

}
