package com.izhuantou.service.impl.p2p;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.izhuantou.damain.webp2p.WebP2pFourAmounts;
import com.izhuantou.dao.webp2p.WebP2pFourAmountsMapper;
import com.izhuantou.dao.webp2p.WebP2pNoviceBiddingRuningMapper;
import com.izhuantou.service.api.p2p.FourNumService;
import com.izhuantou.service.impl.BaseServiceImpl;

/**
 * 首页四个数据展示的业务实现类
 * 
 * @author yangbosen
 *
 */

@Service("fourNumService")
public class FourNumServiceImpl extends BaseServiceImpl<WebP2pFourAmounts> implements FourNumService {
    private static final Logger logger = LoggerFactory.getLogger(FourNumServiceImpl.class);
    @Autowired
    private WebP2pFourAmountsMapper fourNumMapper;
    @Autowired
    private WebP2pNoviceBiddingRuningMapper noviceBiddingRuningMapper;

    @Override
    public WebP2pFourAmounts findFourNum() {
	WebP2pFourAmounts fourNum = new WebP2pFourAmounts();
	try {
	    /**
	     * 计算安全运营天数
	     */
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	    Calendar cal = Calendar.getInstance();
	    cal.setTime(sdf.parse("2017-01-04"));
	    long beginTime = cal.getTimeInMillis();
	    cal.setTime(sdf.parse(sdf.format(new Date())));
	    long nowTime = cal.getTimeInMillis();
	    long between_days = (nowTime - beginTime) / (1000 * 3600 * 24);

	    fourNum = this.fourNumMapper.findFourNum();
	    fourNum.setSumDay(between_days);
	    // 将数字转换为美式表示
	    fourNum.setMoney1(formatTosepara(fourNum.getMoney()));
	    fourNum.setInsertMoney1(formatTosepara(fourNum.getInterestMoney()));
	    fourNum.setMember1(formatTosepara(new BigDecimal(fourNum.getMember())));
	    fourNum.setSumDay1(formatTosepara(new BigDecimal(fourNum.getSumDay())));
	    return fourNum;

	} catch (Exception e) {

	    logger.error("findFourNum()", e.getMessage());
	    return null;
	}
    }

    public static String formatTosepara(BigDecimal data) {
	DecimalFormat df = new DecimalFormat("#,###");
	return df.format(data);
    }

}
