/**
 *
 */
package com.surpass.mvc.system.bas.controller;

import java.io.InputStream;
import java.io.Serializable;
import java.util.Calendar;
import java.util.List;

import org.apache.commons.net.ftp.FTPClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.surpass.mvc.core.BaseController;
import com.surpass.mvc.system.bas.model.Course;
import com.surpass.mvc.system.bas.model.Courseware;
import com.surpass.mvc.system.bas.service.CourseService;
import com.surpass.mvc.system.bas.service.CoursewareService;
import com.surpass.utils.Constants;
import com.surpass.utils.FTPUtil;
import com.surpass.utils.ResultGridStore;
import com.surpass.utils.ResultMsg;
import com.surpass.utils.StringUtil;

/**
 * 类名称： CoursewareController<br>
 * 类描述： 课件管理<br>
 * 创建人： 王鹏飞<br>
 * 创建时间：2018-8-6<br>
 * 修改人： 王鹏飞<br>
 * 修改时间：2018-8-6
 */
@Controller
@RequestMapping("system/bas/courseware")
public class CoursewareController extends BaseController implements Serializable {

	private static final String ftpBasePath = "courseware/";

	/**
	 * CoursewareController.java
	 */
	private static final long serialVersionUID = 1L;

	private static final Logger LOG4J = LoggerFactory.getLogger(CoursewareController.class);

	@Autowired
	private CoursewareService service;
	@Autowired
	private CourseService courseService;

	/**
	 * 页面跳转 方法名称：queryMain<br>
	 * 方法说明: 我的课件页面<br>
	 * 创建人: 王鹏飞<br>
	 * 创建日期: 2016-3-28<br>
	 * 
	 * @return
	 */
	@RequestMapping("queryMain")
	public String queryMain() {
		return Constants.path.RETURN_PATH_SYSTEM + "bas/courseware/courseware.jsp";
	}

	/**
	 * 
	 * 方法名称：getObjGridStore<br>
	 * 方法说明: 获取列表<br>
	 * 创建人: pfwang<br>
	 * 创建日期: 2016-11-8<br>
	 * 
	 * @param role
	 * @param start
	 * @param limit
	 * @return
	 */
	@RequestMapping("getObjGridStore")
	@ResponseBody
	public ResultGridStore getObjGridStore(Courseware obj) {
		System.out.println(obj.getCourse_id());
		ResultGridStore rgs = null;
		try {
			rgs = service.getGridStore(obj);
		} catch (Exception e) {
			e.printStackTrace();
			LOG4J.error(e.getMessage());
		}
		return rgs;
	}

	@RequestMapping("add")
	@ResponseBody
	public ResultMsg add(Courseware obj) {
		ResultMsg msg = new ResultMsg();
		try {
			String account = getLoginUser().getAccount();
			MultipartHttpServletRequest resources = (MultipartHttpServletRequest) getRequest();

			List<MultipartFile> videolist = resources.getFiles("pathFile");
			for (MultipartFile file : videolist) {
				String fileName = file.getOriginalFilename();
				if (file.getSize() > 1073741824) {
					msg.setExecute(false);
					msg.setMsg("文件超过1GB系统暂时不支持，如果必须要上传此视频请联系后台管理员");
					return msg;
				}
				InputStream inputStream = file.getInputStream();
				String suffix = fileName.substring(fileName.lastIndexOf("."));
				String scName = Calendar.getInstance().getTimeInMillis() + "" + (int) (1 + Math.random() * 1000) + suffix;
				String ftpPath = getFilePath(account);
				LOG4J.info("&&&&&&&&&&& FTP 存放文件路径" + ftpPath);
				FTPClient ftpUtil = FTPUtil.connectServer();
				FTPUtil.uploadFile(ftpUtil, ftpPath, inputStream, scName);
				String savePath = FTPUtil.getSavePath() + ftpPath + scName;
				obj.setPath(savePath);
			}
			
			List<MultipartFile> imglist = resources.getFiles("imgFile");
			for (MultipartFile file : imglist) {
				String fileName = file.getOriginalFilename();
				InputStream inputStream = file.getInputStream();
				String suffix = fileName.substring(fileName.lastIndexOf("."));
				String scName = Calendar.getInstance().getTimeInMillis() + "" + (int) (1 + Math.random() * 1000) + suffix;
				String ftpPath = getFilePath(account);
				LOG4J.info("&&&&&&&&&&& FTP 存放文件路径" + ftpPath);
				FTPClient ftpUtil = FTPUtil.connectServer();
				FTPUtil.uploadFile(ftpUtil, ftpPath, inputStream, scName);
				String savePath = FTPUtil.getSavePath() + ftpPath + scName;
				obj.setImg(savePath);
			}
			service.insert(obj);
			Course course=courseService.getcourse(obj.getCourse_id());
			if(StringUtil.isEmpty(course.getPath())){
				course.setPath(obj.getPath());
				courseService.update(course);
			}
			msg.setExecute(true);
			msg.setMsg("新增课件成功!");
		} catch (Exception e) {
			msg.setExecute(false);
			msg.setMsg("新增课件失败!");
			LOG4J.error("新增课件信息失败!", e);
		}
		return msg;
	}

	@RequestMapping("edit")
	@ResponseBody
	public ResultMsg edit(Courseware obj) {
		ResultMsg msg = new ResultMsg();
		try {
			service.update(obj);
			msg.setExecute(true);
			msg.setMsg("修改课件成功!");
		} catch (Exception e) {
			msg.setExecute(false);
			msg.setMsg("修改课件失败!");
			LOG4J.error("修改课件信息失败!", e);
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
				String actUserId = getLoginUser().getAccountUserId();
				int row = service.delete(ids, actUserId);
				if (row > 0) {
					msg.setExecute(true);
					msg.setMsg("删除课件成功!");
				} else {
					msg.setExecute(false);
					msg.setMsg("删除课件失败!");
				}
			} catch (Exception e) {
				msg.setExecute(false);
				msg.setMsg("删除课件失败!");
				LOG4J.error("删除课件信息失败!", e);
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
