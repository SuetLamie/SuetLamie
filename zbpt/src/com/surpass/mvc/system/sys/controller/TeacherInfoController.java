package com.surpass.mvc.system.sys.controller;

import java.io.InputStream;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.net.ftp.FTPClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.surpass.mvc.core.BaseController;
import com.surpass.mvc.system.sys.model.FrmAccountUser;
import com.surpass.mvc.system.sys.model.TeacherInfo;
import com.surpass.mvc.system.sys.service.FrmAccountUserService;
import com.surpass.mvc.system.sys.service.TeacherInfoService;
import com.surpass.utils.Constants;
import com.surpass.utils.FTPUtil;
import com.surpass.utils.ResultGridStore;
import com.surpass.utils.ResultMsg;

@Controller
@RequestMapping("system/sys/teacher")
public class TeacherInfoController extends BaseController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static final String ftpBasePath = "teacherInfo/";
	
	@Autowired
	private TeacherInfoService service;
	
	@Autowired
	private FrmAccountUserService frmAccountUserService;
	
	@RequestMapping("queryMain")
	public String queryMain() {
		return Constants.path.RETURN_PATH_SYSTEM + "sys/teacher/teacher.jsp";
	}
	
	/**
	 * 
	 * 方法名称：getObjGridStore<br>
	 * 方法说明: 获取列表<br>
	 * @param role
	 * @param start
	 * @param limit
	 * @return
	 */
	@RequestMapping("getObjGridStore")
	@ResponseBody
	public ResultGridStore getObjGridStore(TeacherInfo teacherInfo, Integer start, Integer limit) {
		ResultGridStore rgs = null;
		
		String accountUserId = getLoginUser().getAccountUserId();
		
		try {
			FrmAccountUser fau=frmAccountUserService.getFrmAccoutUserById(accountUserId);
			if(fau.getUser_type()==Constants.userType.sysUser){//管理员
				teacherInfo.setAccountid(null);
			}else{
				teacherInfo.setAccountid(accountUserId);
			}
			String orderBy = "";
			rgs = service.getGridStore(teacherInfo, start, limit, orderBy);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rgs;
	}
	
	@RequestMapping("add")
	@ResponseBody
	public ResultMsg add(TeacherInfo teacherInfo) {
		ResultMsg msg = new ResultMsg();
		try {
			teacherInfo.setAccountid(getLoginUser().getAccountUserId());
			MultipartHttpServletRequest resources = (MultipartHttpServletRequest) getRequest();
			List<MultipartFile> videolist = resources.getFiles("pathFile");
			for (MultipartFile file : videolist) {
				String fileName = file.getOriginalFilename();
				InputStream inputStream = file.getInputStream();
				String suffix = fileName.substring(fileName.lastIndexOf("."));
				String scName = Calendar.getInstance().getTimeInMillis() + "" + (int) (1 + Math.random() * 1000) + suffix;
				String ftpPath = getFilePath(getLoginUser().getAccount());
				FTPClient ftpUtil = FTPUtil.connectServer();
				FTPUtil.uploadFile(ftpUtil, ftpPath, inputStream, scName);
				String savePath = FTPUtil.getSavePath() + ftpPath + scName;
				teacherInfo.setPicture(savePath);
			}
			teacherInfo.setCreatedate(new Date());
			service.insert(teacherInfo);
			msg.setExecute(true);
			msg.setMsg("新增教师成功!");
		} catch (Exception e) {
			msg.setExecute(false);
			msg.setMsg("新增教师失败!");
		}
		return msg;
	}
	@RequestMapping("edit")
	@ResponseBody
	public ResultMsg edit(TeacherInfo teacherInfo) {
		ResultMsg msg = new ResultMsg();
		try {
			teacherInfo.setAccountid(getLoginUser().getAccountUserId());
			service.update(teacherInfo);
			msg.setExecute(true);
			msg.setMsg("修改老师成功!");
		} catch (Exception e) {
			msg.setExecute(false);
			msg.setMsg("修改老师失败!");
		}
		return msg;
	}
	private String getFilePath(String loginname) {
		return "/" + ftpBasePath + loginname + "/";
	}
}
