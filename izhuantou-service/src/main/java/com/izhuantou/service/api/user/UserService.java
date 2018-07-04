package com.izhuantou.service.api.user;

import java.util.List;
import java.util.Map;

import com.izhuantou.damain.pay.PayCustomer;
import com.izhuantou.damain.user.MemberBlackList;
import com.izhuantou.damain.user.MemberMember;
import com.izhuantou.damain.vo.PersonalDTO;
import com.izhuantou.damain.vo.UserDTO;
import com.izhuantou.service.api.BaseService;

public interface UserService extends BaseService<MemberMember> {
    /**
     * 根据用户手机号(用户名)查询用户,验证密码
     * 
     * @param user
     * @return
     */
    public Map<String,Object> findUserByName(UserDTO user);

    /**
     * 根据用户名(用户要注册的手机号)验证用户是否存在
     * 
     * @param user
     */
    public String registUser(UserDTO user);

    /**
     * 根据用户名修改用户的密码
     * 
     * @param user
     */
    public Integer updatePassword(UserDTO user);

    /**
     * 获取登录后主页面的 代收本金：cybj，代收利息：yjsy，代收特权：tqsyds
     * 
     * @param memberOID
     * @return
     */
    public PersonalDTO findIndexNumber(String memberOID);

    /**
     * @param memberOID
     * @return
     */
    public Map<String, String> findTwoQuestion(UserDTO user);

    /**
     * 用来判断用户是否开通资金账户
     * 
     * @param memberOID
     * @return
     */
    public PayCustomer findCustomerByMemberOID(String memberOID);

    /**
     * 根据用户OID和状态查询 特权收益
     * 
     * @param memberOID
     * @param state
     * @return
     */
    public List<Map<String, Object>> gainDDTTQBymemberANDstate(String memberOID, String state);

    /**
     * 根据用户OID和状态查询 特权收益
     * 
     * @param memberOID
     * @param state
     * @return
     */
    public List<Map<String, Object>> gainByMemebrOIDAndTQ(String memberOID, String state);

    /**
     * 获取邀请码人的OID
     * 
     * @param yqm_reg
     * @return
     */
    public Map<String, String> registUseryqmReg(String yqm_reg);

    /**
     * 验证用户是否在密码错误的黑名单
     * @param name
     * @return
     */
    public String cheakMemberBlack(String name);
}
