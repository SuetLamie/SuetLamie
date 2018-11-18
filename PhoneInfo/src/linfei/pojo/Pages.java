package linfei.pojo;

import java.util.List;

public class Pages {
	private int totalCount;	//所有数
	private int currentPageNo=1;	//当前页
	private int totalPageCount;	//总页数
	private int Pagesize=5;	//页面大小
	private List<Informations> informations;	//当前页资讯	
	public List<Informations> getInformations() {
		return informations;
	}
	public void setInformations(List<Informations> informations) {
		this.informations = informations;
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
