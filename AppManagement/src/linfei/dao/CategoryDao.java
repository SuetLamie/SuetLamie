package linfei.dao;

import java.util.List;

import linfei.pojo.Category;

/**
 * ���ӿ�
 * @author Administrator
 *
 */
public interface CategoryDao {
	/**
	 * ����ParentId��Category
	 * @return List<Category>
	 */
	public List<Category> selectCategoryByParentId(Integer parentid);
}
