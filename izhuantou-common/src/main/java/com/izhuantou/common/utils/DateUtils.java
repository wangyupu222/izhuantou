package com.izhuantou.common.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.apache.commons.lang.StringUtils;

public abstract class DateUtils {
    public static final String DATE_FROMAT1 = "yyyy-MM-dd";
    public static final String DATE_FROMAT2 = "yyyy-MM-dd HH:mm:ss";
    public static final String DATE_FROMAT3 = "HH:mm:ss";
    public static final String DATE_FROMAT4 = "yyyyMMddHHmmss";
    public static final Long INIT_TIME_DATE4 = 19700101000000L;

    public static Date getDate(String s) {
	return getDate(s, null);

    }

    public static Date getJustDate(String s) {
	return getDate(s, "yyyy-MM-dd");
    }

    public static long getTimeStamp(String s) throws ParseException {
	Date date;
	String format = "yyyy-MM-dd";
	date = new SimpleDateFormat(format).parse(s);
	return date.getTime();
    }

    public static Date getDate(long date) {
	return getDate(date, null);
    }

    public static Date getJustDate(long date) {
	return getDate(date, "yyyy-MM-dd");
    }

    public static Date getDate(long date, String format) {
	if (StringUtils.isEmpty(format)) {
	    format = "yyyy-MM-dd HH:mm:ss";
	}

	return getDate(formatDate(new Date(date), format), format);
    }

    public static Date getDate(String s, String format) {
	Date date;
	try {
	    if (StringUtils.isEmpty(format)) {
		format = "yyyy-MM-dd HH:mm:ss";
	    }

	    date = new SimpleDateFormat(format).parse(s);
	} catch (Exception e) {
	    date = new Date(0L);
	}

	return date;
    }

    public static String formatDate(long date, String format) {
	return formatDate(new Date(date), format);
    }

    public static String formatDate(long date) {
	return formatDate(new Date(date), null);
    }

    public static String formatJustDate(long date) {
	return formatDate(new Date(date), "yyyy-MM-dd");
    }

    /**
     * 微信支付相关接口要求时间格式
     * 
     * @param date
     * @return
     */
    public static Long formatDateForWx(long date) {
	String temp = formatDate(new Date(date), DATE_FROMAT4);
	return StringUtils.isEmpty(temp) ? INIT_TIME_DATE4 : Long.valueOf(temp);
    }

    public static String formatDate(Date date, String format) {
	if (StringUtils.isEmpty(format)) {
	    format = "yyyy-MM-dd HH:mm:ss";
	}

	return new SimpleDateFormat(format).format(date);
    }

    /**
     * 获取系统当前日期(精确到毫秒)，格式：yyyy-MM-dd HH:mm:ss
     * 
     * @return
     */
    public static String getDateFormatter() {
	Date date = new Date();
	DateFormat df = new SimpleDateFormat(DATE_FROMAT2);
	return df.format(date);
    }

    public static Long getbeforeMonth() {
	Calendar cal = Calendar.getInstance();
	cal.add(Calendar.MONTH, -1);
	Long dateTime = cal.getTimeInMillis();
	return dateTime;
    }

    /**
     * 获取下个月日期 如：2015/12/3--2016/1/3
     * 
     * @return
     */
    public static Long getAfterMonth() {
	Calendar cal = Calendar.getInstance();
	cal.add(Calendar.MONTH, 1);
	Long dateTime = cal.getTimeInMillis();
	return dateTime;
    }

    /**
     * 相对今天获取多少个月多少天后的某天
     * 
     * @param someMoney
     *            几个月后
     * @param someDay
     *            几天后
     * @return someMoney=2 someDay = 1 2015/12/3--2016/2/4
     */
    public static Long getAfterSomeMonth(int someMoney, int someDay) {
	Calendar cal = Calendar.getInstance();
	cal.add(Calendar.MONTH, someMoney);
	cal.add(Calendar.DAY_OF_MONTH, someDay);
	Long dateTime = cal.getTimeInMillis();
	return dateTime;
    }

    /**
     * 相对今天获取多少个月多少天后的某天
     * 
     * @param someMoney
     *            几个月后
     * @param someDay
     *            几天后
     * @return someMoney=2 someDay = 1 2015/12/3--2016/2/4
     */
    public static String getAfterSomeMonth2(int someMoney, int someDay) {
	Calendar cal = Calendar.getInstance();
	cal.add(Calendar.MONTH, someMoney);
	cal.add(Calendar.DAY_OF_MONTH, someDay);
	Date dateTime = cal.getTime();
	return DateUtils.formatDate(dateTime, "yyyy-MM-dd");
    }

