package com.brian.dp.metrics.service;

import com.brian.dp.metrics.domain.RequestInfo;
import com.brian.dp.metrics.repository.MetricsStorage;
import org.apache.commons.lang3.StringUtils;


/**
 * collector
 *
 * @author BrianX
 * @date 2022/09/19 14:27
 **/
public class MetricsCollector {

    private MetricsStorage metricsStorage;

    public MetricsCollector(MetricsStorage metricsStorage){
        this.metricsStorage = metricsStorage;
    }

    public void recordRequest(RequestInfo requestInfo) {
        if(requestInfo == null || StringUtils.isBlank(requestInfo.getApiName())){
            return;
        }
        metricsStorage.saveReqestInfo(requestInfo);
    }
}
