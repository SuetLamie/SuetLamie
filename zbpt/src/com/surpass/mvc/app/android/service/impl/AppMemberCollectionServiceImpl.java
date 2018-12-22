package com.surpass.mvc.app.android.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.surpass.mvc.app.android.dao.AppMemberCollectionMapper;
import com.surpass.mvc.app.android.service.AppMemberCollectionService;
import com.surpass.mvc.core.query.ParametersMapBuilder;
import com.surpass.mvc.system.bas.model.Course;
import com.surpass.mvc.system.bas.model.MemberCollectionCourse;
import com.surpass.mvc.system.bas.model.MemberCollectionOpus;
import com.surpass.mvc.system.bas.model.TeacherOpus;

@Service("appMemberCollectionService")
public class AppMemberCollectionServiceImpl implements AppMemberCollectionService{

	/**
	 * 请求参数绑定器
	 */
	@Resource
	private ParametersMapBuilder parametersMapBuilder;
	
	@Autowired
	private AppMemberCollectionMapper dao;

	@Override
	public int totalbyMember(MemberCollectionCourse mcc) throws Exception {
		return dao.totalbyMember(mcc);
	}

	@Override
	public int totalbyCourse(MemberCollectionCourse mcc) throws Exception {
		return dao.totalbyCourse(mcc);
	}

	@Override
	public List<Course> getList(int memberid, int start, int limit, String orderBy)
			throws Exception {
		return dao.getListData(memberid,start,limit);
	}

	@Override
	public void insert(MemberCollectionCourse obj) throws Exception {
		dao.insertSelective(obj);
	}

	@Override
	public void delete(MemberCollectionCourse mcc) throws Exception {
		dao.delete(mcc);
	}

	@Override
	public List<TeacherOpus> getOpusList(int memberid, int start, int limit, String orderBy) throws Exception {
		return dao.getListOpus(memberid, start, limit);
	}

	@Override
	public int totalOpusbyMember(MemberCollectionOpus mco) throws Exception {
		return dao.totalOpusbyMember(mco);
	}

	@Override
	public int totalbyOpus(MemberCollectionOpus mco) throws Exception {
		return dao.totalbyOpus(mco);
	}

	@Override
	public void insertOpus(MemberCollectionOpus obj) throws Exception {
		dao.insertOpus(obj);
	}

	@Override
	public void deleteOpus(MemberCollectionOpus mco) throws Exception {
		dao.deleteOpus(mco);
	}

	@Override
	public List<MemberCollectionCourse> isCollectionCourse(MemberCollectionCourse mcc) throws Exception {
		return dao.isCollectionCourse(mcc);
	}

	@Override
	public List<MemberCollectionOpus> isCollectionOpus(MemberCollectionOpus mco) throws Exception {
		return dao.isCollectionOpus(mco);
	}

}
