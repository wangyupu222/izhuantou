package com.izhuantou.service.impl.lend;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.izhuantou.damain.lend.DDTPrivilege;
import com.izhuantou.damain.lend.HHTPrivilege;
import com.izhuantou.damain.pay.PayPrivilege;
import com.izhuantou.damain.pay.PayPrivilegeMemberMapping;
import com.izhuantou.damain.pay.PayPrivilegeProductMapping;
import com.izhuantou.damain.pay.PayPrivilegeSpecialps;
import com.izhuantou.damain.webp2p.WebP2pNormalBiddingRuning;
import com.izhuantou.damain.webp2p.WebP2pPackageBiddingMainRuning;
import com.izhuantou.damain.webp2p.WebP2pProductRateInfo;
import com.izhuantou.dao.lend.HHTPrivilegeMapper;
import com.izhuantou.dao.pay.PayPrivilegeMapper;
import com.izhuantou.dao.pay.PayPrivilegeMemberMappingMapper;
import com.izhuantou.dao.pay.PayPrivilegeProductMappingMapper;
import com.izhuantou.dao.pay.PayPrivilegeSpecialpsMapper;
import com.izhuantou.dao.webp2p.WebP2pNormalBiddingRuningMapper;
import com.izhuantou.dao.webp2p.WebP2pPackageBiddingMainRuningMapper;
import com.izhuantou.dao.webp2p.WebP2pProductRateInfoMapper;
import com.izhuantou.service.api.lend.PrivilegeService;

@Service("privilegeService")
public class PrivilegeServiceImpl implements PrivilegeService {

    private static final Logger logger = LoggerFactory.getLogger(PrivilegeServiceImpl.class);
    @Autowired
    private HHTPrivilegeMapper hhtPrivilegeMapper;

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
    private WebP2pNormalBiddingRuningMapper normalBiddingRuningMapper;

    @Autowired
    private PayPrivilegeProductMappingMapper privilegeProductMappingMapper;

    public List<HHTPrivilege> findPrivilege(String OID, String memberOID) {
	List<HHTPrivilege> hhtPrivilegeList = new ArrayList<HHTPrivilege>();
	List<HHTPrivilege> hhtPrivilegeListResult = new ArrayList<HHTPrivilege>();
	PayPrivilege privilege = new PayPrivilege();
	PayPrivilegeMemberMapping privilegeMemberMapping = new PayPrivilegeMemberMapping();
	List<PayPrivilegeMemberMapping> privilegeMemberMappingList = new ArrayList<>();
	PayPrivilegeSpecialps privilegeSpecialPS = new PayPrivilegeSpecialps();
	WebP2pPackageBiddingMainRuning packageBiddingMainRuning = new WebP2pPackageBiddingMainRuning();
	WebP2pProductRateInfo productRateInfo = new WebP2pProductRateInfo();

	try {
	    packageBiddingMainRuning = packageBiddingMainRuningMapper.findByOID(OID);
	    productRateInfo = productRateInfoMapper.findByOID(packageBiddingMainRuning.getProductRateInfoID());

	    hhtPrivilegeList = hhtPrivilegeMapper.findByOID(OID);

	    for (HHTPrivilege hhtPrivilege : hhtPrivilegeList) {
		String tqOID = hhtPrivilege.getOID();

		privilege = privilegeMapper.findByOID(tqOID);
		String privilegeName = (String) privilege.getPrivilegeName();
		BigDecimal lowAmount = (BigDecimal) privilege.getLowAmount();
		BigDecimal privilegeRange = (BigDecimal) privilege.getPrivilegeRange();
		int privilegeTerm = (int) privilege.getPrivilegeTerm();
		String privilegeType = privilege.getPrivilegeType();
		int rule = 0;
		String ruleIntroduce = "";
		if (privilege.getRule() != null) {
		    rule = Integer.valueOf(privilege.getRule());
		}
		if (privilege.getRuleIntroduce() != null) {
		    ruleIntroduce = privilege.getRuleIntroduce();
		}
		privilegeMemberMappingList = privilegeMemberMappingMapper.findByCondition(tqOID, memberOID);
		if (privilegeMemberMappingList == null) {
		    break;
		} else {
		    for (PayPrivilegeMemberMapping pay_PrivilegeMemberMapping : privilegeMemberMappingList) {
			// 环环投加息券实体类
			HHTPrivilege hhtPrivilegeResult = new HHTPrivilege();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			String nowTime = sdf.format(new Date());

			String psOID = pay_PrivilegeMemberMapping.getPsOID();

			if (psOID != null && !psOID.trim().equals("")) {
			    privilegeSpecialPS = privilegeSpecialPSMapper.findByOID(psOID);
			    lowAmount = privilegeSpecialPS.getLowMoney();
			    privilegeTerm = (int) (productRateInfo.getProductTerm() * 30);
			}

			hhtPrivilegeResult.setOID(pay_PrivilegeMemberMapping.getOID());
			hhtPrivilegeResult.setPrivilegeName(privilegeName);
			hhtPrivilegeResult.setPrivilegeTerm(String.valueOf(privilegeTerm));
			hhtPrivilegeResult.setPrivilegeType(privilegeType);
			hhtPrivilegeResult.setLowAmount(String.valueOf(lowAmount));
			hhtPrivilegeResult.setPrivilegeRange(String.valueOf(privilegeRange));
			hhtPrivilegeResult.setRule(String.valueOf(rule));
			hhtPrivilegeResult.setRuleIntroduce(ruleIntroduce);

			hhtPrivilegeResult.setPrivilegeOID(pay_PrivilegeMemberMapping.getPrivilegeOID());
			hhtPrivilegeResult.setMappingOID(pay_PrivilegeMemberMapping.getOID());
			SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
			String jsDate = sdf2.format(pay_PrivilegeMemberMapping.getEndDate());
			String ksDate = sdf2.format(pay_PrivilegeMemberMapping.getStartDate());
			hhtPrivilegeResult.setKsDate(ksDate);
			hhtPrivilegeResult.setJsDate(jsDate);
			String jsDate1 = "2017-11-11";
			int s = compare_date2(jsDate1, nowTime);
			if (compare_date2(jsDate, nowTime) >= 0 && compare_date2(ksDate, nowTime) <= 0) {
			    hhtPrivilegeListResult.add(hhtPrivilegeResult);
			}
		    }

		}
	    }
	    return hhtPrivilegeListResult;

	} catch (Exception e) {
	    e.printStackTrace();
	    logger.error("findByOID()", e.getMessage());
	    return null;
	}

    }

