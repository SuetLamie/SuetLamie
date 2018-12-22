package com.surpass.mvc.system.sys.model;

/*frm_config*/
public class FrmConfig {
    /**配置编号*/
    private String cfgId;

    /**配置名称*/
    private String cfgName;

    /**配置描述*/
    private String cfgDesc;

    /**数据值*/
    private String dataValue;

    /** get 配置编号*/
    public String getCfgId() {
        return cfgId;
    }

    /** set 配置编号*/
    public void setCfgId(String cfgId) {
        this.cfgId = cfgId == null ? null : cfgId.trim();
    }

    /** get 配置名称*/
    public String getCfgName() {
        return cfgName;
    }

    /** set 配置名称*/
    public void setCfgName(String cfgName) {
        this.cfgName = cfgName == null ? null : cfgName.trim();
    }

    /** get 配置描述*/
    public String getCfgDesc() {
        return cfgDesc;
    }

    /** set 配置描述*/
    public void setCfgDesc(String cfgDesc) {
        this.cfgDesc = cfgDesc == null ? null : cfgDesc.trim();
    }

    /** get 数据值*/
    public String getDataValue() {
        return dataValue;
    }

    /** set 数据值*/
    public void setDataValue(String dataValue) {
        this.dataValue = dataValue == null ? null : dataValue.trim();
    }
}