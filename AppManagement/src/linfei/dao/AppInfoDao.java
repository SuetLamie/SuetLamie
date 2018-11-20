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
	 * @return List<AppInfo>
	 */
	public List<AppInfo> selectAll(@Param("querySoftwareName")String querySoftwareName,@Param("queryStatus")String queryStatus,@Param("queryFlatformId")String queryFlatformId,@Param("queryCategoryLevel1")String queryCategoryLevel1,@Param("queryCategoryLevel2")String queryCategoryLevel2,@Param("queryCategoryLevel3")String queryCategoryLevel3,@Param("fromindex")Integer fromindex,@Param("pagesize")Integer pagesize);
	/**
	 * ��ѯApp����
	 */
	public int selectCount();
}
