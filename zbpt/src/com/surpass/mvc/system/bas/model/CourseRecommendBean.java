/**
*
*/
package com.surpass.mvc.system.bas.model;

/**
 * 类名称： CourseRecommendBean<br>
 * 类描述： <br>
 * 创建人： 王鹏飞<br>
 * 创建时间：2018-7-7<br>
 * 修改人： 王鹏飞<br>
 * 修改时间：2018-7-7
 */
public class CourseRecommendBean extends Course {

	private int tj_id;
	
	private String recommend_author;
	
	private String recommend_time;

	public String getRecommend_author() {
		return recommend_author;
	}

	public int getTj_id() {
		return tj_id;
	}

	public void setTj_id(int tj_id) {
		this.tj_id = tj_id;
	}

	public void setRecommend_author(String recommend_author) {
		this.recommend_author = recommend_author;
	}

	public String getRecommend_time() {
		return recommend_time;
	}

	public void setRecommend_time(String recommend_time) {
		this.recommend_time = recommend_time;
	}
	
}
