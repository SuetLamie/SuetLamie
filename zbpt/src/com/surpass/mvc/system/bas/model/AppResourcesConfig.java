package com.surpass.mvc.system.bas.model;

public class AppResourcesConfig {
    /**唯一标识*/
    private Long id;

    /**名字*/
    private String name;

    /**路径*/
    private String path;

    /**优先级 数字越大优先级越高*/
    private Integer level;

    /**页面标识*/
    private String page;
    
    private int status = -1;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column app_resources_config.id
     *
     * @return the value of app_resources_config.id
     *
     * @mbggenerated Sat Jul 14 17:37:55 CST 2018
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column app_resources_config.id
     *
     * @param id the value for app_resources_config.id
     *
     * @mbggenerated Sat Jul 14 17:37:55 CST 2018
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column app_resources_config.name
     *
     * @return the value of app_resources_config.name
     *
     * @mbggenerated Sat Jul 14 17:37:55 CST 2018
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column app_resources_config.name
     *
     * @param name the value for app_resources_config.name
     *
     * @mbggenerated Sat Jul 14 17:37:55 CST 2018
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column app_resources_config.path
     *
     * @return the value of app_resources_config.path
     *
     * @mbggenerated Sat Jul 14 17:37:55 CST 2018
     */
    public String getPath() {
        return path;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column app_resources_config.path
     *
     * @param path the value for app_resources_config.path
     *
     * @mbggenerated Sat Jul 14 17:37:55 CST 2018
     */
    public void setPath(String path) {
        this.path = path == null ? null : path.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column app_resources_config.level
     *
     * @return the value of app_resources_config.level
     *
     * @mbggenerated Sat Jul 14 17:37:55 CST 2018
     */
    public Integer getLevel() {
        return level;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column app_resources_config.level
     *
     * @param level the value for app_resources_config.level
     *
     * @mbggenerated Sat Jul 14 17:37:55 CST 2018
     */
    public void setLevel(Integer level) {
        this.level = level;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column app_resources_config.page
     *
     * @return the value of app_resources_config.page
     *
     * @mbggenerated Sat Jul 14 17:37:55 CST 2018
     */
    public String getPage() {
        return page;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column app_resources_config.page
     *
     * @param page the value for app_resources_config.page
     *
     * @mbggenerated Sat Jul 14 17:37:55 CST 2018
     */
    public void setPage(String page) {
        this.page = page == null ? null : page.trim();
    }

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
    
}