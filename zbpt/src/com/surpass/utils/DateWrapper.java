package com.surpass.utils;

public class DateWrapper {

	public final int year, month, day;

	public DateWrapper() {
		this(System.currentTimeMillis());
	}

	public DateWrapper(long dateMillis) {
		this(CalendarHelper.getDate(dateMillis));
	}

	public DateWrapper(String date) {
		String[] dateElements = date.split("-", 2);

		year = Integer.parseInt(dateElements[0]);
		if (dateElements.length == 2) {
			dateElements = dateElements[1].split("-");
			month = Integer.parseInt(dateElements[0]);
			day = (dateElements.length == 2) ? Integer.parseInt(dateElements[1]) : 0;
		} else {
			month = day = 0;
		}
		validate();
	}

	private void validate() {
		if (month < 0 || month > 12) {
			throw new RuntimeException("不正确的月份");
		}

		int maxday = CalendarHelper.getActualMaximum(year, month);
		if (day < 0 || day > maxday) {
			throw new RuntimeException("不正确的日期");
		}
	}

	/**
	 * 返回下一天
	 */
	public DateWrapper nextDay() {
		String date = "";
		if (year != 0 && month != 0 && day != 0) { // 只能是在day模式前提下
			if (day == CalendarHelper.getActualMaximum(year, month)) {
				if (month == 12) {
					date = (year + 1) + "-1-1";
				} else {
					date = year + "-" + (month + 1) + "-1";
				}
			} else {
				date = year + "-" + month + "-" + (day + 1);
			}
			return new DateWrapper(date);
		}
		return null;
	}

	/**
	 * 返回前一天
	 */
	public DateWrapper previousDay() {
		String date = "";
		if (year != 0 && month != 0 && day != 0) { // 只能是在day模式前提下
			if (day == 1 && month == 1) {
				date = (year - 1) + "-12-31";
			} else if (day == 1) {
				date = year + "-" + (month - 1) + "-"
						+ CalendarHelper.getActualMaximum(year, month);
			} else {
				date = year + "-" + month + "-" + (day - 1);
			}
			return new DateWrapper(date);
		}
		return null;
	}

	/**
	 * 返回本月的第一天
	 */
	public DateWrapper firstDay() {
		if (year != 0 && month != 0) {
			return new DateWrapper(year + "-" + month + "-1");
		}
		return null;
	}

	/**
	 * 返回本月的最后一天
	 */
	public DateWrapper lastDay() {
		if (year != 0 && month != 0) {
			int day = CalendarHelper.getActualMaximum(year, month);
			return new DateWrapper(year + "-" + month + "-" + day);
		}
		return null;
	}

	/**
	 * @return 返回当前日期是否在今天之前
	 */
	public boolean isBeforeNow() {
		return isBeforeWith(new DateWrapper());
	}

	/**
	 * @return 返回当前日期是否在指定日期之前
	 */
	public boolean isBeforeWith(DateWrapper date) {
		if (this.year < date.year) {
			return true;
		} else if (this.year == date.year) {
			if (this.month < date.month) {
				return true;
			} else if (this.month == date.month) {
				if (this.day < date.day) {
					return true;
				}
			}
		}

		return false;
	}

	/**
	 * @return 返回当前时间是否是今天
	 */
	public boolean isNow() {
		return equals(new DateWrapper());
	}

	/**
	 * @return 返回当前日期是否在今天之后
	 */
	public boolean isBehindNow() {
		return isBehindWith(new DateWrapper());
	}

	/**
	 * @return 返回当前日期是否在指定日期之后
	 */
	public boolean isBehindWith(DateWrapper date) {
		return (!isBeforeWith(date) && !isNow());
	}

	@Override
	public String toString() {
		return (year == 0) ? year + "" : year
				+ ((month == 0) ? "" : "-" + month + ((day == 0) ? "" : "-" + day));
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof DateWrapper)) {
			return false;
		}
		final DateWrapper date = (DateWrapper) o;
		return (year == date.year && month == date.month && day == date.day);
	}

	@Override
	public int hashCode() {
		return toString().hashCode();
	}

}
