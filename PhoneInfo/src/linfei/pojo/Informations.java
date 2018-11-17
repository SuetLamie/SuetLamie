package linfei.pojo;

import java.util.Date;
import java.util.List;

/**
 * 资讯表
 * @author Administrator
 *
 */
public class Informations {
	private Integer id;	//资讯 id
	private String title;	//资讯标题
	private String content;	//资讯内容
	private Integer replyCount;	//回复次数
	private Integer viewCount;	//查看次数
	private Date reportTime;	//发表时间
	private Date lastPostTime;	//最后回复时间	
	private List<Replies> replies;	//回复表实体
	public List<Replies> getReplies() {
		return replies;
	}
	public void setReplies(List<Replies> replies) {
		this.replies = replies;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Integer getReplyCount() {
		return replyCount;
	}
	public void setReplyCount(Integer replyCount) {
		this.replyCount = replyCount;
	}
	public Integer getViewCount() {
		return viewCount;
	}
	public void setViewCount(Integer viewCount) {
		this.viewCount = viewCount;
	}
	public Date getReportTime() {
		return reportTime;
	}
	public void setReportTime(Date reportTime) {
		this.reportTime = reportTime;
	}
	public Date getLastPostTime() {
		return lastPostTime;
	}
	public void setLastPostTime(Date lastPostTime) {
		this.lastPostTime = lastPostTime;
	}
}
