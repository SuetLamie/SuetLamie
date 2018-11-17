package linfei.dao;

import org.apache.ibatis.annotations.Param;

import linfei.pojo.User;

public interface UserDao {
	/**
	 * µÇÂ¼ÑéÖ¤²éÑ¯
	 * @param devCode
	 * @param devPassword
	 * @return User
	 */
	public User dologin(@Param("devCode")String devCode,@Param("devPassword")String devPassword);
}
