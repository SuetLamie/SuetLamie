package com.surpass.mvc.system.sys.controller;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.surpass.mvc.core.BaseController;
import com.surpass.mvc.system.bas.model.Member;
import com.surpass.mvc.system.bas.service.MemberService;
import com.surpass.utils.Constants.HuaXinSendMsg;
import com.surpass.utils.HttpReqUtil;
import com.surpass.utils.MD5Util;
import com.surpass.utils.ResultApp;
import com.surpass.utils.RotaryDrawUtil;

import net.sf.json.JSONObject;
@Controller
@RequestMapping("app/sys/sendMsgController")
public class SendMsgController extends BaseController implements Serializable{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private MemberService memberService;
	/**
     * (获取短信验证码)
     * @param phone 手机号
     * @param ways 1注册 2忘记密码
     * @return String
	 * @throws Exception 
     */
	@RequestMapping("getResult")
	@ResponseBody
    public ResultApp getResult(@RequestParam String phone,String ways){
		ResultApp msg = new ResultApp();
    	String randNum = RotaryDrawUtil.getRandNum();
        String smsContent = "【美妆名师】您的验证码为"+randNum+"，如非本人操作，请忽略此短信。"; //这里的randNum 和 smsContent和上面的静态变量是一样的，可删除可保留
        Member member=memberService.selectByMobilePhone(phone);
        try {
        	if(ways.equals("1")){
        		if(member !=null){
                	msg.setMsg("此手机号已被注册！");
                	return msg;
                }else{
                	Map <Object, Object> statusparams = new HashMap<Object, Object>();// 请求参数
                	statusparams.put("action", "send");
                	statusparams.put("userid", "");
                	statusparams.put("account", HuaXinSendMsg.ACCOUNT);
                	statusparams.put("password", MD5Util.stringMD5(HuaXinSendMsg.PASSWORD));
                	statusparams.put("mobile", phone);
                	statusparams.put("content", smsContent);
                	statusparams.put("sendTime", "");
                	statusparams.put("extno", "");
                	
                	String xml=HttpReqUtil.net(HuaXinSendMsg.BASE_URL, statusparams, "POST");
                	JSONObject objects = JSONObject.fromObject(xml);
                	if(objects.getString("returnstatus").equals("Success")){
                		msg.setPageData(randNum);
                    	msg.setSuccess(true);
                    	msg.setMsg("验证码发送成功");
                    	return msg;
                	}else{
                		msg.setSuccess(false);
                		msg.setMsg("验证码发送失败");
                    	return msg;
                	}
                }
        	}else if(ways.equals("2")){
        		if(member !=null){
        			Map <Object, Object> statusparams = new HashMap<Object, Object>();// 请求参数
                	statusparams.put("action", "send");
                	statusparams.put("userid", "");
                	statusparams.put("account", HuaXinSendMsg.ACCOUNT);
                	statusparams.put("password", MD5Util.stringMD5(HuaXinSendMsg.PASSWORD));
                	statusparams.put("mobile", phone);
                	statusparams.put("content", smsContent);
                	statusparams.put("sendTime", "");
                	statusparams.put("extno", "");
                	
                	String xml=HttpReqUtil.net(HuaXinSendMsg.BASE_URL, statusparams, "POST");
                	JSONObject objects = JSONObject.fromObject(xml);
                	if(objects.getString("returnstatus").equals("Success")){
                		msg.setPageData(randNum);
                    	msg.setSuccess(true);
                    	msg.setMsg("验证码发送成功");
                    	return msg;
                	}else{
                		msg.setSuccess(false);
                		msg.setMsg("验证码发送失败");
                    	return msg;
                	}
                }else{
                	msg.setMsg("无此用户");
                	msg.setSuccess(true);
                	return msg;
                }
        	}
        	
		} catch (Exception e) {
			// TODO: handle exception
		}
		return msg;
    }
}