package com.izhuantou.fund.rpc.impl.bidding;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.izhuantou.damain.vo.bidding.BiddingDTO;
import com.izhuantou.damain.webp2p.WebP2pNoviceBiddingRuning;
import com.izhuantou.damain.webp2p.WebP2pPackageBiddingMainRuning;
import com.izhuantou.dao.webp2p.WebP2pNoviceBiddingRuningMapper;
import com.izhuantou.dao.webp2p.WebP2pPackageBiddingMainRuningMapper;
import com.izhuantou.fund.rpc.api.bidding.NoviceBidding;
import com.izhuantou.fund.rpc.api.bidding.PackageBiddingMainRuning;
import com.izhuantou.fund.rpc.api.bidding.ProcessPageBidding;

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
	private NoviceBidding controlNoviceBidding;
	@Autowired
	private PackageBiddingMainRuning controlPackageBiddingMainRuning;
	@Autowired
	private WebP2pNoviceBiddingRuningMapper noviceBiddingRuningMapper;
	@Autowired
	private WebP2pPackageBiddingMainRuningMapper packageBiddingMainRuningMapper;

	@Override
	public String ProcessPageNewChujiesave(BiddingDTO biddto) {
		try {
			if (biddto != null) {
				// TODO 读取redis
				String res="";
				WebP2pNoviceBiddingRuning noviceBid = this.noviceBiddingRuningMapper.findByOID(biddto.getBiddingOID());
				if (noviceBid != null && "1".equals(noviceBid.getProductStatus())) {
					res = this.controlNoviceBidding.bidNoviceBidding(biddto);
					if (StringUtils.isNotEmpty(res)&&"1".equals(res)) {
						logger.info("======新手出借成功========");
						//将参数存入redis
						return res;
					} else {
						logger.info("======新手出借失败========" + res);
						return res;
					}
				}
			}
		} catch (Exception e) {
			logger.error("ProcessPageNewChujiesave(BiddingDTO biddto)" + e.getMessage());
		}
		return null;
	}

	@Override
	public String ProcessPageHHChujiesave(BiddingDTO biddto) {
		try {
			String result="";
			if (biddto != null) {
				// 环环
				WebP2pPackageBiddingMainRuning mainrun = packageBiddingMainRuningMapper.findByOID(biddto.getBiddingOID());
				if (mainrun != null && "1".equals(mainrun.getProductStatus())) {
					result = controlPackageBiddingMainRuning.bidPackageBiddingDifferent(biddto);
					if (StringUtils.isNotEmpty(result) && "1".equals(result)) {
						logger.info("======环环投出借成功=====");
						return result;
					}
					// 获取线程状态
					// DTO d =
					// controlPlanContent.gainPlanByClassName("cn.com.hoonsoft.WebP2P.PrivilegeMemberReturnSend");
					//PlanContent d = new PlanContent();
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
			return result;
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