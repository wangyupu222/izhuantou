package com.izhuantou.service.impl.mobile.mobilePersonalCenter;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.izhuantou.common.utils.DateUtils;
import com.izhuantou.damain.mobile.personalCenter.myLendDetial.MobileDDTAllInfo;
import com.izhuantou.damain.mobile.personalCenter.myLendDetial.MobileDDTCyzDTO;
import com.izhuantou.damain.mobile.personalCenter.myLendDetial.MobileDDTYwcDTO;
import com.izhuantou.damain.mobile.personalCenter.myLendDetial.MobileHHTCyzDTO;
import com.izhuantou.damain.mobile.personalCenter.myLendDetial.MobileHHTYwcDTO;
import com.izhuantou.damain.mobile.personalCenter.myLendDetial.MobileMyLendCYZDTO;
import com.izhuantou.damain.mobile.personalCenter.myLendDetial.MobileMyLendYWCDTO;
import com.izhuantou.damain.mobile.personalCenter.myLendDetial.MobileXinShouCyzDTO;
import com.izhuantou.damain.mobile.personalCenter.myLendDetial.MobileXinShouListDTO;
import com.izhuantou.damain.mobile.personalCenter.myLendDetial.MobileXinShouYwcDTO;
import com.izhuantou.damain.mobile.personalCenter.myLendDetial.MobileZZTCyzDTO;
import com.izhuantou.damain.mobile.personalCenter.myLendDetial.MobileZZTYwcDTO;
import com.izhuantou.damain.pay.PayCashPool;
import com.izhuantou.damain.pay.PayReturnPlan;
import com.izhuantou.damain.pay.PayTransferReturn;
import com.izhuantou.damain.webp2p.WebP2pNoviceBiddingRuning;
import com.izhuantou.dao.mobile.PersonalMyLend.MobilePersonalChuJieCYZMapper;
import com.izhuantou.dao.mobile.PersonalMyLend.MobilePersonalChuJieYWCMapper;
import com.izhuantou.dao.pay.PayCashPoolMapper;
import com.izhuantou.dao.pay.PayReturnPlanMapper;
import com.izhuantou.dao.pay.PayTransferReturnMapper;
import com.izhuantou.dao.webp2p.WebP2pNoviceBiddingRuningMapper;
import com.izhuantou.service.api.mobile.mobilePersonalCenter.MobileMyLendListService;

@Service("mobileMyLendListService")
public class MobileMyLendListServiceImpl implements MobileMyLendListService {
    @Autowired
    MobilePersonalChuJieCYZMapper mobilePersonalChuJieCYZMapper;
    @Autowired
    PayCashPoolMapper payCashPoolDao;
    @Autowired
    MobilePersonalChuJieYWCMapper mobilePersonalChuJieYWCMapper;
    @Autowired
    WebP2pNoviceBiddingRuningMapper noviceBiddingRuningMapper;
    @Autowired
    PayTransferReturnMapper transferReturnMapper;
    @Autowired
    PayReturnPlanMapper payReturnPlanDao;

