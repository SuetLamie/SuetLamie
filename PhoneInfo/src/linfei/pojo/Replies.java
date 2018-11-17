package linfei.pojo;

import java.util.Date;

/**
 * 回复表
 * @author Administrator
 *
 */
public class Replies {
	private Integer id;	//回复 id
	private String content;	//回复内容
	private Date replyTime;	//回复时间
	private Integer infoId;	// 资讯 id
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getReplyTime() {
		return replyTime;
	}
	public void setReplyTime(Date replyTime) {
		this.replyTime = replyTime;
	}
	public Integer getInfoId() {
		return infoId;
	}
	public void setInfoId(Integer infoId) {
		this.infoId = infoId;
	}
}
