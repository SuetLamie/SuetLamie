package linfei.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import linfei.pojo.AppInfo;

/**
 * App������Ϣ
 * @author Administrator
 *
 */
public interface AppInfoDao {
	/**
	 * ��ҳ��ѯApp
	 * @return
	 */
	public List<AppInfo> selectAll(@Param("querySoftwareName")String querySoftwareName,@Param("status")Integer status,@Param("flatformId")Integer flatformId,@Param("categoryLevel1")Integer categoryLevel1,@Param("categoryLevel2")Integer categoryLevel2,@Param("categoryLevel3")Integer categoryLevel3,@Param("fromindex")int fromindex,@Param("pagesize")int pagesize);
	/**
	 * ��ѯApp����
	 */
	public int selectCount();
}
