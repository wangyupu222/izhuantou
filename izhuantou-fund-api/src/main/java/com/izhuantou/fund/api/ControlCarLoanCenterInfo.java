package com.izhuantou.fund.api;

import java.util.List;

import com.izhuantou.damain.vo.flow.CarLoanMemberInfoBaseDTO;
import com.izhuantou.damain.webp2p.WebP2pCarLoanCenterInfo;

public interface ControlCarLoanCenterInfo extends BaseService<WebP2pCarLoanCenterInfo> {
    /**
     * 用户汽车信息
     * 
     * @param memberOID
     * @return
     */
    List<CarLoanMemberInfoBaseDTO> gainCarLoanMemberInfoBase(String memberOID);

}
