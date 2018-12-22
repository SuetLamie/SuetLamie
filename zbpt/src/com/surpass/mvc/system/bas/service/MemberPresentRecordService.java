package com.surpass.mvc.system.bas.service;

import java.util.List;

import com.surpass.mvc.system.bas.model.MemberPresentRecord;
import com.surpass.utils.ResultGridStore;

public interface MemberPresentRecordService {
	/**
	 * 
	 * 方法名称：getPresentRecordGridStore<br>
	 * 方法说明:获取提现记录排序 <br>
	 * 创建人: 何扬<br>
	 * 创建日期: 2018-8-29<br>
	 * 
	 * @param presentRecord
	 * @param start
	 * @param limit
	 * @param orderBy
	 * @return
	 */
	ResultGridStore getPresentRecordGridStore(MemberPresentRecord presentRecord, int start, int limit, String orderBy);
	
	/**
	 * 
	 * 方法名称：insert<br>
	 * 方法说明: 前端新增提现记录<br>
	 * 创建人: 何扬<br>
	 * 创建日期: 2018-8-29<br>
	 * 
	 * @param presentRecord
	 * @throws Exception
	 */
	void insert(MemberPresentRecord presentRecord) throws Exception;
	
	/**
	 * 
	 * 方法名称：update<br>
	 * 方法说明: 更新提现记录<br>
	 * 创建人: 何扬<br>
	 * 创建日期: 2018-8-29<br>
	 * 
	 * @param presentRecord
	 * @throws Exception
	 */
	void update(MemberPresentRecord presentRecord) throws Exception;
	
	/**
	 * 
	 * 方法名称：delete<br>
	 * 方法说明: 删除记录<br>
	 * 创建人: 何扬<br>
	 * 创建日期: 2018-8-1<br>
	 * 
	 * @param ids
	 * @throws Exception
	 */
	int delete(String ids) throws Exception;
	
	List<MemberPresentRecord> getPresentRecordByMemberId(Integer memberId,int start, int limit, String orderBy)throws Exception;
	
	int totalbymemberId(Integer memberId);
}
