package linfei.pojo;

import java.util.Date;

public class User {
	private int id;	//�û�ID
	private String devCode;	//�û����
	private String devName;	//�û��ǳ�
	private String dexPassword;	//�û�����
	private String devEmail;	//�û�����
	private String devInfo;	//�û���Ϣ
	private String createdBy;	//������
	private Date creationDate;	//��������
	private String modifyBy;	//�޸���
	private Date modifyDate;	//�޸�����
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDevCode() {
		return devCode;
	}
	public void setDevCode(String devCode) {
		this.devCode = devCode;
	}
	public String getDevName() {
		return devName;
	}
	public void setDevName(String devName) {
		this.devName = devName;
	}
	public String getDexPassword() {
		return dexPassword;
	}
	public void setDexPassword(String dexPassword) {
		this.dexPassword = dexPassword;
	}
	public String getDevEmail() {
		return devEmail;
	}
	public void setDevEmail(String devEmail) {
		this.devEmail = devEmail;
	}
	public String getDevInfo() {
		return devInfo;
	}
	public void setDevInfo(String devInfo) {
		this.devInfo = devInfo;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public Date getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	public String getModifyBy() {
		return modifyBy;
	}
	public void setModifyBy(String modifyBy) {
		this.modifyBy = modifyBy;
	}
	public Date getModifyDate() {
		return modifyDate;
	}
	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}
}
