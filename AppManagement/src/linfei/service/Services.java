package linfei.service;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import linfei.dao.AppInfoDao;
import linfei.dao.BackendUserDao;
import linfei.dao.CategoryDao;
import linfei.dao.DataDictionaryDao;
import linfei.dao.UserDao;
import linfei.pojo.AppInfo;
import linfei.pojo.BackendUser;
import linfei.pojo.Category;
import linfei.pojo.DataDictionary;
import linfei.pojo.User;
@Service
@Controller
public class Services {
	@Autowired
	private UserDao userrdao;
	@Autowired
	private CategoryDao categorydao;
	@Autowired
	private BackendUserDao backenduserdao;
	@Autowired
	private DataDictionaryDao datadictionarydao;
	@Autowired
	private AppInfoDao appinfodao;
	/**
	 * ��¼��֤
	 * @param devCode
	 * @param devPassword
	 * @return User
	 */
	public User dologin(String devCode,String devPassword){
		User user=userrdao.dologin(devCode, devPassword);
		return user;
	}
	/**
	 * ��̨��¼��֤
	 * @param devCode
	 * @param devPassword
	 * @return BackendUser
	 */
	public BackendUser backdologin(String userCode,String userPassword){
		BackendUser backenduser=backenduserdao.dologin(userCode, userPassword);
		return backenduser;
	}
	/**
	 * ����ParentId��Category
	 * @param parentid
	 * @return List<Category>
	 */
	public List<Category> selectCategoryByParentId(Integer parentid){
		List<Category> list=categorydao.selectCategoryByParentId(parentid);
		return list;
	}
	/**
	 * ����typecode��ѯ�����ֵ�
	 * @param typecode
	 * @return List<DataDictionary>
	 */
	public List<DataDictionary> selectDataDictionaryBytypecode(String typecode){
		List<DataDictionary> list=datadictionarydao.selectBytypecode(typecode);
		return list;
	}
	/**
	 * ��ҳ��ѯApp������Ϣ
	 * @return
	 */
	public List<AppInfo> selectAllAppInfo(String querySoftwareName,String queryStatus,String queryFlatformId,String queryCategoryLevel1,String queryCategoryLevel2,String queryCategoryLevel3,Integer fromindex,Integer pagesize){
		int i=(fromindex-1)*pagesize;
		List<AppInfo> list=appinfodao.selectAll(querySoftwareName, queryStatus, queryFlatformId, queryCategoryLevel1, queryCategoryLevel2, queryCategoryLevel3, i, pagesize);
		return list;
	}
	/**
	 * ��ѯApp����
	 */
	public int selectCount(){
		int count=appinfodao.selectCount();
		return count;
	}
}
