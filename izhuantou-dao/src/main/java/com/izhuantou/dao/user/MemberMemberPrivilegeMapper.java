package com.izhuantou.dao.user;

import com.github.abel533.mapper.Mapper;
import com.izhuantou.damain.user.MemberMemberPrivilege;

/**
 * 用户特权
 * 
 * @author yangbosen
 * @version 1.0
 */
public interface MemberMemberPrivilegeMapper extends Mapper<MemberMemberPrivilege> {
    /**
     * 通过特权OID和状态查找单条信息
     * 
     * @param tqOID
     * @return
     */
    public MemberMemberPrivilege findByOIDAndsatate(String tqOID);

    /**
     * 根据OID查找特权信息
     * 
     * @param OID
     * @return
     */
    public MemberMemberPrivilege findByOID(String OID);

    /***
     * 修改用户的特权状态
     * 
     * @param MEMBER_MemberPrivilege
     * @return
     */
    public String updState(MemberMemberPrivilege MEMBER_MemberPrivilege);

}
