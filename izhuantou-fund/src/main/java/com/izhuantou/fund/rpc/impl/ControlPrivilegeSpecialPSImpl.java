package com.izhuantou.fund.rpc.impl;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.izhuantou.common.tool.ToolDateTime;
import com.izhuantou.common.tool.ToolsDatas;
import com.izhuantou.damain.pay.PayPrivilegeSpecialps;
import com.izhuantou.damain.webp2p.WebP2pPackageBiddingMainRuning;
import com.izhuantou.damain.webp2p.WebP2pProductRateInfo;
import com.izhuantou.fund.rpc.api.ControlPackageBiddingMainRuning;
import com.izhuantou.fund.rpc.api.ControlPrivilegeSpecialPS;
import com.izhuantou.fund.rpc.api.ControlProductRateInfo;

@Service("controlPrivilegeSpecialPS")
public class ControlPrivilegeSpecialPSImpl extends BaseServiceImpl<PayPrivilegeSpecialps>
	implements ControlPrivilegeSpecialPS {

    @Autowired
    private ControlPackageBiddingMainRuning controlPackageBiddingMainRuning;
    @Autowired
    private ControlProductRateInfo controlProductRateInfo;

    @Override
    public void savePrivilegePs(String memberOID, BigDecimal amount, String biddingOID) {
	try {
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

	    // 查询标的信息
	    WebP2pPackageBiddingMainRuning pbm = this.controlPackageBiddingMainRuning.queryByid(biddingOID);
	    WebP2pProductRateInfo pdri = this.controlProductRateInfo.queryByid(pbm.getProductRateInfoID());
	    String time = " 00:00:00";
	    /*
	     * 计算回款 发送日期
	     */
	    Date rdate = ToolDateTime.addMonth(ToolDateTime.gainDate(), (int) (double) pdri.getProductTerm());
	    Date returndate = ToolDateTime.addDay(rdate, 1);
	    String rd = sdf.format(returndate) + time;
	    // 回款日减3天为发劵日
	    String sd = ToolsDatas.gainPlusAndDay(rd, 2, 1) + time;
	    Timestamp returnDate = Timestamp.valueOf(rd);
	    Timestamp sendDate = Timestamp.valueOf(sd);
	    /*
	     * 计算金额
	     */
	    BigDecimal lowMoney = amount.multiply(BigDecimal.valueOf(0.8)).setScale(0, BigDecimal.ROUND_HALF_UP);
	    /*
	     * 根据金额判断tqOID
	     */
	    String tqOID = "";
	    Properties prop = new Properties();
	    // InputStream url = ProcessRegistHandle.class.getClassLoader()
	    // .getResourceAsStream("privilege.properties");
	    // prop.load(url);
	    amount.compareTo(BigDecimal.valueOf(50000));
	    if (amount.compareTo(BigDecimal.valueOf(50000)) == -1) {
		// 如果小于5万
		tqOID = prop.getProperty("SpecialTqOID1");
	    }
	    if ((amount.compareTo(BigDecimal.valueOf(50000)) == 1 && amount.compareTo(BigDecimal.valueOf(100000)) == -1)
		    || amount.compareTo(BigDecimal.valueOf(50000)) == 0) {
		// 如果大于5万并且小于10万，或者是出借5万
		tqOID = prop.getProperty("SpecialTqOID2");
	    }
	    if (amount.compareTo(BigDecimal.valueOf(100000)) == 1
		    || amount.compareTo(BigDecimal.valueOf(100000)) == 0) {
		// 如果大于10万，或者正好出借10万
		tqOID = prop.getProperty("SpecialTqOID3");
	    }
	    PayPrivilegeSpecialps dto = new PayPrivilegeSpecialps();
	    dto.setMemberOID(memberOID);
	    dto.setPrivilegeOID(tqOID);
	    dto.setMoney(amount);
	    dto.setLowMoney(lowMoney);
	    dto.setReturnDate(returnDate);
	    dto.setSendDate(sendDate);
	    dto.setSendType("0");
	    dto.setIsUsed("0");
	    this.save(dto);
	} catch (Exception e) {
	    System.out.println(e.getMessage());
	}
    }
}
