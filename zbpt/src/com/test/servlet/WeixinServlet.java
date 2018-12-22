package com.test.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.aspectj.bridge.MessageUtil;
import org.dom4j.DocumentException;

import com.surpass.utils.SignatureUtil;

public class WeixinServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		 String signature = req.getParameter("signature");
		 String timestamp = req.getParameter("timestamp");
		 String nonce = req.getParameter("nonce");
		 String echostr = req.getParameter("echostr");
		 
		 PrintWriter out =resp.getWriter();
		 
		 if(SignatureUtil.checkSignature(signature, timestamp, nonce)){
			 out.print(echostr);
		 }
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		PrintWriter out= resp.getWriter();
		try {
			
//				TextMessage text=new TextMessage();
//				text.setFromUserName(toUserName);
//				text.setToUserName(fromUserName);
//				text.setMsgType("text");
//				text.setCreateTime(new Date().getTime());
//				text.setContent("您发送的消息是:"+content);
//				message= MessageUtil.textMessageToXml(text);
//				System.out.println(message);
		}finally {
			out.close();
		}
	}
}
