package com.surpass.utils;

import java.util.Random;
/**
 * 类名称：RotaryDrawUtil<br>
 * 方法说明: 生成以MS开头的8位数推荐码<br>
 * 创建人: 何扬<br>
 * 创建日期: 2018-8-2<br>
 * 
 * @return
 */
public class RotaryDrawUtil{
	/**
	 * 获取会员的推荐码
	 * */
	public static String card(){
		   String code="MS";
	       int[] array = {0,1,2,3,4,5,6,7,8,9};
	       Random rand = new Random();
	       for (int i = 10; i > 1; i--) {
	           int index = rand.nextInt(i);
	           int tmp = array[index];
	           array[index] = array[i - 1];
	           array[i - 1] = tmp;
	       }
	       int result = 0;
	       for(int i = 0; i < 6; i++){
	           result = result * 10 + array[i];
	       }
	       code+=result;
	       return code;
	   }
	/**
	 * 获取6位数验证码
	 * */
	public static String getRandNum() {
		String randNum = new Random().nextInt(1000000) + "";
		System.out.println("生成" + randNum);
		if (randNum.length() != 6) { // 如果生成的不是6位数随机数则返回该方法继续生成
			return getRandNum();
		}
		return randNum;
	}

	public static void main(String[] args) {
		System.out.println(card());
	}
}