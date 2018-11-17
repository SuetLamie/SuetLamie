package linfei.dao;

import java.util.List;

import linfei.pojo.DataDictionary;

/**
 * 数据字典接口
 * @author Administrator
 *
 */
public interface DataDictionaryDao {
	public List<DataDictionary> selectBytypecode(String typecode);
}
