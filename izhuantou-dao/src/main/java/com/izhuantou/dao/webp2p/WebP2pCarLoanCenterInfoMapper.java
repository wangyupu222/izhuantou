package com.izhuantou.dao.webp2p;

import java.util.List;

import com.github.abel533.mapper.Mapper;
import com.izhuantou.damain.vo.flow.CarLoanMemberInfoBaseDTO;
import com.izhuantou.damain.webp2p.WebP2pCarLoanCenterInfo;

/**
 * 已租贷购详细信息
 * 
 * @author yangbosen
 * @version 1.0
 */
public interface WebP2pCarLoanCenterInfoMapper extends Mapper<WebP2pCarLoanCenterInfo> {

    public WebP2pCarLoanCenterInfo findByloanNumber(String loanNumber);

    /**
     * 查看用户车辆基本的信息
     * 
     * @param memberOID
     * @return
     */
    public List<CarLoanMemberInfoBaseDTO> findCarLoanMemberInfoBase(String memberOID);

}
