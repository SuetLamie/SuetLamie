package com.surpass.utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLDecoder;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.axis.utils.ByteArrayOutputStream;

public class StringUtil {

	public static boolean isEmpty(String str) {
		if (null == str || str.trim().equals("")) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * URL转码
	 * 
	 * @param str
	 * @return
	 */
	public static String getString(String str) throws Exception {
		if (str == null || "".equals(str)) {
			return "";
		}
		String temp = new String(str.getBytes("ISO-8859-1"), "utf-8");
		return URLDecoder.decode(temp, "utf-8");
	}

	public static boolean isNotEmpty(String str) {
		if (str == null || str.trim().equals("")) {
			return false;
		} else {
			return true;
		}
	}

	private StringUtil() {

	}

	/**
	 * 功能描述：字符串转为整型，如转换出异常将返回0
	 * 
	 * @date:2011-10-18
	 * @param str
	 * @return
	 */
	public static int convert2Int(String str) {
		try {
			int i = Integer.parseInt(str);
			return i;
		} catch (Exception e) {
			return 0;
		}
	}

	/**
	 * 功能描述：字符系列左补0
	 * 
	 * @param str
	 * @return
	 */
	public static String leftFillZero(String valueOf, int length) {
		return LeftFillChar(valueOf, "0", length);
	}

	/**
	 * 功能描述：补充字符系列 -1：左补充； 1：右补充
	 * 
	 * @param str
	 * @return
	 */
	public static String strFillChar(String valueOf, String ch, int length, int direction) {
		if (direction > 0) {
			// RightFill
			return RightFillChar(valueOf, ch, length);
		} else {
			// DefaultLeftFill
			return LeftFillChar(valueOf, ch, length);
		}
	}

	/**
	 * 功能描述：字符系列右补ch
	 * 
	 * @param str
	 * @return
	 */
	public static String RightFillChar(String valueOf, String ch, int length) {
		if (valueOf == null) {
			valueOf = "";
		}
		StringBuffer tempValue = new StringBuffer(valueOf);

		if ((valueOf.length() == 0) || (valueOf.length() < length)) {
			while (tempValue.length() < length) {
				tempValue.append(ch);
			}
		} else if (valueOf.length() == length) {
			return valueOf;
		} else if (valueOf.length() > length) {
			return valueOf.substring(0, length);
		}
		return tempValue.toString();
	}

	/**
	 * 功能描述：字符系列左补ch
	 * 
	 * @param str
	 * @return
	 */
	public static String LeftFillChar(String valueOf, String ch, int length) {

		if (valueOf == null) {
			valueOf = "";
		}
		StringBuffer tempValue = new StringBuffer(valueOf);

		if ((valueOf.length() == 0) || (valueOf.length() < length)) {
			while (tempValue.length() < length) {
				tempValue.insert(0, ch);
			}
		} else if (valueOf.length() == length) {
			return valueOf;
		} else if (valueOf.length() > length) {
			return valueOf.substring(0, length);
		}
		return tempValue.toString();
	}

	/**
	 * 去除字符串的前后空格
	 * 
	 * @param str
	 *            操作字符串
	 * @return 除字符串的前后空格后的字符串
	 */
	public static String trim(String str) {
		if (str == null)
			return null;
		return str.trim();
	}

	/**
	 * 去除左边的'0'字符
	 * 
	 * @param str
	 * @return
	 */
	public static String leftTrimZero(String str) {
		int len = str.length();
		int st = 0;
		char[] val = str.toCharArray();
		while ((st < len) && (val[st] == '0')) {
			st++;
		}
		return (st < len + 1) ? str.substring(st) : str;
	}

	/**
	 * 返回清空和去除空前后格的字符串
	 * 
	 * @param str
	 *            需要处理的字符串
	 * @return 处理完毕的字符串
	 */
	public static String trimorempty(String str) {
		String result = "";
		if (str == null)
			result = "";
		else
			result = trim(str);
		return result;

	}

	/**
	 * 
	 * @param 判断是否是空字符串
	 * @return
	 */
	public static boolean isEmptyString(String s) {
		if (s == null)
			return true;
		if ("".equals(s.trim()))
			return true;
		return false;
	}

	/**
	 * 从字符串中得到所有匹配的字符串
	 * 
	 * @param source
	 *            源字符串
	 * @param regex
	 *            正则表达式
	 * @return
	 */
	public static String getRegexString(String source, String regex) {
		if ((null == source) || (null == regex))
			return "";
		StringBuffer result = new StringBuffer();
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(source);
		while (matcher.find()) {
			result.append(matcher.group());
		}
		return result.toString();
	}

	/**
	 * 
	 * 方法说明:替换null为"" <br>
	 * 创建人: 王鹏<br>
	 * 创建日期: 2013-04-02 11:39:10<br>
	 * 
	 * @param str
	 * @return <br>
	 */
	public static String checkNull(String str) {
		if (str != null) {
			return str;
		} else {
			return "";
		}
	}

	/**
	 * Local Test
	 */
	public static void main(String[] args) {

	}

	public static String toString(Object obj) {
		return obj == null ? "" : obj.toString().trim();
	}

	public static boolean isEmpty(Object obj) {
		return toString(obj).equals("") ? true : false;
	}

	public static boolean isNotEmpty(Object obj) {
		return toString(obj).equals("") ? false : true;
	}

	public static String[] splitString(String str, String split) {
		if (str.indexOf(split) > -1) {
			return str.split(split);
		} else {
			if (str.length() > 0) {
				String str1[] = { str };
				return str1;
			} else {
				return null;
			}
		}
	}

	public static String firstLowerCaps(String str) {
		StringBuffer sbuf = new StringBuffer(str.length());
		sbuf.append(str.substring(0, 1).toLowerCase()).append(str.substring(1));
		return sbuf.toString();
	}

	public static String getInputStream2Str(InputStream in) {
		try {
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			byte[] b = new byte[1024];
			int ch = 0;
			while ((ch = in.read(b)) != -1) {
				bos.write(b, 0, ch);
			}
			return bos.toString();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (null != in) {
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return null;
	}
}
