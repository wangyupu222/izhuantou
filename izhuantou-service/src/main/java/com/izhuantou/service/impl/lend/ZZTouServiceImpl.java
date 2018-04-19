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

import com.izhuantou.damain.lend.ZZTou;
import com.izhuantou.dao.lend.ZZTouMapper;
import com.izhuantou.service.api.lend.ZZTouService;
import com.izhuantou.service.impl.BaseServiceImpl;

/**
 * 转转投业务实现类
 * 
 * @author yangbosen
 *
 */
@Service("zzTouService")
public class ZZTouServiceImpl extends BaseServiceImpl<ZZTou> implements ZZTouService {
    private static final Logger logger = LoggerFactory.getLogger(ZZTouServiceImpl.class);
    @Autowired
    private ZZTouMapper zzTouMapper;

    @Override
    public List<ZZTou> findResult() {
	List<ZZTou> zzTouListDetial = new ArrayList<ZZTou>();
	try {
	    ZZTou zzTou = new ZZTou();
	    List<ZZTou> list = zzTouMapper.findAll();
	    for (ZZTou list1 : list) {
		// 计算投资进程
		BigDecimal temp = new BigDecimal(Double.parseDouble(String.valueOf(list1.getHoldingAmount()))
			/ Double.parseDouble(String.valueOf(list1.getBiddingAmount())) * 100);
		double process = temp.intValue();
		zzTou = zzTouMapper.findByOID(list1.getProductRateInfoID());
		zzTou.setBiddingName(list1.getBiddingName());
		zzTou.setStartBidAmount(list1.getStartBidAmount());
		zzTou.setBiddingAmount(list1.getBiddingAmount());
		// 防止精度丢失
		zzTou.setYearRate((new BigDecimal(zzTou.getYearRate())).multiply(new BigDecimal(100.0)).doubleValue());
		zzTou.setRate(String.format("%.2f", zzTou.getYearRate()));
		zzTou.setProcess(process);
		zzTou.setOID(list1.getOID());
		zzTou.setNewBiddingName(list1.getBiddingName());
		zzTou.setProductStatus(list1.getProductStatus());
		zzTou.setHoldingAmount(list1.getHoldingAmount());
		// 可投金额
		BigDecimal kt = ((BigDecimal) list1.getBiddingAmount()).subtract((BigDecimal) list1.getHoldingAmount());
		zzTou.setSy(kt);
		if (zzTou.getRepaymentType().equals("OPI")) {
		    zzTou.setRepaymentType("一次性还本付息");
		} else if (zzTou.getRepaymentType().equals("EPEI")) {
		    zzTou.setRepaymentType("等本等息");
		} else {
		    zzTou.setRepaymentType("按月返息到期还本");
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
			long diff = dsTime - now;

			// 计算差多少天
			long day = diff / nd;
			// 计算差多少小时
			long hour = diff % nd / nh;
			// 计算差多少分钟
			long min = diff % nd % nh / nm;
			// 计算差多少秒//输出结果
			long sec = diff % nd % nh % nm / ns;

			zzTou.setSj(day + ":" + hour + ":" + min + ":" + sec);
			// 将时间差转换为date
			SimpleDateFormat sdf = new SimpleDateFormat();
			Date date = new Date(diff);
			zzTou.setDsTime((Date) sdf.parse(sdf.format(date)));
			// 隐藏产品后边数字
			String xmmc = list1.getBiddingName();
			int n = xmmc.indexOf("-");
			xmmc = xmmc.substring(0, n + 1) + "??????????";
			zzTou.setBiddingName(xmmc);
			// re.put("xmmcNew", xmmc);
		    } else {
			zzTou.setXtcz(0);
			zzTou.setSj("0");
			zzTou.setBiddingName(list1.getBiddingName());
		    }
		}

		zzTouListDetial.add(zzTou);
	    }

	    return zzTouListDetial;

	} catch (Exception e) {
	    e.printStackTrace();
	    logger.error("findFourNum()", e.getMessage());
	    return null;
	}
    }

}