    public static String getFullYear() {
	return new SimpleDateFormat("yyyy").format(new Date());
    }

    public static String getMonth() {
	return new SimpleDateFormat("MM").format(new Date());
    }

    public static String getDay() {
	return new SimpleDateFormat("dd").format(new Date());
    }

    public static String getHour() {
	return new SimpleDateFormat("HH").format(new Date());
    }

    public static String getMinute() {
	return new SimpleDateFormat("mm").format(new Date());
    }

    public static String getSecond() {
	return new SimpleDateFormat("ss").format(new Date());
    }

    /**
     * 描述 今天凌晨
     * 
     * @param currentTimeMillis
     * @return
     */
    public static Long getDayDate(long currentTimeMillis) {
	return DateUtils.getDate(DateUtils.formatJustDate(currentTimeMillis) + " 00:00:00", null).getTime();
    }

    /**
     * 描述 今天最后23:59:59
     * 
     * @param currentTimeMillis
     * @return
     */
    public static Long getLastDate(long currentTimeMillis) {
	return DateUtils.getDate(DateUtils.formatJustDate(currentTimeMillis) + "  23:59:59", null).getTime();
    }

    /**
     * 计算两个日期之间相差的天数
     * 
     * @param smdate
     *            较小的时间
     * @param bdate
     *            较大的时间
     * @return 相差天数
     * @throws ParseException
     */
    public static int daysBetween(Date smdate, Date bdate) throws ParseException {
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	smdate = sdf.parse(sdf.format(smdate));
	bdate = sdf.parse(sdf.format(bdate));
	Calendar cal = Calendar.getInstance();
	cal.setTime(smdate);
	long time1 = cal.getTimeInMillis();
	cal.setTime(bdate);
	long time2 = cal.getTimeInMillis();
	long between_days = (time2 - time1) / (1000 * 3600 * 24);
	return Integer.parseInt(String.valueOf(between_days));
    }

    /**
     * 计算两个日期之间相差的天数
     * 
     * @param smdate
     *            较小的时间
     * @param bdate
     *            较大的时间
     * @return 相差天数
     * @throws ParseException
     */
    public static int daysBetween(String smdate, String bdate) throws ParseException {
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	Calendar cal = Calendar.getInstance();
	cal.setTime(sdf.parse(smdate));
	long time1 = cal.getTimeInMillis();
	cal.setTime(sdf.parse(bdate));
	long time2 = cal.getTimeInMillis();
	long between_days = (time2 - time1) / (1000 * 3600 * 24);
	return Integer.parseInt(String.valueOf(between_days));
    }

    public static String formatTime(String style, Long time) {
	if (!StringUtils.isEmpty(style)) {
	    return DateUtils.formatDate(time, style);
	}
	return DateUtils.formatJustDate(time);
    }

    /**
     * 相对今天获取多少个月多少天之前的某天
     * 
     * @param inputDate
     *            输入日期参数
     * @param someDay
     *            几天前
     * @return inputDate=1449729904677 someDay = 7 2015/12/10--2015/10/3
     */
    public static Long getBeforeDay(Date inputDate, int someDay) {
	Calendar cal = Calendar.getInstance();
	cal.setTime(inputDate);
	cal.add(Calendar.DAY_OF_MONTH, -someDay);
	Long dateTime = cal.getTimeInMillis();
	return dateTime;
    }

    /**
     * 获取前dayNum的日期 0点0分long型时间
     * 
     * @param dayNum
     * @return
     */
    public static Long getBeforeMonthStart(int dayNum) {
	Long l = DateUtils.getBeforeDay(new Date(), dayNum);
	Long start = DateUtils.getDayDate(l);
	return start;
    }

    /**
     * 获取前dayNum的日期，并返回 月-日的 字符串数组
     * 
     * @param dayNum
     * @return
     */
    public static List<String> getBeforeMonthDays(int dayNum) {
	List<String> monthStr = new ArrayList<String>(30);
	for (int i = 0; i < dayNum; i++) {
	    monthStr.add(DateUtils.formatDate(DateUtils.getBeforeDay(new Date(), i), "yyyy-MM-dd"));
	}
	return monthStr;
    }

