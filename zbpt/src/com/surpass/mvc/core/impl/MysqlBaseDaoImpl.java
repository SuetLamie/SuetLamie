package com.surpass.mvc.core.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import com.surpass.utils.ResultGridStore;
import com.surpass.utils.SpringContextUtil;

@SuppressWarnings({ "rawtypes", "unchecked" })
public class MysqlBaseDaoImpl {

	@Resource(name = "jdbcTemplate_tz")
	private JdbcTemplate jdbcTemplate_tz;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(MysqlBaseDaoImpl.class);

	/**
	 * 
	 * 方法名称：getJdbcTemplate_tz<br>
	 * 方法说明:本系统数据源模版 <br>
	 * 创建人: 方曙强<br>
	 * 创建日期: 2016-6-14<br>
	 * 
	 * @return
	 */
	public JdbcTemplate getJdbcTemplate_tz() {
		BasicDataSource ds = (BasicDataSource) SpringContextUtil.getBean("dataSource_tz");
		LOGGER.info("jdbcTemplate getNumActive=============" + ds.getNumActive());
		return jdbcTemplate_tz;
	}

	/**
	 * 
	 * 方法说明: <br>
	 * 创建人: 方曙强<br>
	 * 创建日期: May 21, 2015 <br>
	 * 
	 * @param tbName
	 * @param bean
	 * @throws Exception
	 */
	public void save(String tbName, Map bean) throws Exception {
		String sql = "insert into " + tbName + " (";
		String values = " values(";
		List list = new ArrayList();
		try {
			Iterator<String> keys = bean.keySet().iterator();
			boolean isFirst = true;
			while (keys.hasNext()) {
				String key = (String) keys.next();
				if (isFirst) {
					sql += key;
					values += "?";
					isFirst = false;
				} else {
					sql += "," + key;
					values += ",?";
				}
				list.add(bean.get(key));
			}
			sql += ")" + values + ")";
			int ct = getJdbcTemplate_tz().update(sql, list.toArray());
			if (ct == 0) {
				throw new RuntimeException("数据保存失败！");
			} else {
			}
		} catch (Exception e) {
			throw e;
		}
	}

	/**
	 * 
	 * 方法名称：save<br>
	 * 方法说明: <br>
	 * 创建人: 方曙强<br>
	 * 创建日期: Sep 27, 2013<br>
	 * 
	 * @param tbName
	 * @param bean
	 * @param defCol
	 *            要执行SQL的列 "id,name"
	 * @return
	 * @throws Exception
	 */
	public void save(String tbName, Map bean, String defCol) throws Exception {
		defCol = "," + defCol + ",";
		String sql = "insert into " + tbName + " (";
		String values = " values(";
		List list = new ArrayList();
		try {
			Iterator<String> keys = bean.keySet().iterator();
			boolean isFirst = true;
			while (keys.hasNext()) {
				String key = (String) keys.next();
				Object vl = "?";
				if (defCol.indexOf("," + key + ",") != -1) {
					vl = bean.get(key);
				} else {
					list.add(bean.get(key));
				}
				if (isFirst) {
					sql += key;
					values += vl;
					isFirst = false;
				} else {
					sql += "," + key;
					values += "," + vl;
				}
			}
			sql += ")" + values + ")";
			int ct = getJdbcTemplate_tz().update(sql, list.toArray());
			if (ct == 0) {
				throw new RuntimeException("数据保存失败！");
			}
		} catch (Exception e) {
			throw e;
		}
	}

	public void save(String tbName, Map bean, String defCol, JdbcTemplate tempJdbcTemplate) throws Exception {
		defCol = "," + defCol + ",";
		String sql = "insert into " + tbName + " (";
		String values = " values(";
		List list = new ArrayList();
		try {
			Iterator<String> keys = bean.keySet().iterator();
			boolean isFirst = true;
			while (keys.hasNext()) {
				String key = (String) keys.next();
				Object vl = "?";
				if (defCol.indexOf("," + key + ",") != -1) {
					vl = bean.get(key);
				} else {
					list.add(bean.get(key));
				}
				if (isFirst) {
					sql += key;
					values += vl;
					isFirst = false;
				} else {
					sql += "," + key;
					values += "," + vl;
				}
			}
			sql += ")" + values + ")";
			int ct = tempJdbcTemplate.update(sql, list.toArray());
			if (ct == 0) {
				throw new RuntimeException("数据保存失败！");
			}
		} catch (Exception e) {
			throw e;
		}
	}
	
