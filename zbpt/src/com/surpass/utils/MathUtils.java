package com.surpass.utils;

import java.text.DecimalFormat;

public final class MathUtils {

	private MathUtils() {
	}

	private final static String FORMAT_PREFIX = "#.";

	/**
	 * 返回按照保留2位小数格式化后的结果值
	 */
	public static double leachNumber(double value) {
		return leachNumber(value, 2);
	}

	/**
	 * 返回按照保留小数位格式化后的结果值
	 */
	public static double leachNumber(double value, int num) {
		DecimalFormat df = getDecimalFormat(num);
		return Double.valueOf(df.format(value));
	}

	private static DecimalFormat getDecimalFormat(int num) {
		String str = FORMAT_PREFIX;
		for (int i = 0; i < num; i++) {
			str += "0";
		}
		return new DecimalFormat(str);
	}
}
