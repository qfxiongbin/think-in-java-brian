package com.brian.dp.metrics.report;

import com.brian.dp.metrics.domain.RequestInfo;
import com.brian.dp.metrics.domain.RequestStat;
import com.brian.dp.metrics.repository.MetricsStorage;
import com.brian.dp.metrics.service.Aggregator;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * console reporter
 *
 * @author BrianX
 * @date 2022/09/19 15:43
 **/
public class ConsoleReporter {
    private MetricsStorage metricsStorage;
    private ScheduledExecutorService executor;

    public ConsoleReporter(MetricsStorage metricsStorage){
        this.metricsStorage = metricsStorage;
        this.executor = Executors.newSingleThreadScheduledExecutor();
    }

    public void startRepeatedReport(long periodInSeconds, long durationInSeconds){
        executor.scheduleAtFixedRate(new Runnable(){
            @Override
            public void run() {
                long durationInMills = durationInSeconds * 1000;
                long endTimeInMills = System.currentTimeMillis();
                long startTimeInMills = endTimeInMills - durationInMills;

                Map<String, List<RequestInfo>> requsstInfos = metricsStorage.getRequestInfos(startTimeInMills,endTimeInMills);
                Map<String, RequestStat> stats = new HashMap<>();
                if(requsstInfos != null){
                    for(Map.Entry<String, List<RequestInfo>> entry :  requsstInfos.entrySet()){
                        String apiName = entry.getKey();
                        List<RequestInfo> requestInfosPerApi = entry.getValue();
                        RequestStat requestStat = Aggregator.aggregate(requestInfosPerApi,durationInMills);
                        stats.put(apiName, requestStat);
                    }
                }
                System.out.printf("Time Span :["+ startTimeInMills + ", "+ endTimeInMills+"]");
                Gson gson = new Gson();
                System.out.println(gson.toJson(stats));
            }
        },0,periodInSeconds, TimeUnit.SECONDS);
    }
}
