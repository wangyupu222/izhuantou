package com.izhuantou.data.rpc.impl.bidding;

import java.math.BigDecimal;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.izhuantou.common.tool.ToolDateTime;
import com.izhuantou.common.tool.ToolsDatas;
import com.izhuantou.common.utils.DateUtils;
import com.izhuantou.damain.pay.PayDebitCredit;
import com.izhuantou.damain.vo.bidding.BiddingDTO;
import com.izhuantou.damain.webp2p.WebP2pNoviceBiddingRuning;
import com.izhuantou.dao.webp2p.WebP2pNoviceBiddingRuningMapper;
import com.izhuantou.data.rpc.api.bidding.ControlDebitCredit;
import com.izhuantou.data.rpc.api.bidding.ControlNoviceBidding;
import com.izhuantou.data.rpc.impl.BaseServiceImpl;
import com.izhuantou.fund.rpc.api.ProcessPageFinish;
import com.izhuantou.third.rpc.api.memberagrement.MemberMemberAgreementService;

/**
 * 新手标的相关实现类
 *
 * @author fucheng
 * @date 2018-03-06
 */
@Service("controlNoviceBidding")
public class ControlNoviceBiddingImpl extends BaseServiceImpl<WebP2pNoviceBiddingRuning>
		implements ControlNoviceBidding {
	private static final Logger logger = LoggerFactory.getLogger(ControlNoviceBiddingImpl.class);

	@Autowired
	private ControlDebitCredit controlDebitCredit;
	@Autowired
	private WebP2pNoviceBiddingRuningMapper noviceBiddingRuningMapper;
	@Autowired
	private MemberMemberAgreementService memberMemberAgreementService;
	@Autowired
	private ProcessPageFinish processPageFinish;

	@Override
	public String bidNoviceBiddingNew(BiddingDTO biddto) {
		try {
			WebP2pNoviceBiddingRuning noviceBid = this.noviceBiddingRuningMapper.findByOID(biddto.getBiddingOID());
			PayDebitCredit debitCredit = new PayDebitCredit();
			debitCredit = controlDebitCredit.addDebitCreditNew(biddto);
			// 出借成功后生成服务协议
			memberMemberAgreementService.gainMemberXSAgreement(biddto.getMemberOID(), "2", biddto.getBiddingOID(),
					debitCredit.getOID());

			// 开始修改标的金额
			BigDecimal bdje = noviceBid.getBiddingAmount(); // 标的总金额
			BigDecimal ytje = noviceBid.getHoldingAmount(); // 已投金额
			BigDecimal newytje = ytje.add(biddto.getAmount());
			noviceBid.setHoldingAmount(newytje); // 修改标的可投金额
			if (bdje.compareTo(newytje) == 0) {
				// 满标
				noviceBid.setProductStatus("2");
				noviceBid.setLoanDay(ToolDateTime.gainDate());
				
				Integer number = biddto.getTerm();
				String eday = ToolsDatas.gainPlusAndReduceDay(ToolDateTime.gainDate().toString(), number, 0);
				Date endday = DateUtils.getDate(eday);
				noviceBid.setEndDay(endday);
				// 调用fund系统的满标流程
				processPageFinish.noviceBiddingFinish(biddto.getBiddingOID());
			}
			noviceBiddingRuningMapper.updateNoviceBiddingRuningByOID(noviceBid);
			return "1";
		} catch (Exception e) {
			logger.error("bidNoviceBidding(BiddingDTO biddto)" + e.getMessage());
			return null;
		}
	}

	@Override
	public String bidNoviceBiddingZz(BiddingDTO biddto) {
		try {

			WebP2pNoviceBiddingRuning noviceBid = this.noviceBiddingRuningMapper.findByOID(biddto.getBiddingOID());
			if (StringUtils.isEmpty(biddto.getTqOID())) {
				controlDebitCredit.addTransferReturnZZ(biddto);
			} else {
				controlDebitCredit.addTransferReturn(biddto);
			}
			BigDecimal bdje = noviceBid.getBiddingAmount();// 标的金额
			BigDecimal ytje = noviceBid.getHoldingAmount();// 已投金额
			BigDecimal newytje = ytje.add(biddto.getAmount());
			noviceBid.setHoldingAmount(newytje); // 更新标的已投金额
			if (bdje.compareTo(newytje) == 0) { // 满标了
				// 满标了
				noviceBid.setProductStatus("2");
				noviceBid.setLoanDay(ToolDateTime.gainDate());
				// 传过去的是标的的金额
				biddto.setAmount(bdje);
				processPageFinish.noviceZzBiddingFinish(biddto);
			
			}
			noviceBiddingRuningMapper.updateNoviceBiddingRuningByOID(noviceBid);
			return "1";
		} catch (Exception e) {
			logger.error("bidNoviceBidding(BiddingDTO biddto)" + e.getMessage());
			return null;
		}
	}

	
	
}
