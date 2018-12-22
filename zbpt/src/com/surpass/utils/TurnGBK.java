/***********************************
 * 功能描述：转码工具类
 * 作者姓名：夏翡
 * 公司名称：陕西中石
 * 创建日期：2009-9-14
 ***********************************/
package com.surpass.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
/**
 *功能描述：工具类，用于将字符串由“iso-8859-1”转到“gbk”
 */
public class TurnGBK 
{
	/**
	 * 字符转码，由iso8859-1转为gbk
	 * @param str
	 * @return String
	 */
	public static String turnGBK(String temp)
	{
		if(temp!=null)
		{
			try {
				temp=new String(temp.getBytes("iso-8859-1"),"gbk");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return temp;
	}
	
	
	/**
	 * 此方法将String 从 application/x-www-form-urlencoded MIME 格式解码
	 * @param temp
	 * @return
	 */
	public static String turnURLDecoder(String temp)
	{
		if(temp!=null)
		{
			try {
				temp=URLDecoder.decode(temp, "UTF-8");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return temp;
	}
	/**
	 * 字符转码，由iso8859-1转为utf-8
	 * @param str
	 * @return String
	 */
	public static String turnUTF8(String temp)
	{
		if(temp!=null)
		{
			try {
				temp=new String(temp.getBytes("iso-8859-1"),"utf-8");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return temp;
	}
}
