/**
 * 
 */
package com.surpass.mvc.system.sys.service.impl;

import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.surpass.mvc.system.sys.bean.ComboBox;
import com.surpass.mvc.system.sys.dao.SysMapper;
import com.surpass.mvc.system.sys.model.DictDetail;
import com.surpass.mvc.system.sys.model.SysMenu;
import com.surpass.mvc.system.sys.service.SysService;

/**
 * 
 * 类名称： SysServiceImpl<br>
 * 类描述： <br>
 * 创建人： 方曙强<br>
 * 创建时间：2016-12-12<br>
 * 修改人： 方曙强<br>
 * 修改时间：2016-12-12
 */
@Service("sysServiceImpl")
public class SysServiceImpl implements SysService {
	@Autowired
	private SysMapper sysMapper;

	@Override
	public List<SysMenu> getMenuTreeList(Map<String, String> setMap) {
		return sysMapper.getMenuTreeList(setMap);
	}

	@Override
	public String getSysPower(String user_id) {
		return sysMapper.getSysPower(user_id);
	}

	@Override
	public List<ComboBox> getDictList(JSONObject jsonData) {
		return sysMapper.getDictList(jsonData);
	}

	/*
	*/
	@Override
	public List<DictDetail> getDictDetailList(DictDetail dictDetail) {
		return sysMapper.getDictDetailList(dictDetail);
	}

}
