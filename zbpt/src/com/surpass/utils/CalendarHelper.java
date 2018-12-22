package com.surpass.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 
 * @author fangshuqiang
 * 
 */
public final class CalendarHelper {

	public static final String DATE_FORMAT = "yyyy-MM-dd";

	private CalendarHelper() {
	}

	/**
	 * 返回格式为"yyyy-MM-dd"的时间字符串
	 */
	public static String getDate(long dateMillis) {
		return getTime(dateMillis, DATE_FORMAT);
	}

	/**
	 * 返回格式化的时间字符串
	 */
	public static String getTime(long dateMillis, String dateFormat) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(dateMillis);

		SimpleDateFormat sdNowDate = new SimpleDateFormat(dateFormat);
		return sdNowDate.format(calendar.getTime());
	}

	/**
	 * dateString 必须是"yyyy-MM-dd"的格式
	 */
	public static long getDate(String dateString) throws ParseException {
		return getDate(dateString, DATE_FORMAT);
	}

	/**
	 * 根据dateFormat格式化dateString为long型返回
	 */
	public static long getDate(String dateString, String dateFormat) throws ParseException {
		SimpleDateFormat sf = new SimpleDateFormat(dateFormat);
		return sf.parse(dateString).getTime();
	}

	/**
	 * 得到指定年月的最大天数
	 */
	public static int getActualMaximum(int year, int month) {
		Calendar calendar = Calendar.getInstance();
		try {
			SimpleDateFormat simpleDate = new SimpleDateFormat("yyyy-MM");
			calendar.setTime(simpleDate.parse(year + "-" + month));
		} catch (ParseException ignore) {
		}
		return calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
	}

	/**
	 * 返回指定的时间戳到当前时间的相隔天数。
	 */
	public static int getIntervalDays(long startday) {
		return getIntervalDays(startday, System.currentTimeMillis());
	}

	/**
	 * 返回指定的时间戳相隔的天数，忽略秒数，只计算到天。
	 */
	public static int getIntervalDays(long startMillis, long endMillis) {
		Calendar startday = Calendar.getInstance();
		startday.setTimeInMillis(startMillis);

		Calendar endday = Calendar.getInstance();
		endday.setTimeInMillis(endMillis);

		return getIntervalDays(startday, endday);
	}

	/**
	 * 返回两个时间之间相隔天数
	 */
	public static int getIntervalDays(Calendar startday, Calendar endday) {
		// 确保startday在endday之前
		if (startday.after(endday)) {
			Calendar cal = startday;
			startday = endday;
			endday = cal;
		}
		// 分别得到两个时间的毫秒数
		long sl = startday.getTimeInMillis();
		long el = endday.getTimeInMillis();

		long ei = el - sl;

		// 根据毫秒数计算间隔天数
		return (int) (ei / (1000 * 60 * 60 * 24));
	}

	/**
	 * 日期类型转换字符串
	 * 
	 * @param format
	 *            日期格式化 yyyy-MM-dd HH:mm:ss yyyyMMddHHmmssSSS yyyy-MM-dd
	 * @return String
	 */
	public static Calendar currentCalendar() {
		return Calendar.getInstance();
	}

	public static String getCalendarStr(String format) {
		SimpleDateFormat df = new SimpleDateFormat(format);
		return df.format(currentCalendar().getTime());
	}

	public static String getDateStr(Date dt, String format) {
		SimpleDateFormat df = new SimpleDateFormat(format);
		return df.format(dt.getTime());
	}

	public static String getDateStr(long dt, String format) {
		SimpleDateFormat df = new SimpleDateFormat(format);
		return df.format(dt);
	}

	public static int getWeekOfYear(Date dt) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(dt);
		return cal.get(Calendar.WEEK_OF_YEAR);
	}

	public static void main(String[] args) {
		System.err.println(CalendarHelper.getDateStr(new Date(), "yyyy-MM-dd HH:mm:ss"));
		System.err.println(getWeekOfYear(new Date()));
	}
}
