package com.surpass.utils;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;

/**
 * 
 * 类名称： ObjectRowMapper<br>
 * 类描述： <br>
 * 创建人： 方曙强<br>
 * 创建时间：Sep 25, 2013<br>
 * 修改人： 方曙强<br>
 * 修改时间：Sep 25, 2013
 */
public class ObjectRowMapper implements RowMapper {
	private String columnMode;

	public ObjectRowMapper() {
	}

	/**
	 * 
	 * @param columnMode
	 *            格式 usId,usName
	 */
	public ObjectRowMapper(String columnMode) {
		this.columnMode = "," + columnMode + ",";
	}

	public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
		Map rtMap = new HashMap();
		ResultSetMetaData rsmd = rs.getMetaData();
		int columnCount = rsmd.getColumnCount();
		if (columnMode != null) {
			for (int i = 0; i < columnCount; i++) {
				String columnName = rsmd.getColumnName(i + 1).toLowerCase();
				int idx = columnMode.toLowerCase().indexOf(
						"," + columnName.toLowerCase() + ",");
				if (idx != -1) {
					columnName = columnMode.substring(idx + 1, idx
							+ columnName.length() + 1);
				}
				Object vl = rs.getObject(i + 1);
				if ("true".equals(vl)) {
					vl = true;
				} else if ("false".equals(vl)) {
					vl = false;
				}
				if (vl instanceof Date) {
					rtMap.put(columnName,rs.getTimestamp(i+1));
				} else {
					rtMap.put(columnName, vl);
				}
			}

		} else {
			for (int i = 0; i < columnCount; i++) {
				String columnName = rsmd.getColumnName(i + 1).toLowerCase();
				Object vl = rs.getObject(i + 1);
				if ("true".equals(vl)) {
					vl = true;
				} else if ("false".equals(vl)) {
					vl = false;
				}
				if (vl instanceof Date) {
					rtMap.put(columnName,rs.getTimestamp(i+1));
				} else {
					rtMap.put(columnName, vl);
				}
			}
		}
		return rtMap;
	}
}
