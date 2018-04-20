package com.izhuantou.fund.impl;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.izhuantou.damain.lend.PlanContent;
import com.izhuantou.damain.webp2p.WebP2pNoviceBiddingRuning;
import com.izhuantou.damain.webp2p.WebP2pPackageBiddingMainRuning;
import com.izhuantou.dao.webp2p.WebP2pNoviceBiddingRuningMapper;
import com.izhuantou.dao.webp2p.WebP2pPackageBiddingMainRuningMapper;
import com.izhuantou.fund.api.ControlNoviceBidding;
import com.izhuantou.fund.api.ControlPackageBiddingMainRuning;
import com.izhuantou.fund.api.ProcessPageBidding;

/**
 * 投标相关接口的实现类
 *
 * @author fucheng
 * @date 2018-03-06
 */
@Service("processPageBidding")
public class ProcessPageBiddingImpl implements ProcessPageBidding {

    @Autowired
    private ControlNoviceBidding controlNoviceBidding;
    @Autowired
    private ControlPackageBiddingMainRuning controlPackageBiddingMainRuning;
    @Autowired
    private WebP2pNoviceBiddingRuningMapper noviceBiddingRuningMapper;
    @Autowired
    private WebP2pPackageBiddingMainRuningMapper packageBiddingMainRuningMapper;

    @Override
    public String ProcessPageChujiesave(Map<String, Object> map) {
	// 通过相关的type的类型进行具体的业务操作
	try {
	    String type = (String) map.get("type"); // 标的类型
	    String biddingOID = (String) map.get("biddingOID"); // 标的OID
	    String memberOID = (String) map.get("memberOID");
	    String tqOID = (String) map.get("tqOID");
	    String mappingOID = (String) map.get("mappingOID");
	    BigDecimal amount = new BigDecimal((String) map.get("amount"));
	    String laiyuan = (String) map.get("laiyuan");
	    // String type = (String) map.get("type"); // 标的类型
	    // String biddingOID = (String) map.get("biddingOID"); // 标的OID

	    if ("dd".equals(type)) {
		// 点点
	    } else if ("new".equals(type)) {
		// 新手
		WebP2pNoviceBiddingRuning noviceBid = this.noviceBiddingRuningMapper.findByOID(biddingOID);
		if (noviceBid != null && noviceBid.getProductStatus().equals("1")) {
		    // 表示可以投标
		    Map<String, Object> typeMap = new HashMap<>();
		    // 参数封装
		    typeMap.put("memberOID", memberOID);
		    typeMap.put("biddingOID", biddingOID);
		    typeMap.put("amount", amount);
		    typeMap.put("tqOID", tqOID);
		    typeMap.put("laiyuan", laiyuan);
		    typeMap.put("mappingOID", mappingOID);
		    if (this.controlNoviceBidding.bidNomalBidding(typeMap) == 1) {
			System.out.println("======新手出借成功========");
		    } else {
			System.out.println("======新手出借失败========");
		    }

		} else {
		    // 表示已经停标
		    System.out.println("========已经停标========");
		}
	    } else if ("hh".equals(type)) {
		// 环环
		WebP2pPackageBiddingMainRuning mr = packageBiddingMainRuningMapper.findByOID(biddingOID);

		if ("1".equals(mr.getProductStatus())) {
		    // memberOID, biddingOID, amount, tqOID, laiyuan, mappingOID
		    // 20171108因以租代购标的新建出借方法--Cannon
		    Map<String, Object> typeMap = new HashMap<>();
		    typeMap.put("memberOID", map.get("memberOID"));
		    typeMap.put("biddingOID", biddingOID);
		    typeMap.put("amount", map.get("amount"));
		    typeMap.put("tqOID", map.get("tqOID"));
		    typeMap.put("laiyuan", map.get("laiyuan"));
		    typeMap.put("mappingOID", map.get("mappingOID"));
		    typeMap.put("money", new BigDecimal(100));
		    Integer n = controlPackageBiddingMainRuning.bidPackageBiddingDifferent(typeMap);
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
		    System.out.print("======环环投出借成功=====");
		} else {
		    // 提示信息
		    System.out.print("======环环投出借失败=====");
		}
	    } else if ("ZZ".equals(type)) {
		// 转转
	    }

	} catch (Exception e) {
	    System.out.println(e.getMessage());
	}
	return null;
    }
}