package com.izhuantou.service.impl.personalCenter;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.izhuantou.common.utils.DateUtils;
import com.izhuantou.damain.vo.ListRepaymentCollection;
import com.izhuantou.damain.webp2p.WebP2pPackageBiddingMainContentRuning;
import com.izhuantou.dao.personalCenter.MyLoanMapper;
import com.izhuantou.dao.webp2p.WebP2pLoanProductRateInfoMapper;
import com.izhuantou.dao.webp2p.WebP2pNormalBiddingRuningMapper;
import com.izhuantou.dao.webp2p.WebP2pNoviceBiddingRuningMapper;
import com.izhuantou.dao.webp2p.WebP2pPackageBiddingMainContentRuningMapper;
import com.izhuantou.service.api.personalCenter.MyRepaymentService;

@Service("myRepaymentService")
public class MyRepaymentServiceImpl implements MyRepaymentService {
    @Autowired
    private MyLoanMapper myLoanMapper;
    @Autowired
    WebP2pPackageBiddingMainContentRuningMapper packageBiddingMainContentRuningMapper;
    @Autowired
    WebP2pNoviceBiddingRuningMapper noviceBiddingRuningMapper;
    @Autowired
    WebP2pNormalBiddingRuningMapper normalBiddingRuningMapper;
    @Autowired
    WebP2pLoanProductRateInfoMapper loanProductRateInfoMapper;

