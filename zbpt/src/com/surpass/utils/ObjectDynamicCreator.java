package com.surpass.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 
 * 类名称： ObjectDynamicCreator<br>
 * 类描述： <br>
 * 创建人： 方曙强<br>
 * 创建时间：Sep 25, 2013<br>
 * 修改人： 方曙强<br>
 * 修改时间：Sep 25, 2013
 */
public class ObjectDynamicCreator {
	/**
	 * 返回由对象的属性为key,值为map的value的Map集合
	 * 
	 * @param obj
	 *            Object
	 * @return mapValue Map<String,String>
	 * @throws Exception
	 */
	public static Map<String, Object> getFieldVlaue(Object obj) throws Exception {
		Map<String, Object> mapValue = new HashMap<String, Object>();
		Class<?> cls = obj.getClass();
		Field[] fields = cls.getDeclaredFields();
		for (Field field : fields) {
			String name = field.getName();
			String strGet = "get" + name.substring(0, 1).toUpperCase() + name.substring(1, name.length());
			Method methodGet = null;
			try {
				// private 修饰：
				if (2 == field.getModifiers()) {
					methodGet = cls.getDeclaredMethod(strGet);
				} else {
					continue;
				}
			} catch (Exception e) {
				throw new RuntimeException(cls.getSimpleName() + "类 " + strGet + " 方法初始化失败！");
			}
			if (null != methodGet) {
				Object value = methodGet.invoke(obj);
				if (null != value) {
					mapValue.put(name, value);
				}
			}
		}
		return mapValue;
	}

	/**
	 * 返回由Map的key对属性，value对应值组成的对应
	 * 
	 * @param map
	 *            Map<String,String>
	 * @param cls
	 *            Class
	 * @return obj Object
	 * @throws Exception
	 */
	public static Object setFieldValue(Map map, Class<?> cls) throws Exception {
		Field[] fields = cls.getDeclaredFields();
		Object obj = cls.newInstance();
		for (Field field : fields) {
			Class<?> clsType = field.getType();
			String name = field.getName();
			String strSet = "set" + name.substring(0, 1).toUpperCase() + name.substring(1, name.length());
			Method methodSet = null;
			try {
				// private 修饰：
				if (2 == field.getModifiers()) {
					methodSet = cls.getDeclaredMethod(strSet, clsType);
				} else {
					continue;
				}
			} catch (Exception e) {
				throw new RuntimeException(cls.getSimpleName() + "类 " + strSet + " 方法初始化失败！");
			}
			if (map.containsKey(name)) {
				if (null != map.get(name)) {
					Object objValue = typeConversion(clsType, map.get(name).toString());
					methodSet.invoke(obj, objValue);
				}
			}
		}
		return obj;
	}

	/**
	 * 将Map里面的部分值通过反射设置到已有对象里去
	 * 
	 * @param obj
	 *            Object
	 * @param data
	 *            Map<String,String>
	 * @return obj Object
	 * @throws Exception
	 */
	public static Object setObjectFileValue(Object obj, Map<String, String> data) throws Exception {
		Class<?> cls = obj.getClass();
		Field[] fields = cls.getDeclaredFields();
		for (Field field : fields) {
			Class<?> clsType = field.getType();
			String name = field.getName();
			String strSet = "set" + name.substring(0, 1).toUpperCase() + name.substring(1, name.length());
			Method methodSet = null;
			try {
				// private 修饰：
				if (2 == field.getModifiers()) {
					methodSet = cls.getDeclaredMethod(strSet, clsType);
				} else {
					continue;
				}
			} catch (Exception e) {
				throw new RuntimeException(cls.getSimpleName() + "类 " + strSet + " 方法初始化失败！");
			}
			if (data.containsKey(name)) {
				Object objValue = typeConversion(clsType, data.get(name));
				methodSet.invoke(obj, objValue);
			}
		}
		return obj;
	}

	/**
	 * 把对象的值用Map对应装起来
	 * 
	 * @param map
	 *            Map<String,String>
	 * @param obj
	 *            Object
	 * @return 与对象属性对应的Map Map<String,String>
	 */
	public static Map<String, String> compareMap(Map<String, String> map, Object obj) {
		Map<String, String> mapValue = new HashMap<String, String>();
		Field[] fields = obj.getClass().getDeclaredFields();
		for (Field field : fields) {
			String name = field.getName();
			if (map.containsKey(name)) {
				mapValue.put(name, map.get(name));
			}
		}
		return mapValue;
	}

	/**
	 * 把临时对象的值复制到持久化对象上
	 * 
	 * @param oldObject
	 *            Object 持久化对象
	 * @param newObject
	 *            Object 临时对象
	 * @return 持久化对象
	 * @throws Exception
	 */
	public static Object mergedObject(Object oldObject, Object newObject) throws Exception {
		Class<?> cls = newObject.getClass();
		Field[] fields = cls.getDeclaredFields();
		for (Field field : fields) {
			Class<?> clsType = field.getType();
			String name = field.getName();
			String method = name.substring(0, 1).toUpperCase() + name.substring(1, name.length());
			String strGet = "get" + method;
			Method methodGet = cls.getDeclaredMethod(strGet);
			Object object = methodGet.invoke(newObject);
			if (object != null) {
				String strSet = "set" + method;
				Method methodSet = cls.getDeclaredMethod(strSet, clsType);
				Object objValue = typeConversion(clsType, object.toString());
				methodSet.invoke(oldObject, objValue);
			}
		}
		return oldObject;
	}

	public static Object typeConversion(Class<?> cls, String vl) {
		Object obj = null;
		String nameType = cls.getSimpleName();
		if ("Integer".equals(nameType)) {
			obj = Integer.valueOf(vl);
		} else if ("String".equals(nameType)) {
			obj = vl;
		} else if ("Float".equals(nameType)) {
			obj = Float.valueOf(vl);
		} else if ("Double".equals(nameType)) {
			obj = Double.valueOf(vl);
		} else if ("Boolean".equals(nameType)) {
			obj = (vl.length() > 1 ? Boolean.valueOf(vl) : (vl.equals("1") ? true : false));
		} else if ("Long".equals(nameType)) {
			obj = Long.valueOf(vl);
		} else if ("Short".equals(nameType)) {
			obj = Short.valueOf(vl);
		} else if ("Date".equals(nameType)) {
			if (vl.length() == 10 && vl.indexOf("-") > -1) {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				try {
					obj = sdf.parse(vl);
				} catch (ParseException e) {
					e.printStackTrace();
				}
			} else {
				obj = new Date(new Timestamp(System.currentTimeMillis()).valueOf(vl).getTime());
			}

		} else {
			obj = vl;
		}
		return obj;
	}
}