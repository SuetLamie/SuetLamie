package linfei.pojo;

import java.util.Date;

import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * ��Ŀ��ͬ��Ϣ����
 * @author �ַ�
 *
 */
public class InfoManagement {
	private Integer id;	//id��
	@NotNull(message="��Ŀ���Ʋ���Ϊ��")
	private String projectName;	//��Ŀ����
	@DateTimeFormat
	private Date signTime;	//ǩ������
	private String payment;	//���ʽ
	private Integer contractNo;	//��ͬ���
	private Double contractMonery;	//��ͬ�ܽ��
	private Date warrantyTime;	//����ʱ�����
	private String Invoice;	//��Ʊ�ƻ�
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
