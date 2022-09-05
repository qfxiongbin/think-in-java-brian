package com.brian.dp.apialert.domain;

import lombok.Data;

/**
 * ApiStatInfo
 *
 * @author BrianX
 * @date 2022/09/05 17:53
 **/
@Data
public class ApiStatInfo {
    private String api;
    private long requestCount;
    private long errorCount;
    private long durationOfSeconds;
}
