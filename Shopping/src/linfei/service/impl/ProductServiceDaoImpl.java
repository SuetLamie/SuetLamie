package linfei.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import linfei.dao.ProductMapper;
import linfei.pojo.Product;
import linfei.service.ProductServiceDao;
@Service
@Controller
public class ProductServiceDaoImpl implements ProductServiceDao {
	@Autowired
	private ProductMapper product;
	@Override
	public int deleteByPrimaryKey(Integer id) {
		int cun=product.deleteByPrimaryKey(id);
		return cun;
	}

	@Override
	public int insert(Product record) {
		int cun=product.insert(record);
		return cun;
	}

	@Override
	public int insertSelective(Product record) {
		int cun=product.insertSelective(record);
		return cun;
	}

	@Override
	public Product selectByPrimaryKey(Integer id) {
		Product pro=product.selectByPrimaryKey(id);
		return pro;
	}

	@Override
	public int updateByPrimaryKeySelective(Product record) {
		int cun=product.updateByPrimaryKeySelective(record);
		return cun;
	}

	@Override
	public int updateByPrimaryKey(Product record) {
		int cun=product.updateByPrimaryKey(record);
		return cun;
	}

	@Override
	public List<Product> selectAllProduct() {
		List<Product> list=product.selectAllProduct();
		return list;
	}
}