    /** 持有中列表 */
    @Override
    public List<MobileMyLendCYZDTO> LendCYZList(String memberOID) {
	try {
	    List<MobileMyLendCYZDTO> result = new ArrayList<>();
	    // MobileXinShouListDTO xsCyzCollection = new
	    // MobileXinShouListDTO();
	    List<MobileXinShouListDTO> xsCyzCollection = mobilePersonalChuJieCYZMapper.findByMemberOID(memberOID);

	    // 获取债转标信息 controlMobileChujieList.gainByMemberZZInfo(memberOID,
	    // "investment");
	    List<MobileXinShouCyzDTO> dtoxs = mobilePersonalChuJieCYZMapper.findByOutMemberOID(memberOID, "investment");
	    for (MobileXinShouCyzDTO mobileXinShouCyzDTO : dtoxs) {
		MobileMyLendCYZDTO mobileMyLendCYZDTO = new MobileMyLendCYZDTO();
		if ("xs".equals(mobileXinShouCyzDTO.getType())) {
		    // 出借时间截取
		    Timestamp s = new Timestamp(mobileXinShouCyzDTO.getAddDateTime().getTime());
		    String cjsj = s.toString().substring(0, 10);
		    mobileMyLendCYZDTO.setCjsjTime(cjsj);
		    mobileMyLendCYZDTO.setBusinessOID(mobileXinShouCyzDTO.getBusinessOID());
		    mobileMyLendCYZDTO.setNhll(mobileXinShouCyzDTO.getYearRate().toString());
		    mobileMyLendCYZDTO.setXmmc(mobileXinShouCyzDTO.getBiddingName());
		    mobileMyLendCYZDTO.setCjje(mobileXinShouCyzDTO.getMoney().toString());
		    mobileMyLendCYZDTO.setDOID(mobileXinShouCyzDTO.getDOID());
		    mobileMyLendCYZDTO.setProductType(mobileXinShouCyzDTO.getProductType());
		    mobileMyLendCYZDTO.setBiOID(mobileXinShouCyzDTO.getOID());
		    if (mobileXinShouCyzDTO.getCond() == null) {
			mobileMyLendCYZDTO.setCond("0");
		    } else {
			mobileMyLendCYZDTO.setCond(mobileXinShouCyzDTO.getCond());
		    }
		    mobileMyLendCYZDTO.setType("xs");
		    result.add(mobileMyLendCYZDTO);

		}
	    }

	    String cjsjxs = null;
	    List<MobileXinShouCyzDTO> dtoxsfinish = mobilePersonalChuJieCYZMapper.findByOutMemberOID(memberOID,
		    "finish");
	    if (dtoxsfinish.size() > 0) {
		for (MobileXinShouCyzDTO mobileXinShouCyzDTO : dtoxsfinish) {
		    if ("xs".equals(mobileXinShouCyzDTO.getType().toString())) {
			Timestamp s = new Timestamp(mobileXinShouCyzDTO.getAddDateTime().getTime());
			cjsjxs = s.toString().substring(0, 10);
		    }
		}
	    }
	    for (MobileXinShouListDTO mobileXinShouListDTO : xsCyzCollection) {
		MobileMyLendCYZDTO mobileMyLendCYZDTO = new MobileMyLendCYZDTO();
		if (cjsjxs != null) {
		    mobileMyLendCYZDTO.setCjsjTime(cjsjxs);
		} else {
		    Timestamp s = new Timestamp(mobileXinShouListDTO.getCjsjDate().getTime());
		    String cjsjTime = s.toString().substring(0, 10);
		    mobileMyLendCYZDTO.setCjsjTime(cjsjTime);
		}
		mobileMyLendCYZDTO.setBusinessOID(mobileXinShouListDTO.getOID());
		mobileMyLendCYZDTO.setType("xs");
		result.add(mobileMyLendCYZDTO);
	    }
	    // ********************************************************************
	    // 环环持有
	    List<MobileHHTCyzDTO> hhCyzCollection = mobilePersonalChuJieCYZMapper.findByhhtMemberOID(memberOID);
	    if (hhCyzCollection.size() > 0) {
		for (MobileHHTCyzDTO mobileHHTCyzDTO : hhCyzCollection) {
		    MobileMyLendCYZDTO mobileMyLendCYZDTO = new MobileMyLendCYZDTO();
		    mobileMyLendCYZDTO.setType("hh");
		    Timestamp s = new Timestamp(mobileHHTCyzDTO.getCjsjTime().getTime());
		    String cjsjTime = s.toString().substring(0, 10);
		    mobileMyLendCYZDTO.setCjsjTime(cjsjTime);
		    mobileMyLendCYZDTO.setXmmc(mobileHHTCyzDTO.getXmmc());
		    mobileMyLendCYZDTO.setZqs(mobileHHTCyzDTO.getZqs().toString());
		    mobileMyLendCYZDTO.setNhll(mobileHHTCyzDTO.getNhll().toString());
		    mobileMyLendCYZDTO.setCjje(mobileHHTCyzDTO.getCjje().toString());
		    mobileMyLendCYZDTO.setCashPoolOID(mobileHHTCyzDTO.getCashPoolOID());
		    mobileMyLendCYZDTO.setBiOID(mobileHHTCyzDTO.getOID());
		    // mobileMyLendCYZDTO.setOID(mobileHHTCyzDTO.getBusinessOID());
		    // 替换个人中心利率显示 terry
		    PayCashPool cashpooldto = payCashPoolDao.findByOID(mobileHHTCyzDTO.getCashPoolOID());
		    BigDecimal zero = new BigDecimal(0);
		    if (null != cashpooldto.getJXother()
			    && ((BigDecimal) cashpooldto.getJXother()).compareTo(zero) > 0) {
			// 额外加息利率
			BigDecimal jx = (BigDecimal) cashpooldto.getJXother();
			// 原产品预收本息
			// BigDecimal ysbx = (BigDecimal) hhdto.get("ysbx");
			mobileMyLendCYZDTO.setJXother(jx.toString());
			// hhdto.replace("ysbx", ysbx.add((BigDecimal)
			// cashpooldto.get("JXinterest")));

		    } else {
			mobileMyLendCYZDTO.setJXother("0");
		    }
		    result.add(mobileMyLendCYZDTO);
		}
	    }

	    // ********************************************************************
	    // 点点持有
	    List<MobileDDTAllInfo> normalnum = mobilePersonalChuJieCYZMapper.findAllDDT();
	    if (normalnum.size() > 0) {
		List<MobileDDTCyzDTO> ddCyzCollection = mobilePersonalChuJieCYZMapper.findByddtMembeOID(memberOID);
		if (ddCyzCollection.size() > 0) {
		    for (MobileDDTCyzDTO mobileDDTCyzDTO : ddCyzCollection) {
			MobileMyLendCYZDTO mobileMyLendCYZDTO = new MobileMyLendCYZDTO();
			mobileMyLendCYZDTO.setXmmc(mobileDDTCyzDTO.getXmmc());
			mobileMyLendCYZDTO.setProductType(mobileDDTCyzDTO.getProductType());
			mobileMyLendCYZDTO.setCjje(mobileDDTCyzDTO.getCjje().toPlainString());
			mobileMyLendCYZDTO.setZqs(mobileDDTCyzDTO.getZqs().toString());
			mobileMyLendCYZDTO.setNhll(mobileDDTCyzDTO.getNhll().toString());
			mobileMyLendCYZDTO.setBiOID(mobileDDTCyzDTO.getBiOID());
			mobileMyLendCYZDTO.setType("dd");
			Timestamp s = new Timestamp(mobileDDTCyzDTO.getCjsjTime().getTime());
			String cjsjTime = s.toString().substring(0, 10);
			mobileMyLendCYZDTO.setCjsjTime(cjsjTime);
			result.add(mobileMyLendCYZDTO);
		    }
		}

	    }
	    // *******************************************************************
	    // 转转持有
	    List<MobileZZTCyzDTO> zzCyzCollection = mobilePersonalChuJieCYZMapper.findByzztMembeOID(memberOID);
	    if (zzCyzCollection.size() > 0) {
		for (MobileZZTCyzDTO mobileZZTCyzDTO : zzCyzCollection) {
		    MobileMyLendCYZDTO mobileMyLendCYZDTO = new MobileMyLendCYZDTO();
		    mobileMyLendCYZDTO.setXmmc(mobileMyLendCYZDTO.getXmmc());
		    mobileMyLendCYZDTO.setBiOID(mobileMyLendCYZDTO.getBiOID());
		    mobileMyLendCYZDTO.setDOID(mobileMyLendCYZDTO.getDOID());
		    mobileMyLendCYZDTO.setCjje(mobileMyLendCYZDTO.getCjje());
		    mobileMyLendCYZDTO.setNhll(mobileMyLendCYZDTO.getNhll());
		    Timestamp s = new Timestamp(mobileZZTCyzDTO.getCjsjDate().getTime());
		    String cjsjTime = s.toString().substring(0, 10);
		    mobileMyLendCYZDTO.setCjsjTime(cjsjTime);
		    mobileMyLendCYZDTO.setProductType(mobileZZTCyzDTO.getProductType());
		    result.add(mobileMyLendCYZDTO);
		}
	    }
	    return result;

	} catch (Exception e) {
	    return null;
	}
    }

