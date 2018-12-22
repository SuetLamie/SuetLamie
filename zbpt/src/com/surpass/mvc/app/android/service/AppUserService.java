package com.surpass.mvc.app.android.service;

import java.util.List;

import com.surpass.mvc.system.bas.model.Member;
import com.surpass.mvc.system.bas.model.MemberAccount;
import com.surpass.mvc.system.bas.model.MemberBrowsing;
import com.surpass.mvc.system.bas.model.MemberIdentity;

public interface AppUserService {
	
	Member login(Member member);
	
	void updatePassword(Member member) throws Exception;
	
	int addAccount(MemberAccount ma) throws Exception;
	
	/**新增会员身份表*/
	int addIdentity(MemberIdentity mi);
	/**更新会员身份表*/
	int updateIdentity(MemberIdentity mi);
	/**获取用户身份*/
	MemberIdentity getMemberIdentity(Integer memberid);
	/**获取代理商或者合伙人*/
	List<MemberIdentity> getMemberIdentityList(MemberIdentity mi);
	/**获取合伙人数量*/
	int totalByPartnerIdentity(MemberIdentity mi);
	/**查询下级会员*/
	List<Member> selectByPersonNO(String commendNo);
	/**查询推荐码*/
	Member selectByCommendNO(String commendNo);
	/**会员信息查询*/
	Member getMemberById(Integer id);
	/**新增会员浏览记录*/
	int addBrowsingHistory(MemberBrowsing mb);
	/**获取会员浏览记录*/
	List<MemberBrowsing> getBrowsingHistoryByMember(MemberBrowsing obj, int start, int limit, String orderBy);
	/**更新会员浏览记录*/
	int updateBrowsingHistory(MemberBrowsing mb);
	/**查询浏览记录是否重复*/
	MemberBrowsing checkBrowsingHistoryByMember(MemberBrowsing mb);

}
