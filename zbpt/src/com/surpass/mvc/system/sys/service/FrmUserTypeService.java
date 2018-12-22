package com.surpass.mvc.system.sys.service;

import java.util.List;

import com.surpass.mvc.system.sys.bean.ComboBox;

/**
 * 
 * 类名称： FrmUserTypeService<br>
 * 类描述：用户类型管理 ServiceImpl <br>
 * 创建人： 方曙强<br>
 * 创建时间：2016-12-12<br>
 * 修改人： 方曙强<br>
 * 修改时间：2016-12-12
 */
public interface FrmUserTypeService {
	List<ComboBox> getUserTypeComboxData();
}
