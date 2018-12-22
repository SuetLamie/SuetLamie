/**
 *
 */
package com.surpass.mvc.system.bas.controller;

import java.io.Serializable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.surpass.mvc.core.BaseController;
import com.surpass.mvc.system.bas.service.CourseTypeService;
import com.surpass.mvc.system.sys.model.DictDetail;
import com.surpass.utils.Constants;
import com.surpass.utils.ResultGridStore;
import com.surpass.utils.ResultMsg;
import com.surpass.utils.StringUtil;

/**
 * 类名称： Course<br>
 * 类描述： 课程子类别管理<br>
 * 创建人： 王鹏飞<br>
 * 创建时间：2018-5-23<br>
 * 修改人： 王鹏飞<br>
 * 修改时间：2018-5-23
 */
@Controller
@RequestMapping("system/bas/coursetype")
public class CourseTypeController extends BaseController implements Serializable {

	/**
	 * CourseController.java
	 */
	private static final long serialVersionUID = 1L;

	private static final Logger LOG4J = LoggerFactory.getLogger(CourseTypeController.class);

	@Autowired
	private CourseTypeService service;

	@RequestMapping("queryMain")
	public String queryMain() {
		return Constants.path.RETURN_PATH_SYSTEM + "bas/courseType/courseType.jsp";
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
	public ResultGridStore getObjGridStore(DictDetail obj, Integer start, Integer limit) {
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

	@RequestMapping("add")
	@ResponseBody
	public ResultMsg add(DictDetail obj) {
		ResultMsg msg = new ResultMsg();
		try {
			String accountUserId = getLoginUser().getAccountUserId();
			obj.setCreate_user(accountUserId);
			service.insert(obj);
			msg.setExecute(true);
			msg.setMsg("增加课程子分类成功!");
		} catch (Exception e) {
			msg.setExecute(false);
			msg.setMsg("增加课程子分类失败!");
		}
		return msg;
	}

	@RequestMapping("edit")
	@ResponseBody
	public ResultMsg edit(DictDetail obj) {
		ResultMsg msg = new ResultMsg();
		try {
			service.update(obj);
			msg.setExecute(true);
			msg.setMsg("修改课程子分类成功!");
		} catch (Exception e) {
			msg.setExecute(false);
			msg.setMsg("修改课程子分类失败!");
		}
		return msg;
	}

	@RequestMapping("delete")
	@ResponseBody
	public ResultMsg delete(String ids) {
		ResultMsg msg = new ResultMsg();
		if (StringUtil.isNotEmpty(ids)) {
			try {
				int row = service.delete(ids);
				if (row > 0) {
					msg.setExecute(true);
					msg.setMsg("删除课程子分类成功!");
				} else {
					msg.setExecute(false);
					msg.setMsg("删除课程子分类失败!");
				}
			} catch (Exception e) {
				msg.setExecute(false);
				msg.setMsg("删除课程子分类失败!");
				LOG4J.error("删除课程子分类失败!", e);
			}
		} else {
			msg.setExecute(false);
			msg.setMsg("数据传输失败!");
		}
		return msg;
	}

}