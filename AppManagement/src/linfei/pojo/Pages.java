package linfei.pojo;

import java.util.List;

/**
 * ��ҳ��Ϣ
 * @author Administrator
 *
 */
public class Pages {
	private int totalCount;	//������
	private int currentPageNo;	//��ǰҳ
	private int totalPageCount;	//��ҳ��
	private int Pagesize=5;	//ҳ���С
	private List<AppInfo> appinfo;	//��ǰҳApp��Ϣ	
	public List<AppInfo> getAppinfo() {
		return appinfo;
	}
	public void setAppinfo(List<AppInfo> appinfo) {
		this.appinfo = appinfo;
	}
	public int getTotalPageCount() {
		return totalCount%Pagesize==0?(totalCount/Pagesize):(totalCount/Pagesize)+1;
	}
	public void setTotalPageCount(int totalPageCount) {
		this.totalPageCount = totalPageCount;
	}
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	public int getCurrentPageNo() {
		return currentPageNo;
	}
	public void setCurrentPageNo(int currentPageNo) {
		this.currentPageNo = currentPageNo;
	}
	public int getPagesize() {
		return Pagesize;
	}
	public void setPagesize(int pagesize) {
		Pagesize = pagesize;
	}
}
