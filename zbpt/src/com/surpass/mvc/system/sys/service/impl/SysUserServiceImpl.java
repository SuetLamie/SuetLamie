package com.surpass.mvc.system.sys.service.impl;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.surpass.mvc.core.query.ParametersMapBuilder;
import com.surpass.mvc.system.sys.dao.FrmAccountMapper;
import com.surpass.mvc.system.sys.dao.FrmAccountUserMapper;
import com.surpass.mvc.system.sys.dao.SysUserMapper;
import com.surpass.mvc.system.sys.model.FrmAccount;
import com.surpass.mvc.system.sys.model.FrmAccountUser;
import com.surpass.mvc.system.sys.model.SysUser;
import com.surpass.mvc.system.sys.model.SysUserAccount;
import com.surpass.mvc.system.sys.service.SysUserService;
import com.surpass.utils.Constants;
import com.surpass.utils.ResultGridStore;

@Service("sysUserServiceImpl")
public class SysUserServiceImpl implements SysUserService {
	@Autowired
	private SysUserMapper sysUserMapper;
	@Autowired
	private FrmAccountMapper accountMapper;
	@Autowired
	private FrmAccountUserMapper accountUserMapper;
	/**
	 * 请求参数绑定器
	 */
	@Resource
	private ParametersMapBuilder parametersMapBuilder;

	@Override
	public ResultGridStore getUserGridStore(SysUserAccount user, Integer start, Integer limit, String orderBy) {
		ResultGridStore rg = new ResultGridStore();
		try {
			rg.setList(sysUserMapper.query(parametersMapBuilder.build(start, limit, orderBy, user)));
			rg.setTotalProperty(sysUserMapper.total(user));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rg;
	}

	@Override
	public void insert(SysUser sysUser, FrmAccount account) throws Exception {
		try {
			accountMapper.insertSelective(account);
			sysUserMapper.insertSelective(sysUser);
			FrmAccountUser frmAccountUser = new FrmAccountUser();
			frmAccountUser.setAccount_id(account.getAccount_id());
			frmAccountUser.setUser_id(sysUser.getId().toString());
			frmAccountUser.setUser_type(Constants.userType.sysUser);
			frmAccountUser.setState("1");
			accountUserMapper.insert(frmAccountUser);
		} catch (Exception e) {
			throw e;
		}

	}

	@Override
	public void updateSysUser(SysUser sysUser, FrmAccount account) throws Exception {
		try {
			accountMapper.updateByPrimaryKeySelective(account);
			sysUser.setU_time(new Date());
			sysUserMapper.updateByPrimaryKeySelective(sysUser);
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public FrmAccount findAccount(String account) {
		try {
			FrmAccount temp = new FrmAccount();
			temp.setAccount(account);
			return accountMapper.selectByBean(temp);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void changeUserState(String act_user_id, String state) throws Exception {
		try {
			accountUserMapper.changeUserState(act_user_id.split(","), state);
		} catch (Exception e) {
			throw e;
		}

	}

	@Override
	public SysUserAccount selectSysUserAccountById(String id) {
		return sysUserMapper.selectSysUserAccountById(id);
	}

}
