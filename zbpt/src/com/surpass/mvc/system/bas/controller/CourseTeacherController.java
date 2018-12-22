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
import com.surpass.mvc.system.bas.model.Course;
import com.surpass.mvc.system.bas.model.CourseTeacher;
import com.surpass.mvc.system.bas.service.CourseTeacherService;
import com.surpass.utils.Constants;
import com.surpass.utils.ResultGridStore;
import com.surpass.utils.ResultMsg;
import com.surpass.utils.StringUtil;

/**
 * 类名称： Course<br>
 * 类描述： 课程讲师推荐管理<br>
 * 创建人： 王鹏飞<br>
 * 创建时间：2018-5-23<br>
 * 修改人： 王鹏飞<br>
 * 修改时间：2018-5-23
 */
@Controller
@RequestMapping("system/bas/courseteacher")
public class CourseTeacherController extends BaseController implements Serializable {

	/**
	 * CourseController.java
	 */
	private static final long serialVersionUID = 1L;

	private static final Logger LOG4J = LoggerFactory.getLogger(CourseTeacherController.class);

	@Autowired
	private CourseTeacherService service;

	/**
	 * 页面跳转 方法名称：queryMain<br>
	 * 方法说明: 我的课程页面<br>
	 * 创建人: 王鹏飞<br>
	 * 创建日期: 2016-3-28<br>
	 * 
	 * @return
	 */
	@RequestMapping("queryMain")
	public String queryMain() {
		return Constants.path.RETURN_PATH_SYSTEM + "bas/courseTeacher/courseTeacher.jsp";
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
	public ResultGridStore getObjGridStore(Course obj, Integer start, Integer limit) {
		ResultGridStore rgs = null;
		String accountUserId = getLoginUser().getAccountUserId();
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
	public ResultMsg add(CourseTeacher obj) {
		ResultMsg msg = new ResultMsg();
		try {
			String accountUserId = getLoginUser().getAccountUserId();
			obj.setAuthor_act_user_id(accountUserId);
			obj.setAuthor_act_user_name(getLoginUser().getUsername());
			service.insert(obj);
			msg.setExecute(true);
			msg.setMsg("新增推荐课程成功!");
		} catch (Exception e) {
			msg.setExecute(false);
			msg.setMsg("新增推荐课程失败!");
			LOG4J.error("新增推荐课程信息失败!", e);
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
					msg.setMsg("删除推荐课程成功!");
				} else {
					msg.setExecute(false);
					msg.setMsg("删除推荐课程失败!");
				}
			} catch (Exception e) {
				msg.setExecute(false);
				msg.setMsg("删除推荐课程失败!");
				LOG4J.error("删除推荐课程信息失败!", e);
			}
		} else {
			msg.setExecute(false);
			msg.setMsg("数据传输失败!");
		}
		return msg;
	}

}
