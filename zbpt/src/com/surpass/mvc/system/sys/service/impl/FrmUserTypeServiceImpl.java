package com.surpass.mvc.system.sys.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.surpass.mvc.system.sys.bean.ComboBox;
import com.surpass.mvc.system.sys.dao.FrmUserTypeMapper;
import com.surpass.mvc.system.sys.service.FrmUserTypeService;

/**
 * 
 * 类名称： FrmUserTypeServiceImpl<br>
 * 类描述：用户类型管理 ServiceImpl<br>
 * 创建人： 方曙强<br>
 * 创建时间：2016-12-12<br>
 * 修改人： 方曙强<br>
 * 修改时间：2016-12-12
 */
@Service("frmUserTypeServiceImpl")
public class FrmUserTypeServiceImpl implements FrmUserTypeService {
	@Autowired
	private FrmUserTypeMapper mapper;

	@Override
	public List<ComboBox> getUserTypeComboxData() {
		return mapper.getUserTypeComboxData();
	}
}
