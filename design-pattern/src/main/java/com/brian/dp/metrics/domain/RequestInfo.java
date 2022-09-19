package com.brian.dp.metrics.domain;

import lombok.Data;

/**
 * request info domain
 *
 * @author BrianX
 * @date 2022/09/19 14:32
 **/

@Data
public class RequestInfo {
    private String apiName;
    private double responseTime;
    private long timestamp;

    public RequestInfo(String apiName, double responseTime,long timestamp){
        this.apiName = apiName;
        this.responseTime = responseTime;
        this.timestamp = timestamp;
    }
}
