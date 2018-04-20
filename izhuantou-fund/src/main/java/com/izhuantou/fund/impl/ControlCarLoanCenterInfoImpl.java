package com.izhuantou.fund.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.izhuantou.damain.vo.flow.CarLoanMemberInfoBaseDTO;
import com.izhuantou.damain.webp2p.WebP2pCarLoanCenterInfo;
import com.izhuantou.dao.webp2p.WebP2pCarLoanCenterInfoMapper;
import com.izhuantou.fund.api.ControlCarLoanCenterInfo;

@Service("controlCarLoanCenterInfo")
public class ControlCarLoanCenterInfoImpl extends BaseServiceImpl<WebP2pCarLoanCenterInfo>
	implements ControlCarLoanCenterInfo {

    @Autowired
    private WebP2pCarLoanCenterInfoMapper carLoanCenterInfoMapper;

    @Override
    public List<CarLoanMemberInfoBaseDTO> gainCarLoanMemberInfoBase(String memberOID) {
	try {
	    return this.carLoanCenterInfoMapper.findCarLoanMemberInfoBase(memberOID);
	} catch (Exception e) {
	    System.out.println(e.getMessage());
	    return null;
	}
    }

}
