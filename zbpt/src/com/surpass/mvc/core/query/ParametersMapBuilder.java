package com.surpass.mvc.core.query;

import java.util.Map;

public abstract interface ParametersMapBuilder {
	public abstract Map<String, Object> build(int start, int limit, String paramString, Parameter paramParameter);

	public abstract Map<String, Object> build(int start, int limit, String paramString, Parameter[] paramArrayOfParameter);

	public abstract Map<String, Object> build(int start, int limit, String paramString, Object paramObject);

	public abstract Map<String, Object> build(Parameter[] paramArrayOfParameter);

	public abstract Map<String, Object> build(String paramString, Parameter[] paramArrayOfParameter);
}
