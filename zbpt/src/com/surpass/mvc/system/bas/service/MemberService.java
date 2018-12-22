package com.surpass.mvc.system.bas.service;

import java.util.List;

import com.surpass.mvc.system.bas.model.Member;
import com.surpass.utils.ResultGridStore;

/**
 * 类名称： MemberService<br>
 * 类描述： 会员信息管理 <br>
 * 创建人： 何扬<br>
 * 创建时间：2018-8-1<br>

 */
public interface MemberService {
	
	/**
	 * 
	 * 方法名称：getMemberGridStore<br>
	 * 方法说明:获取会员列表排序 <br>
	 * 创建人: 何扬<br>
	 * 创建日期: 2018-8-1<br>
	 * 
	 * @param member
	 * @param start
	 * @param limit
	 * @param orderBy
	 * @return
	 */
	ResultGridStore getMemberGridStore(Member member, int start, int limit, String orderBy);
	
	/**
	 * 
	 * 方法名称：insert<br>
	 * 方法说明: 前端新增注册会员<br>
	 * 创建人: 何扬<br>
	 * 创建日期: 2018-8-1<br>
	 * 
	 * @param member
	 * @throws Exception
	 */
	void insert(Member member) throws Exception;
	
	/**
	 * 
	 * 方法名称：selectByTopLevel<br>
	 * 方法说明: 查询会员上级<br>
	 * 创建人: 何扬<br>
	 * 创建日期: 2018-8-1<br>
	 * 
	 * @param member
	 * @throws Exception
	 */
	int selectByTopLevel(Integer id) throws Exception;
	
	/**
	 * 
	 * 方法名称：updateMember<br>
	 * 方法说明: 修改会员<br>
	 * 创建人: 何扬<br>
	 * 创建日期: 2018-8-1<br>
	 * 
	 * @param member
	 * @throws Exception
	 */
	void updateMember(Member member) throws Exception;
	
	/**
	 * 
	 * 方法名称：deleteMember<br>
	 * 方法说明: 删除会员<br>
	 * 创建人: 何扬<br>
	 * 创建日期: 2018-8-1<br>
	 * 
	 * @param ids
	 * @throws Exception
	 */
	int deleteMember(String ids) throws Exception;
	/**
	 * 
	 * 方法名称：verifyMember<br>
	 * 方法说明: <br>
	 * 创建人: 何扬<br>
	 * 创建日期: 2018-8-1<br>
	 * 
	 * @param member
	 * @return
	 */
	int verifyMember(Member member);
	
	/**
	 * 
	 * 方法名称：getMemberById<br>
	 * 方法说明: 通过会员编号获取会员信息<br>
	 * 创建人: 何扬<br>
	 * 创建日期: 2018-8-1<br>
	 * 
	 * @param id
	 * @return
	 */
	Member getMemberById(Integer id);
	/**
	 * 
	 * 方法名称：selectByCommendNO<br>
	 * 方法说明: 查询推荐码<br>
	 * 创建人: 何扬<br>
	 * 创建日期: 2018-8-2<br>
	 * 
	 * @param commendNo
	 * @return
	 */
	Member selectByCommendNO(String commendNo);
	
	/**
	 * 
	 * 方法名称：selectByMobilePhone<br>
	 * 方法说明: 查询手机号<br>
	 * 创建人: 何扬<br>
	 * 创建日期: 2018-8-18<br>
	 * 
	 * @param mobilePhone
	 * @return
	 */
	Member selectByMobilePhone(String mobilePhone);
	/**
	 * 
	 * 方法名称：getMemberByOpenId<br>
	 * 方法说明: 通过OpenId获取会员信息<br>
	 * 创建人: 何扬<br>
	 * 创建日期: 2018-8-23<br>
	 * 
	 * @param openId
	 * @return
	 */
	Member getMemberByOpenId(String openId);
	
	/**
	 * 
	 * 方法名称：selectByPersonNO<br>
	 * 方法说明: 查询推荐码<br>
	 * 创建人: 何扬<br>
	 * 创建日期: 2018-8-2<br>
	 * 
	 * @param commendNo
	 * @return
	 */
	List<Member> selectByPersonNO(String commendNo);
}
