package com.izhuantou.fund.rpc.api;

import java.util.List;

import com.izhuantou.damain.user.MemberMemberPrivilege;

/**
 *
 * @author fucheng
 * @date 2018-03-07
 */
public interface ControlMemberPrivilege extends BaseService<MemberMemberPrivilege> {

    /**
     * 根据OID 和 状态来获取实体
     * 
     * @param OID
     * @param state
     * @return
     */
    public List<MemberMemberPrivilege> findByOIDAndsatate(String OID);

}
