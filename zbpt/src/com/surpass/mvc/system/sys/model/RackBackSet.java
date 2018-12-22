package com.surpass.mvc.system.sys.model;

/*t_sys_rackbackset*/
public class RackBackSet {
    /**返佣编号*/
    private String id;

    /**返佣标题*/
    private String title;

    /**一级返佣*/
    private Double levelone;

    /**二级返佣*/
    private Double leveltwo;

    /**三级返佣*/
    private Double levelthree;

    /**教师收入比例*/
    private Double teacherincome;

    /**代理商收入*/
    private Double agentincome;

    /**平台收入比例*/
    private Double adminincome;

    /** get 返佣编号*/
    public String getId() {
        return id;
    }

    /** set 返佣编号*/
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /** get 返佣标题*/
    public String getTitle() {
        return title;
    }

    /** set 返佣标题*/
    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    /** get 一级返佣*/
    public Double getLevelone() {
        return levelone;
    }

    /** set 一级返佣*/
    public void setLevelone(Double levelone) {
        this.levelone = levelone;
    }

    /** get 二级返佣*/
    public Double getLeveltwo() {
        return leveltwo;
    }

    /** set 二级返佣*/
    public void setLeveltwo(Double leveltwo) {
        this.leveltwo = leveltwo;
    }

    /** get 三级返佣*/
    public Double getLevelthree() {
        return levelthree;
    }

    /** set 三级返佣*/
    public void setLevelthree(Double levelthree) {
        this.levelthree = levelthree;
    }

    /** get 教师收入比例*/
    public Double getTeacherincome() {
        return teacherincome;
    }

    /** set 教师收入比例*/
    public void setTeacherincome(Double teacherincome) {
        this.teacherincome = teacherincome;
    }

    /** get 代理商收入*/
    public Double getAgentincome() {
        return agentincome;
    }

    /** set 代理商收入*/
    public void setAgentincome(Double agentincome) {
        this.agentincome = agentincome;
    }

    /** get 平台收入比例*/
    public Double getAdminincome() {
        return adminincome;
    }

    /** set 平台收入比例*/
    public void setAdminincome(Double adminincome) {
        this.adminincome = adminincome;
    }
}