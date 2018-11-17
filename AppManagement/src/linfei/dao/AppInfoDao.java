package linfei.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import linfei.pojo.AppInfo;

/**
 * App基础信息
 * @author Administrator
 *
 */
public interface AppInfoDao {
	/**
	 * 分页查询App
	 * @return
	 */
	public List<AppInfo> selectAll(@Param("querySoftwareName")String querySoftwareName,@Param("status")Integer status,@Param("flatformId")Integer flatformId,@Param("categoryLevel1")Integer categoryLevel1,@Param("categoryLevel2")Integer categoryLevel2,@Param("categoryLevel3")Integer categoryLevel3,@Param("fromindex")int fromindex,@Param("pagesize")int pagesize);
	/**
	 * 查询App总数
	 */
	public int selectCount();
}
