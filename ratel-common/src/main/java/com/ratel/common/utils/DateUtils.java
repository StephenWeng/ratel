package com.ratel.common.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * ClassName: DateUtils <br/>
 * Function: 日期处理工具类. <br/>
 * date: 2018年2月11日 上午10:44:46 <br/>
 *
 * @author wenzhang
 * @version
 * @see [相关类/方法]
 * @Description:
 * @since JDK 1.8
 */
public class DateUtils {
	private final static Log logger = LogFactory.getLog(DateUtils.class);

	/**
	 * 年-月-日
	 */
	public static String YYYY_MM_DD = "yyyy-MM-dd";

	/**
	 * 年-月
	 */
	public static String YYYY_MM = "yyyy-MM";

	/**
	 * 年月
	 */
	public static String YYYYMM = "yyyyMM";

	/**
	 * 年-月-日 时:分:秒
	 */
	public static String YYYY_MM_DD_HHMMSS = "yyyy-MM-dd HH:mm:ss";

	/**
	 * 时:分:秒
	 */
	public static String HHMMSS = "HH:mm:ss";

	private static Calendar calS = Calendar.getInstance();

	/**
	 * @Title getNowYearMonthDayWeek
	 * @author wanglc
	 * @Description: 取当前年月日 星期
	 * @date 2013-12-6
	 * @return
	 */
	public static String getNowYearMonthDayWeek() {
		return DateUtils.getNowDate("yyyy年M月d日 H:m:s") + " 星期" + "日一二三四五六".charAt(getNowDayOfWeek() - 1);
	}

	/**
	 * 取当前日期是星期几
	 *
	 * @Title getNowDayOfWeek
	 * @author wanglc
	 * @date 2013-12-6
	 * @return
	 */
	public static int getNowDayOfWeek() {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		return calendar.get(Calendar.DAY_OF_WEEK);
	}

	/**
	 * 取当前时间
	 *
	 * @Title getNowDate
	 * @author wanglc
	 * @date 2013-12-6
	 * @param format
	 * @return 字符串日期
	 */
	public static String getNowDate(String format) {
		String dateTime = "";
		try {
			Date now = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat(format);
			dateTime = sdf.format(now);
		} catch (Exception e) {
			logger.error(e);
		}
		return dateTime;
	}

	/**
	 * 把日期转化为字符类型
	 *
	 * @Title nowStringDate
	 * @author wanglc
	 * @date 2013-12-6
	 * @param pattern
	 * @return
	 */
	public static String nowStringDate(String pattern) {
		return dateToString(new Date(), pattern);
	}

	/**
	 * 获得昨天的日期
	 *
	 * @Title getStringYesterday
	 * @author wanglc
	 * @date 2013-12-6
	 * @param pattern
	 * @return
	 */
	public static String getStringYesterday(String pattern) {
		return dateToString(new Date(new Date().getTime() - 24 * 3600 * 1000), pattern);
	}

	/**
	 * 得到当前日期
	 *
	 * @Title nowDate
	 * @author wanglc
	 * @date 2013-12-6
	 * @param pattern
	 * @return
	 */
	public static Date nowDate(String pattern) {
		String nowStringDate = nowStringDate(pattern);
		return stringToDate(nowStringDate, pattern);
	}

