package com.surpass.mvc.app.android.service;

import java.util.List;

import com.surpass.mvc.system.bas.model.MemberAccount;
import com.surpass.mvc.system.bas.model.MemberCard;
import com.surpass.mvc.system.bas.model.MemberIntegral;
import com.surpass.mvc.system.bas.model.MemberPresentRecord;
import com.surpass.mvc.system.bas.model.MemberRecharge;

public interface AppMemberAccountService {
	/**
	 * 删除会员卡
	 * */
	void deleteCard(MemberCard mc)throws Exception;
	
	/**
	 * 获取用户卡片
	 * */
	List<MemberCard> getListCard(MemberCard mc)throws Exception;
	
	/**
	 * 新增卡片
	 * */
	void insertCard(MemberCard obj)throws Exception;
	/**获取用户积分*/
	int getIntegral(Integer id)throws Exception;
	/**获取账户信息*/
	MemberAccount getAccountInfo(Integer memberid)throws Exception;
	/**更新账户信息*/
	int updateAccountByMemberId(MemberAccount ma) throws Exception;
	/**增加账户提现记录*/
	int insertPresentRecord(MemberPresentRecord mrp)throws Exception;
	/**获取提现记录*/
	List<MemberPresentRecord> getListAccountByMemberId(MemberPresentRecord mpr, int start, int limit, String orderBy) throws Exception;
	/**增加账户提现记录*/
	int insertRecharge(MemberRecharge mr)throws Exception;
	/**获取提现记录*/
	List<MemberRecharge> getListRechargeByMemberId(MemberRecharge mr, int start, int limit, String orderBy) throws Exception;
	/**获取积分记录*/
	List<MemberIntegral> getListMemberIntegralByMemberId(MemberIntegral mi, int start, int limit, String orderBy) throws Exception;
	/**增加用户积分记录*/
	int insertMemberIntegral(MemberIntegral mi)throws Exception;
	/**获取用户当前签到数据*/
	MemberIntegral getMemberIntegralBySignDate(Integer memberid)throws Exception;
}
