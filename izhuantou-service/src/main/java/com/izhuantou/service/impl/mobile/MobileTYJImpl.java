package com.izhuantou.service.impl.mobile;

import java.io.InputStream;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.izhuantou.common.utils.StringUtil;
import com.izhuantou.damain.mobile.MobileTYJPoJo;
import com.izhuantou.damain.pay.PayCashPool;
import com.izhuantou.damain.pay.PayDebitCredit;
import com.izhuantou.damain.pay.PayTransferReturn;
import com.izhuantou.damain.user.MemberMember;
import com.izhuantou.damain.vo.CustomerTyjDTO;
import com.izhuantou.dao.pay.PayCashPoolMapper;
import com.izhuantou.dao.pay.PayCustomerTyjMapper;
import com.izhuantou.dao.pay.PayDebitCreditMapper;
import com.izhuantou.dao.pay.PayTransferReturnMapper;
import com.izhuantou.dao.user.MemberMemberMapper;
import com.izhuantou.service.api.mobile.MobileTYJService;

@Service("mobileTYJ")
public class MobileTYJImpl implements MobileTYJService {

    @Autowired
    private PayTransferReturnMapper transferReturnMapper;

    @Autowired
    private PayCustomerTyjMapper customertyjMapper;

    @Autowired
    private MemberMemberMapper userDao;

    @Autowired
    private PayDebitCreditMapper debitCreditMapper;

    @Autowired
    private PayCashPoolMapper payCashPoolDao;

    @Autowired
    private PayCashPoolMapper cashpoolDao;

    @Autowired
    private MemberMemberMapper user;

