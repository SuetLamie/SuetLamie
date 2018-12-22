package com.surpass.mvc.wechat.auth.controller;

import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.xml.sax.SAXException;

import com.github.wxpay.sdk.WXPayUtil;
import com.surpass.mvc.core.BaseController;
import com.surpass.mvc.wechat.auth.model.req.AuthCodeParams;
import com.surpass.mvc.wechat.auth.model.req.AuthTokenParams;
import com.surpass.mvc.wechat.auth.model.req.UnifiedOrderParams;
import com.surpass.mvc.wechat.auth.model.resp.AuthAccessToken;
import com.surpass.mvc.wechat.auth.model.resp.AuthUserInfo;
import com.surpass.mvc.wechat.auth.model.resp.JsPayResult;
import com.surpass.mvc.wechat.auth.model.resp.UnifiedOrderResult;
import com.surpass.mvc.wechat.auth.service.WechatAuthService;
import com.surpass.mvc.weixin.miniprogram.model.MiniprogramUser;
import com.surpass.mvc.weixin.miniprogram.service.MiniprogramUserService;
import com.surpass.utils.Constants;
import com.surpass.utils.Constants.PayConstant;
import com.surpass.utils.Constants.SystemConstant;
import com.surpass.utils.Constants.WechatConfig;
import com.surpass.utils.HttpReqUtil;
import com.surpass.utils.MD5;
import com.surpass.utils.MsgUtil;
import com.surpass.utils.ResultMsg;
import com.surpass.utils.SignatureUtil;
import com.surpass.utils.StringUtil;
import com.surpass.utils.XmlUtil;

/**
 * JS-SDK
 * 
 * @author phil
 * @date 2017年8月21日
 *
 */
