package com.brian.dp.metrics.repository.impl;

import com.brian.dp.metrics.domain.RequestInfo;
import com.brian.dp.metrics.repository.MetricsStorage;

import java.util.List;
import java.util.Map;

/**
 * redis metrics storage impl
 *
 * @author BrianX
 * @date 2022/09/19 14:39
 **/
public class RedisMetricsStorage implements MetricsStorage {
    @Override
    public void saveReqestInfo(RequestInfo reqestInfo) {

    }

    @Override
    public List<RequestInfo> getRequestInfos(String apiName, long startTimeInMillis, long endTimeInMillis) {
        return null;
    }

    @Override
    public Map<String, List<RequestInfo>> getRequestInfos(long startTimeInMillis, long endTimeInMillis) {
        return null;
    }
}