    @Override
    public MobileTYJPoJo tybInfo(String memberOID) {
	List<PayDebitCredit> debitCreditList = new ArrayList<PayDebitCredit>();
	List<PayCashPool> payCashPool = new ArrayList<PayCashPool>();
	List<PayTransferReturn> transferReturnList = new ArrayList<PayTransferReturn>();
	List<CustomerTyjDTO> dtoTyj = new ArrayList<CustomerTyjDTO>();
	CustomerTyjDTO dto = new CustomerTyjDTO();
	MemberMember member = new MemberMember();
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	MobileTYJPoJo tybInfo = new MobileTYJPoJo();
	try {
	    // 加载配置文件,读取体验标配置信息 bob 10.13
	    Properties prop = new Properties();
	    InputStream url = MobileTYJImpl.class.getClassLoader().getResourceAsStream("privilege.properties");
	    prop.load(url);

	    // ***** bob 10.13 返回体验标相关信息
	    String TYJhdzt = prop.getProperty("TYJhdzt");
	    String TYJPrincipalMoney = prop.getProperty("TYJPrincipalMoney");
	    String TYJnhll = prop.getProperty("TYJnhll");
	    String TYJSY = prop.getProperty("TYJSY");
	    String TYDay = prop.getProperty("TYDay");
	    // 体验金新旧用户区分时间
	    String TYJDate = prop.getProperty("TYJDate");

	    // 判断memberOID是不是空串,如果是空说明未登录,不进行后续操作,仅返回体验人数
	    if (memberOID != null && !StringUtil.isEmpty(memberOID)) {
		// 查询是否过其他产品 0：未出借 1：出借
		debitCreditList = debitCreditMapper.findByOutMember(memberOID);
		payCashPool = payCashPoolDao.gainByMemberOID(memberOID);
		transferReturnList = transferReturnMapper.findByCondition(memberOID);
		// 查询三个表有没有出借的记录
		int CjFlag = 0;
		int n1 = debitCreditList.size();
		int n2 = payCashPool.size();
		int n3 = transferReturnList.size();
		if (n1 == 0 && n2 == 0 && n3 == 0) {
		    // 如果查询出来的记录都是0，说明从未出借过
		    CjFlag = 0;
		} else {
		    CjFlag = 1;
		}
		// 查询是否体验体验标
		dtoTyj = customertyjMapper.findByMemberOID(memberOID);
		if (dtoTyj.size() > 0) {
		    // 如果有体验金使用记录,查询用户体验标信息,返回前台
		    Date addDateTime = new Date();
		    Date endDateTime = new Date();
		    for (CustomerTyjDTO d : dtoTyj) {
			addDateTime = (Date) d.getAddDateTime();
			if (addDateTime.before(sdf.parse(TYJDate))) {
			    endDateTime = addDay(addDateTime, 2);
			} else {
			    endDateTime = addDay(addDateTime, Integer.parseInt(TYDay));
			}
			d.setAddDateTime(addDateTime);
			d.setEndDateTime(endDateTime);
			// dto.put("addDateTime", sdf.format(addDateTime));
			// dto.put("endDateTime", sdf.format(endDateTime));
			d.setIsUsed(d.getIsUsed());
			// dto.put("isUsed", (String)d.get("isUsed"));
			BigDecimal TyjPrice = (BigDecimal) d.getTyjPrice();
			TYJSY = TyjPrice.toString();
			tybInfo.setTYJSY(TYJSY);

		    }
		    if (endDateTime.after(new Date(System.currentTimeMillis()))) {
			// 如果结束时间在当前时间之后
			dto.setTyjFlag("2");
		    }
		    if (endDateTime.before(new Date(System.currentTimeMillis()))
			    && (CjFlag == 0 || (CjFlag == 1 && cashpoolDao.gainByMemberOID(memberOID).size() == 0))) {
			// 如果结束体验时间在当前时间之前,并且没有出借记录,或者是有出借记录,但是cashpool里没有记录(新手标)
			// 显示待提现
			dto.setTyjFlag("3");
		    }
		    if (endDateTime.before(new Date(System.currentTimeMillis())) && CjFlag == 1
			    && cashpoolDao.gainByMemberOID(memberOID).size() > 0) {
			// 如果结束体验时间,在系统时间之前,说明已到期,并且此时用户已经出借,并且cashpool里有记录(出借环环投)
			// 显示收益回款中
			dto.setTyjFlag("4");
		    }
		    if ("1".equals((String) dto.getIsUsed())) {
			dto.setTyjFlag("5");
		    }

		} else {
		    // **** bob 10.11 体验标注册时间限制 121-126.133
		    String sxTime = prop.getProperty("TYJSXTime").trim();
		    member = user.findUserByOID(memberOID);
		    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

		    Date date = format.parse(format.format(member.getAddDateTime()));

		    Timestamp zcsj = new Timestamp(date.getTime());
		    Date zcsjTime = new Date(zcsj.getTime());
		    // 果没有体验金使用记录,并且注册时间大于2017.10.12，判断用户是否出借过产品,判定资格
		    if (CjFlag == 0) {
			// 没有出借记录
			if (zcsjTime.after(sdf.parse(sxTime))) {
			    dto.setTyjFlag("0");
			} else {
			    dto.setTyjFlag("1");
			}

		    } else if (CjFlag == 1) {
			// 有出借记录
			dto.setTyjFlag("1");
		    }
		}
		tybInfo.setTYJSY(TYJSY);
	    }

	    tybInfo.setTYJhdzt(TYJhdzt);
	    tybInfo.setTYJPrincipalMoney(TYJPrincipalMoney);
	    tybInfo.setTYJnhll(TYJnhll);

	    tybInfo.setTYDay(TYDay);
	    tybInfo.setTYJType(dto.getTyjFlag());
	    return tybInfo;
	} catch (Exception e) {
	    return null;
	}

    }

    /**
     * 返回给定的日期加指定天数后所得到的日期。
     * 
     * @param dOne
     *            要处理的日期
     * @param iDay
     *            对指定的日期要加的天数
     * @return 返回当前日期加指定天数的日期。
     */
    public static java.util.Date addDay(java.util.Date dOne, int iDay) throws Exception {
	try {
	    Calendar calOne = Calendar.getInstance();
	    calOne.setTime(dOne);
	    calOne.add(Calendar.DATE, iDay);
	    return calOne.getTime();
	} catch (Throwable cause) {
	    throw new Exception("日期加天失败", cause);
	}
    }
}