@Controller
@RequestMapping("app/wechat/wxauthController")
public class WechatAuthController extends BaseController implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	private MiniprogramUserService wechatUserInfoservice;
	
	@Autowired
	private WechatAuthService wechatAuthService;
	
	/** 
	 * 静默授权进入H5支付页面 
	 * @return 
	 * @throws Exception 
	 *//*  
	@GetMapping("preJsPay")
	public String jsPay( ) throws Exception {  
	    AuthAccessToken authAccessToken = null;  
	    String code = this.getRequest().getParameter("code");
//	    Optional<String> code_ = Optional.of(code);
//	    return code_.orElse("error");
	    if(StringUtils.isEmpty(code)){
	    	return "error";
	    }
	    String state = this.getRequest().getParameter("state");  
	    if(state.equals(MD5.MD5Encode("ceshi", ""))){
	        AuthTokenParams authTokenParams = new AuthTokenParams();  
	        authTokenParams.setAppid(WechatConfig.APP_ID);
	        authTokenParams.setSecret(WechatConfig.APP_SECRET);  
	        authTokenParams.setCode(code);
	        authAccessToken = wechatAuthService.getAuthAccessToken(authTokenParams, null);
	        logger.debug("正在支付的openid {} ", authAccessToken.getOpenid());  
	        return "wxpay/jspay";
	    }
	    return "error";
	}  */

	@RequestMapping("oauthUrl")
	@ResponseBody
	public ResultMsg oauthUrl() throws Exception {
		ResultMsg msg = new ResultMsg();
		AuthCodeParams authCodeParams = new AuthCodeParams();
		authCodeParams.setRedirect_uri(WechatConfig.REDIRECT_URL);
		authCodeParams.setAppid(WechatConfig.APP_ID);
		authCodeParams.setScope(AuthCodeParams.SCOPE_SNSPAIUSERINFO);// 弹出授权权页面(获取用户基本信息)
		authCodeParams.setState(MD5.MD5Encode("ceshi", "")); //防止被攻击,用于校验
		String url = wechatAuthService.getAuthPath(authCodeParams, WechatConfig.AUTHORIZE_OAUTH_URL);
		msg.setSuccess(true);
		msg.setData(url);
		return msg;
	}
	
	@RequestMapping("getCodeDate")
	@ResponseBody
	public ResultMsg getCodeDate(@RequestParam String code) throws Exception {
		System.out.println(code);
		ResultMsg msg = new ResultMsg();
		AuthTokenParams authTokenParams = new AuthTokenParams();
		authTokenParams.setAppid(WechatConfig.APP_ID);
		authTokenParams.setSecret(WechatConfig.APP_SECRET);
		authTokenParams.setCode(code);
		AuthAccessToken auth=wechatAuthService.getAuthAccessToken(authTokenParams, WechatConfig.GET_OAUTH_TOKEN_URL);
		if(!StringUtil.isEmpty(auth)){
			AuthUserInfo aui=wechatAuthService.getAuthUserInfo(auth.getAccess_token(), auth.getOpenid());
			MiniprogramUser user = new MiniprogramUser();
			user=wechatUserInfoservice.selectByPrimaryKey(aui.getOpenid());
			if(user!=null){
				msg.setData(user);
				msg.setSuccess(true);
				msg.setMsg("数据库中有此用户，进数据库后查询到此用户返回到前端页面");
				return msg;
			}else{
				MiniprogramUser userinfo = new MiniprogramUser();
				userinfo.setAvatarUrl(aui.getHeadimgurl());
				userinfo.setCity(aui.getCity());
				userinfo.setCountry(aui.getCountry());
				userinfo.setNickName(aui.getNickname());
				userinfo.setGender(aui.getSex());
				userinfo.setOpenId(aui.getOpenid());
				userinfo.setProvince(aui.getProvince());
				wechatUserInfoservice.insertSelective(userinfo);
				MiniprogramUser userI=wechatUserInfoservice.selectByPrimaryKey(aui.getOpenid());
				msg.setData(userI);
				msg.setSuccess(true);
				msg.setMsg("此前没有此用户，新增此用户进数据库后查询到此用户返回到前端页面");
				return msg;
			}
		}else{
			msg.setData(null);
			msg.setSuccess(false);
			msg.setMsg("刷新页面");
			return msg;
		}
		
	}
	/**
	 * 微信内H5调起支付
	 * 
	 * @param params
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("jsPay")
	@ResponseBody
	public Map<String, Object> jsPay(@ModelAttribute(value = "params") UnifiedOrderParams params) {
		Map<String, Object> data = new HashMap<>();
		JsPayResult result = null;
		if (params.equals(null) || params.getOpenid()==null) {
			data.put("code", -1);
			data.put("msg", "支付数据错误");
			return data;
		}
		// 统一下单
		String out_trade_no = WXPayUtil.createOutTradeNo();
		//int total_fee = 1; // 产品价格1分钱,用于测试
		String spbill_create_ip = HttpReqUtil.getRemortIP(this.getRequest());
		String nonce_str = WXPayUtil.generateNonceStr(); // 随机数据
		// 参数组装
		UnifiedOrderParams unifiedOrderParams = new UnifiedOrderParams();
		unifiedOrderParams.setAppid(WechatConfig.APP_ID);// 必须
		unifiedOrderParams.setMch_id(PayConstant.MCH_ID);// 必须
		unifiedOrderParams.setOut_trade_no(out_trade_no);// 必须
		unifiedOrderParams.setBody(params.getBody());// 必须 微信支付-支付测试
		unifiedOrderParams.setAttach(params.getAttach());// 附加数据
		unifiedOrderParams.setTotal_fee(params.getTotal_fee()); // 必须
		unifiedOrderParams.setNonce_str(nonce_str); // 必须
		unifiedOrderParams.setSpbill_create_ip(spbill_create_ip); // 必须
		unifiedOrderParams.setTrade_type("JSAPI"); // 必须
		unifiedOrderParams.setOpenid(params.getOpenid());
		unifiedOrderParams.setNotify_url(WechatConfig.NOTIFY_URL);// 异步通知url
		// 统一下单 请求的Xml(正常的xml格式)
		String unifiedXmL = MsgUtil.abstractPayToXml(unifiedOrderParams);// 签名并入util
		// 返回<![CDATA[SUCCESS]]>格式的XML
		String unifiedOrderResultXmL = HttpReqUtil.HttpsDefaultExecute(SystemConstant.POST_METHOD,
				WechatConfig.UNIFIED_ORDER_URL, null, unifiedXmL, "UTF-8");
		// 进行签名校验
		try {
			if (SignatureUtil.checkIsSignValidFromWeiXin(unifiedOrderResultXmL, PayConstant.API_KEY, "UTF-8")) {
				String timeStamp = WXPayUtil.createTimeStamp();
				// 统一下单响应
				UnifiedOrderResult unifiedOrderResult = XmlUtil.getObjectFromXML(unifiedOrderResultXmL,
						UnifiedOrderResult.class);
				result = new JsPayResult();
				result.setAppId(WechatConfig.APP_ID);
				result.setTimeStamp(timeStamp);
				result.setNonceStr(unifiedOrderResult.getNonce_str());// 直接用返回的
				/**** prepay_id 2小时内都有效，再次支付方法自己重写 ****/
				result.setPackageStr("prepay_id=" + unifiedOrderResult.getPrepay_id());
				/**** 用对象进行签名 ****/
				String paySign = SignatureUtil.createSign(result, PayConstant.API_KEY,
						SystemConstant.DEFAULT_CHARACTER_ENCODING);
				result.setPaySign(paySign);
				result.setResultCode(unifiedOrderResult.getResult_code());
				data.put("code", 0);
				data.put("msg", "支付成功");
				data.put("success", true);
				data.put("data", result);
			} else {
				data.put("code", -1);
				data.put("success", false);
				data.put("msg", "支付签名验证错误");
			}
		} catch (ParserConfigurationException | IOException | SAXException e) {
			data.put("code", -1);
			data.put("success", false);
			data.put("msg", "支付失败");
		}
		return data;
	}
	
	/**
	 * 
	 * 方法名称：queryMain<br>
	 * 方法说明:前台管理首页<br>
	 * 创建人: 何扬<br>
	 * 创建日期: 2018-8-11<br>
	 * 
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("queryMain")
	public String queryMain() throws Exception {
		return Constants.path.RETURN_PATH_HTML + "index.html";
	}
}