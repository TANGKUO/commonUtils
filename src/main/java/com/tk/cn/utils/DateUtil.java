package com.tk.cn.utils;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;

public class DateUtil {

	// 日期格式常量
	/**
	 * 日期格式：yyyy-MM-dd
	 */
	public static final String DATE_FORMAT_WITHOUT_TIME = "yyyy-MM-dd";

	/**
	 * 日期格式：yyyy-M-d
	 */
	public static final String DATE_FORMAT_WITHOUT_TIME_SINGLE = "yyyy-M-d";

	/**
	 * 日期格式：yyyy-MM-dd HH:mm:ss
	 */
	public static final String DATE_FORMAT_WITH_TIME = "yyyy-MM-dd HH:mm:ss";

	/**
	 * 日期格式：yyyy-M-d H:m:s
	 */
	public static final String DATE_FORMAT_WITH_TIME_SINGLE = "yyyy-M-d H:m:s";

	/**
	 * 日期格式：HH:mm:ss
	 */
	public static final String DATE_FORMAT_ONLY_TIME = "HH:mm:ss";

	/**
	 * 日期格式：yyyyMMddHHmmssSSS
	 */
	public static final String DATE_FORMAT_NO = "yyyyMMddHHmmssSSS";

	/**
	 * 日期格式：yyMMddHHmmssSSS
	 */
	public static final String DATE_FORMAT_SAMPLE_NO = "yyMMddHHmmssSSS";

	/**
	 * 日期格式：yyyyMMddHHmmss
	 */
	public static final String DATE_FORMAT_SHORT_NO = "yyyyMMddHHmmss";

	/**
	 * 日期格式：yyyy-MM
	 */
	public static final String MONTH_FORMAT = "yyyy-MM";

	/**
	 * 日期格式：yyyyMM
	 */
	public static final String MONTH_FORMAT_NO = "yyyyMM";

	/**
	 * 日期格式：yyyyMMdd
	 */
	public static final String DAY_FORMAT_NO = "yyyyMMdd";

	/**
	 * 日期格式：HHmmss
	 */
	public static final String HOUR_FORMAT_NO = "HHmmss";

	/**
	 * GMT格式:yyyy-MM-dd'T'HH:mm:ss.SSSZ
	 */
	public static final String GMT_FORMAT_NO = "yyyy-MM-dd HH:mm:ss 'GMT'";

	/** 周期类型，天 */
	public static final String PERIOD_TYPE_DAY = "d";
	/** 周期类型，周 */
	public static final String PERIOD_TYPE_WEEK = "w";
	/** 周期类型，月 */
	public static final String PERIOD_TYPE_MONTH = "m";
	/** 周期类型，年 */
	public static final String PERIOD_TYPE_YEAR = "y";
	/** 周期类型，所有 */
	public static final String PERIOD_TYPE_ALL = "a";

	/** 周期索引，所有 */
	public static final String PERIOD_INDEX_ALL = "all";

	/**
	 * 转换成Date类型（支持标准格式的转换）
	 * 
	 * @param source
	 *            待转换的字符串
	 * @return java.util.Date对象，转换失败则返回null
	 */
	public static Date parseDate(String source) {
		return parseDate(source,
				new String[] { DATE_FORMAT_WITHOUT_TIME, DATE_FORMAT_WITH_TIME, DATE_FORMAT_WITHOUT_TIME_SINGLE,
						DATE_FORMAT_WITHOUT_TIME, DATE_FORMAT_WITH_TIME_SINGLE, MONTH_FORMAT });
	}

	/**
	 * 转换成Date类型(指定Pattern)
	 * 
	 * @param source
	 *            待转换的字符串
	 * @param pattern
	 *            限定日期模式
	 * @return java.util.Date对象，转换失败则返回null
	 */
	public static Date parseDate(String source, String pattern) {
		return parseDate(source, new String[] { pattern });
	}

	/**
	 * 转换成Date类型（指定Pattern数组）
	 * 
	 * @param source
	 *            待转换的字符串
	 * @param patterns
	 *            限定日期模式数组
	 * @return java.util.Date对象，转换失败则返回null
	 */
	public static Date parseDate(String source, String[] patterns) {
		Date date = null;
		try {
			date = DateUtils.parseDate(source, patterns);
		} catch (ParseException e) {
			// logger.info(e.getMessage());
		}
		return date;
	}

