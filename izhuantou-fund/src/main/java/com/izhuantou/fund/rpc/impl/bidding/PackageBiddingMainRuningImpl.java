package com.izhuantou.fund.rpc.impl.bidding;

import java.math.BigDecimal;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.izhuantou.common.redismq.MessageType;
import com.izhuantou.common.tool.ToolDateTime;
import com.izhuantou.common.utils.JsonUtil;
import com.izhuantou.damain.pay.PayPrivilege;
import com.izhuantou.damain.pay.PayPrivilegeMemberMapping;
import com.izhuantou.damain.pay.PayPrivilegeSpecialps;
import com.izhuantou.damain.vo.bidding.BiddingDTO;
import com.izhuantou.damain.webp2p.WebP2pPackageBiddingMainRuning;
import com.izhuantou.damain.webp2p.WebP2pProductRateInfo;
import com.izhuantou.dao.pay.PayPrivilegeMapper;
import com.izhuantou.dao.pay.PayPrivilegeMemberMappingMapper;
import com.izhuantou.dao.pay.PayPrivilegeSpecialpsMapper;
import com.izhuantou.dao.webp2p.WebP2pPackageBiddingMainRuningMapper;
import com.izhuantou.dao.webp2p.WebP2pProductRateInfoMapper;
import com.izhuantou.fund.rpc.api.bidding.BiddingCashFreeze;
import com.izhuantou.fund.rpc.api.bidding.PackageBiddingMainRuning;
import com.izhuantou.fund.rpc.impl.BaseServiceImpl;
import com.koomii.redismq.Message;
import com.koomii.redismq.RedisMQMessageSender;

@Service("packageBiddingMainRuning")
public class PackageBiddingMainRuningImpl extends BaseServiceImpl<WebP2pPackageBiddingMainRuning>
	implements PackageBiddingMainRuning {
    private static final Logger logger = LoggerFactory.getLogger(PackageBiddingMainRuningImpl.class);
    @Autowired
    private BiddingCashFreeze controlDebitCredit;
    @Autowired
    private PayPrivilegeMapper privilegeMapper;
    @Autowired
    private PayPrivilegeMemberMappingMapper privilegeMemberMappingMapper;
    @Autowired
    private PayPrivilegeSpecialpsMapper privilegeSpecialPSMapper;
    @Autowired
    private WebP2pPackageBiddingMainRuningMapper packageBiddingMainRuningMapper;
    @Autowired
    private WebP2pProductRateInfoMapper productRateInfoMapper;
    @Autowired
    private RedisMQMessageSender messageSender;

    @Override
    public String bidPackageBiddingDifferent(BiddingDTO biddto) {
	try {
	    String result = "";
	    if (biddto != null) {
		WebP2pPackageBiddingMainRuning pbm = packageBiddingMainRuningMapper.findByOID(biddto.getBiddingOID());
		WebP2pProductRateInfo pdri = productRateInfoMapper.findByOID(pbm.getProductRateInfoID());
		// 团标产品投标
		BigDecimal privilegePrincipal = new BigDecimal(0);
		BigDecimal privilegeInterest = new BigDecimal(0);
		if (StringUtils.isEmpty(biddto.getTqOID())) {
		    biddto.setBeginDate(ToolDateTime.gainDate());
		    // 将bitdto存入redis
		    result = controlDebitCredit.investment(biddto);
		    if (StringUtils.isNotEmpty(result) && "1".equals(result)) {
				// 将环环标的数据放到redis队列中
				String biddtoStr = JsonUtil.toStr(biddto);
				System.err.println("发送的信息为"+biddtoStr);
				Long mqResult = messageSender.put(new Message(MessageType.HH_BIDDING_MESSAGE, biddtoStr));
				if (mqResult != null) {
				    System.out.println("消息发送成功");
				} else {
				    System.out.println("消息发送失败");
				}
			    }
		} else {
		    PayPrivilege tq = privilegeMapper.findByOID(biddto.getTqOID());
		    if (tq != null) {
			if (StringUtils.isNotEmpty(tq.getPrivilegeType()) && "1".equals(tq.getPrivilegeType())) {
			    // 加息
			    privilegeInterest = tq.getPrivilegeRange();
			} else if (tq.getPrivilegeType() != null && "0".equals(tq.getPrivilegeType())) {
			    // 红包
			    privilegePrincipal = tq.getPrivilegeRange();
			} else {
			    logger.info("特权投标失败");
			    return null;
			}
		    }
		    biddto.setPrivilegeInterest(privilegeInterest);
		    biddto.setPrivilegePrincipal(privilegePrincipal);
		    biddto.setBeginDate(ToolDateTime.gainDate());
		    result = controlDebitCredit.investment(biddto);
		    if (StringUtils.isNotEmpty(result) && "1".equals(result)) {
			// 将环环标的数据放到redis队列中
			String biddtoStr = JsonUtil.toStr(biddto);
			System.err.println("发送的信息为"+biddtoStr);
			Long mqResult = messageSender.put(new Message(MessageType.HH_BIDDING_MESSAGE, biddtoStr));
			if (mqResult != null) {
			    System.out.println("消息发送成功");
			} else {
			    System.out.println("消息发送失败");
			}
		    }
		}
		if (StringUtils.isNotEmpty(result) && "1".equals(result)) {
		    if (StringUtils.isNotEmpty(biddto.getTqOID())) {
			// 如果特权不为空，则需要更改特权使用状态
			if (StringUtils.isNotEmpty(biddto.getMappingOID())) {
			    /**
			     * 更新 传入mappingOID回来，因app旧版本问题，如果mappOID为null，
			     * 需要按tqOID该更改特权状态
			     */
			    PayPrivilegeMemberMapping mapping = privilegeMemberMappingMapper
				    .findByOID(biddto.getMappingOID());
			    mapping.setIsUsed("1");
			    privilegeMemberMappingMapper.updatePrivilegeMemberMapping(mapping);
			    if (StringUtils.isNotEmpty(mapping.getPsOID())) {
				// 如果psOID存在，说明是万能权，需要更改万能权特殊说明表
				PayPrivilegeSpecialps ps = privilegeSpecialPSMapper.findByOID(mapping.getPsOID());
				ps.setProductInfoOID(pdri.getOID());
				ps.setJXDay((int) (pdri.getProductTerm() * 30));
				ps.setIsUsed("1");
				privilegeSpecialPSMapper.updatePrivilegeSpecialPS(ps);
			    }
			} else {
			    // 如果用户同一特权有多张劵，把该特权劵都改为已使用
			    List<PayPrivilegeMemberMapping> d = privilegeMemberMappingMapper
				    .findByCondition(biddto.getTqOID(), biddto.getMemberOID());
			    for (PayPrivilegeMemberMapping dto : d) {
				dto.setIsUsed("1");
				privilegeMemberMappingMapper.updatePrivilegeMemberMapping(dto);
			    }
			}
		    }
		}
	    }
	    return result;
	} catch (Exception e) {
	    logger.error("投标失败" + e.getMessage());
	    return null;
	}

    }
}
