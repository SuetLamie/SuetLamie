package linfei.pojo;

/*products*/
public class Product {
    /**商品id*/
    private Integer id;

    /**商品名称*/
    private String name;

    /**商品价格*/
    private Double price;

    /**商品数量*/
    private Integer num;

    /**商品详情*/
    private String info;

    /** get 商品id*/
    public Integer getId() {
        return id;
    }

    /** set 商品id*/
    public void setId(Integer id) {
        this.id = id;
    }

    /** get 商品名称*/
    public String getName() {
        return name;
    }

    /** set 商品名称*/
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /** get 商品价格*/
    public Double getPrice() {
        return price;
    }

    /** set 商品价格*/
    public void setPrice(Double price) {
        this.price = price;
    }

    /** get 商品数量*/
    public Integer getNum() {
        return num;
    }

    /** set 商品数量*/
    public void setNum(Integer num) {
        this.num = num;
    }

    /** get 商品详情*/
    public String getInfo() {
        return info;
    }

    /** set 商品详情*/
    public void setInfo(String info) {
        this.info = info == null ? null : info.trim();
    }
}