    /** 已完成列表 */

    @Override
    public List<MobileMyLendYWCDTO> LendYWCList(String memberOID) {
	try {
	    List<MobileXinShouYwcDTO> xsYwcCollection = mobilePersonalChuJieYWCMapper.findByMemberOID(memberOID);
	    List<MobileMyLendYWCDTO> result = new ArrayList<>();
	    if (xsYwcCollection.size() > 0) {
		for (MobileXinShouYwcDTO mobileXinShouYwcDTO : xsYwcCollection) {
		    MobileMyLendYWCDTO mobileMyLendYWCDTO = new MobileMyLendYWCDTO();
		    WebP2pNoviceBiddingRuning noviceBiddingRuning = new WebP2pNoviceBiddingRuning();

		    noviceBiddingRuning = noviceBiddingRuningMapper.findByOID(mobileXinShouYwcDTO.getBiOID());
		    String doid = null;

		    doid = noviceBiddingRuning.getDebitCreditOID();
		    if (doid != null) {

			mobileMyLendYWCDTO.setCond(mobileXinShouYwcDTO.getDOID());
			PayTransferReturn dtotransfer = transferReturnMapper
				.gainTransferReturnByDebitCreditNewOIDAndState(mobileXinShouYwcDTO.getDOID(), "finish");
			mobileMyLendYWCDTO.setDOID(dtotransfer.getOID());

			// doid 不为null 表示为债转标,查用户出借时间,通过标的id和用户id --jasen
			PayTransferReturn dto1 = transferReturnMapper
				.gainTransferReturnByDebitCreditOIDAndOutMemberOID(doid, memberOID);
			// 设置出借时间放入实体
			Timestamp s = new Timestamp(dto1.getAddDateTime().getTime());
			String cjsjTime = s.toString().substring(0, 10);
			mobileMyLendYWCDTO.setCjsjTime(cjsjTime);

			mobileMyLendYWCDTO.setType("xs");
			result.add(mobileMyLendYWCDTO);
		    } else {
			mobileMyLendYWCDTO.setCond("0");
			Timestamp s = new Timestamp(mobileXinShouYwcDTO.getCjsjDate().getTime());
			String cjsj = s.toString().substring(0, 10);
			mobileMyLendYWCDTO.setCjsjTime(cjsj);
			mobileMyLendYWCDTO.setType("xs");
			result.add(mobileMyLendYWCDTO);
		    }
		    mobileMyLendYWCDTO.setXmmc(mobileXinShouYwcDTO.getXmmc());
		    mobileMyLendYWCDTO.setCjje(mobileXinShouYwcDTO.getCjje().toString());
		    mobileMyLendYWCDTO.setNhll(mobileXinShouYwcDTO.getNhll().toString());
		    mobileMyLendYWCDTO.setBiOID(mobileXinShouYwcDTO.getBiOID());
		}
	    }

	    // ********************************************************
	    // 环环已完成
	    List<MobileHHTYwcDTO> hhYwcCollection = mobilePersonalChuJieYWCMapper.findByhhtMemberOID(memberOID);
	    if (hhYwcCollection.size() > 0) {
		for (MobileHHTYwcDTO mobileHHTYwcDTO : hhYwcCollection) {
		    MobileMyLendYWCDTO mobileMyLendYWCDTO = new MobileMyLendYWCDTO();
		    Timestamp s = new Timestamp(mobileHHTYwcDTO.getCjsjTime().getTime());
		    String cjsj = s.toString().substring(0, 10);
		    mobileMyLendYWCDTO.setCjsjTime(cjsj);

		    // 替换个人中心利率显示 terry
		    PayCashPool cashpooldto = payCashPoolDao.findByOID(mobileHHTYwcDTO.getCashPoolOID());
		    BigDecimal zero = new BigDecimal(0);
		    if (null != cashpooldto.getJXother()
			    && ((BigDecimal) cashpooldto.getJXother()).compareTo(zero) > 0) {
			// 额外加息利率
			BigDecimal jx = (BigDecimal) cashpooldto.getJXother();
			// 原产品预收本息
			// BigDecimal ysbx = (BigDecimal) hhdto.get("ysbx");
			mobileMyLendYWCDTO.setJXother(jx.toString());
			// hhdto.replace("ysbx", ysbx.add((BigDecimal)
			// cashpooldto.get("JXinterest")));
		    } else {
			mobileMyLendYWCDTO.setJXother("0");
		    }
		    mobileMyLendYWCDTO.setType("hh");
		    mobileMyLendYWCDTO.setXmmc(mobileHHTYwcDTO.getXmmc());
		    mobileMyLendYWCDTO.setCjje(mobileHHTYwcDTO.getCjje().toString());
		    mobileMyLendYWCDTO.setNhll(mobileHHTYwcDTO.getNhll().toString());
		    mobileMyLendYWCDTO.setZqs(mobileHHTYwcDTO.getZqs());
		    mobileMyLendYWCDTO.setCashPoolOID(mobileHHTYwcDTO.getCashPoolOID());
		    mobileMyLendYWCDTO.setBiOID(mobileHHTYwcDTO.getOID());

		    result.add(mobileMyLendYWCDTO);
		}
	    }
	    // **********************************************************
	    // 点点已完成
	    List<MobileDDTYwcDTO> ddYwcCollection = mobilePersonalChuJieYWCMapper.findByddtMembeOID(memberOID);
	    if (ddYwcCollection.size() > 0) {
		for (MobileDDTYwcDTO mobileDDTYwcDTO : ddYwcCollection) {
		    MobileMyLendYWCDTO mobileMyLendYWCDTO = new MobileMyLendYWCDTO();
		    mobileMyLendYWCDTO.setType("dd");
		    Timestamp s = new Timestamp(mobileDDTYwcDTO.getCjsjTime().getTime());
		    String cjsj = s.toString().substring(0, 10);
		    mobileMyLendYWCDTO.setCjsjTime(cjsj);
		    mobileMyLendYWCDTO.setXmmc(mobileDDTYwcDTO.getXmmc());
		    mobileMyLendYWCDTO.setCjje(mobileDDTYwcDTO.getCjje().toString());
		    mobileMyLendYWCDTO.setNhll(mobileDDTYwcDTO.getNhll().toString());
		    mobileMyLendYWCDTO.setBiOID(mobileDDTYwcDTO.getBiOID());
		    result.add(mobileMyLendYWCDTO);
		}
	    }

	    // *********************************************************
	    // 转转投已完成
	    List<MobileZZTYwcDTO> aDto2 = mobilePersonalChuJieYWCMapper.findByzztMembeOID(memberOID);
	    if (aDto2.size() > 0) {
		for (MobileZZTYwcDTO mobileZZTYwcDTO : aDto2) {
		    MobileMyLendYWCDTO mobileMyLendYWCDTO = new MobileMyLendYWCDTO();
		    List<PayReturnPlan> rpdc = payReturnPlanDao
			    .findReturnPlanByMemberOIDAndState(mobileZZTYwcDTO.getOID(), "plan");

		    int qs = 0;
		    BigDecimal ysbx = new BigDecimal(0);
		    BigDecimal ysbj = new BigDecimal(0);
		    BigDecimal yslx = new BigDecimal(0);
		    String hkzt = "正常还款";
		    String dqrq = new Date().toString();
		    for (PayReturnPlan payReturnPlan : rpdc) {
			ysbx = ysbx.add((BigDecimal) payReturnPlan.getMoney());
			ysbj = ysbj.add((BigDecimal) payReturnPlan.getPrincipalMoney());
			yslx = yslx.add((BigDecimal) payReturnPlan.getInterestMoney());
			String repayDate = String.valueOf(payReturnPlan.getReturnDate());
			if (DateUtils.compare_date(dqrq, repayDate) > 0) {
			    hkzt = "逾期";
			}
			qs++;
		    }
		    mobileMyLendYWCDTO.setBusinessOID(mobileZZTYwcDTO.getBusinessOID());
		    mobileMyLendYWCDTO.setXmmc(mobileZZTYwcDTO.getBiddingName());
		    mobileMyLendYWCDTO.setCjje(mobileZZTYwcDTO.getMoney());
		    mobileMyLendYWCDTO.setNhll(mobileZZTYwcDTO.getYearRate());
		    mobileMyLendYWCDTO.setZqs(mobileZZTYwcDTO.getProductTerm());
		    // re2.put("cjsjDate",
		    // ToolDateTime.formatDateTime((Timestamp)
		    // dto.get("addDateTime"), "yyyy-MM-dd"));// pay

		    String cjsj = mobileZZTYwcDTO.getAddDateTime().substring(0, 10);
		    mobileMyLendYWCDTO.setCjsjTime(cjsj);
		    if (rpdc.size() > 0) {
			result.add(mobileMyLendYWCDTO);
		    }
		}
	    }
	    return result;
	} catch (Exception e) {
	    return null;
	}

    }

}