	/**
	 * 格式化日期
	 * 
	 * @param date
	 *            日期对象
	 * @param pattern
	 *            格式化日期模式
	 * @return 返回格式化后的日期字符串
	 */
	public static String formatDate(Date date, String pattern) {
		if (date == null) {
			return "";
		}
		return DateFormatUtils.format(date, pattern);
	}

	/**
	 * 用这种格式：yyyy-MM-dd HH:mm:ss，格式化日期
	 * 
	 * @param date
	 *            日期对象
	 * @return 返回格式化后的日期字符串
	 */
	public static String formatDateWithTime(Date date) {
		if (date == null) {
			return "";
		}
		return DateFormatUtils.format(date, DATE_FORMAT_WITH_TIME);
	}

	/**
	 * 用这种格式：yyyy-MM-dd，格式化日期
	 * 
	 * @param date
	 *            日期对象
	 * @return 返回格式化后的日期字符串
	 */
	public static String formatDateWithoutTime(Date date) {
		if (date == null) {
			return "";
		}
		return DateFormatUtils.format(date, DATE_FORMAT_WITHOUT_TIME);
	}

	/**
	 * 获取系统当前默认时区的ID
	 * 
	 * @return
	 */
	public static String getTimeZoneId() {
		Calendar cal = Calendar.getInstance(); // 使用默认时区和语言环境获得一个日历
		TimeZone timeZone = cal.getTimeZone(); // 获得时区

		return timeZone.getID(); // 返回此时区的 ID
	}

	/**
	 * 获取系统当前默认时区与GMT时区的时间差.(单位:毫秒)
	 * 
	 * @return
	 */
	private static int getDiffTimeZoneRawOffset() {
		return TimeZone.getDefault().getRawOffset() - TimeZone.getTimeZone("GMT").getRawOffset();
	}

	/**
	 * 获取当前时间所对应的格林威治时间戳（单位:秒）
	 * 
	 * @return 格林威治时间戳
	 */
	public static int getGMTTime() {
		return (int) ((System.currentTimeMillis() - getDiffTimeZoneRawOffset()) / 1000);
	}

	/**
	 * 获取指定时间日期字符串所转换后的格林威治时间戳（单位：秒）
	 * 
	 * @param formatStr
	 *            指定时间日期字符串
	 * @return
	 */
	public static int getGMTTime(String formatStr) {
		Date date = DateUtil.parseDate(formatStr);

		return (int) ((date.getTime() - getDiffTimeZoneRawOffset()) / 1000);
	}

	/**
	 * 获取指定格林威治时间戳所转换后系统默认时区对应的时间戳（单位:秒）
	 * 
	 * @param time
	 *            指定的时间戳
	 * @return
	 */
	public static int getLocalTime(int time) {
		return (int) (time + (getDiffTimeZoneRawOffset() / 1000));
	}

	/**
	 * 获取系统默认时区的当前时间戳（单位:秒）
	 * 
	 * @return
	 */
	public static int getLocalTime() {
		return (int) (System.currentTimeMillis() / 1000);
	}

	/**
	 * 获取当前时间所对应的格林威治时间戳（单位:毫秒）
	 * 
	 * @return
	 */
	public static long getGMTTimeMillis() {
		return System.currentTimeMillis() - getDiffTimeZoneRawOffset();
	}

	/**
	 * 获取指定时间日期字符串所转换后的格林威治时间戳（单位：毫秒）
	 * 
	 * @param formatStr
	 *            指定时间日期字符串
	 * @return
	 */
	public static long getGMTTimeMillis(String formatStr) {
		Date date = DateUtil.parseDate(formatStr);
		return date.getTime() - getDiffTimeZoneRawOffset();
	}

	/**
	 * 获取指定格林威治时间戳所转换后系统默认时区对应的时间戳（单位:毫秒）
	 * 
	 * @param time
	 *            指定的时间戳
	 * @return
	 */
	public static long getLocalTimeMillis(long time) {
		return time + getDiffTimeZoneRawOffset();
	}

	/**
	 * 获取系统默认时区的当前时间戳（单位:毫秒）
	 * 
	 * @return
	 */
	public static long getLocalTimeMillis() {
		return System.currentTimeMillis();
	}

