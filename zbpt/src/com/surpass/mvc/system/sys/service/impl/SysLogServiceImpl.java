/**  
* @Title: SysLogServiceImpl.java 
* @Package com.mvc.sys.service.impl 
* @author pfwang
* @date 2016-1-7 上午11:10:48 
* @version V1.0  
*/ 
package com.surpass.mvc.system.sys.service.impl;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.surpass.mvc.core.query.ParametersMapBuilder;
import com.surpass.mvc.system.sys.dao.SysLogMapper;
import com.surpass.mvc.system.sys.model.SysLog;
import com.surpass.mvc.system.sys.service.SysLogService;
import com.surpass.utils.ResultGridStore;

/**  
 * 
 * 类名称： SysLogServiceImpl<br>
 * 类描述：  系统日志<br>
 * 创建人： 王鹏飞<br>
 * 创建时间：2016-4-1<br>
 * 修改人： 王鹏飞<br>
 * 修改时间：2016-4-1
 */
@Service("sysLogServiceImpl")
public class SysLogServiceImpl implements SysLogService{

	/**
	 * 系统日志  dao接口
	 */
	@Autowired
	private SysLogMapper sysLogMapper;
	
	/**
	 * 请求参数绑定器
	 */
	@Resource
	private ParametersMapBuilder parametersMapBuilder;
	
	/* (non-Javadoc)
	 * @see com.prochanges.mvc.system.sys.service.SysLogService#getGridStore(com.prochanges.mvc.system.sys.bean.SysLog, java.lang.Integer, java.lang.Integer, java.lang.String)
	 */
	@Override
	public ResultGridStore getGridStore(SysLog sLog, Integer start, Integer limit, String orderBy) {
		ResultGridStore rg = new ResultGridStore();
		try {
			rg.setList(sysLogMapper.query(parametersMapBuilder.build(start, limit, orderBy, sLog)));
			rg.setTotalProperty(sysLogMapper.total(sLog));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rg;
	}

	/* (non-Javadoc)
	 * @see com.prochanges.mvc.system.sys.service.SysLogService#insert(com.prochanges.mvc.system.sys.bean.SysLog)
	 */
	@Override
	public void insert(SysLog sl) throws Exception {
		sysLogMapper.insert(sl);
	}

}
