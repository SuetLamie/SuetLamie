package linfei.dao;

import java.util.List;

import linfei.pojo.DataDictionary;

/**
 * �����ֵ�ӿ�
 * @author Administrator
 *
 */
public interface DataDictionaryDao {
	public List<DataDictionary> selectBytypecode(String typecode);
}
