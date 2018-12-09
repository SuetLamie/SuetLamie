package linfei.pojo;

import java.util.Date;

import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * 项目合同信息管理
 * @author 林飞
 *
 */
public class InfoManagement {
	private Integer id;	//id号
	@NotNull(message="项目名称不能为空")
	private String projectName;	//项目名称
	@DateTimeFormat
	private Date signTime;	//签订日期
	private String payment;	//付款方式
	private Integer contractNo;	//合同编号
	private Double contractMonery;	//合同总金额
	private Date warrantyTime;	//免费质保年限
	private String Invoice;	//发票计划
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	public Date getSignTime() {
		return signTime;
	}
	public void setSignTime(Date signTime) {
		this.signTime = signTime;
	}
	public String getPayment() {
		return payment;
	}
	public void setPayment(String payment) {
		this.payment = payment;
	}
	public Integer getContractNo() {
		return contractNo;
	}
	public void setContractNo(Integer contractNo) {
		this.contractNo = contractNo;
	}
	public Double getContractMonery() {
		return contractMonery;
	}
	public void setContractMonery(Double contractMonery) {
		this.contractMonery = contractMonery;
	}
	public Date getWarrantyTime() {
		return warrantyTime;
	}
	public void setWarrantyTime(Date warrantyTime) {
		this.warrantyTime = warrantyTime;
	}
	public String getInvoice() {
		return Invoice;
	}
	public void setInvoice(String invoice) {
		Invoice = invoice;
	}	
}
