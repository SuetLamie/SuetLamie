/**
 * 
 */
package com.surpass.mvc.system.sys.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.Serializable;
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
import com.surpass.mvc.system.sys.model.AppVersion;
import com.surpass.mvc.system.sys.service.AppVersionService;
import com.surpass.utils.Constants;
import com.surpass.utils.FTPUtil;
import com.surpass.utils.ResultGridStore;
import com.surpass.utils.ResultMsg;
import com.surpass.utils.StringUtil;
import com.surpass.utils.UUIDGenerator;

/**
 * 
 * 类名称： AppVersionController<br>
 * 类描述： APP更新管理<br>
 */
@Controller
@RequestMapping("system/sys/AppVersionController")
public class AppVersionController extends BaseController implements Serializable {

	/**
	 * AppVersionController.java
	 */
	private static final long serialVersionUID = 1L;
	private static final Logger log4j = LoggerFactory.getLogger(AppVersionController.class);
	/**
	 * service
	 */
	@Autowired
	private AppVersionService service;

	/**
	 * 页面跳转 方法名称：queryMain<br>
	 * 方法说明: <br>
	 * 创建人: 王鹏飞<br>
	 * 
	 * @return
	 */
	@RequestMapping("queryMain")
	public String queryMain() {
		return Constants.path.RETURN_PATH_SYSTEM + "sys/appVersion/appVersion.jsp";
	}

	/**
	 * 
	 * 方法名称：getObjGridStore<br>
	 * 方法说明: 获取列表<br>
	 * 创建人: pfwang<br>
	 * 
	 * @return
	 */
	@RequestMapping("getObjGridStore")
	@ResponseBody
	public ResultGridStore getObjGridStore(AppVersion obj, Integer start, Integer limit) {
		ResultGridStore rgs = null;
		try {
			String orderBy = "";
			rgs = service.getGridStore(obj, start, limit, orderBy);
		} catch (Exception e) {
			log4j.error(e.getMessage());
		}
		return rgs;
	}

	@RequestMapping("saveAppVersion")
	@ResponseBody
	public ResultMsg saveAppVersion(AppVersion obj) {
		ResultMsg msg = new ResultMsg();
		try {
			if (service.validate(obj.getV_code()) > 0) {
				msg.setExecute(false);
				msg.setMsg("此版本编号已存在!");
				msg.setData("v_code");
			} else {
				String[] code = obj.getV_code().split("\\.");
				if (code.length != 3) {
					msg.setExecute(false);
					msg.setMsg("版本编号不规范!");
					msg.setData("v_code");
				} else {
					String maxcode1 = service.getNewCode();
					if (StringUtil.isEmpty(maxcode1)) {
						// 文件 路径
						String savePath = getRequest().getSession().getServletContext().getRealPath("");
						savePath = savePath.replace("\\zbpt", "\\zbpt_apk\\");
						// 文件夹
						File saveFile = new File(savePath);
						if (!saveFile.exists()) {
							saveFile.mkdirs();
						}
						String fileName = "zbpt_apk" + obj.getV_code() + ".apk";
						saveFile = new File(savePath, fileName);
						MultipartHttpServletRequest mr = (MultipartHttpServletRequest) getRequest();
						List<MultipartFile> filelist = mr.getFiles("file");
						for (MultipartFile multipartFile : filelist) {
							multipartFile.transferTo(saveFile);
						}
						// 文件服务器 获取文件字节
						String saveFtpUrl = "/apk/";
						String saveFtpPath = "apk/";

						FileInputStream inputStream = new FileInputStream(saveFile);
						FTPClient ftpUtil = FTPUtil.connectServer();
						FTPUtil.uploadFile(ftpUtil, saveFtpPath, inputStream, fileName);
						// FTPUtil.closeConnect(ftpUtil);
						// 删除临时文件
						saveFile.delete();
						obj.setId(UUIDGenerator.generateUUID().toString());
						obj.setPath(FTPUtil.getSavePath() + saveFtpUrl + fileName);
						obj.setIs_rollback("0");
						service.insert(obj);
						msg.setExecute(true);
						msg.setMsg("APP版本增加成功！");
					} else {
						String[] maxCode = service.getNewCode().split("\\.");
						Integer nowcode = Integer.valueOf(code[0] + code[1] + code[2]);
						Integer max = Integer.valueOf(maxCode[0] + maxCode[1] + maxCode[2]);
						if (max > nowcode) {
							msg.setExecute(false);
							msg.setMsg("新增版本编号应大于系统当前最大版本号!");
							msg.setData("v_code");
						} else {
							// 文件 路径
							String savePath = getRequest().getSession().getServletContext().getRealPath("");
							savePath = savePath.replace("\\zbpt", "\\zbpt_apk\\");
							// 文件夹
							File saveFile = new File(savePath);
							if (!saveFile.exists()) {
								saveFile.mkdirs();
							}
							String fileName = "zbpt_apk" + obj.getV_code() + ".apk";
							saveFile = new File(savePath, fileName);
							MultipartHttpServletRequest mr = (MultipartHttpServletRequest) getRequest();
							List<MultipartFile> filelist = mr.getFiles("file");
							for (MultipartFile multipartFile : filelist) {
								multipartFile.transferTo(saveFile);
							}
							// 文件服务器 获取文件字节
							String saveFtpUrl = "/apk/";
							String saveFtpPath = "apk/";

							FileInputStream inputStream = new FileInputStream(saveFile);
							FTPClient ftpUtil = FTPUtil.connectServer();
							FTPUtil.uploadFile(ftpUtil, saveFtpPath, inputStream, fileName);
							// 删除临时文件
							saveFile.delete();
							obj.setId(UUIDGenerator.generateUUID().toString());
							obj.setPath(FTPUtil.getSavePath() + saveFtpUrl + fileName);
							obj.setIs_rollback("0");
							service.insert(obj);
							msg.setExecute(true);
							msg.setMsg("APP版本增加成功！");
						}
					}
				}

			}
		} catch (Exception e) {
			msg.setExecute(false);
			msg.setMsg("新增APP版本失败!");
			log4j.error("新增APP版本信息失败!", e);
		}
		return msg;
	}

	@RequestMapping("updateAppVersion")
	@ResponseBody
	public ResultMsg updateAppVersion(AppVersion obj) {
		ResultMsg msg = new ResultMsg();
		try {
			service.update(obj);
			msg.setExecute(true);
			msg.setMsg("修改APP版本成功!");
		} catch (Exception e) {
			msg.setExecute(false);
			msg.setMsg("修改APP版本失败!");
			log4j.error("修改APP版本信息失败!", e);
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
					msg.setMsg("删除APP版本成功!");
				} else {
					msg.setExecute(false);
					msg.setMsg("删除APP版本失败!");
				}
			} catch (Exception e) {
				msg.setExecute(false);
				msg.setMsg("删除APP版本失败!");
				log4j.error("删除APP版本信息失败!", e);
			}
		} else {
			msg.setExecute(false);
			msg.setMsg("数据传输失败!");
		}
		return msg;
	}
}
