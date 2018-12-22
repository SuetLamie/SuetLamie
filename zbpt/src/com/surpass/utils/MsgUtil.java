package com.surpass.utils;

import com.surpass.mvc.wechat.auth.model.req.AbstractPayParams;
import com.surpass.mvc.wechat.auth.model.resp.RespAbstractMsg;
import com.surpass.utils.Constants.WechatOpen;
import com.surpass.utils.XmlUtil.XStreamFactroy;
import com.thoughtworks.xstream.XStream;

/**
 * 默认请求消息处理类
 * 
 * @author phil
 * 
 */
public class MsgUtil {

	/**
	 * 将java对象转换为xml
	 * 
	 * @param msg
	 * @return
	 */
	public static String msgToXml(RespAbstractMsg msg) {
		String result = "";
		if (msg != null) {
			XStream xs = XStreamFactroy.init(true);
			xs.alias("xml", msg.getClass());
			result = xs.toXML(msg);
		}
		return result;
	}

	/**
	 * 支付参数
	 * @param params
	 * @return
	 */
	public static String abstractPayToXml(AbstractPayParams params) {
//		String sign1=WXPayUtil.generateNonceStr(params,)
		String sign = SignatureUtil.createSign(params, WechatOpen.API_KEY, "UTF-8");
		params.setSign(sign);
		return XmlUtil.toSplitXml(params);
	}
}