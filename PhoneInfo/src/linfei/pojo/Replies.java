package linfei.pojo;

import java.util.Date;

/**
 * 回复表
 * @author Administrator
 *
 */
public class Replies {
	private Integer uid;	//回复 id
	private String ucontent;	//回复内容
	private Date replyTime;	//回复时间
	private Integer infoId;	// 资讯 id
	public Date getReplyTime() {
		return replyTime;
	}
	public void setReplyTime(Date replyTime) {
		this.replyTime = replyTime;
	}
	public Integer getUid() {
		return uid;
	}
	public void setUid(Integer uid) {
		this.uid = uid;
	}
	public String getUcontent() {
		return ucontent;
	}
	public void setUcontent(String ucontent) {
		this.ucontent = ucontent;
	}
	public Integer getInfoId() {
		return infoId;
	}
	public void setInfoId(Integer infoId) {
		this.infoId = infoId;
	}
}
