package com.surpass.mvc.system.bas.controller;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.surpass.mvc.core.BaseController;
import com.surpass.mvc.system.bas.model.TeacherOpus;
import com.surpass.mvc.system.bas.model.TeacherOpusHot;
import com.surpass.mvc.system.bas.service.TeacherOpusHotService;
import com.surpass.mvc.system.sys.model.TeacherInfo;
import com.surpass.mvc.system.sys.service.TeacherInfoService;
import com.surpass.utils.Constants;
import com.surpass.utils.ResultGridStore;
import com.surpass.utils.ResultMsg;
import com.surpass.utils.StringUtil;
@Controller
@RequestMapping("system/bas/teacherOpusHot")
public class TeacherOpusHotController extends BaseController implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private TeacherOpusHotService service;
	
	@Autowired
	private TeacherInfoService teacherInfoService;
	
	@RequestMapping("queryMain")
	public String queryMain() {
		return Constants.path.RETURN_PATH_SYSTEM + "bas/teacherOpus/teacherOpusHot.jsp";
	}
	
	@RequestMapping("getObjGridStore")
	@ResponseBody
	public ResultGridStore getObjGridStore(TeacherOpus obj, Integer start, Integer limit) {
		ResultGridStore rgs = null;

//		String accountUserId = getLoginUser().getAccountUserId();
//		obj.setAct_user_id(accountUserId);
		try {
			String orderBy = "";
			rgs = service.getGridStore(obj, start, limit, orderBy);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rgs;
	}
	@RequestMapping("add")
	@ResponseBody
	public ResultMsg add(TeacherOpusHot obj) {
		ResultMsg msg = new ResultMsg();
		try {
			String accountUserId = getLoginUser().getAccountUserId();
			TeacherInfo teacherInfo=teacherInfoService.getTeacherInfoByAccoutId(accountUserId);
			obj.setTeacherid(teacherInfo.getId());
			obj.setTeachername(teacherInfo.getNickname());
			service.insert(obj);
			msg.setExecute(true);
			msg.setMsg("新增作品推荐成功!");
		} catch (Exception e) {
			msg.setExecute(false);
			msg.setMsg("新增作品推荐失败!");
		}
		return msg;
	}
	/**
	 * 
	 * 方法名称：delete<br>
	 * 方法说明: 删除<br>
	 * 创建人: pfwang<br>
	 * 创建日期: 2017-2-15<br>
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping("delete")
	@ResponseBody
	public ResultMsg delete(String ids) {
		ResultMsg msg = new ResultMsg();
		if (StringUtil.isNotEmpty(ids)) {
			try {	
				int row = service.delete(ids);
				if (row > 0) {
					msg.setExecute(true);
					msg.setMsg("删除推荐成功!");
				} else {
					msg.setExecute(false);
					msg.setMsg("删除推荐失败!");
				}
			} catch (Exception e) {
				msg.setExecute(false);
				msg.setMsg("删除推荐失败!");
			}
		} else {
			msg.setExecute(false);
			msg.setMsg("数据传输失败!");
		}
		return msg;
	}
}
