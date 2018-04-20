package com.izhuantou.fund.api;

import java.util.List;

import com.izhuantou.damain.webp2p.WebP2pPackageBiddingMainContentRuning;

public interface ControlPackageMainContentMapping {

    List<WebP2pPackageBiddingMainContentRuning> gainRealBiddingByMainOID(String mainBiddingOID);

}
