package com.brian.dp.metrics.domain;

import lombok.Data;

/**
 * Request stat domain
 *
 * @author BrianX
 * @date 2022/09/19 15:01
 **/
@Data
public class RequestStat {
    private double maxResponseTime;
    private double minResponseTime;
    private double avgResponseTime;
    private double p999ResponseTime;
    private double p99ResponseTime;
    private long count;
    private long tps;

}
