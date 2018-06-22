package com.izhuantou.dao.webp2p;

import com.github.abel533.mapper.Mapper;
import com.izhuantou.damain.webp2p.WebP2pLoanApply;

/**
 * 借款申请管理（已注册用户）(网贷系统审批申请匹配小贷数据)
 * 
 * @author dear
 * @version 1.0
 */
public interface WebP2pLoanApplyMapper extends Mapper<WebP2pLoanApply> {

    /**
     * 根据实体插入到数据库中
     * 
     * @param WEBP2P_LoanApply
     */
    public void insertData(WebP2pLoanApply webP2pLoanApply);

    /**
     * 查询WEBP2P_LoanApply表的总记录数
     * 
     * @param memberOID
     * @return
     */
    public Integer findCount(String memberOID);

    /**
     * 根据实体更新数据库
     * 
     * @param WEBP2P_LoanApply
     */
    public void update(WebP2pLoanApply webP2pLoanApply);

    /**
     * 根据OID查询单条数据
     * 
     * @param OID
     * @return
     */
    public WebP2pLoanApply findByOID(String OID);
}
