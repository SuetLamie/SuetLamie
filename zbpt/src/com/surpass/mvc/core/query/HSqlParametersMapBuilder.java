package com.surpass.mvc.core.query;

import java.util.Map;

public class HSqlParametersMapBuilder extends AbstractParametersMapBuilder {
	protected void adaptQueryParameters(int start, int limit, String orderBy, Map<String, Object> parametersMap) {
		parametersMap.put("start", Integer.valueOf(start));
		parametersMap.put("limit", Integer.valueOf(limit));
		parametersMap.put("orderBy", orderBy);
	}
}