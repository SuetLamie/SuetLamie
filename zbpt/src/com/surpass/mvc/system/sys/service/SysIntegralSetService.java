package com.surpass.mvc.system.sys.service;

import com.surpass.mvc.system.sys.model.SysIntegralSet;
import com.surpass.utils.ResultGridStore;

public interface SysIntegralSetService {
	
	SysIntegralSet getPoint(String integralNo);
	
	int updatePoint(SysIntegralSet sis);
	
	ResultGridStore getGridStore(SysIntegralSet sis, Integer start, Integer limit, String orderBy);

}
