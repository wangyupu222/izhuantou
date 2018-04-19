package com.izhuantou.service.impl.lend;

import java.sql.Timestamp;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.izhuantou.common.utils.StringUtil;
import com.izhuantou.damain.p2p.P2pPageLoan;
import com.izhuantou.damain.p2p.P2pZtloan;
import com.izhuantou.damain.vo.FromLendInfo;
import com.izhuantou.dao.lend.FormLendInfoMapper;
import com.izhuantou.dao.p2p.PageLoanMapper;
import com.izhuantou.service.api.lend.FormLendService;

@Service("formLendService")
public class FormLendServiceImpl implements FormLendService {
    private static final Logger logger = LoggerFactory.getLogger(FormLendServiceImpl.class);
    @Autowired
    private FormLendInfoMapper formLendInfoMapper;

    @Autowired
    private PageLoanMapper pageLoanMapper;

    @Override
    public String updFrom(FromLendInfo lendInfo) {
	try {
	    P2pZtloan zt = new P2pZtloan();
	    String s_province = lendInfo.getS_province();
	    String s_city = lendInfo.getS_city();
	    String s_county = lendInfo.getS_county();
	    String yzm_reg = lendInfo.getYzm_reg();
	    String city = "";

	    if (s_province.equals("天津市") || s_province.equals("北京市") || s_province.equals("上海市")
		    || s_province.equals("重庆市")) {
		city = s_province + s_county;
	    } else {
		city = s_province + s_city + s_county;
	    }

	    lendInfo.setAddDateTime(new Date());

	    zt.setName(lendInfo.getName());
	    zt.setCity(city);
	    zt.setCard(lendInfo.getCard());
	    zt.setPhone(lendInfo.getPhone());
	    zt.setMoney(lendInfo.getMoney());
	    zt.setLoanTerm(lendInfo.getLoanTerm());
	    zt.setPurpose(lendInfo.getS_province());

	    zt.setAddDateTime(new Timestamp(System.currentTimeMillis()));
	    zt.setOID(StringUtil.getUUID());
	    zt.setVersion(new Integer(0));
	    zt.setRefresh(new Boolean(true));
	    if (zt.getAddDateTime() == null) {
		zt.setUpdDateTime(new Timestamp(System.currentTimeMillis()));
	    }
	    zt.setValid(new Boolean(true));

	    if (lendInfo.getSys_yzmsg().equals(yzm_reg)) {
		formLendInfoMapper.insertData(zt);
	    } else {
		// 验证码错误
		return "验证码不正确";
	    }

	    return "1";

	} catch (Exception e) {
	    e.printStackTrace();
	    logger.error("updFrom()", e.getMessage());
	    return "验证码不正确";
	}

    }

    @Override
    public P2pPageLoan findByOID(String OID) {
	try {
	    P2pPageLoan pageLoan = new P2pPageLoan();

	    pageLoan = pageLoanMapper.findPageByOID(OID);
	    return pageLoan;
	} catch (Exception e) {
	    return null;
	}

    }

}
