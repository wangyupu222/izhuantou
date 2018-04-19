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

import com.izhuantou.damain.lend.DDTou;
import com.izhuantou.dao.lend.DDTouMapper;
import com.izhuantou.service.api.lend.DDTouService;
import com.izhuantou.service.impl.BaseServiceImpl;

/**
 * 点点投实现类
 * 
 * @author yangbosen
 *
 */
@Service("ddTouService")
public class DDTouServiceImpl extends BaseServiceImpl<DDTou> implements DDTouService {
    private static final Logger logger = LoggerFactory.getLogger(DDTouServiceImpl.class);
    @Autowired
    private DDTouMapper ddTouMapper;

    @Override
    public List<DDTou> findResult() {
	List<DDTou> ddTouListDetial = new ArrayList<DDTou>();
	try {
	    DDTou ddTou = new DDTou();
	    List<DDTou> list = ddTouMapper.findAll();
	    for (DDTou ddList : list) {
		BigDecimal temp = new BigDecimal(Double.parseDouble(String.valueOf(ddList.getHoldingAmount()))
			/ Double.parseDouble(String.valueOf(ddList.getBiddingAmount())) * 100);
		double process = temp.intValue();
		ddTou = ddTouMapper.findByOID(ddList.getProductRateInfoID());
		ddTou.setBiddingName(ddList.getBiddingName());
		ddTou.setStartBidAmount(ddList.getStartBidAmount());
		ddTou.setBiddingAmount(ddList.getBiddingAmount());
		// 防止精度丢失
		ddTou.setYearRate((new BigDecimal(ddTou.getYearRate())).multiply(new BigDecimal(100.0)).doubleValue());
		double rate = ddTou.getYearRate();
		ddTou.setRate(String.format("%.2f", rate));
		ddTou.setProcess(process);
		ddTou.setNewBiddingAmount(ddList.getBiddingName());
		ddTou.setOID(ddList.getOID());
		ddTou.setProductStatus(ddList.getProductStatus());
		ddTou.setHoldingAmount(ddList.getHoldingAmount());

		if (ddTou.getRepaymentType().equals("OPI")) {
		    ddTou.setRepaymentType("一次性还本付息");
		} else if (ddTou.getRepaymentType().equals("EPEI")) {
		    ddTou.setRepaymentType("等本等息");
		} else {
		    ddTou.setRepaymentType("按月返息到期还本");
		}
		BigDecimal kt = ((BigDecimal) ddList.getBiddingAmount())
			.subtract((BigDecimal) ddList.getHoldingAmount());
		ddTou.setSy(kt);
		// Integer szds = ddList.getSzds();
		// 判断szds是否为空
		if (ddList.getSzds() != null) {
		    // 判断是否是倒计时标
		    if (ddList.getSzds().equals(1)) {

			long now = (new Date()).getTime();
			// Date dsTime = xinShouZhuan.getDsTime();
			long dsTime = (ddList.getDsTime()).getTime();
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

			ddTou.setSj(day + ":" + hour + ":" + min + ":" + sec);

			SimpleDateFormat sdf = new SimpleDateFormat();
			Date date = new Date(diff);
			ddTou.setDsTime((Date) sdf.parse(sdf.format(date)));
			// 隐藏产品后边数字
			String xmmc = ddList.getBiddingName();
			int n = xmmc.indexOf("-");
			xmmc = xmmc.substring(0, n + 1) + "??????????";
			ddTou.setBiddingName(xmmc);
		    } else {
			ddTou.setXtcz(0);
			ddTou.setSj("0");
			ddTou.setBiddingName(ddList.getBiddingName());
		    }
		}

		ddTouListDetial.add(ddTou);
	    }

	    return ddTouListDetial;

	} catch (Exception e) {
	    e.printStackTrace();
	    logger.error("findResult()", e.getMessage());
	    return null;
	}
    }

}
