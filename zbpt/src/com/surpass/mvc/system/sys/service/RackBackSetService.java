package com.surpass.mvc.system.sys.service;

import com.surpass.mvc.system.sys.model.RackBackSet;
import com.surpass.utils.ResultGridStore;

public interface RackBackSetService {
	
	RackBackSet getRackBackSetInfo(String rackBackSetId);
	
	int updateInfo(RackBackSet rbs);
	
	ResultGridStore getGridStore(RackBackSet rbs, Integer start, Integer limit, String orderBy);
}
