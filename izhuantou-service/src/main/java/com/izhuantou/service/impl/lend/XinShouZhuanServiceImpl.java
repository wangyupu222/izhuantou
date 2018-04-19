package com.izhuantou.service.impl.lend;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.izhuantou.damain.lend.XinShouZhuan;
import com.izhuantou.dao.lend.XinShouZhuanMapper;
import com.izhuantou.service.api.lend.XinShouZhuanService;
import com.izhuantou.service.impl.BaseServiceImpl;

@Service("xinShouZhuanService")
public class XinShouZhuanServiceImpl extends BaseServiceImpl<XinShouZhuan> implements XinShouZhuanService {
    private static final Logger logger = LoggerFactory.getLogger(XinShouZhuanServiceImpl.class);
    @Autowired
    private XinShouZhuanMapper xinShouZhuanMapper;

    XinShouZhuan xinShouZhuan = new XinShouZhuan();

    @Override
    public XinShouZhuan FindAll() {

	try {
	    xinShouZhuan = this.xinShouZhuanMapper.FindAll();

	    xinShouZhuan.setDays(15);

	    xinShouZhuan.setYearRate(
		    (new BigDecimal(xinShouZhuan.getYearRate())).multiply(new BigDecimal(100.0)).doubleValue());

	    xinShouZhuan.setRate(String.format("%.2f", xinShouZhuan.getYearRate()));

	    BigDecimal temp = new BigDecimal(Double.parseDouble(String.valueOf(xinShouZhuan.getHoldingAmount()))
		    / Double.parseDouble(String.valueOf(xinShouZhuan.getBiddingAmount())) * 100);

	    xinShouZhuan.setProcess(temp.intValue());

	    xinShouZhuan.setBiddingName(xinShouZhuan.getBiddingName());

	    BigDecimal kt = ((BigDecimal) xinShouZhuan.getBiddingAmount())
		    .subtract((BigDecimal) xinShouZhuan.getHoldingAmount());

	    xinShouZhuan.setSy(kt);

	    if (xinShouZhuan.getRepaymentType().equals("OPI")) {
		xinShouZhuan.setRepaymentType("一次性还本付息");
	    } else if (xinShouZhuan.getRepaymentType().equals("EPEI")) {
		xinShouZhuan.setRepaymentType("等本等息");
	    } else {
		xinShouZhuan.setRepaymentType("按月返息到期还本");
	    }

	    if (xinShouZhuan.getSzds().equals(1)) {

		long now = (new Date()).getTime();
		// Date dsTime = xinShouZhuan.getDsTime();
		long dsTime = (xinShouZhuan.getDsTime()).getTime();
		long nd = 1000 * 24 * 60 * 60;
		long nh = 1000 * 60 * 60;
		long nm = 1000 * 60;
		long ns = 1000;
		long diff = dsTime - now;

		// 计算差多少天
		long day = diff / nd;
		// 计算差多少小时
		long hour = diff % nd / nh;
		// 计算差多少分钟
		long min = diff % nd % nh / nm;
		// 计算差多少秒//输出结果
		long sec = diff % nd % nh % nm / ns;

		xinShouZhuan.setSj(day + ":" + hour + ":" + min + ":" + sec);
		SimpleDateFormat sdf = new SimpleDateFormat();
		Date date = new Date(diff);
		xinShouZhuan.setDsTime((Date) sdf.parse(sdf.format(date)));
		// 隐藏产品后边数字
		String xmmc = xinShouZhuan.getBiddingName();
		int n = xmmc.indexOf("-");
		xmmc = xmmc.substring(0, n + 1) + "??????????";
		xinShouZhuan.setNewbiddingName(xmmc);
	    } else {

		xinShouZhuan.setXtcz(0);
		xinShouZhuan.setSj("0");
		xinShouZhuan.setNewbiddingName("");
	    }

	    return xinShouZhuan;

	} catch (Exception e) {

	    logger.error("FindAll()", e.getMessage());

	    return null;

	}

    }

}
