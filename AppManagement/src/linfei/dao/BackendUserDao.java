package linfei.dao;

import org.apache.ibatis.annotations.Param;

import linfei.pojo.BackendUser;

public interface BackendUserDao {
	/**
	 * ��¼��֤��ѯ
	 * @param devCode
	 * @param devPassword
	 * @return BackendUser
	 */
	public BackendUser dologin(@Param("userCode")String userCode,@Param("userPassword")String userPassword);
}
