package com.izhuantou.dao.p2p;

import com.izhuantou.damain.p2p.P2pHuiYuanUser;

/**
 * 会员用户信息表
 * 
 * @author dear
 * @version 1.0
 */
public interface P2pHuiYuanUserMapper {
    /**
     * 根据身份证号查询会员信息
     * 
     * @param card
     * @return
     */
    public P2pHuiYuanUser findHuiYuanUserByCard(String card);

    /**
     * 跟新会员信息
     * 
     * @param user
     * @return
     */
    public int updateHuiYuanUserByOID(P2pHuiYuanUser user);

    /**
     * 添加一条会员信息
     * 
     * @param user
     * @return
     */
    public int saveHuiYuanUserByOID(P2pHuiYuanUser user);

}
