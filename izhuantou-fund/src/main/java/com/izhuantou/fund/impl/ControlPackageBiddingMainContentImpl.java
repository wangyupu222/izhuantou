package com.izhuantou.fund.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.izhuantou.damain.webp2p.WebP2pPackageBiddingMainContentRuning;
import com.izhuantou.fund.api.ControlPackageBiddingMainContent;

/**
 *
 * @author fucheng
 * @date 2018-03-07
 */
@Service("controlPackageBiddingMainContent")
public class ControlPackageBiddingMainContentImpl extends BaseServiceImpl<WebP2pPackageBiddingMainContentRuning>
	implements ControlPackageBiddingMainContent {

    @Override
    public List<WebP2pPackageBiddingMainContentRuning> findByContentAndLoanPTOID(String biddingOID) {
	try {
	    WebP2pPackageBiddingMainContentRuning contentRuning = new WebP2pPackageBiddingMainContentRuning();
	    contentRuning.setOID(biddingOID);
	    return this.queryListByWhere(contentRuning);
	} catch (Exception e) {
	    System.out.println(e.getMessage());
	    return null;
	}
    }

    @Override
    public List<WebP2pPackageBiddingMainContentRuning> findPackageBiddingMainContentRuningByOID(String biddingOID) {
	try {
	    WebP2pPackageBiddingMainContentRuning contentRuning = new WebP2pPackageBiddingMainContentRuning();
	    contentRuning.setOID(biddingOID);
	    return this.queryListByWhere(contentRuning);
	} catch (Exception e) {
	    System.out.println(e.getMessage());
	    return null;
	}
    }

    @Override
    public List<WebP2pPackageBiddingMainContentRuning> findByLoanNum(String bidLoanNumber) {
	try {
	    WebP2pPackageBiddingMainContentRuning contentRuning = new WebP2pPackageBiddingMainContentRuning();
	    contentRuning.setLoanNumber(bidLoanNumber);
	    contentRuning.setFinancetransfertype("normal");
	    return this.queryListByWhere(contentRuning);
	} catch (Exception e) {
	    System.out.println(e.getMessage());
	    return null;
	}
    }

}
