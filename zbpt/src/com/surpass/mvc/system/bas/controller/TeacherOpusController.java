package com.surpass.mvc.system.bas.controller;

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
import com.surpass.mvc.system.bas.model.TeacherOpus;
import com.surpass.mvc.system.bas.service.TeacherOpusService;
import com.surpass.mvc.system.sys.model.FrmAccountUser;
import com.surpass.mvc.system.sys.model.TeacherInfo;
import com.surpass.mvc.system.sys.service.FrmAccountUserService;
import com.surpass.mvc.system.sys.service.TeacherInfoService;
import com.surpass.utils.Constants;
import com.surpass.utils.FTPUtil;
import com.surpass.utils.ResultGridStore;
import com.surpass.utils.ResultMsg;
import com.surpass.utils.StringUtil;

@Controller
@RequestMapping("system/bas/teacherOpus")
public class TeacherOpusController extends BaseController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static final String ftpBasePath = "teacheropus/";
	
	@Autowired
	private TeacherOpusService service;
	
	@Autowired
	private TeacherInfoService teacherInfoService;
	
	@Autowired
	private FrmAccountUserService frmAccountUserService;
	
	@RequestMapping("queryMain")
	public String queryMain() {
		return Constants.path.RETURN_PATH_SYSTEM + "bas/teacherOpus/teacheropus.jsp";
	}
	
	/**
	 * 
	* 方法名称：getObjGridStoreByTeacher<br>
	* 方法说明: 获取教师自己的作品列表<br>
	* 创建人: 何扬<br>
	* 创建日期: 2018-9-21<br>
	* @param obj
	* @param start
	* @param limit
	* @return
	 */
	@RequestMapping("getObjGridStoreByTeacher")
	@ResponseBody
	public ResultGridStore getObjGridStoreByTeacher(TeacherOpus obj, Integer start, Integer limit) {
		ResultGridStore rgs = null;
		String accountUserId = getLoginUser().getAccountUserId();
		try {
			FrmAccountUser fau=frmAccountUserService.getFrmAccoutUserById(accountUserId);
			TeacherInfo teacherInfo=teacherInfoService.getTeacherInfoByAccoutId(accountUserId);
			if(fau.getUser_type()==Constants.userType.sysUser){//管理员
				obj.setTeacherid(null);
			}else{
				obj.setTeacherid(teacherInfo.getId());
			}
			String orderBy = "";
			rgs = service.getGridStore(obj, start, limit, orderBy);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rgs;
	}
	
	@RequestMapping("add")
	@ResponseBody
	public ResultMsg add(TeacherOpus obj) {
		ResultMsg msg = new ResultMsg();
		try {
			String accountUserId = getLoginUser().getAccountUserId();
			TeacherInfo teacherInfo=teacherInfoService.getTeacherInfoByAccoutId(accountUserId);
			obj.setTeacherid(teacherInfo.getId());
			obj.setTeachernane(teacherInfo.getNickname());
			MultipartHttpServletRequest resources = (MultipartHttpServletRequest) getRequest();
			
			List<MultipartFile> videolist = resources.getFiles("pathFile");
			for (MultipartFile file : videolist) {
				String fileName = file.getOriginalFilename();
				InputStream inputStream = file.getInputStream();
				String suffix = fileName.substring(fileName.lastIndexOf("."));
				String scName = Calendar.getInstance().getTimeInMillis() + "" + (int) (1 + Math.random() * 1000) + suffix;
				String ftpPath = getFilePath(obj.getTeacherid());
				FTPClient ftpUtil = FTPUtil.connectServer();
				FTPUtil.uploadFile(ftpUtil, ftpPath, inputStream, scName);
				String savePath = FTPUtil.getSavePath() + ftpPath + scName;
				obj.setPath(savePath);
			}
			List<MultipartFile> imagelist = resources.getFiles("imgFile");
			for (MultipartFile file : imagelist) {
				String fileName = file.getOriginalFilename();
				InputStream inputStream = file.getInputStream();
				String suffix = fileName.substring(fileName.lastIndexOf("."));
				String scName = Calendar.getInstance().getTimeInMillis() + "" + (int) (1 + Math.random() * 1000) + suffix;
				String ftpPath = getFilePath(obj.getTeacherid());
				FTPClient ftpUtil = FTPUtil.connectServer();
				FTPUtil.uploadFile(ftpUtil, ftpPath, inputStream, scName);
				String savePath = FTPUtil.getSavePath() + ftpPath + scName;
				obj.setImg(savePath);
			}
			obj.setCreatedate(new Date());
			obj.setStatus("1");
			service.insert(obj);
			msg.setExecute(true);
			msg.setMsg("新增作品成功!");
		} catch (Exception e) {
			msg.setExecute(false);
			msg.setMsg("新增作品失败!");
		}
		return msg;
	}

	@RequestMapping("edit")
	@ResponseBody
	public ResultMsg edit(TeacherOpus obj) {
		ResultMsg msg = new ResultMsg();
		try {
			String accountUserId = getLoginUser().getAccountUserId();
			TeacherInfo teacherInfo=teacherInfoService.getTeacherInfoByAccoutId(accountUserId);
			obj.setTeacherid(teacherInfo.getId());
			obj.setTeachernane(teacherInfo.getNickname());

			service.update(obj);
			msg.setExecute(true);
			msg.setMsg("修改成功!");
		} catch (Exception e) {
			msg.setExecute(false);
			msg.setMsg("修改失败!");
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
					msg.setMsg("删除成功!");
				} else {
					msg.setExecute(false);
					msg.setMsg("删除失败!");
				}
			} catch (Exception e) {
				msg.setExecute(false);
				msg.setMsg("删除失败!");
			}
		} else {
			msg.setExecute(false);
			msg.setMsg("数据传输失败!");
		}
		return msg;
	}
	
	private String getFilePath(String loginname) {
		return "/" + ftpBasePath + loginname + "/";
	}
}