    /**
     * 获取当天最小的时间戳
     * 
     * @return
     */
    public static Long dayMinTime() {
	Calendar calendar = new GregorianCalendar();
	calendar.set(Calendar.HOUR_OF_DAY, 0);
	calendar.set(Calendar.MINUTE, 0);
	calendar.set(Calendar.SECOND, 0);
	return calendar.getTime().getTime();
    }

    /**
     * 获取当天最大的时间戳
     * 
     * @return
     */
    public static Long dayMaxTime() {
	Calendar calendar = new GregorianCalendar();
	calendar.set(Calendar.HOUR_OF_DAY, 23);
	calendar.set(Calendar.MINUTE, 59);
	calendar.set(Calendar.SECOND, 59);
	return calendar.getTime().getTime();
    }

    /**
     * 获取昨天最小的时间戳
     * 
     * @return
     */
    public static Long yesterDayMinTime() {
	Calendar calendar = Calendar.getInstance();
	calendar.add(Calendar.DATE, -1);
	calendar.set(Calendar.HOUR_OF_DAY, 0);
	calendar.set(Calendar.MINUTE, 0);
	calendar.set(Calendar.SECOND, 0);
	return calendar.getTime().getTime();
    }

    /**
     * 获取昨天最大的时间戳
     * 
     * @return
     */
    public static Long yesterDayMaxTime() {
	Calendar calendar = Calendar.getInstance();
	calendar.add(Calendar.DATE, -1);
	calendar.set(Calendar.HOUR_OF_DAY, 23);
	calendar.set(Calendar.MINUTE, 59);
	calendar.set(Calendar.SECOND, 59);
	return calendar.getTime().getTime();
    }

    /**
     * 获取前天最小的时间戳
     * 
     * @return
     */
    public static Long beforDayMinTime() {
	Calendar calendar = Calendar.getInstance();
	calendar.add(Calendar.DATE, -2);
	calendar.set(Calendar.HOUR_OF_DAY, 0);
	calendar.set(Calendar.MINUTE, 0);
	calendar.set(Calendar.SECOND, 0);
	return calendar.getTime().getTime();
    }

    /**
     * 获取前天最大的时间戳
     * 
     * @return
     */
    public static Long beforDayMaxTime() {
	Calendar calendar = Calendar.getInstance();
	calendar.add(Calendar.DATE, -2);
	calendar.set(Calendar.HOUR_OF_DAY, 23);
	calendar.set(Calendar.MINUTE, 59);
	calendar.set(Calendar.SECOND, 59);
	return calendar.getTime().getTime();
    }

    public static String formatDateXX(Long gqsjCh) {
	String format = "yyyy-MM-dd HH-mm-ss";
	Date date = new Date(gqsjCh);
	return new SimpleDateFormat(format).format(date);
    }

    /**
     * 获取之后的分钟时间戳
     * 
     * @param minute
     * @return
     */
    public static Long dateMinute(int minute) {
	/*
	 * Calendar afterTime = Calendar.getInstance();
	 * afterTime.add(Calendar.MINUTE, minute); return
	 * afterTime.getTime().getTime();
	 */
	long curren = System.currentTimeMillis();
	curren += minute * 60 * 1000;
	return curren;
    }

    /**
     * 获取某时间戳几天之后的时间戳
     * 
     * @param minute
     * @return
     */
    public static Long dateMinute(long currentTime, long day) {
	currentTime += day * 24l * 60l * 60l * 1000l;
	return currentTime;
    }

    /**
     * 描述 提前几分钟
     * 
     * @param minute
     * @return
     */
    public static Long beforeMinute(Long inputDate, int minute) {
	long curren = System.currentTimeMillis();
	curren -= minute * 60 * 1000;
	return curren;
    }

    /**
     * 描述 提前几秒钟
     * 
     * @param minute
     * @return
     */
    public static Long beforeSecond(Long inputDate, int second) {
	long curren = inputDate;
	curren -= second * 1000;
	return curren;
    }

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

    public static String formatDateTime(long timeMills) {
	long day = timeMills / (24 * 60 * 60 * 1000);
	long hour = (timeMills / (60 * 60 * 1000) - day * 24);
	long min = (timeMills / (60 * 1000) - day * 24 * 60 - hour * 60);
	long s = (timeMills / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);
	long sss = (timeMills - day * 24 * 60 * 60 * 1000 - hour * 60 * 60 * 1000 - min * 60 * 1000 - s * 1000);
	return (day > 0 ? day + "," : "") + hour + ":" + min + ":" + s + "." + sss;
    }

}