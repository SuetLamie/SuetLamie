package com.surpass.mvc.app.android.controller;

import java.io.BufferedOutputStream;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.surpass.mvc.app.android.model.MoneyBuy;
import com.surpass.mvc.app.android.service.AppMemberAccountService;
import com.surpass.mvc.app.android.service.AppOrderAndRebateService;
import com.surpass.mvc.app.android.service.AppUserService;
import com.surpass.mvc.core.BaseController;
import com.surpass.mvc.system.bas.model.Course;
import com.surpass.mvc.system.bas.model.Member;
import com.surpass.mvc.system.bas.model.MemberAccount;
import com.surpass.mvc.system.bas.model.MemberIdentity;
import com.surpass.mvc.system.bas.model.MemberIntegral;
import com.surpass.mvc.system.bas.model.MemberRakeBack;
import com.surpass.mvc.system.bas.model.MemberRecharge;
import com.surpass.mvc.system.bas.model.Order;
import com.surpass.mvc.system.bas.service.CourseService;
import com.surpass.mvc.system.bas.service.MemberService;
import com.surpass.mvc.system.sys.model.AdminIncome;
import com.surpass.mvc.system.sys.model.RackBackSet;
import com.surpass.mvc.system.sys.model.TeacherInfo;
import com.surpass.mvc.system.sys.service.AdminIncomeService;
import com.surpass.mvc.system.sys.service.RackBackSetService;
import com.surpass.mvc.system.sys.service.TeacherInfoService;
import com.surpass.mvc.wechat.auth.model.resp.PayNotifyResult;
import com.surpass.utils.Constants.WechatOpen;
import com.surpass.utils.Constants.orderSet;
import com.surpass.utils.Constants.systemSet;
import com.surpass.utils.IOUtil;
import com.surpass.utils.ResultApp;
import com.surpass.utils.ResultMsg;
import com.surpass.utils.SignatureUtil;
import com.surpass.utils.StringUtil;
import com.surpass.utils.XmlUtil;
@Controller
@RequestMapping("app/bas/order")
public class AppOrderAndRebateController extends BaseController implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private AppOrderAndRebateService service;
	@Autowired
	private AppUserService userService;
	@Autowired
	private AppMemberAccountService accountService;
	@Autowired
	private MemberService memberService;
	@Autowired
	private RackBackSetService rackBackSetService;
	@Autowired
	private AdminIncomeService adminIncomeService;
	@Autowired
	private CourseService courseService;
	@Autowired
	private TeacherInfoService teacherInfoService;
	
	
	/**
	 * 下单
	 * @throws Exception 
	 * **/
	public ResultApp addOrder(Order order) throws Exception {
		ResultApp msg = new ResultApp();
		order.setCreatedate(new Date());
		// 新增订单
		service.insertOrder(order);
		if (order.getType().equals(systemSet.CourseType)) {// 调用课程返佣方法，进行返佣逻辑
			if (order.getMoney() > 1) {
				courseRakeback(order.getMemberid(), order.getOrderno(), order.getMoney(), systemSet.KCFY,
						order.getCourseid());
			}
			if(order.getElectronicmoney()>1){
				courseRakeback(order.getMemberid(), order.getOrderno(), order.getElectronicmoney(), systemSet.KCFY,
						order.getCourseid());
			}
		} else if (order.getType().equals(systemSet.GoodsType)) {// 调用课程返佣方法，进行返佣逻辑
			if (order.getMoney() > 1) {
				goodsRakeback(order.getMemberid(), order.getOrderno(), order.getMoney(), systemSet.CPFY,
						order.getShopid());
			}
			if (order.getElectronicmoney() > 1) {
				goodsRakeback(order.getMemberid(), order.getOrderno(), order.getElectronicmoney(), systemSet.CPFY,
						order.getShopid());
			}
		}
		msg.setMsg("支付成功");
		msg.setSuccess(true);
		return msg;

	}
	/*未写 商城 完善后写此返佣*/
	public void goodsRakeback(int memberid,String orderno,double money,String type,int shopid)
	{
		
	}
	/**增加返佣记录**/
	public void insertRakeBack(int memberid,double money,int personid,String orderid,String type){
		MemberRakeBack mrb=new MemberRakeBack();
		Member memberInfo = memberService.getMemberById(memberid);
		Member personmemberInfo = memberService.getMemberById(personid);
		mrb.setMemberid(memberid);
		mrb.setMembername(memberInfo.getName());
		mrb.setMoney(money);
		mrb.setPersonid(personid);
		mrb.setPersonname(personmemberInfo.getName());
		mrb.setOrderid(orderid);
		mrb.setType(type);
		mrb.setCreatedate(new Date());
		service.insertRakeBack(mrb);
	}
	/**增加平台收入记录*/
	public void addAdminIncome(String orderno,double money,String type){
		AdminIncome ai=new AdminIncome();
		ai.setCreatedate(new Date());
		ai.setMoney(money);
		ai.setOrderid(orderno);
		ai.setType(type);
		adminIncomeService.addAdminIncome(ai);
	}
	
	/**
	 * 课程返佣
	 * */
	public void courseRakeback(int memberid,String orderno,double money,String type,int courseid) throws Exception{
		Member member=memberService.getMemberById(memberid);
		RackBackSet rackBackSet=rackBackSetService.getRackBackSetInfo(type);
		if(StringUtil.isEmpty(member.getLevel())){
			member.setLevel(systemSet.Level);
			memberService.updateMember(member);
		}
		/*会员三级返佣*/
		//有上一级
		if(!StringUtil.isEmpty(member.getPersonno())){
			Member topOne=memberService.selectByCommendNO(member.getPersonno());
			//属于付费会员
			if(!StringUtil.isEmpty(topOne.getLevel())&& !StringUtil.isEmpty(topOne)){
				double oneMoney=money*rackBackSet.getLevelone();
				MemberAccount ma=accountService.getAccountInfo(topOne.getId());
				double sum=ma.getHasmoney()+oneMoney;
				ma.setHasmoney(sum);
				ma.setUpdatetime(new Date());
				accountService.updateAccountByMemberId(ma);
				insertRakeBack(memberid,oneMoney,topOne.getId(),orderno,systemSet.CourseType);
			}
			//有上二级
			if(!StringUtil.isEmpty(topOne.getPersonno())){
				Member topTwo=memberService.selectByCommendNO(topOne.getPersonno());
				//属于付费会员
				if(!StringUtil.isEmpty(topTwo.getLevel())&& !StringUtil.isEmpty(topTwo)){
					double twoMoney=money*rackBackSet.getLeveltwo();
					MemberAccount ma=accountService.getAccountInfo(topTwo.getId());
					double sum=ma.getHasmoney()+twoMoney;
					ma.setHasmoney(sum);
					ma.setUpdatetime(new Date());
					accountService.updateAccountByMemberId(ma);
					insertRakeBack(memberid,twoMoney,topTwo.getId(),orderno,systemSet.CourseType);
				}
				//有上三级
				if(!StringUtil.isEmpty(topTwo.getPersonno())){
					Member topThree=memberService.selectByCommendNO(topTwo.getPersonno());
					if(!StringUtil.isEmpty(topThree.getLevel())&& !StringUtil.isEmpty(topThree)){
						double threeMoney=money*rackBackSet.getLeveltwo();
						MemberAccount ma=accountService.getAccountInfo(topThree.getId());
						double sum=ma.getHasmoney()+threeMoney;
						ma.setHasmoney(sum);
						ma.setUpdatetime(new Date());
						accountService.updateAccountByMemberId(ma);
						insertRakeBack(memberid,threeMoney,topThree.getId(),orderno,systemSet.CourseType);
					}
				}else{//没有上三级会员,此级返佣归平台所有
					double threeMoney=money*rackBackSet.getLeveltwo();
					if(threeMoney!=0){//设置了三级返佣
						addAdminIncome(orderno, threeMoney, systemSet.AdminIncomeCourseType);
					}
				}
			}else{//没有上二级会员,此级返佣归平台所有
				double twoMoney=money*rackBackSet.getLeveltwo();
				addAdminIncome(orderno, twoMoney, systemSet.AdminIncomeCourseType);
			}
		}else{//没有上一级会员,此级返佣归平台所有
			double oneMoney=money*rackBackSet.getLevelone();
			addAdminIncome(orderno, oneMoney, systemSet.AdminIncomeCourseType);
		}
		/*代理返佣*/
		MemberIdentity mi=new MemberIdentity();
		mi.setAgentidentity("1");
		List<MemberIdentity> identitylist=userService.getMemberIdentityList(mi);
		int size=identitylist.size();
		double sumagentMoney=money*rackBackSet.getAgentincome();
		if(size!=0){//有代理商
			double agentMoney=sumagentMoney/size;
			for(MemberIdentity m:identitylist){
				MemberAccount ma=accountService.getAccountInfo(m.getMemberid());
				double sum=ma.getHasmoney()+agentMoney;
				ma.setHasmoney(sum);
				ma.setUpdatetime(new Date());
				accountService.updateAccountByMemberId(ma);
				insertRakeBack(memberid,agentMoney,m.getMemberid(),orderno,systemSet.CourseType);
			}
		}else{//不存在代理商,此收入归平台类型1:购物2.课程3.教师到期返佣4代理到期返佣*/
			addAdminIncome(orderno, sumagentMoney, systemSet.AdminIncomeAgentEndType);
		}
		/*教师收入*/
		Course course=courseService.getcourse(courseid);
		TeacherInfo teacherInfo=teacherInfoService.getTeacherInfoByAccoutId(course.getTeacher_id());
		//此教师存在
		if(!StringUtil.isEmpty(teacherInfo.getMemberid())){
			Member memberInfo=memberService.getMemberById(teacherInfo.getMemberid());
			MemberIdentity memberIdentity=userService.getMemberIdentity(memberInfo.getId());
			double teacherMoney=money*rackBackSet.getTeacherincome();
			if(!StringUtil.isEmpty(memberIdentity.getTeacherenddate())&&memberIdentity.getTeacherenddate().after(new Date())&&memberIdentity.getTeacheridentity().equals("1")){
				MemberAccount ma=accountService.getAccountInfo(memberInfo.getId());
				double sum=ma.getHasmoney()+teacherMoney;
				ma.setHasmoney(sum);
				ma.setUpdatetime(new Date());
				accountService.updateAccountByMemberId(ma);
				insertRakeBack(memberid,teacherMoney,memberInfo.getId(),orderno,systemSet.CourseType);
			}
		}else{//教师不存在，教师收入归平台类型1:购物2.课程3.教师到期返佣4代理到期返佣
			addAdminIncome(orderno, sumagentMoney, systemSet.AdminIncomeTeacherEndType);
		}
		/*平台收入类型1:购物2.课程3.教师到期返佣4代理到期返佣*/
		double adminIncomeMoney=money*rackBackSet.getAdminincome();
		addAdminIncome(orderno, adminIncomeMoney, systemSet.AdminIncomeCourseType);
	}
	/**
	 * 余额购买
	 * **/
	@RequestMapping("moneyBuy")
	@ResponseBody
	public ResultApp moneyBuy(MoneyBuy moneyBuy){
		ResultApp msg = new ResultApp();
		try {
			MemberAccount memberAccount =accountService.getAccountInfo(moneyBuy.getMemberid());
			if(memberAccount.getHasmoney()<moneyBuy.getMoney()){
				msg.setMsg("金额不足,请先充值");
				return msg;
			}
			// 新建订单对象
			Order order = new Order();
			String orderno = UUID.randomUUID().toString().replace("-", "").toLowerCase();
			order.setOrderno(orderno);
			order.setType(moneyBuy.getType());
			order.setMemberid(moneyBuy.getMemberid());
			order.setPaystatus(orderSet.EndPay);
			order.setTradestatus(orderSet.EndTrade);
			order.setDeliverystatus(orderSet.EndDelivery);
			order.setMoney(moneyBuy.getMoney());
			order.setElectronicmoney(moneyBuy.getElectronicmoney());
			if (moneyBuy.getType().equals(systemSet.CourseType)) {
				Course course = courseService.getcourse(moneyBuy.getCourseid());
				// 课程id
				order.setCourseid(moneyBuy.getCourseid());
				order.setName(course.getTitle());
				order.setNum(1);
				//若此课程有积分
				if(course.getJi_fen()>0){
					MemberIntegral mi=new MemberIntegral();
					mi.setPoint(course.getJi_fen());
					mi.setStatus("1");
					mi.setCreatedate(new Date());
					mi.setType("2");
					mi.setMemberid(order.getMemberid());
					accountService.insertMemberIntegral(mi);
					memberAccount.setIntegral(memberAccount.getIntegral()+course.getJi_fen());
				}
			} else if (moneyBuy.getType().equals(systemSet.GoodsType)) {
				// 商品id
				order.setShopid(moneyBuy.getShopid());
			}
			//若使用了电子币的钱,则扣除电子币金额
			if(moneyBuy.getElectronicmoney()!=0){
				memberAccount.setElectronicmoney(memberAccount.getElectronicmoney()-moneyBuy.getElectronicmoney());
			}
			double hasmoney=memberAccount.getHasmoney()-moneyBuy.getMoney();
			memberAccount.setHasmoney(hasmoney);
			memberAccount.setUpdatetime(new Date());
			accountService.updateAccountByMemberId(memberAccount);
			addOrder(order);
			msg.setSuccess(true);
			msg.setMsg("购买成功");
		} catch (Exception e) {
			msg.setSuccess(false);
			msg.setMsg("购买失败,此课程不存在");
		}
		return msg;
	}
	
	/**
	 * 用户取消订单
	 * **/
	@RequestMapping("deleteOrder")
	@ResponseBody
	public ResultApp delete(Order order){
		ResultApp msg = new ResultApp();
		try {
			service.deleteOrder(order);
			msg.setSuccess(true);
			msg.setMsg("删除订单成功");
		} catch (Exception e) {
			msg.setSuccess(false);
			msg.setMsg("删除订单失败"+e.getMessage());
		}
		return msg;
	}
	/**
	 * 更新订单
	 * **/
	@RequestMapping("updateOrder")
	@ResponseBody
	public ResultApp updateOrder(Order order){
		ResultApp msg = new ResultApp();
		try {
			service.updateOrder(order);
			msg.setSuccess(true);
			msg.setMsg("更新订单成功");
		} catch (Exception e) {
			msg.setSuccess(false);
			msg.setMsg("更新订单失败"+e.getMessage());
		}
		return msg;
	}
	/**
	 * 获取用户订单
	 * **/
	@RequestMapping("getOrderByMemberId")
	@ResponseBody
	public ResultApp getOrderByMemberId(Order order,int pageindex, int pagenum){
		ResultApp msg = new ResultApp();
		try {
			List<Order> list=service.getOrderByMemberId(order, pageindex * pagenum, pagenum, null);
			msg.setPageData(list);
			msg.setSuccess(true);
			msg.setMsg("获取用户订单成功");
		} catch (Exception e) {
			msg.setSuccess(false);
			msg.setMsg("获取用户订单失败"+e.getMessage());
		}
		return msg;
	}
	@RequestMapping("notify")
	@ResponseBody
	public ResultMsg acquaint() throws Exception {
		ResultMsg resultState = new ResultMsg();
		String resXml = ""; // 反馈给微信服务器
		String notifyXml = IOUtil.inputStreamToString(this.getRequest().getInputStream(), "UTF-8");// 微信支付系统发送的数据（<![CDATA[product_001]]>格式）
		// 验证签名
		if (SignatureUtil.checkIsSignValidFromWeiXin(notifyXml, WechatOpen.API_KEY, "UTF-8")) {
			PayNotifyResult notify = XmlUtil.getObjectFromXML(notifyXml, PayNotifyResult.class);
			if (Objects.equals("SUCCESS", notify.getResult_code())) {
				resultState.setData(0);// 表示成功
				resultState.setSuccess(true);
				resultState.setMsg(notify.getResult_code());
				/**** 业务逻辑 生成订单****/
				//新建订单对象
				Order order=new Order();
				String [] ary =notify.getAttach().split(",");
				//会员ID
				int memberId=Integer.parseInt(ary[0]);
				double money=notify.getTotal_fee();
				//类型  1课程2商品3充值
				String type =ary[1];
				order.setMemberid(memberId);
				order.setOrderno(notify.getOut_trade_no());
				order.setPaystatus(orderSet.EndPay);
				order.setTradestatus(orderSet.EndTrade);
				order.setDeliverystatus(orderSet.EndDelivery);
				order.setType(type);
				order.setMoney(Double.valueOf(money/100));
				if(order.getType().equals(systemSet.CourseType)){
					//课程id 
					int courseid =Integer.parseInt(ary[2]);
					order.setCourseid(courseid);
					Course course=courseService.getcourse(courseid);
					order.setName(course.getTitle());
					order.setNum(1);
					addOrder(order);
				}else if(order.getType().equals(systemSet.GoodsType)){
					//商品id 
					int shopid =Integer.parseInt(ary[2]);
					order.setShopid(shopid);
					addOrder(order);
				}else if(order.getType().equals(systemSet.RechargeType)){
					MemberRecharge mr=new MemberRecharge();
					MemberAccount memberAccount =accountService.getAccountInfo(order.getMemberid());
					double electronicmoney=0;
					if(!StringUtil.isEmpty(ary[2])){
						electronicmoney=Double.valueOf(ary[2]);
						memberAccount.setElectronicmoney(memberAccount.getElectronicmoney()+electronicmoney);
					}
					mr.setMemberid(order.getMemberid());
					mr.setMoney(order.getMoney()+electronicmoney);
					mr.setStatus("1");
					mr.setType("3");
					double hasmoney=memberAccount.getHasmoney()+order.getMoney();
					memberAccount.setHasmoney(hasmoney);
					memberAccount.setUpdatetime(new Date());
					accountService.updateAccountByMemberId(memberAccount);
					accountService.insertRecharge(mr);
					order.setCreatedate(new Date());
					service.insertOrder(order);
				}
				// 通知微信.异步确认成功.必写.不然会一直通知后台.八次之后就认为交易失败了
				resXml = "<xml><return_code>SUCCESS</return_code><return_msg>OK</return_msg></xml>";
			} else {
				resultState.setData(-1);// 支付失败
				resultState.setMsg(notify.getErr_code_des());
				resultState.setSuccess(false);
				resXml = "<xml>" + "<return_code><![CDATA[FAIL]]></return_code>" + "<return_msg><![CDATA["
						+ notify.getErr_code_des() + "]]></return_msg>" + "</xml> ";
			}
		} else {
			resultState.setData(-1);// 支付失败
			resultState.setMsg("签名验证错误");
			resultState.setSuccess(false);
			resXml = "<xml>" + "<return_code><![CDATA[FAIL]]></return_code>"
					+ "<return_msg><![CDATA[签名验证错误]]></return_msg>" + "</xml> ";
		}
		BufferedOutputStream out = null;
		try {
			out = new BufferedOutputStream(this.getResponse().getOutputStream());
			out.write(resXml.getBytes());
			out.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultState;
	}
	/**
	 * 
	 * 方法名称：getRakeBackByMemberId<br>
	 * 方法说明: 获取用户获得的返佣记录<br>
	 */
	@RequestMapping("getRakeBackByMemberId")
	@ResponseBody
	public ResultApp getRakeBackByMemberId(MemberRakeBack mrb,int pageindex, int pagenum) {
		ResultApp msg = new ResultApp();
		try{
			List<MemberRakeBack> lis = service.getRakeBackByMemberId(mrb, pageindex * pagenum, pagenum, null);
			msg.setPageData(lis);
			msg.setSuccess(true);
			msg.setMsg("查询获得的返佣记录成功");
		}catch (Exception e) {
			msg.setSuccess(false);
			msg.setMsg("查询失败");
		}
		return msg;
	}
	/**
	 * 
	 * 方法名称：getRakeBackByPersonId<br>
	 * 方法说明: 获取用户购买物品返佣给谁的记录<br>
	 */
	@RequestMapping("getRakeBackByPersonId")
	@ResponseBody
	public ResultApp getRakeBackByPersonId(MemberRakeBack mrb,int pageindex, int pagenum) {
		ResultApp msg = new ResultApp();
		try{
			List<MemberRakeBack> lis = service.getRakeBackByPersonId(mrb, pageindex * pagenum, pagenum, null);
			msg.setPageData(lis);
			msg.setSuccess(true);
			msg.setMsg("查询返佣给谁的记录成功");
		}catch (Exception e) {
			msg.setSuccess(false);
			msg.setMsg("查询失败");
		}
		return msg;
	}
}
