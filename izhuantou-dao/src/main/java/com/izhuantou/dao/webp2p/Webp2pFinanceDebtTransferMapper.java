package com.izhuantou.dao.webp2p;

import com.github.abel533.mapper.Mapper;
import com.izhuantou.damain.webp2p.Webp2pFinanceDebtTransfer;

/**
 *财务自动债转表
 * 
 * @author dear
 * @version 1.0
 */
public interface Webp2pFinanceDebtTransferMapper extends Mapper<Webp2pFinanceDebtTransfer> {
	/**
	 * 查询
	 * @param OID
	 * @return
	 */
    Webp2pFinanceDebtTransfer findByOID(String OID);
    /**
     * 更新
     * @param transfer
     * @return
     */
    int updateFinanceDebt(Webp2pFinanceDebtTransfer transfer);
    
}
