package linfei.dao;

import java.util.List;

import linfei.pojo.Product;

public interface ProductMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table products
     *	删除商品来自主键
     * @mbggenerated Wed Dec 19 14:06:06 CST 2018
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table products
     *	添加商品
     * @mbggenerated Wed Dec 19 14:06:06 CST 2018
     */
    int insert(Product record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table products
     *	动态添加商品
     * @mbggenerated Wed Dec 19 14:06:06 CST 2018
     */
    int insertSelective(Product record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table products
     *	查询商品来自主键
     * @mbggenerated Wed Dec 19 14:06:06 CST 2018
     */
    Product selectByPrimaryKey(Integer id);
    
    /**
     * 查询所有商品
     * @return List<Product>
     */
    List<Product> selectAllProduct();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table products
     *	根据主键动态更新商品
     * @mbggenerated Wed Dec 19 14:06:06 CST 2018
     */
    int updateByPrimaryKeySelective(Product record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table products
     *	根据主键更新商品
     * @mbggenerated Wed Dec 19 14:06:06 CST 2018
     */
    int updateByPrimaryKey(Product record);
}