    @Override
    public List<ListRepaymentCollection> findMyRepayment(String memberOID) {

	try {
	    List<ListRepaymentCollection> dtoc = new ArrayList<>();
	    List<ListRepaymentCollection> dtocNew = new ArrayList<>();
	    dtoc = myLoanMapper.findByMemberOID(memberOID);
	    // ******** 如果上述方法查询不到值,说明该用户有可能走的抵押类产品,需要再次查询
	    boolean ddFlag = false;
	    if (dtoc.size() == 0) {
		dtoc = myLoanMapper.findZCByMemberOID(memberOID);
		ddFlag = true;
	    }
	    // dtoc.setRowSize(1);
	    Calendar c = Calendar.getInstance();
	    String repayDate = null;
	    int HNow = c.get(Calendar.HOUR_OF_DAY);// 20170823Cannon--获取当前日期的小时
	    int MNow = c.get(Calendar.MINUTE);// 20170823Cannon--获取当前日期的分钟
	    String nowday = new Date().toString();
	    BigDecimal principalMoney = null;
	    BigDecimal overmoney = null;
	    BigDecimal surplusPrincipal = null;
	    BigDecimal oncepunish = null;
	    BigDecimal interestMoney = null;
	    BigDecimal wysxf = new BigDecimal(0.0);
	    BigDecimal applyAmount = null;
	    BigDecimal sylx = null;
	    String lastdate = null;
	    BigDecimal debitRate = null;
	    String biaoti = null;
	    Integer term = null;
	    Timestamp loandatetime = null;
	    String businessOID = null;
	    String planOID = null;
	    BigDecimal yhje = null;
	    BigDecimal yhbj = new BigDecimal(0.0);

	    // ****************20170420 上午 Cannon 关于还款新弹出框数据显示修改****************
	    BigDecimal totalInterestMoney = new BigDecimal(0.0);// 当前总利息(已还+本期待还)
	    BigDecimal rate = new BigDecimal(0);// 每期 的单月利率
	    BigDecimal zxsxf = new BigDecimal(0.0);// 当期服务费字段声明
	    BigDecimal ptglf = new BigDecimal(0.0);// 当期管理费字段声明
	    BigDecimal wyj = new BigDecimal(0.0);// 违约金字段声明
	    BigDecimal fx = new BigDecimal(00.0);// 罚息字段声明
	    BigDecimal yqglf = new BigDecimal(0.0);// 逾期管理费字段声明
	    BigDecimal alllx = new BigDecimal(0.0);// 剩余全部利息字段声明
	    BigDecimal allbj = new BigDecimal(0);// 剩余本金
	    String planRepayDay = "";// 计划还款日
	    String realRepayDay = new Date().toString();// 实际还款日
	    // ********20170424 下午 Cannon
	    int lmonth = 0;// 逾期期数
	    // **************************
	    int lday = 0;// 逾期天数字段声明
	    for (ListRepaymentCollection listRepaymentCollection : dtoc) {
		alllx = alllx.add((BigDecimal) listRepaymentCollection.getInterestMoney());
		allbj = allbj.add((BigDecimal) listRepaymentCollection.getPrincipalMoney());
	    }
	    if (dtoc.size() > 0) {
		for (ListRepaymentCollection dtoQuery : dtoc) {
		    //

		    DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		    repayDate = df.format(dtoQuery.getRepayDate());
		    nowday = df.format(new Date());

		    String but = "false";

		    // 获取还款日期减一个月的日期
		    String startDate = DateUtils.gainPlusAndReduceDay(repayDate, 1, 1);
		    // 判断还款按钮
		    int n = DateUtils.compare_date2(nowday, repayDate);
		    int n1 = DateUtils.compare_date2(nowday, startDate);
		    if ((n == -1) && (n1 == 1)) {
			but = "true";
		    }
		    if (n == 0) {
			but = "true";
		    }

		    String loantime = df.format(dtoQuery.getLoandatetime());

		    // 返回该笔是第几期还款
		    int qs = DateUtils.gainMonthNum(loantime, repayDate);

		    principalMoney = (BigDecimal) dtoQuery.getPrincipalMoney();
		    surplusPrincipal = (BigDecimal) dtoQuery.getSurplusPrincipal();

		    // nowday = DateUtils.getDateFormatter();
		    int space = DateUtils.daysBetween(repayDate, nowday);
		    // int space = getDateSpace(repayDate, nowday);

		    applyAmount = (BigDecimal) dtoQuery.getMoney();
		    yhje = (BigDecimal) dtoQuery.getAllmoney();
		    debitRate = (BigDecimal) dtoQuery.getDebitRate();
		    biaoti = (String) dtoQuery.getBusinessName();
		    term = (Integer) dtoQuery.getTerm();
		    // shijianchuo
		    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

		    // String time="1970-01-06 11:45:55";
		    Date date = format.parse(format.format(dtoQuery.getLoandatetime()));

		    loandatetime = new Timestamp(date.getTime());

		    businessOID = (String) dtoQuery.getBusinessOID();
		    planOID = (String) dtoQuery.getPlanOID();
		    interestMoney = (BigDecimal) dtoQuery.getInterestMoney();
		    // ****************20170420 Cannon 立即结清新添弹出款
		    // 查询显示修改**************
		    zxsxf = (BigDecimal) dtoQuery.getServiceMoney();

		    ptglf = (BigDecimal) dtoQuery.getManageMoney();

		    planRepayDay = dtoQuery.getRepayDate().toString();
		    // ??????0.01066667
		    rate = (BigDecimal) dtoQuery.getRate();

		    // ******************************************************************************
		    String yuqiflag = null;
		    if (space >= 1) // 2017-04-12 milo 还款
		    {
			yuqiflag = "yuqi";
		    } else if (space == 0 && HNow >= 22 && MNow >= 0) {
			yuqiflag = "yuqi";
		    } else {
			yuqiflag = "zchk";
		    }
		    dtoQuery.setYuqiflag(yuqiflag);
		    overmoney = principalMoney.multiply(new BigDecimal(0.001));
		    overmoney = overmoney.add(surplusPrincipal.multiply(new BigDecimal(0.0006)));
		    overmoney = overmoney.add(new BigDecimal(500.0));

		    if ("zchk".equals(yuqiflag)) {
			wysxf = applyAmount.multiply(new BigDecimal(0.025));
			wysxf = wysxf.add(interestMoney);// 违约手续费
			oncepunish = principalMoney.add(surplusPrincipal.add(wysxf));
			lastdate = gainDateOfOverMonth(repayDate, -1);
			Integer intesertday = getDateSpaceday(lastdate, nowday);
			yhbj = surplusPrincipal.add(principalMoney);
			sylx = applyAmount.multiply(debitRate).multiply(BigDecimal.valueOf(intesertday.doubleValue()))
				.divide(new BigDecimal(36500), 2, BigDecimal.ROUND_HALF_UP);// 未还利息
			List<WebP2pPackageBiddingMainContentRuning>  contentList=
					packageBiddingMainContentRuningMapper.findByLoanNum(biaoti);
			// 按照来项目逻辑是一个列表 
			String loanProductRateInfoID =  contentList.get((contentList.size()-1)).getLoanProductRateInfoID();
			if (loanProductRateInfoID == null) {
			    loanProductRateInfoID = noviceBiddingRuningMapper.findByCondition(biaoti)
				    .getLoanProductRateInfoID();
			}
			if (loanProductRateInfoID == null) {
			    loanProductRateInfoID = normalBiddingRuningMapper.findByLoan(biaoti)
				    .getLoanProductRateInfoID();
			}

			// 获取对应违约金利率
			wyj = applyAmount.multiply((BigDecimal) loanProductRateInfoMapper
				.findByOID(loanProductRateInfoID).getBeforeRate());
			// *****************************************************20170423
			// Cannon 提前结清问题
			// 罚息费用计算***************************************************************
			fx = interestMoney.setScale(2, BigDecimal.ROUND_HALF_EVEN);

			// ****************************************************************************************************************************************************

			oncepunish = oncepunish.add(sylx);// 总计

			oncepunish = ((((yhbj.add(interestMoney)).add(zxsxf)).add(ptglf)).add(wyj)).add(fx);
			dtoQuery.setNextSJ(format.format(dtoQuery.getRepayDate()));
			dtoQuery.setLoanSJ(format.format(dtoQuery.getLoandatetime()));
			dtoQuery.setYhje(yhje);
			dtoQuery.setZxsxf(zxsxf);
			dtoQuery.setPtglf(ptglf);
			dtoQuery.setWyj(wyj.setScale(2, BigDecimal.ROUND_HALF_EVEN));
			dtoQuery.setYqfx(fx);
			dtoQuery.setOncePay(oncepunish.setScale(2, BigDecimal.ROUND_HALF_EVEN));
			dtoQuery.setYhbj(yhbj);
			dtoQuery.setBut(but);
			dtoQuery.setQs(qs + "");

			// dtoQuery.setWyj(new BigDecimal("0"));
		    }

		    // *****************************************************20170420
		    // Cannon 逾期问题
		    // 计算相应费用***************************************************************
		    if ("yuqi".equals(yuqiflag)) {

			// DateFormat df1 = new SimpleDateFormat("yyyy-MM-dd");
			// planRepayDay = df.format(dtoQuery.getRepayDate());
			realRepayDay = df.format(new Date());
			planRepayDay = df.format(dtoQuery.getRepayDate());

			yhbj = surplusPrincipal.add(principalMoney);

			lday = DateUtils.daysBetween(planRepayDay, realRepayDay);
			// 逾期，计算逾期天数
			// lday = getDateSpace(df.format(planRepayDay),
			// realRepayDay);
			if (lday == 0) {
			    lday++;// 20170823 cannon 超过还款日的22点就算逾期
			}
			// ********20170424 下午 Cannon
			lmonth = DateUtils.gainMonthNum(planRepayDay, realRepayDay);
			// lmonth = DateUtils.daysBetween(planRepayDay,
			// realRepayDay);

			if (lmonth == 0) {
			    lmonth += 1;
			}
			yhje = principalMoney.multiply(new BigDecimal(lmonth));
			// **************************
			// 逾期费用计算
			// 全部剩余本金*0.06%*逾期天数*0.1%
			yqglf = yhbj.multiply(new BigDecimal(0.0006)).multiply(new BigDecimal(lday)).setScale(2,
				BigDecimal.ROUND_HALF_EVEN);
			// 全部剩余利息计算：查询记录中的
			// BigDecimal t = (new
			// BigDecimal(term).subtract((totalInterestMoney.divide(interestMoney)))).add(new
			// BigDecimal(1)).multiply(rate).multiply(applyAmount);
			// （全部剩余本金+全部剩余利息）*逾期天数*0.001
			fx = allbj.add(alllx).multiply(new BigDecimal(0.001)).multiply(new BigDecimal(lday)).setScale(2,
				BigDecimal.ROUND_HALF_EVEN);
			wyj = new BigDecimal(500);
			oncepunish = (((((yhje.add(interestMoney.multiply(new BigDecimal(lmonth))))
				.add(zxsxf.multiply(new BigDecimal(lmonth))))
					.add(ptglf.multiply(new BigDecimal(lmonth)))).add(wyj)).add(yqglf)).add(fx);
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
			Date laondate = new Date(loandatetime.getTime());
			dtoQuery.setLoanSJ(simpleDateFormat.format(laondate));
			dtoQuery.setYhje(yhje);
			dtoQuery.setYqglf(yqglf);
			dtoQuery.setYqfx(fx);
			dtoQuery.setYqwyj(wyj);
			dtoQuery.setOncePay(oncepunish);
			dtoQuery.setYqlx(interestMoney.multiply(new BigDecimal(lmonth)));
			dtoQuery.setZxsxf(zxsxf.multiply(new BigDecimal(lmonth)));
			dtoQuery.setPtglf(ptglf.multiply(new BigDecimal(lmonth)));
			dtoQuery.setBut(but);
			dtoQuery.setQs(qs + "");
			dtoQuery.setYhbj(yhbj);
			dtoQuery.setNextSJ(format.format(dtoQuery.getRepayDate()));
			dtoQuery.setWyj(new BigDecimal("0"));
		    }

		    dtocNew.add(dtoQuery);
		    break;
		}
	    }

	    return dtocNew;
	} catch (Exception e) {
	    // TODO: handle exception
	    e.printStackTrace();
	    return null;
	}

    }

