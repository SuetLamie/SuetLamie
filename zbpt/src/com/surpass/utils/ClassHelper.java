package com.surpass.utils;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public final class ClassHelper {

	private ClassHelper() {
	}

	/**
	 * 返回所有声明为private的属性.
	 */
	public static List<Field> getAllDeclaredFields(Class entityClass) {
		List<Field> fields = new ArrayList<Field>();

		if (entityClass != null) {
			Field[] properties = entityClass.getDeclaredFields();
			for (int i = 0; i < properties.length; i++) {
				if (properties[i].getModifiers() == 2) {
					fields.add(properties[i]);
				}
			}

			// 父类的属性
			Class superClass = entityClass.getSuperclass();
			fields.addAll(getAllDeclaredFields(superClass));
		}

		return fields;
	}

	/**
	 * 返回Set或者List中的泛型
	 */
	public static Class getGenericClass(Field field) {
		Type type = field.getGenericType();
		if (type instanceof ParameterizedType) {
			ParameterizedType pt = (ParameterizedType) type;
			return (Class) pt.getActualTypeArguments()[0];
		}
		return null;
	}
	
	/**
	 * 过滤CGLIB动态生成的类，则强制转换
	 */
	public static Class leachClass(Class clazz) throws Exception {
		if (clazz.getName().indexOf("$$EnhancerByCGLIB$$") > -1) {
			return (Class.forName(clazz.getName().substring(0,
					clazz.getName().indexOf("$$"))));
		}
		return clazz;
	}
}
