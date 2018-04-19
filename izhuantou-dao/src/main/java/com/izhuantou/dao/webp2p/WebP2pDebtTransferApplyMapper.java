package com.izhuantou.dao.webp2p;

import java.math.BigDecimal;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.github.abel533.mapper.Mapper;
import com.izhuantou.damain.vo.MylendZQWCDaoDTO;
import com.izhuantou.damain.webp2p.WebP2pDebtTransferApply;

/**
 * 债转申请管理表
 * 
 * @author dear
 * @version 1.0
 */
public interface WebP2pDebtTransferApplyMapper extends Mapper<WebP2pDebtTransferApply> {

    /**
     * 根据memberOID 和审核状态查询 转让记录
     * 
     * @param memberOID
     * @param status
     * @return
     */
    public List<MylendZQWCDaoDTO> findByMemberAndstatus(@Param("memberOID") String memberOID,
	    @Param("startIndex") Integer startIndex, @Param("pageSize") Integer pageSize);

    /**
     * 查询债权转让记录总数
     * 
     * @param memberOID
     * @return
     */
    public int getZQJLRowCount(String memberOID);

    /**
     * 根据资金池oID查看
     * 
     * @return
     */
    public List<WebP2pDebtTransferApply> findBydebitCreditOID(String debitCreditOID);

    /**
     * 保存DebtTransferApply记录
     * 
     * @return
     */
    public int saveDebtTransferApply(WebP2pDebtTransferApply debtTransferApply);

    /**
     * 根据债转借贷关系OID 和钱数 利率查询
     * 
     * @param debitCreditOID
     * @param money
     * @param creditRate
     * @return
     */
    public WebP2pDebtTransferApply findByBusinessOIDMoneyCreditRate(@Param("debitCreditOID") String debitCreditOID,
	    @Param("bdjg") BigDecimal bdjg, @Param("creditRate") BigDecimal nhll);

}
