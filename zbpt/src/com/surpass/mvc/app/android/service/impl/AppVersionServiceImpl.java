/**
 * 
 */
package com.surpass.mvc.app.android.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.surpass.mvc.app.android.service.AppVersionService;
import com.surpass.mvc.system.sys.dao.AppVersionMapper;
import com.surpass.mvc.system.sys.model.AppVersion;

/**
 * 类名称： AppVersionServiceImpl<br>
 * 类描述： <br>
 * 创建人：pfwang<br>
 */
@Service("appVersionServiceImpl")
public class AppVersionServiceImpl implements AppVersionService{

	@Autowired
	private AppVersionMapper appVersionMapper;
	
	@Override
	public AppVersion getAppVersion() {
		return appVersionMapper.getAppVersion();
	}

}
