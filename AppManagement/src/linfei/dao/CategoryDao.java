package linfei.dao;

import java.util.List;

import linfei.pojo.Category;

/**
 * 类别接口
 * @author Administrator
 *
 */
public interface CategoryDao {
	/**
	 * 根据ParentId查Category
	 * @return List<Category>
	 */
	public List<Category> selectCategoryByParentId(Integer parentid);
}