    public static int getDateSpaceday(String date1, String date2) throws Exception {
	int days = 0;
	try {

	    SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
	    Calendar calst = Calendar.getInstance();

	    Calendar caled = Calendar.getInstance();

	    calst.setTime(sf.parse(date1));
	    caled.setTime(sf.parse(date2));

	    // 设置时间为0时
	    calst.set(Calendar.HOUR_OF_DAY, 0);
	    calst.set(Calendar.MINUTE, 0);
	    calst.set(Calendar.SECOND, 0);
	    caled.set(Calendar.HOUR_OF_DAY, 0);
	    caled.set(Calendar.MINUTE, 0);
	    caled.set(Calendar.SECOND, 0);
	    // 得到两个日期相差的天数
	    days = ((int) (caled.getTime().getTime() / 1000) - (int) (calst.getTime().getTime() / 1000)) / 3600 / 24;
	} catch (Exception e) {
	    throw new Exception("获取失败！", e);
	}
	return days;
    }

    /**
     * 返回多个月后的正确日期
     * 
     * @return 对应信息
     * @param date格式为yyyy-MM-dd
     * @throws Exception
     */
    public static String gainDateOfOverMonth(String date, Integer monnum) throws Exception {

	try {

	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	    Calendar ca = Calendar.getInstance();
	    ca.setTime(sdf.parse(date));
	    LocalDate holiday = LocalDate.parse(date, formatter);
	    Integer yy = holiday.getYear();
	    Integer mm = holiday.getMonthValue();
	    Integer dd = holiday.getDayOfMonth();
	    mm = mm + monnum;
	    while (mm > 12) {
		mm = mm - 12;
		yy = yy + 1;
	    }
	    while (mm < 1) {
		mm = mm + 12;
		yy = yy - 1;
	    }
	    // 获取传值day是否等于传值月的最后一天,如相等对月份进行增减,给day赋值新月份最大天数 terry
	    if (dd == ca.getActualMaximum(Calendar.DAY_OF_MONTH)) {
		ca.add(Calendar.MONTH, monnum);
		dd = ca.getActualMaximum(Calendar.DAY_OF_MONTH);
		System.out.println(dd);
	    }
	    return LocalDate.of(yy, mm, dd).toString();
	} catch (Exception ex) {
	    throw new Exception("计算多个月后的日期失败", ex);
	}

    }

    public static int getDateSpace(String date1, String date2) throws Exception {
	int days = 0;
	try {

	    SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
	    Calendar calst = Calendar.getInstance();

	    Calendar caled = Calendar.getInstance();

	    calst.setTime(sf.parse(date1));
	    caled.setTime(sf.parse(date2));

	    // 设置时间为0时
	    calst.set(Calendar.HOUR_OF_DAY, 0);
	    calst.set(Calendar.MINUTE, 0);
	    calst.set(Calendar.SECOND, 0);
	    caled.set(Calendar.HOUR_OF_DAY, 0);
	    caled.set(Calendar.MINUTE, 0);
	    caled.set(Calendar.SECOND, 0);
	    // 得到两个日期相差的天数
	    days = ((int) (caled.getTime().getTime() / 1000) - (int) (calst.getTime().getTime() / 1000)) / 3600 / 24;
	} catch (Exception e) {
	    throw new Exception("获取失败！", e);
	}
	return days;
    }

}