	/**
	 * 日期转化字符
	 *
	 * @Title dateToString
	 * @author wanglc
	 * @date 2013-12-6
	 * @param date
	 * @param pattern
	 * @param locale
	 * @return
	 */
	public static String dateToString(Date date, String pattern, Locale locale) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(pattern, locale);
			return sdf.format(date);
		} catch (Exception e) {
			return "";
		}
	}

	/**
	 * 日期转化字符
	 *
	 * @Title dateToString
	 * @author wanglc
	 * @date 2013-12-6
	 * @param date
	 * @param pattern
	 * @return
	 */
	public static String dateToString(Date date, String pattern) {
		Locale locale = Locale.getDefault();
		return dateToString(date, pattern, locale);
	}

	/**
	 * 字符类型日期转化为长类型
	 *
	 * @Title stringToLong
	 * @author wanglc
	 * @date 2013-12-6
	 * @param strDate
	 * @param pattern
	 * @param locale
	 * @return
	 * @throws ParseException
	 */
	public static long stringToLong(String strDate, String pattern, Locale locale) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat(pattern, locale);
		Date date = sdf.parse(strDate);
		return date.getTime();
	}

	/**
	 * 字符类型日期转化为长类型
	 *
	 * @Title stringToLong
	 * @author wanglc
	 * @date 2013-12-6
	 * @param strDate
	 * @param pattern
	 * @return
	 * @throws ParseException
	 */
	public static long stringToLong(String strDate, String pattern) throws ParseException {
		Locale locale = Locale.CHINESE;
		return stringToLong(strDate, pattern, locale);
	}

	/**
	 * 字符转化为日期
	 *
	 * @Title stringToDate
	 * @author wanglc
	 * @date 2013-12-6
	 * @param strDate
	 * @param pattern
	 * @return
	 */
	public static Date stringToDate(String strDate, String pattern) {
		try {
			long ltime = stringToLong(strDate, pattern);
			return new Date(ltime);
		} catch (Exception ex) {
			return null;
		}
	}

	/**
	 * 字符转化为日期
	 *
	 * @Title stringToDate
	 * @author wanglc
	 * @date 2013-12-6
	 * @param strDate
	 * @param pattern
	 * @param otherPattern
	 * @return
	 */
	public static Date stringToDate(String strDate, String pattern, String otherPattern) {
		try {
			long ltime = stringToLong(strDate, pattern);
			return new Date(ltime);
		} catch (Exception ex) {
			try {
				long ltime = stringToLong(strDate, otherPattern);
				return new Date(ltime);
			} catch (Exception e) {
				return null;
			}
		}
	}

	/**
	 * 格式化日期
	 *
	 * @Title formatDate
	 * @author wanglc
	 * @date 2013-12-6
	 * @param date
	 * @param pattern
	 * @return
	 */
	public static Date formatDate(Date date, String pattern) {
		String s = dateToString(date, pattern);
		return stringToDate(s, pattern);
	}

	/**
	 * 取得当前日期的天份
	 *
	 * @Title getEmbodyDay
	 * @author wanglc
	 * @date 2013-12-6
	 * @param date
	 * @return
	 */
	public static int getEmbodyDay(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int embodyDay = calendar.get(Calendar.DAY_OF_MONTH);
		return embodyDay;

	}

	/**
	 * 取得当前日期的月份
	 *
	 * @Title getEmbodyMonth
	 * @author wanglc
	 * @date 2013-12-6
	 * @param date
	 * @return
	 */
	public static int getEmbodyMonth(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int embodyMonth = calendar.get(Calendar.MONTH) + 1;
		return embodyMonth;

	}

	/**
	 * 取得当前日期的年份
	 *
	 * @Title getEmbodyYear
	 * @author wanglc
	 * @date 2013-12-6
	 * @param date
	 * @return
	 */
	public static int getEmbodyYear(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int embodyYear = calendar.get(Calendar.YEAR);
		return embodyYear;

	}

	/**
	 * 根据参数和格式取未来日期
	 *
	 * @Title getFutureDay
	 * @author wanglc
	 * @date 2013-12-6
	 * @param appDate
	 * @param format
	 * @param days
	 * @return
	 */
	public static String getFutureDay(String appDate, String format, int days) {
		String future = "";
		try {
			Calendar calendar = GregorianCalendar.getInstance();
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
			Date date = simpleDateFormat.parse(appDate);
			calendar.setTime(date);
			calendar.add(Calendar.DATE, days);
			date = calendar.getTime();
			future = simpleDateFormat.format(date);
		} catch (Exception e) {

		}

		return future;
	}

	/**
	 * 根据参数和格式取未来时间
	 *
	 * @Title getFutureTime
	 * @author wanglc
	 * @date 2013-12-6
	 * @param appDate
	 * @param format
	 * @param hours
	 * @return
	 */
	public static String getFutureTime(String appDate, String format, int hours) {
		String future = "";
		try {
			Calendar calendar = GregorianCalendar.getInstance();
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
			Date date = simpleDateFormat.parse(appDate);
			calendar.setTime(date);
			calendar.add(Calendar.HOUR, hours);
			date = calendar.getTime();
			future = simpleDateFormat.format(date);
		} catch (Exception e) {

		}
		return future;
	}

	/**
	 *
	 * @Title getFutureTime
	 * @author :技术部-文章
	 * @Description 获取传入时间几小时后的时间
	 * @date 2018年12月23日 下午7:30:59
	 * @param now
	 *            传入时间
	 * @param hours
	 *            间隔小时数
	 * @return Date 未来时间
	 */
	public static Date getFutureTime(Date now, int hours) {
		Date date = null;
		try {
			Calendar calendar = GregorianCalendar.getInstance();
			calendar.setTime(now);
			calendar.add(Calendar.HOUR, hours);
			date = calendar.getTime();
			return date;
		} catch (Exception e) {
			return date;
		}
	}

	/**
	 *
	 * @Description:两个日期比较，返回较大日期.
	 *
	 * @author wenzhang
	 * @date:2018年2月11日 上午10:47:03
	 * @Title:compareTime
	 * @param time1
	 *            日期1
	 * @param time2
	 *            日期2
	 * @return 较大的日期
	 * @since JDK 1.8
	 */
	public static String compareTime(String time1, String time2) {
		Date date1, date2;
		DateFormat f = new SimpleDateFormat("hh:mm");
		try {
			date1 = f.parse(time1);
			date2 = f.parse(time2);
			if (date1.compareTo(date2) >= 0) {
				return time1;
			} else {
				return time2;
			}
		} catch (ParseException e) {
		}
		return "";
	}

	/**
	 * 判断日期是否为月初
	 */
	public static boolean isFirstDayOfMonth(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		// calendar.set(Calendar.DATE, (calendar.get(Calendar.DATE) + 1));
		// 加上以后判断月末
		if (calendar.get(Calendar.DAY_OF_MONTH) == 1) {
			return true;
		}
		return false;
	}

	/**
	 *
	 * @Description:查询两个日期的中间日期.
	 *
	 * @author wenzhang
	 * @date:2018年2月11日 上午10:48:14
	 * @Title:getDaysBetweenDates
	 * @param d1
	 *            日期1
	 * @param d2
	 *            日期2
	 * @param format
	 * @return 两个日期的中间值
	 * @since JDK 1.8
	 */
	public static long getDaysBetweenDates(String d1, String d2, String format) {
		return getDaysBetweenDates(stringToDate(d1, format), stringToDate(d2, format));
	}

	public static long getDaysBetweenDates(Date d1, Date d2) {
		long t1 = d1.getTime();
		long t2 = d2.getTime();
		long value = (t1 - t2) / (1000 * 60 * 60 * 24);
		return value;
	}

	/**
	 * 计算剩余时间
	 *
	 * @param startDateStr
	 * @param endDateStr
	 * @return
	 */
	public static String remainDateToString(Date startDate, Date endDate) {

		calS.setTime(startDate);
		int startY = calS.get(Calendar.YEAR);
		int startM = calS.get(Calendar.MONTH);
		int startD = calS.get(Calendar.DATE);
		int startDayOfMonth = calS.getActualMaximum(Calendar.DAY_OF_MONTH);

		calS.setTime(endDate);
		int endY = calS.get(Calendar.YEAR);
		int endM = calS.get(Calendar.MONTH);
		// 处理2011-01-10到2011-01-10，认为服务为一天
		int endD = calS.get(Calendar.DATE) + 1;
		int endDayOfMonth = calS.getActualMaximum(Calendar.DAY_OF_MONTH);

		StringBuilder sBuilder = new StringBuilder();
		if (endDate.compareTo(startDate) < 0) {
			return sBuilder.append("过期").toString();
		}
		int lday = endD - startD;
		if (lday < 0) {
			endM = endM - 1;
			lday = startDayOfMonth + lday;
		}
		// 处理天数问题，如：2011-01-01 到 2013-12-31 2年11个月31天 实际上就是3年
		if (lday == endDayOfMonth) {
			endM = endM + 1;
			lday = 0;
		}
		int mos = (endY - startY) * 12 + endM - startM;
		int lyear = mos / 12;
		int lmonth = mos % 12;
		if (lyear > 0) {
			sBuilder.append(lyear + "年");
		}
		if (lmonth > 0) {
			sBuilder.append(lmonth + "个月");
		}
		if (lday > 0) {
			sBuilder.append(lday + "天");
		}
		return sBuilder.toString();
	}

	public static String getDatePoor(Date endDate, Date nowDate) {
		long nd = 1000 * 24 * 60 * 60;
		long nh = 1000 * 60 * 60;
		long nm = 1000 * 60;
		// long ns = 1000;
		// 获得两个时间的毫秒时间差异
		long diff = endDate.getTime() - nowDate.getTime();
		// 计算差多少天
		long day = diff / nd;
		// 计算差多少小时
		long hour = diff % nd / nh;
		// 计算差多少分钟
		long min = diff % nd % nh / nm;
		// 计算差多少秒//输出结果
		if (0 != day) {
			return day + "天" + hour + "小时" + min + "分钟";
		}
		if (0 != hour) {
			return hour + "小时" + min + "分钟";
		}
		return min + "分钟";
	}
}
