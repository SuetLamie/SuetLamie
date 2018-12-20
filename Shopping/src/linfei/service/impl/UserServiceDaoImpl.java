package linfei.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import linfei.dao.UserMapper;
import linfei.pojo.User;
import linfei.service.UserServiceDao;

@Service
@Controller
public class UserServiceDaoImpl implements UserServiceDao {
	@Autowired
	private UserMapper user;
	@Override
	public int deleteByPrimaryKey(Integer id) {
		int cun=user.deleteByPrimaryKey(id);
		return cun;
	}

	@Override
	public int insert(User record) {
		int cun=user.insert(record);
		return cun;
	}

	@Override
	public int insertSelective(User record) {
		int cun=user.insertSelective(record);
		return cun;
	}

	@Override
	public User selectByPrimaryKey(Integer id) {
		User use=user.selectByPrimaryKey(id);
		return use;
	}

	@Override
	public int updateByPrimaryKeySelective(User record) {
		int cun=user.updateByPrimaryKeySelective(record);
		return cun;
	}

	@Override
	public int updateByPrimaryKey(User record) {
		int cun=user.updateByPrimaryKey(record);
		return cun;
	}

	@Override
	public List<User> selectAllUser() {
		List<User> list=user.selectAllUser();
		return list;
	}

	@Override
	public User selectUserByUserAndPassword(User u) {
		User use=user.selectUserByUserAndPassword(u);
		return use;
	}
}
