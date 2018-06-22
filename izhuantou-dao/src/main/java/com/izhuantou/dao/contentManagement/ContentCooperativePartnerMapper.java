package com.izhuantou.dao.contentManagement;

import java.util.List;

import com.github.abel533.mapper.Mapper;
import com.izhuantou.damain.p2p.P2pCooperativePartner;

/**
 * 内容管理-合作伙伴
 * 
 * @author liyang
 * @version 1.0
 */
public interface ContentCooperativePartnerMapper extends Mapper<P2pCooperativePartner> {
    
	/**
     * 查询出所有的合作伙伴
     * @return
     */
    public List<P2pCooperativePartner> findCooperativePartner();
    
    /**
     * 根据OID查询合作伙伴内容
     * @param OID 合作伙伴oid
     * @return
     */
    public P2pCooperativePartner findCooperativePartnerByOID(String OID);
    
    /**
     * 添加一条合作伙伴
     * @param P2pCooperativePartner 合作伙伴bean
     * @return 
     */
    public int addCooperativePartner(P2pCooperativePartner p2pCooperativePartner);
    
    /**
     * 修改一条合作伙伴
     * @param P2pCooperativePartner 合作伙伴bean
     * @return 
     */
    public int updateCooperativePartner(P2pCooperativePartner p2pCooperativePartner);
    
    /**
     * 删除一条合作伙伴
     * @param OID 合作伙伴oid
     * @return
     */
    public int deleteCooperativePartner(String OID);
}
