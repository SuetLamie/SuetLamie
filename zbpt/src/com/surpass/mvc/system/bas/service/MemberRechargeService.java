package com.surpass.mvc.system.bas.service;



import java.util.List;

import com.surpass.mvc.system.bas.model.MemberRecharge;
import com.surpass.utils.ResultGridStore;

/**
 * 类名称： MemberRechargeService<br>
 * 类描述： 会员充值记录管理 <br>
 * 创建人： 何扬<br>
 * 创建时间：2018-8-9<br>
 */
public interface MemberRechargeService {
	
	/**
	 * 
	 * 方法名称：insert<br>
	 * 方法说明: 会员记录<br>
	 * 创建人: 何扬<br>
	 * 创建日期: 2018-8-3<br>
	 * 
	 * @param memberRecharge
	 * @throws Exception
	 */
	void insert(MemberRecharge memberRecharge) throws Exception;
	/**
	 * 
	 * 方法名称：updateMemberRecharge<br>
	 * 方法说明: 修改会员记录<br>
	 * 创建人: 何扬<br>
	 * 创建日期: 2018-8-3<br>
	 * 
	 * @param memberRecharge
	 * @throws Exception
	 */
	int updateMemberRecharge(MemberRecharge memberRecharge) throws Exception;
	
	List<MemberRecharge> getMemberRechargeByMemberId(Integer memberId,int start, int limit, String orderBy)throws Exception;
	
	/**
	 * 
	 * 方法名称：getMemberRechargeGridStore<br>
	 * 方法说明:获取充值记录排序 <br>
	 * 创建人: 何扬<br>
	 * 创建日期: 2018-8-29<br>
	 * 
	 * @param memberRecharge
	 * @param start
	 * @param limit
	 * @param orderBy
	 * @return
	 */
	ResultGridStore getMemberRechargeGridStore(MemberRecharge memberRecharge, int start, int limit, String orderBy);
	
	int totalbymemberId(Integer memberId);
}
