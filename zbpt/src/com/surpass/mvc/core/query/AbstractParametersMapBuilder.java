package com.surpass.mvc.core.query;

import java.util.HashMap;
import java.util.Map;

import com.surpass.utils.StringUtil;

public abstract class AbstractParametersMapBuilder implements ParametersMapBuilder {
	//第二次调用
	public Map<String, Object> build(int start, int limit, String orderBy, Parameter parameter) {
		return build(start, limit, orderBy, new Parameter[] { parameter });
	}
	//第一次调用
	public Map<String, Object> build(int start, int end, String orderBy, Object object) {
		Parameter parameter = new Parameter(StringUtil.firstLowerCaps(object.getClass().getSimpleName()), object);
		return build(start, end, orderBy, parameter);
	}
	//第三次调用
	public Map<String, Object> build(int start, int limit, String orderBy, Parameter[] parameters) {
		Map parametersMap = new HashMap();
		adaptQueryParameters(start, limit, orderBy, parametersMap);

		if (parameters != null) {
			for (Parameter parameter : parameters) {
				parametersMap.put(parameter.getName(), parameter.getValue());
			}
		}

		return parametersMap;
	}

	protected abstract void adaptQueryParameters(int start, int limit, String paramString, Map<String, Object> paramMap);

	public Map<String, Object> build(String orderBy, Parameter[] parameters) {
		Map parametersMap = new HashMap();
		if (!StringUtil.isEmpty(orderBy)) {
			parametersMap.put("orderBy", orderBy);
		}
		if ((parameters != null) && (parameters.length > 0)) {
			for (Parameter parameter : parameters) {
				parametersMap.put(parameter.getName(), parameter.getValue());
			}
		}
		return parametersMap;
	}

	public Map<String, Object> build(Parameter[] parameters) {
		Map parametersMap = new HashMap();
		if ((parameters != null) && (parameters.length > 0)) {
			for (Parameter parameter : parameters) {
				parametersMap.put(parameter.getName(), parameter.getValue());
			}
		}
		return parametersMap;
	}
}