package linfei.dao;

import linfei.pojo.Replies;

/**
 * 回复表-接口
 * @author Administrator
 *
 */
public interface RepliesDao {
	/**
	 * 增加回复记录
	 * @param replies
	 * @return
	 */
	public int insertreplies(Replies replies);	
}
