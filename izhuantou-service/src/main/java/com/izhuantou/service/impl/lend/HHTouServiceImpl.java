package com.izhuantou.service.impl.lend;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.izhuantou.damain.lend.HHTou;
import com.izhuantou.dao.lend.HHTouMapper;
import com.izhuantou.service.api.lend.HHTouService;
import com.izhuantou.service.impl.BaseServiceImpl;

/**
 * 环环投业务实现类
 * 
 * @author yangbosen
 *
 */
@Service("hhTouService")
public class HHTouServiceImpl extends BaseServiceImpl<HHTou> implements HHTouService {
    private static final Logger logger = LoggerFactory.getLogger(HHTouServiceImpl.class);

    @Autowired
    private HHTouMapper hhTouMapper;

    @Override
    public List<HHTou> findResult() {
	List<HHTou> hhTouListDetial = new ArrayList<HHTou>();
	try {
	    HHTou hhTou_Detial = new HHTou();
	    List<HHTou> list = hhTouMapper.findAll();
	    for (HHTou list1 : list) {
		hhTou_Detial = hhTouMapper.findByOID(list1.getProductRateInfoID());
		hhTou_Detial.setProductTerm(hhTou_Detial.getProductTerm());
		hhTou_Detial.setOID(list1.getOID());
		// 防止精度丢失
		hhTou_Detial.setYearRate(
			(new BigDecimal(hhTou_Detial.getYearRate())).multiply(new BigDecimal(100.0)).doubleValue());
		// 最终取值
		hhTou_Detial.setRate(String.format("%.2f", hhTou_Detial.getYearRate()));

		if (hhTou_Detial.getRepaymentType().equals("OPI")) {
		    hhTou_Detial.setRepaymentType("一次性还本付息");
		} else if (hhTou_Detial.getRepaymentType().equals("EPEI")) {
		    hhTou_Detial.setRepaymentType("等本等息");
		} else {
		    hhTou_Detial.setRepaymentType("按月返息到期还本");
		}
		hhTou_Detial.setPackageName(list1.getPackageName());
		hhTou_Detial.setNewPackageName(list1.getPackageName());
		// Integer szds = list1.getSzds();
		hhTou_Detial.setSzds(list1.getSzds());
		hhTou_Detial.setDsTime(list1.getDsTime());
		hhTou_Detial.setProductStatus(list1.getProductStatus());
		if (list1.getSykt() == null) {
		    hhTou_Detial.setSykt((double) 0);

		} else {
		    hhTou_Detial.setSykt(list1.getSykt());
		}

		if (null != list1.getJXOther() && (list1.getJXOther().compareTo(new BigDecimal(0)) > 0)) {
		    hhTou_Detial.setJXOther(list1.getJXOther());
		} else {
		    hhTou_Detial.setJXOther(new BigDecimal(0));
		}

		if (list1.getSzds() != null) {
		    // 判断是否是倒计时标
		    if (list1.getSzds().equals(1)) {

			long now = (new Date()).getTime();
			// Date dsTime = xinShouZhuan.getDsTime();
			long dsTime = (list1.getDsTime()).getTime();
			long nd = 1000 * 24 * 60 * 60;
			long nh = 1000 * 60 * 60;
			long nm = 1000 * 60;
			long ns = 1000;
			SimpleDateFormat sdf = new SimpleDateFormat();
			long diff = dsTime - now;
			Date date = new Date(diff);
			hhTou_Detial.setDsTime((Date) sdf.parse(sdf.format(date)));
			// 计算差多少天
			long day = diff / nd;
			// 计算差多少小时
			long hour = diff % nd / nh;
			// 计算差多少分钟
			long min = diff % nd % nh / nm;
			// 计算差多少秒//输出结果
			long sec = diff % nd % nh % nm / ns;

			hhTou_Detial.setSj(day + ":" + hour + ":" + min + ":" + sec);

			// 隐藏产品后边数字
			String xmmc = list1.getPackageName();
			int n = xmmc.indexOf("-");
			xmmc = xmmc.substring(0, n + 1) + "??????????";
			hhTou_Detial.setPackageName(xmmc);
		    } else {
			hhTou_Detial.setXtcz(0);
			hhTou_Detial.setSj("0");
			hhTou_Detial.setPackageName(list1.getPackageName());
		    }
		}

		hhTouListDetial.add(hhTou_Detial);
	    }

	    return hhTouListDetial;

	} catch (Exception e) {
	    e.printStackTrace();
	    logger.error("findFourNum()", e.getMessage());
	    return null;
	}
    }

}
