package com.izhuantou.common.tool;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class ToolsDatas {

    /**
     * 返回多个月后的正确日期
     * 
     * @return 对应信息
     * @param date格式为yyyy-MM-dd
     * @throws Exception
     */
    public static String gainOverMonthDate(String date, Integer monnum) throws Exception {

	try {

	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
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
	    return LocalDate.of(yy, mm, dd).toString();
	} catch (Exception ex) {
	    throw new Exception("计算多个月后的日期失败", ex);
	}

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

    /**
     * 根据返回编码返回对应资金接口返回值
     * 
     * @return 对应信息
     * @param code
     *            返回编码
     * @throws Exception
     */
    public static String returnStr(String code) throws Exception {
	String str = "";
	try {
	    switch (code) {
	    case "0000":
		str = "成功";
		break;
	    case "1000":
		str = "取系统跟踪号失败";
		break;
	    case "1001":
		str = "无此用户";
		break;
	    case "1002":
		str = "用户未激活";
		break;
	    }
	} catch (Exception ex) {
	    throw new Exception("计算失败", ex);
	}

	return str;
    }

    /**
     * 计算时间差（起息日，结算日，转出日之间关系）具体计算时间有计划决定
     * 
     * @return 结算日与转出日相差天数
     * @param beginTime
     *            起息日
     * @param endTime
     *            转出日
     * @throws Exception
     */
    /*
     * public static long calculationTimeDifference(String beginTime, String
     * endTime) throws Exception { long re = 0; try { SimpleDateFormat df = new
     * SimpleDateFormat("yyyy-MM-dd"); java.util.Date eday = df.parse(endTime);
     * java.util.Date bday = df.parse(beginTime); java.sql.Timestamp teday =
     * Timestamp.valueOf(endTime); int jsr =
     * Integer.parseInt(ToolProperty.gainCustomValue("Jsday"));// 结算日期 int by =
     * Integer.parseInt(ToolDateTime.formatDateTime(ToolDateTime.gainDateTime(
     * bday), "yyyy")); int ey =
     * Integer.parseInt(ToolDateTime.formatDateTime(ToolDateTime.gainDateTime(
     * eday), "yyyy")); int bm =
     * Integer.parseInt(ToolDateTime.formatDateTime(ToolDateTime.gainDateTime(
     * bday), "MM")); int em =
     * Integer.parseInt(ToolDateTime.formatDateTime(ToolDateTime.gainDateTime(
     * eday), "MM")); int bd =
     * Integer.parseInt(ToolDateTime.formatDateTime(ToolDateTime.gainDateTime(
     * bday), "dd")); int ed =
     * Integer.parseInt(ToolDateTime.formatDateTime(ToolDateTime.gainDateTime(
     * eday), "dd")); int jsy = 0;// 结算月份 int jsn = 0;// 结算年份
     * 
     * if (em == 12) { if (ed <= jsr)// 转出日小于结算日，结算月份等于转出月份 { jsn = ey; jsy =
     * em; } else {// 转出日大于结算日，结算月份+1 jsn = ey + 1; jsy = 1; }
     * 
     * } else { if (ed <= jsr)// 转出日小于结算日，结算月份等于转出月份 { jsn = ey; jsy = em; }
     * else {// 转出日大于结算日，结算月份+1 jsn = ey; jsy = em + 1; } } java.util.Date jsday
     * = df.parse(jsn + "-" + jsy + "-" + jsr); long x = eday.getTime() -
     * bday.getTime(); long days = x / (24 * 60 * 60 * 1000);// 总共持有天数 long y =
     * jsday.getTime() - bday.getTime(); long dayjs = y / (24 * 60 * 60 *
     * 1000);// 周期结算天数 re = dayjs - days;
     * 
     * } catch (Exception ex) { throw new Exception("计算失败", ex); }
     * 
     * return re;
     * 
     * }
     */

    /**
     * 获取计算后日期（月份增减）
     * 
     * @return 获得的日期
     * @param day
     *            初始日期
     * @param number
     *            加减数量
     * @param pr
     *            0加，1减
     * @throws Exception
     */
    public static String gainPlusAndReduceDay(String day, int number, int pr) throws Exception {
	String re = "";
	try {
	    // *gc.add(1,-1)表示年份减一.
	    // *gc.add(2,-1)表示月份减一.
	    // *gc.add(3,-1)表示周减一.
	    // *gc.add(5,-1)表示天减一.
	    Date d = new Date();
	    GregorianCalendar gc = new GregorianCalendar();
	    SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
	    Date dd = sf.parse(day);
	    gc.setTime(dd);
	    if (pr > 0) {// 减
		gc.add(2, -number);
	    } else {// 加
		gc.add(2, +number);
	    }
	    gc.set(gc.get(Calendar.YEAR), gc.get(Calendar.MONTH), gc.get(Calendar.DATE));
	    re = sf.format(gc.getTime());

	} catch (Exception e) {
	    throw new Exception("获取失败！", e);
	}
	return re;
    }

    /**
     * 获取计算后日期（天数增减）
     * 
     * @return 获得的日期
     * @param day
     *            初始日期
     * @param number
     *            加减数量
     * @param pr
     *            0加，1减
     * @throws Exception
     */
    public static String gainPlusAndDay(String day, int number, int pr) throws Exception {
	String re = "";
	try {
	    // *gc.add(1,-1)表示年份减一.
	    // *gc.add(2,-1)表示月份减一.
	    // *gc.add(3,-1)表示周减一.
	    // *gc.add(5,-1)表示天减一.
	    Date d = new Date();
	    GregorianCalendar gc = new GregorianCalendar();
	    SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
	    Date dd = sf.parse(day);
	    gc.setTime(dd);
	    if (pr > 0) {// 减
		gc.add(5, -number);
	    } else {// 加
		gc.add(5, +number);
	    }
	    gc.set(gc.get(Calendar.YEAR), gc.get(Calendar.MONTH), gc.get(Calendar.DATE));
	    re = sf.format(gc.getTime());

	} catch (Exception e) {
	    throw new Exception("获取失败！", e);
	}
	return re;
    }

    /**
     * 获取计算后日期（天数增减）
     * 
     * @param dateTime
     * @param number
     * @param pr
     * @return
     * @throws Exception
     */
    public static String gainPlusAndDateTime(String dateTime, int number, int pr) throws Exception {
	String re = "";
	try {
	    // *gc.add(1,-1)表示年份减一.
	    // *gc.add(2,-1)表示月份减一.
	    // *gc.add(3,-1)表示周减一.
	    // *gc.add(5,-1)表示天减一.
	    Date d = new Date();
	    GregorianCalendar gc = new GregorianCalendar();
	    SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    Date dd = sf.parse(dateTime);
	    gc.setTime(dd);
	    if (pr > 0) {// 减
		gc.add(5, -number);
	    } else {// 加
		gc.add(5, +number);
	    }
	    gc.set(gc.get(Calendar.YEAR), gc.get(Calendar.MONTH), gc.get(Calendar.DATE));
	    re = sf.format(gc.getTime());

	} catch (Exception e) {
	    throw new Exception("获取失败！", e);
	}
	return re;
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

    /**
     * 获取下一个还款日
     * 
     * @return 获得的日期
     * @throws Exception
     */
    /*
     * public static String gainHKday() throws Exception { String re = ""; try {
     * SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd"); String nowday =
     * (String) ToolDateTime.gainDateTime().toString(); String nextday =
     * gainPlusAndReduceDay(nowday, 1, 0); java.util.Date eday =
     * df.parse(nextday); java.util.Date bday = df.parse(nowday); int hkr =
     * Integer.parseInt(ToolProperty.gainCustomValue("Hkday"));// 结算日期 int by =
     * Integer.parseInt(ToolDateTime.formatDateTime(ToolDateTime.gainDateTime(
     * bday), "yyyy")); int ey =
     * Integer.parseInt(ToolDateTime.formatDateTime(ToolDateTime.gainDateTime(
     * eday), "yyyy")); int bm =
     * Integer.parseInt(ToolDateTime.formatDateTime(ToolDateTime.gainDateTime(
     * bday), "MM")); int em =
     * Integer.parseInt(ToolDateTime.formatDateTime(ToolDateTime.gainDateTime(
     * eday), "MM")); int bd =
     * Integer.parseInt(ToolDateTime.formatDateTime(ToolDateTime.gainDateTime(
     * bday), "dd")); int ed =
     * Integer.parseInt(ToolDateTime.formatDateTime(ToolDateTime.gainDateTime(
     * eday), "dd")); int om = 0; if ((ey - by) > 0) {// 跨年了 int x = 12 - bm; om
     * = x + em; } else { om = em; } re = ey + "-" + om + "-" + hkr;
     * 
     * } catch (Exception e) { throw new Exception("获取失败！", e); } return re; }
     */

    /**
     * 获取下一个结算日
     * 
     * @return 获得的日期
     * @throws Exception
     */
    /*
     * public static String gainJSday() throws Exception { String re = ""; try {
     * SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd"); String nowday =
     * (String) ToolDateTime.gainDateTime().toString(); String nextday =
     * gainPlusAndReduceDay(nowday, 1, 0); java.util.Date eday =
     * df.parse(nextday); java.util.Date bday = df.parse(nowday); int jsr =
     * Integer.parseInt(ToolProperty.gainCustomValue("Jsday"));// 结算日期 int by =
     * Integer.parseInt(ToolDateTime.formatDateTime(ToolDateTime.gainDateTime(
     * bday), "yyyy")); int ey =
     * Integer.parseInt(ToolDateTime.formatDateTime(ToolDateTime.gainDateTime(
     * eday), "yyyy")); int bm =
     * Integer.parseInt(ToolDateTime.formatDateTime(ToolDateTime.gainDateTime(
     * bday), "MM")); int em =
     * Integer.parseInt(ToolDateTime.formatDateTime(ToolDateTime.gainDateTime(
     * eday), "MM")); int bd =
     * Integer.parseInt(ToolDateTime.formatDateTime(ToolDateTime.gainDateTime(
     * bday), "dd")); int ed =
     * Integer.parseInt(ToolDateTime.formatDateTime(ToolDateTime.gainDateTime(
     * eday), "dd")); int om = 0; if ((ey - by) > 0) {// 跨年了 int x = 12 - bm; om
     * = x + em; } else { om = em; } re = ey + "-" + om + "-" + jsr;
     * 
     * } catch (Exception e) { throw new Exception("获取失败！", e); } return re; }
     */

    /**
     * 获取两个日期相差期数（月份增减）
     * 
     * @return 获得的日期
     * @param beginTime
     *            初始日期
     * @param endTime
     *            结束日期
     * @throws Exception
     */
    public static int gainMonthCount(String beginTime, String endTime) throws Exception {
	int re = 0;
	try {
	    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	    java.util.Date eday = df.parse(endTime);
	    java.util.Date bday = df.parse(beginTime);
	    int by = Integer.parseInt(ToolDateTime.formatDateTime(ToolDateTime.gainDateTime(bday), "yyyy"));
	    int ey = Integer.parseInt(ToolDateTime.formatDateTime(ToolDateTime.gainDateTime(eday), "yyyy"));
	    int bm = Integer.parseInt(ToolDateTime.formatDateTime(ToolDateTime.gainDateTime(bday), "MM"));
	    int em = Integer.parseInt(ToolDateTime.formatDateTime(ToolDateTime.gainDateTime(eday), "MM"));
	    int bd = Integer.parseInt(ToolDateTime.formatDateTime(ToolDateTime.gainDateTime(bday), "dd"));
	    int ed = Integer.parseInt(ToolDateTime.formatDateTime(ToolDateTime.gainDateTime(eday), "dd"));

	    if ((ey - by) > 0) {
		int x = 12 - bm;
		re = x + em;
	    } else {
		re = em - bm;
	    }
	} catch (Exception e) {
	    throw new Exception("获取失败！", e);
	}
	return re;
    }

    /**
     * 获取两个日期相差期数（月份增减） 会比较几号
     * 
     * @return 获得的日期
     * @param beginTime
     *            初始日期
     * @param endTime
     *            结束日期
     * @author Cannon
     * @throws Exception
     */
    public static int gainMonthCountWithDay(String beginTime, String endTime) throws Exception {

	int re = 0;
	try {
	    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	    java.util.Date eday = df.parse(endTime);
	    java.util.Date bday = df.parse(beginTime);
	    int by = Integer.parseInt(ToolDateTime.formatDateTime(ToolDateTime.gainDateTime(bday), "yyyy"));
	    int ey = Integer.parseInt(ToolDateTime.formatDateTime(ToolDateTime.gainDateTime(eday), "yyyy"));
	    int bm = Integer.parseInt(ToolDateTime.formatDateTime(ToolDateTime.gainDateTime(bday), "MM"));
	    int em = Integer.parseInt(ToolDateTime.formatDateTime(ToolDateTime.gainDateTime(eday), "MM"));
	    int bd = Integer.parseInt(ToolDateTime.formatDateTime(ToolDateTime.gainDateTime(bday), "dd"));
	    int ed = Integer.parseInt(ToolDateTime.formatDateTime(ToolDateTime.gainDateTime(eday), "dd"));

	    if ((ey - by) > 0) {
		if (bd <= ed) {
		    int x = 12 - bm;
		    re = x + em + 1;
		} else {
		    int x = 12 - bm;
		    re = x + em;
		}
	    } else {
		if (bd <= ed) {
		    re = em - bm + 1;
		} else {
		    re = em - bm;
		}

	    }
	} catch (Exception e) {
	    throw new Exception("获取失败！", e);
	}
	return re;

    }

    /**
     * 返回日期中的天(几月中的几号)
     * 
     * @param time
     * @return
     * @throws Exception
     */
    public static int gainDay(String time) throws Exception {
	int day = 0;
	try {
	    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	    java.util.Date Day = df.parse(time);
	    day = Integer.parseInt(ToolDateTime.formatDateTime(ToolDateTime.gainDateTime(Day), "dd"));
	} catch (Exception e) {
	    throw new Exception("获取失败！", e);
	}
	return day;
    }

    public static int gainMonth(String time) throws Exception {
	int month = 0;
	try {
	    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	    java.util.Date Day = df.parse(time);
	    month = Integer.parseInt(ToolDateTime.formatDateTime(ToolDateTime.gainDateTime(Day), "MM"));
	} catch (Exception e) {
	    throw new Exception("获取失败！", e);
	}
	return month;
    }

    /**
     * 获取剩余本息
     * 
     * @return 获得的日期
     * @param zqs
     *            总期数
     * @param syqs
     *            剩余期数
     * @param tzje
     *            投资金额
     * @param nll
     *            年利率
     * @throws Exception
     */
    public static BigDecimal gainSYBX(int zqs, int syqs, BigDecimal tzje, BigDecimal nll) throws Exception {
	BigDecimal re = new BigDecimal(0);
	try {
	    BigDecimal zqsl = new BigDecimal(zqs);
	    BigDecimal cj = tzje;// 投资金额
	    // BigDecimal nll = (BigDecimal) pri1.get("yearRate");//年利率
	    BigDecimal nyf = new BigDecimal(12);
	    BigDecimal ylx = (cj.multiply(nll)).divide(nyf);// 月利率
	    BigDecimal syqsl = new BigDecimal(syqs);
	    BigDecimal ybj = cj.divide(zqsl);// 月本金
	    BigDecimal ysbx = (syqsl.multiply(ylx)).add((syqsl).multiply(ybj));
	    re = ysbx;
	} catch (Exception e) {
	    throw new Exception("获取失败！", e);
	}
	return re;
    }

    /**
     * 获取每期利息（月）
     * 
     * @return 获得的日期
     * @param nll
     *            nll期数
     * @param jkje
     *            借款金额
     * @throws Exception
     */
    public static BigDecimal gainMQLX(BigDecimal nll, BigDecimal jkje) throws Exception {
	BigDecimal re = new BigDecimal(0);
	try {

	    BigDecimal nyf = new BigDecimal(12);
	    BigDecimal mqfy = (jkje.multiply(nll)).divide(nyf);

	    re = mqfy;
	} catch (Exception e) {
	    throw new Exception("获取失败！", e);
	}
	return re;
    }

    /**
     * 获取每期本金（月）
     * 
     * @return 获得的日期
     * @param jkje
     *            借款金额
     * @throws Exception
     */
    public static BigDecimal gainMQBJ(BigDecimal jkje) throws Exception {
	BigDecimal re = new BigDecimal(0);
	try {

	    BigDecimal nyf = new BigDecimal(12);
	    BigDecimal mqbj = jkje.divide(nyf);

	    re = mqbj;
	} catch (Exception e) {
	    throw new Exception("获取失败！", e);
	}
	return re;
    }

    /**
     * 日期对比
     * 
     * @return 1>2=1;1<2=-1;1=2=0
     * @throws Exception
     */
    public static int compare_date(String DATE1, String DATE2) {

	DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
	try {
	    Date dt1 = df.parse(DATE1);
	    Date dt2 = df.parse(DATE2);
	    if (dt1.getTime() > dt2.getTime()) {
		System.out.println("dt1 在dt2前");
		return 1;
	    } else if (dt1.getTime() < dt2.getTime()) {
		System.out.println("dt1在dt2后");
		return -1;
	    } else {
		return 0;
	    }
	} catch (Exception exception) {
	    exception.printStackTrace();
	}
	return 0;
    }

    public static int compare_date2(String DATE1, String DATE2) {

	DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	try {
	    Date dt1 = df.parse(DATE1);
	    Date dt2 = df.parse(DATE2);
	    if (dt1.getTime() > dt2.getTime()) {
		System.out.println("dt1 在dt2前");
		return 1;
	    } else if (dt1.getTime() < dt2.getTime()) {
		System.out.println("dt1在dt2后");
		return -1;
	    } else {
		return 0;
	    }
	} catch (Exception exception) {
	    exception.printStackTrace();
	}
	return 0;
    }

    /**
     * 根据原标的的开始时间、债权转让时的时间、原标的本金、利率来计算从开始时间到债权转让时间时应得到的本金加利息
     * 
     * @param days
     * @param creditRate
     * @param principalMoney
     * @return
     */
    public static BigDecimal transferMoney(long days, BigDecimal creditRate, BigDecimal principalMoney) {
	BigDecimal year = new BigDecimal(365);
	BigDecimal isDays = new BigDecimal(days);
	return ((creditRate.divide(year, 2)).multiply(isDays)).multiply(principalMoney).setScale(2,
		BigDecimal.ROUND_HALF_UP);
    }

    /**
     * 根据标的年收益利率、借款本金，算取每天收益利息
     * 
     * @param cRate
     * @param principalMoney
     * @return
     */
    public static BigDecimal gainInterestPerDays(BigDecimal cRate, BigDecimal principalMoney) {
	BigDecimal year = new BigDecimal(365);

	return ((cRate.multiply(principalMoney)).divide(year, 2)).setScale(2, BigDecimal.ROUND_HALF_UP);
    }

    /**
     * 获取两个日期相差天数
     * 
     * @param addDateTime
     * @param updDateTime
     * @return
     */
    public static BigDecimal daysBetween(Timestamp addDateTime, Timestamp updDateTime) throws ParseException {
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	String ad = addDateTime.toString();
	String upd = updDateTime.toString();
	Calendar cal = Calendar.getInstance();
	cal.setTime(sdf.parse(ad));
	long time1 = cal.getTimeInMillis();
	cal.setTime(sdf.parse(upd));
	long time2 = cal.getTimeInMillis();
	long between_days = (time2 - time1) / (1000 * 3600 * 24);

	return new BigDecimal(Integer.parseInt(String.valueOf(between_days)));
    }

    /**
     * 获取两个日期相差天数
     * 
     * @param addDateTime
     * @param updDateTime
     * @return
     */
    public static long daysBetween(String startDateTime, String endDateTime) throws ParseException {
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	Calendar cal = Calendar.getInstance();
	cal.setTime(sdf.parse(startDateTime));
	long time1 = cal.getTimeInMillis();
	cal.setTime(sdf.parse(endDateTime));
	long time2 = cal.getTimeInMillis();
	long between_days = (time2 - time1) / (1000 * 3600 * 24);

	return between_days;
    }

    /**
     * 获取系统当前日期--格式为yyyy-MM-dd hh:mm:ss 以String类型返回
     * 
     * @return
     */
    public static String gainSystemDateNow() {
	SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
	String nowDate = sDateFormat.format(new java.util.Date());
	return nowDate;
    }

    /**
     * 获取系统当前日期--格式为yyyy-MM-dd HH:mm:ss 以String类型返回
     * 
     * @return
     */
    public static String gainSystemDateNowHH() {
	SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	String nowDate = sDateFormat.format(new java.util.Date());
	return nowDate;
    }

    /**
     * 获取系统当前日期--格式为yyyyMMddhhmmss 以String类型返回
     * 
     * @author terry
     * @return
     */
    public static String gainSystemNow() {
	SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyyMMddhhmmss");
	String nowDate = sDateFormat.format(new java.util.Date());
	return nowDate;
    }

    /**
     * 获取系统当前日期--格式为yyyy-MM-dd 以String类型返回
     * 
     * @author terry
     * @return
     */
    public static String gainSystemNowDate() {
	SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyy-MM-dd");

	Date now = new Date();
	String nowDate = sDateFormat.format(now);
	return nowDate;
    }

    /**
     * 获取系统当前日期--格式为yyyyMMdd 以String类型返回
     * 
     * @author Cannon
     * @return
     */
    public static String gainTimeNow() {
	SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyyMMdd");
	Date now = new Date();
	String nowDate = sDateFormat.format(now);
	return nowDate;
    }

    public static Timestamp gainDaysCondition(Timestamp addDateTime, Timestamp updDateTime) throws ParseException {
	SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	String adt = df.format(df.parse(addDateTime.toString()));
	String upd = df.format(df.parse(updDateTime.toString()));
	DateFormat sdf = DateFormat.getDateInstance();
	Date addDate = sdf.parse(adt);// date类型
	Date updDate = sdf.parse(upd);// date类型

	int addDay = addDate.getDate();// 获取出借日的Date 即为还款日对应月份中的几号
	int updDay = updDate.getDate();// 获取债转时当天对应月份中的几号
	int updMonth = updDate.getMonth();// 获取债转时当天对应月份中的几月
	if (addDay > updDay) {
	    updDate.setDate(addDay);
	} else {
	    updDate.setMonth(updMonth - 1);

	}

	return null;
    }

    /**
     * 获取两个日期相减的月份
     * 
     * @return
     * @throws ParseException
     */
    public static int gainMonthNum(String date1, String date2) throws ParseException {
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	Calendar c1 = Calendar.getInstance();
	Calendar c2 = Calendar.getInstance();
	c1.setTime(sdf.parse(date1));
	c2.setTime(sdf.parse(date2));
	int result = 0;
	int month1 = c1.get(Calendar.MONTH);
	int month2 = c2.get(Calendar.MONTH);
	int year1 = c1.get(Calendar.YEAR);
	int year2 = c2.get(Calendar.YEAR);
	if (year1 == year2) {
	    result = month2 - month1;
	} else {
	    result = 12 * (year2 - year1) + month2 - month1;
	}

	return result;

    }

    /**
     * 当前时间距离传入时间差值
     * 
     * @author terry
     * @param dsTime
     * @return
     * @throws ExceptionOperateToolFail
     */
    public static long[] gainhour(String dsTime) {
	// public static void main(String[] args) throws
	// ExceptionOperateToolFail {
	long nd = 1000 * 24 * 60 * 60;
	long nh = 1000 * 60 * 60;
	long nm = 1000 * 60;
	long ns = 1000;
	Date nowDate = ToolDateTime.parseDate(ToolsDatas.gainSystemDateNowHH(), "yyyy-MM-dd HH:mm:ss");

	Date dsDate = ToolDateTime.parseDate(dsTime, "yyyy-MM-dd HH:mm:ss");
	long ds = dsDate.getTime();
	long now = nowDate.getTime();

	long diff = ds - nowDate.getTime();
	// 计算差多少天
	long day = diff / nd;
	// 计算差多少小时
	long hour = diff % nd / nh;
	// 计算差多少分钟
	long min = diff % nd % nh / nm;
	// 计算差多少秒//输出结果
	long sec = diff % nd % nh % nm / ns;
	System.out.println(day + "天" + hour + "小时" + min + "分钟" + sec + "秒");
	System.out.println(dsTime);
	System.out.println(ToolsDatas.gainSystemDateNowHH());
	long[] dates = { day, hour, min, sec, diff };

	return dates;

    }

}
