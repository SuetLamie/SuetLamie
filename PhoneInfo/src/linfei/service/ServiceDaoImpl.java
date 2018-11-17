package linfei.service;

import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

import linfei.dao.InformationsDao;
import linfei.dao.RepliesDao;
import linfei.pojo.Informations;
import linfei.pojo.Pages;
import linfei.pojo.Replies;

/**
 * 服务层
 * @author Administrator
 *
 */
@org.springframework.stereotype.Service
public class ServiceDaoImpl implements ServiceDao  {
	@Autowired
	private RepliesDao repliesdao;
	@Autowired
	private InformationsDao informationsdao;
	/**
	 * 增加回复记录
	 * @param replies
	 * @return int
	 */
	public int insertreplies(Replies replies){
		int cun=repliesdao.insertreplies(replies);
		return cun;
	}
	/**
	 * 查询所有资讯数量
	 * @return int
	 */
	public int selectAllCount(){
		int cun=informationsdao.selectAllCount();
		return cun;
	}
	/**
	 * 查询来自分页并按照最后回复时间降序
	 * @param fromindex
	 * @param pagesize
	 * @return List<Informations> 
	 */
	public List<Informations> selectByLimitandorder(Pages pages){
		Integer pagesize=pages.getPagesize();
		Integer fromindex=(pages.getCurrentPageNo()-1)*pages.getPagesize();
		List<Informations> list=informationsdao.selectByLimitandorder(fromindex, pagesize);		
		return list;
	}
	/**
	 * 查询来自Id及其全部回复信息
	 * @param id
	 * @return Informations
	 */
	public Informations selectById(Integer id){
		Informations informations=informationsdao.selectById(id);
		return informations;
	}
	/**
	 * 修改查看次数来自Id
	 * @param id
	 * @param viewcount
	 * @return int
	 */
	public int updateviewCountById(Integer id,Integer viewcount){
		int cun=informationsdao.updateviewCountById(id, viewcount);
		return cun;
	}
	/**
	 * 修改回复次数和最后回复时间来自Id
	 * @param id
	 * @param replyCount
	 * @param lastPostTime
	 * @return int
	 */
	public int updatereplyCountandlastPostTimeById(Integer id,Integer replyCount,Date lastPostTime){
		int cun=informationsdao.updatereplyCountandlastPostTimeById(id, replyCount, lastPostTime);
		return cun;
	}
}
