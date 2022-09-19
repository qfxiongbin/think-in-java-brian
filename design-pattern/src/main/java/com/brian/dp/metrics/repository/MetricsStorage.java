package com.brian.dp.metrics.repository;

import com.brian.dp.metrics.domain.RequestInfo;

import java.util.List;
import java.util.Map;

public interface MetricsStorage {

    void saveReqestInfo(RequestInfo reqestInfo);

    List<RequestInfo> getRequestInfos(String apiName, long startTimeInMillis, long endTimeInMillis);

    Map<String , List<RequestInfo>> getRequestInfos(long startTimeInMillis, long endTimeInMillis);




}
