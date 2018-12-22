package com.surpass.mvc.app.android.dao;

import java.util.List;
import java.util.Map;

import com.surpass.mvc.system.bas.model.MemberAccount;
import com.surpass.mvc.system.bas.model.MemberCard;
import com.surpass.mvc.system.bas.model.MemberIntegral;
import com.surpass.mvc.system.bas.model.MemberPresentRecord;
import com.surpass.mvc.system.bas.model.MemberRecharge;

public interface AppMemberAccountMapper {
	
	/*删除用户的卡*/
	int deleteCard(MemberCard mc);
	/*增加用户卡*/
	int insertCard(MemberCard record);
	/*查询用户卡*/
	List<MemberCard> getListCard(MemberCard record) throws Exception;
	/*获取用户积分*/
	int getIntegral(Integer memberid)throws Exception;
	/*获取账户信息*/
	MemberAccount getAccountInfo(Integer memberid)throws Exception;
	
	/*更新账户信息*/
	int updateAccountByMemberId(MemberAccount ma) throws Exception;
	/*增加账户提现记录*/
	int insertPresentRecord(MemberPresentRecord mrp)throws Exception;
	/*获取提现记录*/
	List<MemberPresentRecord> getListPresentRecordByMemberId(Map<String, Object> build) throws Exception;
	/*增加账户充值记录*/
	int insertRecharge(MemberRecharge mr)throws Exception;
	/*获取充值记录*/
	List<MemberRecharge> getListMemberRechargeByMemberId(Map<String, Object> build) throws Exception;
	
	/*获取积分记录*/
	List<MemberIntegral> getListMemberIntegralByMemberId(Map<String, Object> build) throws Exception;
	/*增加用户积分记录*/
	int insertMemberIntegral(MemberIntegral mi)throws Exception;
	/*获取用户当前签到数据*/
	MemberIntegral getMemberIntegralBySignDate(Integer memberid)throws Exception;
}
