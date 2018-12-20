package linfei.service;

import java.util.List;

import linfei.pojo.Product;

public interface ProductServiceDao {
	/**
	 * 根据主键删除商品
	 * @param id
	 * @return int
	 */
	int deleteByPrimaryKey(Integer id);
	/**
	 * 添加商品
	 * @param record
	 * @return int
	 */
	int insert(Product record);
	/**
	 * 动态添加商品
	 * @param record
	 * @return int
	 */
	int insertSelective(Product record);
	/**
	 * 根据主键查询商品
	 * @param id
	 * @return Product
	 */
	Product selectByPrimaryKey(Integer id);
	/**
	 * 查询所有商品
	 * @return List<Product>
	 */
	List<Product> selectAllProduct();
	/**
	 * 根据主键动态更新商品
	 * @param record
	 * @return int
	 */
	int updateByPrimaryKeySelective(Product record);
	/**
	 * 根据主键更新商品
	 * @param record
	 * @return int
	 */
	int updateByPrimaryKey(Product record);
}
