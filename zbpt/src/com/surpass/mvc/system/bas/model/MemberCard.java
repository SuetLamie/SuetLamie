package com.surpass.mvc.system.bas.model;

import java.util.Date;

/*t_sys_member_card*/
public class MemberCard {
    /**编号*/
    private Integer id;

    /**卡号*/
    private String cardno;

    /**开户人*/
    private String cardname;

    /**开户行*/
    private String cardaddress;

    /**创建时间*/
    private Date createdate;

    /**会员编号*/
    private Integer memberid;

    /** get 编号*/
    public Integer getId() {
        return id;
    }

    /** set 编号*/
    public void setId(Integer id) {
        this.id = id;
    }


    public String getCardno() {
		return cardno;
	}

	public void setCardno(String cardno) {
		this.cardno = cardno == null ? null : cardno.trim();
	}

	/** get 开户人*/
    public String getCardname() {
        return cardname;
    }

    /** set 开户人*/
    public void setCardname(String cardname) {
        this.cardname = cardname == null ? null : cardname.trim();
    }

    /** get 开户行*/
    public String getCardaddress() {
        return cardaddress;
    }

    /** set 开户行*/
    public void setCardaddress(String cardaddress) {
        this.cardaddress = cardaddress == null ? null : cardaddress.trim();
    }

    /** get 创建时间*/
    public Date getCreatedate() {
        return createdate;
    }

    /** set 创建时间*/
    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }

    /** get 会员编号*/
    public Integer getMemberid() {
        return memberid;
    }

    /** set 会员编号*/
    public void setMemberid(Integer memberid) {
        this.memberid = memberid;
    }
}