package linfei.service;

import java.util.Date;
import java.util.List;

import linfei.pojo.Informations;
import linfei.pojo.Pages;
import linfei.pojo.Replies;

public interface ServiceDao {
	/**
	 * 增加回复记录
	 * @param replies
	 * @return int
	 */
	public int insertreplies(Replies replies);
	/**
	 * 查询所有资讯数量
	 * @return int
	 */
	public int selectAllCount();
	/**
	 * 查询来自分页并按照最后回复时间降序
	 * @param fromindex
	 * @param pagesize
	 * @return List<Informations> 
	 */
	public List<Informations> selectByLimitandorder(Pages pages);
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
	public int updateviewCountById(Integer id,Integer viewcount);
	/**
	 * 修改回复次数和最后回复时间来自Id
	 * @param id
	 * @param replyCount
	 * @param lastPostTime
	 * @return int
	 */
	public int updatereplyCountandlastPostTimeById(Integer id,Integer replyCount,Date lastPostTime);
}
