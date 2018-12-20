package linfei.service;

import java.util.List;

import linfei.pojo.User;

/**
 * 用户Service层
 * @author Administrator
 *
 */
public interface UserServiceDao {
	/**
	 * 删除用户来自主键
	 * @param id
	 * @return int
	 */
	int deleteByPrimaryKey(Integer id);
	/**
	 * 添加用户
	 * @param record
	 * @return int
	 */
	int insert(User record);
	/**
	 * 动态添加用户
	 * @param record
	 * @return int
	 */
	int insertSelective(User record);
	/**
	 * 根据主键查询用户
	 * @param id
	 * @return User
	 */
	User selectByPrimaryKey(Integer id);
	/**
	 * 根据主键动态修改用户
	 * @param record
	 * @return int
	 */
	int updateByPrimaryKeySelective(User record);
	/**
	 * 根据主键修改用户
	 * @param record
	 * @return int
	 */
	int updateByPrimaryKey(User record);
	/**
     * 查询所有用户
     * @return List<User>
     */
    List<User> selectAllUser();
    /**
     * 根据账号密码查询用户
     * @return User
     */
    User selectUserByUserAndPassword(User user);
}
