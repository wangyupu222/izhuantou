package com.izhuantou.fund.rpc.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.izhuantou.damain.lend.PlanContent;
import com.izhuantou.damain.vo.bidding.BiddingDTO;
import com.izhuantou.damain.webp2p.WebP2pNoviceBiddingRuning;
import com.izhuantou.damain.webp2p.WebP2pPackageBiddingMainRuning;
import com.izhuantou.dao.webp2p.WebP2pNoviceBiddingRuningMapper;
import com.izhuantou.dao.webp2p.WebP2pPackageBiddingMainRuningMapper;
import com.izhuantou.fund.rpc.api.ControlNoviceBidding;
import com.izhuantou.fund.rpc.api.ControlPackageBiddingMainRuning;
import com.izhuantou.fund.rpc.api.ProcessPageBidding;

/**
 * 投标相关接口的实现类
 *
 * @author fucheng
 * @date 2018-03-06
 */
@Service("processPageBidding")
public class ProcessPageBiddingImpl implements ProcessPageBidding {
    private static final Logger logger = LoggerFactory.getLogger(ProcessPageBiddingImpl.class);
    @Autowired
    private ControlNoviceBidding controlNoviceBidding;
    @Autowired
    private ControlPackageBiddingMainRuning controlPackageBiddingMainRuning;
    @Autowired
    private WebP2pNoviceBiddingRuningMapper noviceBiddingRuningMapper;
    @Autowired
    private WebP2pPackageBiddingMainRuningMapper packageBiddingMainRuningMapper;

    @Override
    public String ProcessPageNewChujiesave(BiddingDTO biddto) {
	try {
	    if (biddto != null) {
		WebP2pNoviceBiddingRuning noviceBid = this.noviceBiddingRuningMapper.findByOID(biddto.getBiddingOID());
		if (noviceBid != null && "1".equals(noviceBid.getProductStatus())) {
		    String res = this.controlNoviceBidding.bidNoviceBidding(biddto);
		    if ("1".equals(res)) {
			logger.info("======新手出借成功========");
			return "1";
		    } else {
			logger.info("======新手出借失败========" + res);
			return "0";
		    }
		} else {
		    logger.info("========已经停标========");
		}
	    }
	    logger.info("======新手出借失败========");
	    return "0";
	} catch (Exception e) {
	    logger.error("ProcessPageNewChujiesave(BiddingDTO biddto)" + e.getMessage());
	    return null;
	}
    }

    @Override
    public String ProcessPageHHChujiesave(BiddingDTO biddto) {
	try {
	    if (biddto != null) {
		// 环环
		WebP2pPackageBiddingMainRuning mr = packageBiddingMainRuningMapper.findByOID(biddto.getBiddingOID());

		if (mr != null && "1".equals(mr.getProductStatus())) {
		    // 耗时太久。需要优化
		    String n = controlPackageBiddingMainRuning.bidPackageBiddingDifferent(biddto);
		    if (StringUtils.isNotEmpty(n) && "1".equals(n)) {
			logger.info("======环环投出借成功=====");
			return "1";

		    }
		    // 获取线程状态
		    // DTO d =
		    // controlPlanContent.gainPlanByClassName("cn.com.hoonsoft.WebP2P.PrivilegeMemberReturnSend");
		    PlanContent d = new PlanContent();
		    /*
		     * if (d != null) { String state = (String) d.getState(); if
		     * (state.equals("start")) {
		     * this.controlPrivilegeSpecialPS.savePrivilegePs((String)
		     * map.get("memberOID"), (BigDecimal) map.get("amount"),
		     * (String) map.get("biddingOID")); } }
		     */
		    // 提示信息
		} else {
		    // 提示信息
		    logger.info("======环环投出借失败=====");
		}
	    }
	    return null;
	} catch (Exception e) {
	    logger.error("ProcessPageHHChujiesave(BiddingDTO biddto)" + e.getMessage());
	    return null;
	}
    }

    @Override
    public String ProcessPageDDChujiesave(BiddingDTO biddto) {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public String ProcessPageZZChujiesave(BiddingDTO biddto) {
	// TODO Auto-generated method stub
	return null;
    }
}