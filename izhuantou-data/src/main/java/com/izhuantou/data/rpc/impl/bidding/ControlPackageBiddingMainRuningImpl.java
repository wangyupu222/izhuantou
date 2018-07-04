package com.izhuantou.data.rpc.impl.bidding;

import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.izhuantou.common.utils.StringUtil;
import com.izhuantou.damain.pay.PayCashPool;
import com.izhuantou.damain.vo.bidding.BiddingDTO;
import com.izhuantou.damain.webp2p.WebP2pPackageBiddingMainRuning;
import com.izhuantou.dao.webp2p.WebP2pPackageBiddingMainRuningMapper;
import com.izhuantou.data.rpc.api.bidding.ControlDebitCredit;
import com.izhuantou.data.rpc.api.bidding.ControlPackageBiddingMainRuning;
import com.izhuantou.data.rpc.impl.BaseServiceImpl;
import com.izhuantou.fund.rpc.api.ProcessPageFinish;
import com.izhuantou.third.rpc.api.memberagrement.MemberMemberAgreementService;

@Service("ControlPackageBiddingMainRuning")
public class ControlPackageBiddingMainRuningImpl extends BaseServiceImpl<WebP2pPackageBiddingMainRuning>
		implements ControlPackageBiddingMainRuning {
	private static final Logger logger = LoggerFactory.getLogger(ControlPackageBiddingMainRuningImpl.class);
	@Autowired
	private ControlDebitCredit controlDebitCredit;
	@Autowired
	private WebP2pPackageBiddingMainRuningMapper packageBiddingMainRuningMapper;
	@Autowired
	private MemberMemberAgreementService memberAgreementService;
	@Autowired
	private ProcessPageFinish processPageFinish;

	@Override
	public String bidPackageBiddingDifferent(BiddingDTO biddto) {
		try {
			if (biddto != null) {
				WebP2pPackageBiddingMainRuning pbm = packageBiddingMainRuningMapper.findByOID(biddto.getBiddingOID());
				// 团标产品投标
				PayCashPool aDto = new PayCashPool();

				aDto = controlDebitCredit.investment(biddto);

				memberAgreementService.gainMemberHHTAgreement(biddto.getMemberOID(), "4", biddto.getBiddingOID(),
						aDto.getOID());
				BigDecimal ytje = pbm.getHoldingAmount();// 已投金额
				BigDecimal newytje = ytje.add(biddto.getAmount());
				pbm.setHoldingAmount(newytje);
				// 更新信息
				packageBiddingMainRuningMapper.updatePackageBiddingMainRuning(pbm);

				// 调用fund系统中的环环投标流程
				processPageFinish.packageBiddingMainRuningMatching(biddto.getLaiyuan());

				BigDecimal zero = new BigDecimal(0);
				BigDecimal xe = pbm.getSxAmount();
				BigDecimal yt = pbm.getHoldingAmount();
				if (xe.compareTo(zero) != 0) {
					// 如果已投大于限额将产品停标
					if (yt.compareTo(xe) >= 0) {
						pbm.setProductStatus("10");
						packageBiddingMainRuningMapper.updatePackageBiddingMainRuning(pbm);
					}
				}
				return "1";
			}
			return null;
		} catch (Exception e) {
			logger.error("投标失败" + e.getMessage());
			return null;
		}

	}

	@Override
	public String bidPackageBiddingRed(BiddingDTO biddto) {
		try {
			if (biddto != null) {
				WebP2pPackageBiddingMainRuning pbm = packageBiddingMainRuningMapper.findByOID(biddto.getBiddingOID());
				// 团标产品投标
				PayCashPool aDto = new PayCashPool();
				if (StringUtil.isEmpty(biddto.getRedOID())) {
					aDto = controlDebitCredit.investment(biddto);
				} else {
					aDto = controlDebitCredit.investmentRed(biddto);
				}
				memberAgreementService.gainMemberHHTAgreement(biddto.getMemberOID(), "4", biddto.getBiddingOID(),
						aDto.getOID()); 
				BigDecimal ytje = pbm.getHoldingAmount();// 已投金额
				BigDecimal newytje = ytje.add(biddto.getAmount());
				pbm.setHoldingAmount(newytje);
				// 更新信息
				packageBiddingMainRuningMapper.updatePackageBiddingMainRuning(pbm);
				// 调用fund系统中的环环投标流程
				processPageFinish.packageBiddingMainRuningMatching(biddto.getLaiyuan());
				BigDecimal zero = new BigDecimal(0);
				BigDecimal xe = pbm.getSxAmount();
				BigDecimal yt = pbm.getHoldingAmount();
				if (xe.compareTo(zero) != 0) {
					// 如果已投大于限额将产品停标
					if (yt.compareTo(xe) >= 0) {
						pbm.setProductStatus("10");
						packageBiddingMainRuningMapper.updatePackageBiddingMainRuning(pbm);
					}
				}
				return "1";
			}
			return null;
		} catch (Exception e) {
			logger.error("投标失败" + e.getMessage());
			return null;
		}
	}

	@Override
	public String bidPackageBiddingRedAndPrivilege(BiddingDTO biddto) {
		try {
			if (biddto != null){
				WebP2pPackageBiddingMainRuning pbm = packageBiddingMainRuningMapper.findByOID(biddto.getBiddingOID());
				// 团标产品投标
				PayCashPool aDto = new PayCashPool();
				if (StringUtil.isEmpty(biddto.getRedOID()) || StringUtil.isEmpty(biddto.getTqOID())){
					aDto = controlDebitCredit.investment(biddto);
				} else {
					aDto = controlDebitCredit.investmentRedAndPrivilege(biddto);
				}
				memberAgreementService.gainMemberHHTAgreement(biddto.getMemberOID(), "4", biddto.getBiddingOID(),
						aDto.getOID());
				BigDecimal ytje = pbm.getHoldingAmount();// 已投金额
				BigDecimal newytje = ytje.add(biddto.getAmount());
				pbm.setHoldingAmount(newytje);
				// 更新信息
				packageBiddingMainRuningMapper.updatePackageBiddingMainRuning(pbm);
				// 调用fund系统中的环环投标流程
				processPageFinish.packageBiddingMainRuningMatching(biddto.getLaiyuan());
				BigDecimal zero = new BigDecimal(0);
				BigDecimal xe = pbm.getSxAmount();
				BigDecimal yt = pbm.getHoldingAmount();
				if (xe.compareTo(zero) != 0) {
					// 如果已投大于限额将产品停标
					if (yt.compareTo(xe) >= 0) {
						pbm.setProductStatus("10");
						packageBiddingMainRuningMapper.updatePackageBiddingMainRuning(pbm);
					}
				}
				return "1";
			}
			return null;
		} catch (Exception e) {
			logger.error("投标失败" + e.getMessage());
			return null;
		}
	}

}
