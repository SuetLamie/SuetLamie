package com.surpass.mvc.app.android.dao;

import java.util.List;
import java.util.Map;

import com.surpass.mvc.system.bas.model.Member;
import com.surpass.mvc.system.bas.model.MemberAccount;
import com.surpass.mvc.system.bas.model.MemberBrowsing;
import com.surpass.mvc.system.bas.model.MemberIdentity;

public interface AppUserMapper {
	
	Member login(Member member);
	
	/*会员信息更新*/
	int updateByPhoneSelective(Member member);
	/*新增账户*/
	int addAccount(MemberAccount ma);
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
	/** 方法说明: 查询下级会员*/
	List<Member> selectByPersonNO(String commendNo);
	/** 方法说明: 查询推荐码*/
	Member selectByCommendNO(String commendNo);
	/**会员信息查询*/
	Member getMemberById(Integer id);
	/**新增会员浏览记录*/
	int addBrowsingHistory(MemberBrowsing mb);
	/**获取会员浏览记录*/
	List<MemberBrowsing> getBrowsingHistoryByMember(Map<String, Object> build);
	/**更新会员浏览记录*/
	int updateBrowsingHistory(MemberBrowsing mb);
	/**查询浏览记录是否重复*/
	MemberBrowsing checkBrowsingHistoryByMember(MemberBrowsing mb);
}
