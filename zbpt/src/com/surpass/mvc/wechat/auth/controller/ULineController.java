package com.surpass.mvc.wechat.auth.controller;

import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import javax.xml.parsers.ParserConfigurationException;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.xml.sax.SAXException;

import com.github.wxpay.sdk.WXPayUtil;
import com.surpass.mvc.core.BaseController;
import com.surpass.mvc.wechat.auth.model.req.UnifiedOrderParams;
import com.surpass.mvc.wechat.auth.model.resp.JsPayResult;
import com.surpass.mvc.wechat.auth.model.resp.UnifiedOrderResult;
import com.surpass.utils.Constants.SystemConstant;
import com.surpass.utils.Constants.WechatConfig;
import com.surpass.utils.Constants.WechatOpen;
import com.surpass.utils.HttpReqUtil;
import com.surpass.utils.MsgUtil;
import com.surpass.utils.SignatureUtil;
import com.surpass.utils.XmlUtil;
@Controller
@RequestMapping("app/uline/pay")
public class ULineController extends BaseController implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * app支付接口微信支付
	 * 
	 * @param params
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("wechatAppPay")
	@ResponseBody
	public Map<String, Object> wechatAppPay(@ModelAttribute(value = "params") UnifiedOrderParams params) {
		Map<String, Object> data = new HashMap<>();
		JsPayResult result = null;
		if (params.equals(null)) {
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
		unifiedOrderParams.setAppid(WechatOpen.APP_ID);// 必须
		unifiedOrderParams.setMch_id(WechatOpen.MCH_ID);// 必须
		unifiedOrderParams.setOut_trade_no(out_trade_no);// 必须
		unifiedOrderParams.setBody(params.getBody());// 必须 微信支付-支付测试
		unifiedOrderParams.setAttach(params.getAttach());// 附加数据 格式 会员编号,商品类型,商品id
		unifiedOrderParams.setTotal_fee(params.getTotal_fee()); // 必须
		unifiedOrderParams.setNonce_str(nonce_str); // 必须
		unifiedOrderParams.setSpbill_create_ip(spbill_create_ip); // 必须
		unifiedOrderParams.setTrade_type(WechatOpen.Trade_Type); // 必须
		unifiedOrderParams.setNotify_url(WechatConfig.NOTIFY_URL);// 异步通知url
		// 统一下单 请求的Xml(正常的xml格式)
		String unifiedXmL = MsgUtil.abstractPayToXml(unifiedOrderParams);// 签名并入util
		// 返回<![CDATA[SUCCESS]]>格式的XML
		String unifiedOrderResultXmL = HttpReqUtil.HttpsDefaultExecute(SystemConstant.POST_METHOD,
				WechatConfig.UNIFIED_ORDER_URL, null, unifiedXmL, "UTF-8");
		// 进行签名校验
		try {
			if (SignatureUtil.checkIsSignValidFromWeiXin(unifiedOrderResultXmL, WechatOpen.API_KEY, "UTF-8")) {
				String timeStamp = WXPayUtil.createTimeStamp();
				// 统一下单响应
				UnifiedOrderResult unifiedOrderResult = XmlUtil.getObjectFromXML(unifiedOrderResultXmL,
						UnifiedOrderResult.class);
				Map<String, Object> parameterMap= new TreeMap<String, Object>();
				parameterMap.put("appid", WechatOpen.APP_ID);
		        parameterMap.put("partnerid",WechatOpen.MCH_ID);
		        parameterMap.put("prepayid", unifiedOrderResult.getPrepay_id());
		        parameterMap.put("package", "Sign=WXPay");
		        parameterMap.put("noncestr", unifiedOrderResult.getNonce_str());
		        parameterMap.put("timestamp",timeStamp);
		        String sign = SignatureUtil.createSign(parameterMap, WechatOpen.API_KEY,
						SystemConstant.DEFAULT_CHARACTER_ENCODING);
		        parameterMap.put("sign",sign);
				result = new JsPayResult();
				result.setPrepayid(unifiedOrderResult.getPrepay_id());
				result.setTimeStamp(timeStamp);
				result.setNonceStr(unifiedOrderResult.getNonce_str());// 直接用返回的
				result.setPaySign(sign);
				result.setResultCode(unifiedOrderResult.getResult_code());
				data.put("code", 0);
				data.put("msg", "支付成功");
				data.put("success", true);
				data.put("pageData", result);
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
}