	public void saveAndGetKey(String tbName, Map bean, String pk_key) throws Exception {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		String sql = "insert into " + tbName + " (";
		String values = " values(";
		final List list = new ArrayList();
		try {
			Iterator<String> keys = bean.keySet().iterator();
			boolean isFirst = true;
			while (keys.hasNext()) {
				String key = (String) keys.next();
				if (isFirst) {
					sql += key;
					values += "?";
					isFirst = false;
				} else {
					sql += "," + key;
					values += ",?";
				}
				list.add(bean.get(key));
			}
			sql += ")" + values + ")";
			final String sql_temp = sql;
			getJdbcTemplate_tz().update(new PreparedStatementCreator() {
				public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
					PreparedStatement ps = connection.prepareStatement(sql_temp, Statement.RETURN_GENERATED_KEYS);
					for (int i = 0; i < list.size(); i++) {
						ps.setString(i + 1, list.get(i).toString());
					}
					return ps;
				}
			}, keyHolder);
			bean.put(pk_key, keyHolder.getKey().toString());
		} catch (Exception e) {
			throw e;
		}
	}

	/**
	 * 保存数据入库 并返回 主键 id
	 * 
	 * @param tbName
	 *            表名
	 * @param bean
	 *            消息体
	 * @param pk_key
	 *            主键字段名
	 * @param defCol
	 *            需要调用数据库 函数 的字段名
	 * @throws Exception
	 */
	public void saveAndGetKey(String tbName, Map bean, String pk_key, String defCol) throws Exception {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		defCol = "," + defCol + ",";
		String sql = "insert into " + tbName + " (";
		String values = " values(";
		final List list = new ArrayList();
		try {
			Iterator<String> keys = bean.keySet().iterator();
			boolean isFirst = true;
			while (keys.hasNext()) {
				String key = (String) keys.next();
				Object vl = "?";
				if (defCol.indexOf("," + key + ",") != -1) {
					vl = bean.get(key);
				} else {
					list.add(bean.get(key));
				}
				if (isFirst) {
					sql += key;
					values += vl;
					isFirst = false;
				} else {
					sql += "," + key;
					values += "," + vl;
				}
			}
			sql += ")" + values + ")";
			final String sql_temp = sql;
			getJdbcTemplate_tz().update(new PreparedStatementCreator() {
				public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
					PreparedStatement ps = connection.prepareStatement(sql_temp, Statement.RETURN_GENERATED_KEYS);
					for (int i = 0; i < list.size(); i++) {
						ps.setString(i + 1, list.get(i).toString());
					}
					return ps;
				}
			}, keyHolder);
			bean.put(pk_key, keyHolder.getKey().toString());
		} catch (Exception e) {
			throw e;
		}
	}

	/**
	 * 
	 * 方法说明: 修改<br>
	 * 创建人: 方曙强<br>
	 * 创建日期: May 21, 2015 <br>
	 * 
	 * @param tbName
	 *            表名
	 * @param bean
	 *            消息体
	 * @param id
	 *            根据该字段名 修改记录
	 * @throws Exception
	 */
	public void update(String tbName, Map bean, String id) throws Exception {
		String sql = "update " + tbName + " set ";
		List list = new ArrayList();
		try {
			Iterator<String> keys = bean.keySet().iterator();
			boolean isFirst = true;
			while (keys.hasNext()) {
				String key = (String) keys.next();
				if (!id.equals(key)) {
					if (isFirst) {
						sql += key + "=?";
						isFirst = false;
					} else {
						sql += "," + key + "=?";
					}
					list.add(bean.get(key));
				}
			}
			sql += " where " + id + "=?";
			list.add(bean.get(id));
			int ct = getJdbcTemplate_tz().update(sql, list.toArray());
			if (ct == 0) {
				throw new RuntimeException("数据保存失败！");
			}
		} catch (Exception e) {
			throw e;
		}
	}

	/**
	 * 
	 * 方法名称：update<br>
	 * 方法说明: <br>
	 * 创建人: 方曙强<br>
	 * 创建日期: 2014-7-19<br>
	 * 
	 * @param tbName
	 * @param bean
	 * @param id
	 * @param defCol
	 *            要执行SQL的列 "id,name"
	 * @throws Exception
	 */
	public void update(String tbName, Map bean, String id, String defCol) throws Exception {
		defCol = "," + defCol + ",";
		String sql = "update " + tbName + " set ";
		List list = new ArrayList();
		try {
			Iterator<String> keys = bean.keySet().iterator();
			boolean isFirst = true;
			while (keys.hasNext()) {
				String key = (String) keys.next();
				if (!id.equals(key)) {
					Object vl = "?";
					if (defCol.indexOf("," + key + ",") != -1) {
						vl = bean.get(key);
					} else {
						list.add(bean.get(key));
					}
					if (isFirst) {
						sql += key + "=" + vl;
						isFirst = false;
					} else {
						sql += "," + key + "=" + vl;
					}
				}
			}
			sql += " where " + id + "=?";
			list.add(bean.get(id));
			int ct = getJdbcTemplate_tz().update(sql, list.toArray());
			if (ct == 0) {
				throw new RuntimeException("数据保存失败！");
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	/**
	 * 
	 * 方法名称：update<br>
	 * 方法说明: <br>
	 * 创建人: 方曙强<br>
	 * 创建日期: 2014-7-19<br>
	 * 
	 * @param tbName
	 * @param bean
	 * @param id
	 * @param defCol
	 *            要执行SQL的列 "id,name"
	 * @throws Exception
	 */
	public void update(String tbName, Map bean, String id, String defCol, JdbcTemplate tjt) throws Exception {
		defCol = "," + defCol + ",";
		String sql = "update " + tbName + " set ";
		List list = new ArrayList();
		try {
			Iterator<String> keys = bean.keySet().iterator();
			boolean isFirst = true;
			while (keys.hasNext()) {
				String key = (String) keys.next();
				if (!id.equals(key)) {
					Object vl = "?";
					if (defCol.indexOf("," + key + ",") != -1) {
						vl = bean.get(key);
					} else {
						list.add(bean.get(key));
					}      
					if (isFirst) {
						sql += key + "=" + vl;
						isFirst = false;
					} else {
						sql += "," + key + "=" + vl;
					}
				}
			}
			sql += " where " + id + "=?";
			list.add(bean.get(id));
			int ct = tjt.update(sql, list.toArray());
			if (ct == 0) {
				throw new RuntimeException("数据保存失败！");
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	/**
	 * 
	 * 方法说明: <br>
	 * 创建人: 方曙强<br>
	 * 创建日期: May 21, 2015 <br>
	 * 
	 * @param tbName
	 * @param key
	 * @param vl
	 * @return
	 * @throws Exception
	 */
	public Map getObjById(String tbName, String key, String vl) throws Exception {
		String sql = "select * from " + tbName + " t where t." + key + "=?";
		try {
			List usList = getJdbcTemplate_tz().queryForList(sql, new Object[] { vl });
			if (usList.size() == 1) {
				return (Map) usList.get(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public Map getObjFields(String tbName, String fields, Object[] obj) throws Exception {
		String sql = "select * from " + tbName + " t where 1=1";
		try {
			String fieldArr[] = fields.split(",");
			for (int i = 0; i < fieldArr.length; i++) {
				sql += " and t." + fieldArr[i] + "=?";
			}
			List usList = getJdbcTemplate_tz().queryForList(sql, obj);
			if (usList.size() == 1) {
				return (Map) usList.get(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 
	 * 方法说明: 删除 数据库 数据 <br>
	 * 创建人: 方曙强<br>
	 * 创建日期: May 21, 2015 <br>
	 * 
	 * @param tbName
	 *            表名
	 * @param idName
	 *            根据那个字段名 删除
	 * @param ids
	 *            删除的内容 多条记录 用 "," 隔开
	 * @throws Exception
	 */
	public void deletObjByIds(String tbName, String idName, String ids) throws Exception {
		try {
			String sql = "delete from " + tbName + " where " + idName + " in(" + ids + ")";
			int ct = getJdbcTemplate_tz().update(sql);
			if (ct != ids.split(",").length) {
				throw new RuntimeException("部分数据删除成功！");
			}
		} catch (Exception e) {
			throw e;
		}
	}

	/**
	 * 
	 * 方法说明: 返回要删除 数据库 数据 <br>
	 * 创建人: 王菲<br>
	 * 创建日期: May 21, 2015 <br>
	 * 
	 * @param tbName
	 *            表名
	 * @param idName
	 *            根据那个字段名 删除
	 * @param ids
	 *            删除的内容 多条记录 用 "," 隔开
	 * @throws Exception
	 */
	public List getResultGridStoreByIds(String tbName, String idName, String ids) throws Exception {
		try {
			String sql = "select * from " + tbName + " where " + idName + " in(" + ids + ")";
			List usList = getJdbcTemplate_tz().queryForList(sql);

			if (usList.size() > 0) {
				return usList;
			}

		} catch (Exception e) {
			throw e;
		}
		return null;
	}

	/**
	 * 
	 * 方法说明: <br>
	 * 创建人: 方曙强<br>
	 * 创建日期: May 21, 2015 <br>
	 * 
	 * @param sql
	 * @param start
	 * @param limit
	 * @return
	 */
	public ResultGridStore getResultGridStore(String sql, int start, int limit) {
		ResultGridStore rg = new ResultGridStore();
		String sql_list = sql + " limit " + start + "," + limit;
		String sql_count = "select count(*) from (" + sql + ")as t";
		try {
			rg.setList(getJdbcTemplate_tz().queryForList(sql_list));
			rg.setTotalProperty(getJdbcTemplate_tz().queryForInt(sql_count));
			return rg;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 
	 * 方法说明: <br>
	 * 创建人: 方曙强<br>
	 * 创建日期: May 21, 2015 <br>
	 * 
	 * @param sql
	 * @param start
	 * @param limit
	 * @param tempJdbcTemplate
	 * @return
	 */
	public ResultGridStore getResultGridStore(String sql, int start, int limit, JdbcTemplate tempJdbcTemplate) {
		ResultGridStore rg = new ResultGridStore();
		String sql_list = sql + " limit " + start + "," + limit;
		String sql_count = "select count(*) from (" + sql + ")as t";
		try {
			rg.setList(tempJdbcTemplate.queryForList(sql_list));
			rg.setTotalProperty(tempJdbcTemplate.queryForInt(sql_count));
			return rg;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
