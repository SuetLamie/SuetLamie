package com.surpass.mvc.system.sys.model;

import java.util.Date;

public class SysLog {
    private Integer id;

    /**����ҵ��*/
    private String business;

    /**�������� 1���� 2�޸� 3ɾ�� 4���� 5���� 6��¼ 7�ǳ�*/
    private String oper_type;

    /**�����������*/
    private String oper_key;

    /**�������������*/
    private String content;

    /**�����Ƿ�ɹ� 2: FALSE 1: TRUE*/
    private String success;

    /**�����˺�*/
    private String user_account;

    /**����ʱ��*/
    private Date c_time;
    
    private String c_time_format;
    
    
    /**  
	 * 获取 c_time_format  
	 * @return c_time_format c_time_format  
	 */
	public String getC_time_format() {
		return c_time_format;
	}

	/**  
	 * 设置 c_time_format  
	 * @param c_time_format c_time_format  
	 */
	public void setC_time_format(String c_time_format) {
		this.c_time_format = c_time_format;
	}

	/**
	 * 
	 */
	public SysLog() {
		// TODO Auto-generated constructor stub
	}
    
	/**
	 * 
	 * @param business
	 * @param oper_type 操作类型 1增加 2修改 3删除 4禁用 5启用 6登录 7登出
	 * @param content
	 * @param success 操作是否成功 0: FALSE 1: TRUE
	 * @param user_account
	 */
	public SysLog(String business, String oper_type, String key, String content,
			String success, String user_account) {
		this.business = business;
		this.oper_type = oper_type;
		this.oper_key = key;
		this.content = content;
		this.success = success;
		this.user_account = user_account;
	}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBusiness() {
        return business;
    }

    public void setBusiness(String business) {
        this.business = business == null ? null : business.trim();
    }

    public String getOper_type() {
        return oper_type;
    }

    public void setOper_type(String oper_type) {
        this.oper_type = oper_type == null ? null : oper_type.trim();
    }

    public String getOper_key() {
        return oper_key;
    }

    public void setOper_key(String oper_key) {
        this.oper_key = oper_key == null ? null : oper_key.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success == null ? null : success.trim();
    }

    public String getUser_account() {
        return user_account;
    }

    public void setUser_account(String user_account) {
        this.user_account = user_account == null ? null : user_account.trim();
    }

    public Date getC_time() {
        return c_time;
    }

    public void setC_time(Date c_time) {
        this.c_time = c_time;
    }
}