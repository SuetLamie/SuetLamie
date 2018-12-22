package com.surpass.mvc.wechat.auth.service.impl;

import java.util.Map;
import java.util.TreeMap;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.google.gson.JsonSyntaxException;
import com.surpass.mvc.wechat.auth.model.resp.AccessToken;
import com.surpass.mvc.wechat.auth.model.resp.AuthAccessToken;
import com.surpass.mvc.wechat.auth.model.resp.AuthUserInfo;
import com.surpass.mvc.wechat.auth.model.resp.JsapiTicket;
import com.surpass.mvc.wechat.auth.service.WechatAuthService;
import com.surpass.mvc.wechat.base.param.AbstractParams;
import com.surpass.mvc.wechat.base.result.ResultState;
import com.surpass.utils.Constants.SystemConstant;
import com.surpass.utils.Constants.WechatConfig;
import com.surpass.utils.HttpReqUtil;
import com.surpass.utils.JsonUtil;

/**
 * Wechat Auth Service
 * 
 * @author phil
 * @date 2017年7月9日
 */
@Service("wechatAuthService")
public class WechatAuthServiceImpl implements WechatAuthService {

	private static final Logger LOG4J = LoggerFactory.getLogger(WechatAuthServiceImpl.class);
	
	/**
	 * 获取授权凭证token
	 * 
	 * @param key
	 *            应用appid
	 * @param secret
	 *            应用密匙
	 * @return json格式的字符串
	 */
	public static String getAccessToken(String appid, String secret) {
		TreeMap<String, String> map = new TreeMap<String, String>();
		map.put("grant_type", "client_credential");
		map.put("appid", appid);
		map.put("secret", secret);
		String json = HttpReqUtil.HttpDefaultExecute(SystemConstant.GET_METHOD, WechatConfig.GET_ACCESS_TOKEN_URL, map,
				"", SystemConstant.DEFAULT_CHARACTER_ENCODING);
		String result = null;
		AccessToken accessToken = JsonUtil.fromJsonString(json, AccessToken.class);
		if (accessToken != null) {
			result = accessToken.getAccess_token();
		}
		return result;
	}

	/**
	 * 获取授权请求url
	 * 
	 * @param basic
	 * @param url
	 * @return
	 * @throws Exception
	 */
	public String getAuthPath(AbstractParams basic, String url) throws Exception {
		Map<String, String> params = basic.getParams();
		url = HttpReqUtil.setParmas(params, url, "") + "#wechat_redirect";
		return url;
	}
	
	/**
	 * 获取网页授权凭证
	 * 
	 * @param basic
	 * @param url
	 * @return
	 */
	public AuthAccessToken getAuthAccessToken(AbstractParams basic, String url) {
		AuthAccessToken authAccessToken = null;
		// 获取网页授权凭证
		try {
			if (StringUtils.isEmpty(url)) {
				url = WechatConfig.GET_OAUTH_TOKEN_URL;
			}
			String result = HttpReqUtil.HttpsDefaultExecute(SystemConstant.GET_METHOD, url, basic.getParams(), null, SystemConstant.DEFAULT_CHARACTER_ENCODING);
			authAccessToken = JsonUtil.fromJsonString(result, AuthAccessToken.class);
		} catch (Exception e) {
			LOG4J.debug("error" + e.getMessage());
		}
		return authAccessToken;
	}

	/**
	 * 刷新网页授权验证
	 * 
	 * @param basic
	 *            参数
	 * @param url
	 *            请求路径
	 * @return
	 */
	public AuthAccessToken refreshAuthAccessToken(AbstractParams basic, String url) {
		AuthAccessToken authAccessToken = null;
		// 刷新网页授权凭证
		try {
			if (StringUtils.isEmpty(url)) {
				url = WechatConfig.REFRESH_OAUTH_TOKEN_URL;
			}
			String result = HttpReqUtil.HttpsDefaultExecute(SystemConstant.GET_METHOD, url, basic.getParams(), null, SystemConstant.DEFAULT_CHARACTER_ENCODING);
			authAccessToken = JsonUtil.fromJsonString(result, AuthAccessToken.class);
		} catch (Exception e) {
			LOG4J.debug("error" + e.getMessage());
		}
		return authAccessToken;
	}

	/**
	 * 通过网页授权获取用户信息
	 * 
	 * @param accessToken
	 * @param openid
	 * @return
	 */
	public AuthUserInfo getAuthUserInfo(String accessToken, String openid) {
		AuthUserInfo authUserInfo = null;
		// 通过网页授权获取用户信息
		Map<String, String> params = new TreeMap<String, String>();
		params.put("openid", openid);
		params.put("access_token", accessToken);
		String result = HttpReqUtil.HttpsDefaultExecute(SystemConstant.GET_METHOD, WechatConfig.SNS_USERINFO_URL, params,
				null, SystemConstant.DEFAULT_CHARACTER_ENCODING);
		try {
			authUserInfo = JsonUtil.fromJsonString(result, AuthUserInfo.class);
		} catch (JsonSyntaxException e) {
			LOG4J.debug("transfer exception");
		}
		return authUserInfo;
	}

	/**
	 * 检验授权凭证（access_token）是否有效
	 * 
	 * @param accessToken
	 *            网页授权接口调用凭证
	 * @param openid
	 *            用户的唯一标识
	 * @return { "errcode":0,"errmsg":"ok"}表示成功 { "errcode":40003,"errmsg":"invalid
	 *         openid"}失败
	 */
	public ResultState authToken(String accessToken, String openid) {
		ResultState state = null;
		Map<String, String> params = new TreeMap<String, String>();
		params.put("access_token", accessToken);
		params.put("openid", openid);
		String jsonResult = HttpReqUtil.HttpDefaultExecute(SystemConstant.GET_METHOD,
				WechatConfig.CHECK_SNS_AUTH_STATUS_URL, params, "", SystemConstant.DEFAULT_CHARACTER_ENCODING);
		state = JsonUtil.fromJsonString(jsonResult, ResultState.class);
		return state;
	}

	/**
	 * 获取jsapi_ticket 调用微信JS接口的临时票据
	 * 
	 * @return
	 */
	public String getTicket(String accessToken) {
		JsapiTicket jsapiTicket = null;
		Map<String, String> params = new TreeMap<String, String>();
		params.put("access_token", accessToken);
		params.put("type", "jsapi");
		String result = HttpReqUtil.HttpDefaultExecute(SystemConstant.GET_METHOD, WechatConfig.GET_TICKET_URL, params,
				"", SystemConstant.DEFAULT_CHARACTER_ENCODING);
		jsapiTicket = JsonUtil.fromJsonString(result, JsapiTicket.class);
		if (jsapiTicket.getErrcode() == 0) {
			return jsapiTicket.getTicket();
		}
		return null;
	}
}