    @Override
    public List<DDTPrivilege> findDDTPrivilege(String OID, String memberOID) {
	List<DDTPrivilege> ddtPrivilegeListResult = new ArrayList<>();
	DDTPrivilege ddtPrivilegeResult = new DDTPrivilege();
	List<PayPrivilegeMemberMapping> privilegeMemberMappingList = new ArrayList<>();
	WebP2pNormalBiddingRuning normalBiddingRuning = new WebP2pNormalBiddingRuning();
	List<PayPrivilegeProductMapping> privilegeProductMapping = new ArrayList<PayPrivilegeProductMapping>();

	try {
	    PayPrivilege privilege = new PayPrivilege();
	    normalBiddingRuning = normalBiddingRuningMapper.findByOID(OID);
	    privilegeProductMapping = privilegeProductMappingMapper
		    .findByOID(normalBiddingRuning.getProductRateInfoID());
	    for (PayPrivilegeProductMapping payPrivilegeProductMapping : privilegeProductMapping) {

		String tqOID = payPrivilegeProductMapping.getPrivilegeOID();
		privilege = privilegeMapper.findByOID(tqOID);
		String privilegeName = privilege.getPrivilegeName();
		BigDecimal lowAmount = privilege.getLowAmount();
		BigDecimal privilegeRange = privilege.getPrivilegeRange();
		int privilegeTerm = privilege.getPrivilegeTerm();
		String privilegeType = privilege.getPrivilegeType();
		int rule = 0;
		String ruleIntroduce = "";
		if (privilege.getRule() != null) {
		    rule = Integer.valueOf(privilege.getRule());
		}
		if (privilege.getRuleIntroduce() != null) {
		    ruleIntroduce = (String) privilege.getRuleIntroduce();
		}
		privilegeMemberMappingList = privilegeMemberMappingMapper.findByCondition(tqOID, memberOID);
		if (privilegeMemberMappingList == null) {
		    break;
		} else {
		    for (PayPrivilegeMemberMapping privilegeMemberMapping : privilegeMemberMappingList) {
			// 点点投加息券实体类
			DDTPrivilege ddtPrivilegeResult1 = new DDTPrivilege();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			String nowTime = sdf.format(new Date());

			String jsDate = sdf.format(privilegeMemberMapping.getEndDate());
			String ksDate = sdf.format(privilegeMemberMapping.getStartDate());

			ddtPrivilegeResult1.setMappingOID(privilegeMemberMapping.getOID());
			ddtPrivilegeResult1.setPrivilegeName(privilegeName);
			ddtPrivilegeResult1.setPrivilegeTerm(privilegeTerm);
			ddtPrivilegeResult1.setPrivilegeType(privilegeType);
			ddtPrivilegeResult1.setLowAmount(lowAmount);
			ddtPrivilegeResult1.setPrivilegeRange(privilegeRange);
			ddtPrivilegeResult1.setRule(rule);
			ddtPrivilegeResult1.setRuleIntroduce(ruleIntroduce);

			ddtPrivilegeResult1.setPrivilegeOID(privilegeMemberMapping.getPrivilegeOID());
			ddtPrivilegeResult1.setMappingOID(privilegeMemberMapping.getOID());
			ddtPrivilegeResult1.setKsDate(ksDate);
			ddtPrivilegeResult1.setJsDate(jsDate);

			if (compare_date2(jsDate, nowTime) >= 0 && compare_date2(ksDate, nowTime) <= 0) {
			    ddtPrivilegeListResult.add(ddtPrivilegeResult1);
			}
		    }
		}

	    }
	    return ddtPrivilegeListResult;
	} catch (Exception e) {
	    e.printStackTrace();
	    logger.error("findByOID()", e.getMessage());
	    return null;
	}

    }

    public static int compare_date2(String DATE1, String DATE2) {

	DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	try {
	    Date dt1 = df.parse(DATE1);
	    Date dt2 = df.parse(DATE2);
	    if (dt1.getTime() > dt2.getTime()) {
		return 1;
	    } else if (dt1.getTime() < dt2.getTime()) {
		return -1;
	    } else {
		return 0;
	    }
	} catch (Exception exception) {
	    exception.printStackTrace();
	}
	return 0;
    }

}