	/**
	 * 
	 * @param date
	 * @param intervalType
	 *            Calendar.SECOND
	 * @param interval
	 * @return
	 */
	public static Date add(Date date, int intervalType, int interval) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(intervalType, interval);
		return cal.getTime();
	}

	/**
	 * 获取星期一
	 * 
	 * @param date
	 * @return
	 */
	public static Date getThisMonday(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int monday = 2;
		int weekIndex = cal.get(Calendar.DAY_OF_WEEK);
		if (weekIndex == 1) {
			weekIndex += 7;
		}
		return add(date, Calendar.DAY_OF_MONTH, (monday - weekIndex));
	}

	/**
	 * 获取这周最后一天
	 * 
	 * @param date
	 * @return
	 */
	public static Date getWeekLast(Date date) {
		return add(getThisMonday(date), Calendar.DATE, 6);
	}

	/**
	 * 获取月一号
	 * 
	 * @return
	 */
	public static Date getMonthFirst() {
		return getMonthFirst(new Date());
	}

	/**
	 * 获取月一号
	 * 
	 * @param date
	 * @return
	 */
	public static Date getMonthFirst(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.DATE, 1);
		return cal.getTime();
	}

	/**
	 * 获取月最后一天
	 * 
	 * @param date
	 * @return
	 */
	public static Date getMonthLast(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.MONTH, 1);
		cal.set(Calendar.DATE, 1);
		cal.add(Calendar.DATE, -1);
		return cal.getTime();
	}

	public static Date getMonthLastWithSecond(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.MONTH, 1);
		cal.set(Calendar.DATE, 1);
		cal.add(Calendar.DATE, -1);
		return parseDate(formatDate(cal.getTime(), DATE_FORMAT_WITHOUT_TIME) + " 23:59:59");
	}

	public static Timestamp getMonthLast(Timestamp date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.MONTH, 1);
		cal.set(Calendar.DATE, 1);
		cal.add(Calendar.DATE, -1);
		return new Timestamp(cal.getTime().getTime());
	}

	public static Date getMonthFirstWithSecond() {
		return getMonthFirstWithSecond(new Date());
	}

	/**
	 * 获取下月1号00:00:00
	 * 
	 * @return
	 */
	public static Date getNextMonthFirstWithSecond() {
		Date curDate = new Date();
		curDate.setMonth(curDate.getMonth() + 1);
		return getMonthFirstWithSecond(curDate);
	}

	/**
	 * 获取系统时间往上一个月1号00:00:00
	 * 
	 * @return
	 */
	public static Date getPreMonthFirstWithSecond() {
		return getPreMonthFirstWithSecond(new Date());
	}

	/**
	 * 获取curDate往上一个月的1号00:00:00
	 * 
	 * @return
	 */
	public static Date getPreMonthFirstWithSecond(Date curDate) {
		curDate.setMonth(curDate.getMonth() - 1);
		return getMonthFirstWithSecond(curDate);
	}

	public static Date getMonthFirstWithSecond(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH) + 1;
		String firstDay = year + "-" + (month < 10 ? "0" : "") + month + "-01 00:00:00";
		return parseDate(firstDay);
	}

	public static Date getDay(Date date) {
		return parseDate(formatDateWithoutTime(date));
	}

	public static Date getDayStartSecond(Date date) {
		return parseDate(formatDateWithoutTime(date) + " 00:00:00");
	}

	public static Date getDayLastSecond(Date date) {
		return parseDate(formatDateWithoutTime(date) + " 23:59:59");
	}

	/**
	 * 获取周期索引<br>
	 * 天：yyyy-MM-dd<br>
	 * 周：周一的yyyy-MM-dd<br>
	 * 月：yyyy-MM<br>
	 * 年:yyyy<br>
	 * 所有：all<br>
	 * 例如对于:2011-11-02 23:12:42<br>
	 * DAY:2011-11-02<br>
	 * WEEK:2014-10-27<br>
	 * MONTH:2014-11<br>
	 * YEAR:2014<br>
	 * ALL:all<br>
	 * 
	 * @param date
	 *            日期
	 * @param peroidType
	 *            周期类型
	 * @return
	 */
	public static String getPeriodIndex(Date date, String peroidType) {
		if (date == null) {
			throw new RuntimeException("日期为空");
		}
		if (peroidType == null) {
			throw new RuntimeException("周期类型为空");
		}

		if (PERIOD_TYPE_DAY.equals(peroidType)) {
			return formatDateWithoutTime(date);

		} else if (PERIOD_TYPE_WEEK.equals(peroidType)) {
			return formatDateWithoutTime(getThisMonday(date));

		} else if (PERIOD_TYPE_MONTH.equals(peroidType)) {
			return formatDate(date, MONTH_FORMAT);

		} else if (PERIOD_TYPE_YEAR.equals(peroidType)) {
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			return calendar.get(Calendar.YEAR) + "";

		} else if (PERIOD_TYPE_ALL.equals(peroidType)) {
			return PERIOD_INDEX_ALL;

		} else {
			throw new RuntimeException("不存在该周期类型");
		}
	}

	/**
	 * 指定时间 增加分钟数
	 * 
	 * @param inDate
	 * @param x
	 * @return
	 */
	public static Date getAddDateMinute(Date inDate, int x)// 返回的是字符串型的时间，输入的
	// 是String day, int x
	{
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 24小时制
		// 引号里面个格式也可以是 HH:mm:ss或者HH:mm等等，很随意的，不过在主函数调用时，要和输入的变
		// 量day格式一致
		Date date = null;
		try {
			date = format.parse(formatDate(inDate, DATE_FORMAT_WITH_TIME));
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		if (date == null)
			return null;
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.MINUTE, x);// 24小时制
		date = cal.getTime();
		cal = null;
		return date;

	}

	/**
	 * 往前或往后x个月(00:00:00起)
	 * 
	 * @param inDate
	 * @param x
	 * @return
	 */
	public static Date getAddDateMonthWithSecond(Date inDate, int x) {
		Date addMonthDate = getAddDateMonth(inDate, x);
		return getMonthFirstWithSecond(addMonthDate);
	}

	/**
	 * 往前或往后x个月
	 * 
	 * @param inDate
	 * @param x
	 * @return
	 */
	public static Date getAddDateMonth(Date inDate, int x) {
		Date date = parseDate(formatDate(inDate, DATE_FORMAT_WITH_TIME), DATE_FORMAT_WITH_TIME);
		if (date == null) {
			return null;
		}
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.MONTH, x);
		date = cal.getTime();
		cal = null;
		return date;

	}

	/**
	 * 往前或往后x个小时
	 * 
	 * @param inDate
	 * @param x
	 * @return
	 */
	public static Date getAddDateHour(Date inDate, int x) {
		Date date = parseDate(formatDate(inDate, DATE_FORMAT_WITH_TIME), DATE_FORMAT_WITH_TIME);
		if (date == null) {
			return null;
		}
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.HOUR_OF_DAY, x);
		date = cal.getTime();
		cal = null;
		return date;

	}

	/**
	 * 判断srcDate是不是最新的时间
	 * 
	 * @param srcDate
	 * @param destDate
	 * @return
	 */
	public static boolean isLatest(Date srcDate, Date destDate) {
		if (srcDate == null)
			return false;
		if (destDate == null)
			return true;
		if (srcDate.after(destDate)) {
			return true;
		}
		return false;
	}

	public static void main(String[] args) {
		System.out.println("DAY:" + getPeriodIndex(parseDate("2011-11-02 23:12:42"), PERIOD_TYPE_DAY));
		System.out.println("WEEK:" + getPeriodIndex(parseDate("2011-11-02 23:12:42"), PERIOD_TYPE_WEEK));
		System.out.println("MONTH:" + getPeriodIndex(parseDate("2011-11-02 23:12:42"), PERIOD_TYPE_MONTH));
		System.out.println("YEAR:" + getPeriodIndex(parseDate("2011-11-02 23:12:42"), PERIOD_TYPE_YEAR));
		System.out.println("ALL:" + getPeriodIndex(parseDate("2011-11-02 23:12:42"), PERIOD_TYPE_ALL));

		System.out.println(formatDate(getAddDateMinute(new Date(114, 1, 28, 23, 50, 50), 30), DATE_FORMAT_WITH_TIME));

		System.out.println(isLatest(parseDate("2011-11-02 23:12:42"), null));
		System.out.println(getAddDateHour(parseDate("2011-11-02 23:12:42"), 1));

	}
}
