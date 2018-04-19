package com.izhuantou.dao.user;

import com.github.abel533.mapper.Mapper;
import com.izhuantou.damain.user.MemberMember;

/**
 * 用户表
 * 
 * @author dear
 * @version 1.0
 */
public interface MemberMemberMapper extends Mapper<MemberMember> {
    /**
     * 
     * @param namename为用户名也是手机号查询用户
     * @return
     */
    public MemberMember findUserByName(String name);

    /**
     * 根据用户输入的账号和密码进行注册
     * 
     * @param user
     */
    public int registUser(MemberMember user);

    /**
     * 根据用户输入的账号进行密码修改
     * 
     * @param user
     */
    public int updatePassword(MemberMember user);

    /**
     * 根据OID查询用户信息
     * 
     * @param OID
     * @return
     */
    public MemberMember findUserByOID(String OID);

    /**
     * 设置密保
     * 
     * @param member
     * @return
     */
    public int insertTwoQuestion(MemberMember member);

    /**
     * 更新用户实名后的信息
     * 
     * @param member
     * @return
     */
    public int updateRealNameByOID(MemberMember member);

    /**
     * 判断是不是新手
     * 
     * @return
     */
    public int findXSNum(String memberOID);

    /**
     * 跟新整个member
     * 
     * @return
     */
    public int updateMemberMember(MemberMember member);
}
