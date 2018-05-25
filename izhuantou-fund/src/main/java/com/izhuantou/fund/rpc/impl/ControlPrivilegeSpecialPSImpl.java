package com.izhuantou.fund.rpc.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.izhuantou.common.tool.ToolDateTime;
import com.izhuantou.common.tool.ToolsDatas;
import com.izhuantou.common.utils.DateUtils;
import com.izhuantou.common.utils.StringUtil;
import com.izhuantou.damain.pay.PayPrivilegeSpecialps;
import com.izhuantou.damain.webp2p.WebP2pPackageBiddingMainRuning;
import com.izhuantou.damain.webp2p.WebP2pProductRateInfo;
import com.izhuantou.dao.PropPrivilegeMapper;
import com.izhuantou.dao.pay.PayPrivilegeSpecialpsMapper;
import com.izhuantou.dao.webp2p.WebP2pPackageBiddingMainRuningMapper;
import com.izhuantou.dao.webp2p.WebP2pProductRateInfoMapper;
import com.izhuantou.fund.rpc.api.ControlPrivilegeSpecialPS;
@Service("controlPrivilegeSpecialPS")
public class ControlPrivilegeSpecialPSImpl extends BaseServiceImpl<PayPrivilegeSpecialps>
		implements ControlPrivilegeSpecialPS {
	@Autowired
	private WebP2pPackageBiddingMainRuningMapper packageBiddingMainRuningMapper;
	@Autowired
	private WebP2pProductRateInfoMapper productRateInfoMapper;
	@Autowired
	private PayPrivilegeSpecialpsMapper payPrivilegeSpecialpsMapper;
	@Autowired
	private PropPrivilegeMapper propPrivilegeMapper;
	private static final Logger logger = LoggerFactory.getLogger(ControlCashPoolImpl.class);
	@Override
	public void savePrivilegePs(String memberOID, BigDecimal amount, String biddingOID) {
		try {
			// 查询标的信息
			WebP2pPackageBiddingMainRuning pbm = packageBiddingMainRuningMapper.findByOID(biddingOID);
					
			WebP2pProductRateInfo pdri =productRateInfoMapper.findByOID(pbm.getProductRateInfoID());
			/*
			 * 计算回款 发送日期
			 */
			Date rdate = ToolDateTime.addMonth(ToolDateTime.gainDate(), (int)pdri.getProductTerm());
			Date returnDate = ToolDateTime.addDay(rdate, 1);
			// 回款日减3天为发劵日
			String sd = ToolsDatas.gainPlusAndDay(DateUtils.formatJustDate(returnDate.getTime()), 2, 1);

			Date sendDate = DateUtils.getJustDate(sd);
			/*
			 * 计算金额
			 */
			BigDecimal lowMoney = amount.multiply(BigDecimal.valueOf(0.8)).setScale(0, BigDecimal.ROUND_HALF_UP);
			/*
			 * 根据金额判断tqOID
			 */
			String tqOID = "";
			Map<String, String>remap=propPrivilegeMapper.findPrivilege();
			amount.compareTo(BigDecimal.valueOf(50000));
			if (amount.compareTo(BigDecimal.valueOf(50000)) == -1) {
				// 如果小于5万
				tqOID = remap.get("SpecialTqOID1");
			}
			if ((amount.compareTo(BigDecimal.valueOf(50000)) == 1 && amount.compareTo(BigDecimal.valueOf(100000)) == -1)
					|| amount.compareTo(BigDecimal.valueOf(50000)) == 0) {
				// 如果大于5万并且小于10万，或者是出借5万
				tqOID =  remap.get("SpecialTqOID2");
			}
			if (amount.compareTo(BigDecimal.valueOf(100000)) == 1
					|| amount.compareTo(BigDecimal.valueOf(100000)) == 0) {
				// 如果大于10万，或者正好出借10万
				tqOID =remap.get("SpecialTqOID3");
			}
			PayPrivilegeSpecialps special = new PayPrivilegeSpecialps();
			special.setOID(StringUtil.getUUID());
			special.setMemberOID(memberOID);
			special.setPrivilegeOID(tqOID);
			special.setMoney(amount);
			special.setLowMoney(lowMoney);
			special.setReturnDate(returnDate);
			special.setSendDate(sendDate);
			special.setSendType("0");
			special.setIsUsed("0");
			payPrivilegeSpecialpsMapper.savePrivilegeSpecialPS(special);
		} catch (Exception e) {
			logger.error("savePrivilegePs(String memberOID, BigDecimal amount, String biddingOID)"+e.getMessage());
		}
	}
}
