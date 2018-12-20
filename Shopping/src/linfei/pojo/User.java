package linfei.pojo;


/*user*/
public class User {
    /**主键*/
    private Integer id;

    /**账号*/
    private String name;

    /**密码*/
    private String password;

    /** get 主键*/
    public Integer getId() {
        return id;
    }

    /** set 主键*/
    public void setId(Integer id) {
        this.id = id;
    }

    /** get 账号*/
    public String getName() {
        return name;
    }

    /** set 账号*/
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /** get 密码*/
    public String getPassword() {
        return password;
    }

    /** set 密码*/
    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }
}