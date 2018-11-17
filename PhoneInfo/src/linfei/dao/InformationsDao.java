package linfei.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import linfei.pojo.Informations;

/**
 * 资讯表-接口
 * @author Administrator
 *
 */
public interface InformationsDao {
	/**
	 * 查询所有数量
	 * @return List<Informations>
	 */
	public int selectAllCount();
	/**
	 * 查询来自分页并按照最后回复时间降序
	 * @param fromindex
	 * @param pagesize
	 * @return List<Informations>
	 */
	public List<Informations> selectByLimitandorder(@Param("fromindex")Integer fromindex,@Param("pagesize")Integer pagesize);
	/**
	 * 查询来自Id及其全部回复信息
	 * @param id
	 * @return Informations
	 */
	public Informations selectById(Integer id);
	/**
	 * 修改查看次数来自Id
	 * @param id
	 * @param viewcount
	 * @return int
	 */
	public int updateviewCountById(@Param("id")Integer id,@Param("viewcount")Integer viewcount);
	/**
	 * 修改回复次数和最后回复时间来自Id
	 * @return int
	 */
	public int updatereplyCountandlastPostTimeById(@Param("id")Integer id,@Param("replyCount")Integer replyCount,@Param("lastPostTime")Date lastPostTime);
}
