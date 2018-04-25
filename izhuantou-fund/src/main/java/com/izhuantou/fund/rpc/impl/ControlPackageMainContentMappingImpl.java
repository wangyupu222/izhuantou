package com.izhuantou.fund.rpc.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.izhuantou.damain.webp2p.WebP2pPackageBiddingMainContentRuning;
import com.izhuantou.dao.webp2p.WebP2pPackageBiddingMainContentRuningMapper;
import com.izhuantou.fund.rpc.api.ControlPackageMainContentMapping;

@Service("controlPackageMainContentMapping")
public class ControlPackageMainContentMappingImpl implements ControlPackageMainContentMapping {

    @Autowired
    private WebP2pPackageBiddingMainContentRuningMapper packageBiddingMainContentRuningMapper;

    @Override
    public List<WebP2pPackageBiddingMainContentRuning> gainRealBiddingByMainOID(String mainBiddingOID) {
	try {
	    return packageBiddingMainContentRuningMapper.findRealBiddingByMainOID(mainBiddingOID);

	} catch (Exception e) {
	    System.out.println(e.getMessage());
	    return null;
	}

    }

}
