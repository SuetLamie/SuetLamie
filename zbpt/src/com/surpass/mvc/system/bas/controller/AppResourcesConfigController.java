/**
 *
 */
package com.surpass.mvc.system.bas.controller;

import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.surpass.mvc.system.bas.model.AppResourcesConfig;
import com.surpass.mvc.system.bas.service.AppResourcesConfigService;
import com.surpass.utils.Constants;
import com.surpass.utils.FTPUtil;
import com.surpass.utils.ResultGridStore;
import com.surpass.utils.ResultMsg;
import com.surpass.utils.StringUtil;

/**
 * 类名称： Course<br>
 * 类描述： 应用程序资源管理<br>
 * 创建人： 王鹏飞<br>
 * 创建时间：2018-5-23<br>
 * 修改人： 王鹏飞<br>
 * 修改时间：2018-5-23
 */
@Controller
@RequestMapping("system/bas/appresourcesconfig")
public class AppResourcesConfigController extends BaseController implements Serializable {

	private static final String ftpBasePath = "course/";
	/**
	 * CourseController.java
	 */
	private static final long serialVersionUID = 1L;

	private static final Logger LOG4J = LoggerFactory.getLogger(AppResourcesConfigController.class);

	@Autowired
	private AppResourcesConfigService service;

	/**
	 * 页面跳转 方法名称：queryMain<br>
	 * 方法说明: 我的资源页面<br>
	 * 创建人: 王鹏飞<br>
	 * 创建日期: 2016-3-28<br>
	 * 
	 * @return
	 */
	@RequestMapping("queryMain")
	public String queryMain() {
		return Constants.path.RETURN_PATH_SYSTEM + "bas/appResourcesConfig/appResourcesConfig.jsp";
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
	public ResultGridStore getObjGridStore(AppResourcesConfig obj, Integer start, Integer limit) {
		ResultGridStore rgs = null;
		try {
			String orderBy = "";
			rgs = service.getGridStore(obj, start, limit, orderBy);
		} catch (Exception e) {
			e.printStackTrace();
			LOG4J.error(e.getMessage());
		}
		return rgs;
	}

	@RequestMapping("pageItem")
	@ResponseBody
	public ResultMsg pageItem() {
		ResultMsg msg = new ResultMsg();
		try {
			List<String> lis = service.getAppResourcesConfigPageItem();
			List<Map<String, String>> li = new ArrayList<Map<String,String>>();
			for (String string : lis) {
				Map<String, String> map = new HashMap<String,String>();
				map.put("text", string);
				map.put("id", string);
				li.add(map);
			}
			msg.setData(li);
			msg.setExecute(true);
			msg.setMsg("获取页面标识成功!");
		} catch (Exception e) {
			msg.setExecute(false);
			msg.setMsg("获取页面标识资源失败!");
			LOG4J.error("获取页面标识信息失败!", e);
		}
		return msg;
	}

	@RequestMapping("add")
	@ResponseBody
	public ResultMsg add(AppResourcesConfig obj) {
		ResultMsg msg = new ResultMsg();
		try {
			MultipartHttpServletRequest resources = (MultipartHttpServletRequest) getRequest();

			List<MultipartFile> videolist = resources.getFiles("pathFile");
			for (MultipartFile file : videolist) {
				String fileName = file.getOriginalFilename();
				InputStream inputStream = file.getInputStream();
				String suffix = fileName.substring(fileName.lastIndexOf("."));
				String scName = Calendar.getInstance().getTimeInMillis() + "" + (int) (1 + Math.random() * 1000) + suffix;
				String ftpPath = getFilePath(getLoginUser().getAccount());
				LOG4J.info("&&&&&&&&&&& FTP 存放文件路径" + ftpPath);
				FTPClient ftpUtil = FTPUtil.connectServer();
				FTPUtil.uploadFile(ftpUtil, ftpPath, inputStream, scName);
				String savePath = FTPUtil.getSavePath() + ftpPath + scName;
				obj.setPath(savePath);
			}
			service.insert(obj);
			msg.setExecute(true);
			msg.setMsg("新增资源成功!");
		} catch (Exception e) {
			msg.setExecute(false);
			msg.setMsg("新增资源失败!");
			LOG4J.error("新增资源信息失败!", e);
		}
		return msg;
	}

	@RequestMapping("edit")
	@ResponseBody
	public ResultMsg edit(AppResourcesConfig obj) {
		ResultMsg msg = new ResultMsg();
		try {
			service.updateByKey(obj);
			msg.setExecute(true);
			msg.setMsg("新增资源成功!");
		} catch (Exception e) {
			msg.setExecute(false);
			msg.setMsg("新增资源失败!");
			LOG4J.error("新增资源信息失败!", e);
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
				int row = service.deleteByKey(ids);
				if (row > 0) {
					msg.setExecute(true);
					msg.setMsg("删除资源成功!");
				} else {
					msg.setExecute(false);
					msg.setMsg("删除资源失败!");
				}
			} catch (Exception e) {
				msg.setExecute(false);
				msg.setMsg("删除资源失败!");
				LOG4J.error("删除资源信息失败!", e);
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
