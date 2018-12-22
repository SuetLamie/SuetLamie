package com.surpass.mvc.system.sys.service;

import com.surpass.mvc.system.sys.model.AdminIncome;
import com.surpass.utils.ResultGridStore;

public interface AdminIncomeService {
	
	/**增加收入记录*/
	int addAdminIncome(AdminIncome ai);
	
	ResultGridStore getGridStore(AdminIncome ai, Integer start, Integer limit, String orderBy);
}
