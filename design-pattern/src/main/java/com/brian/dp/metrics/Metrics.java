package com.brian.dp.metrics;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Metrics demo v1.0
 *
 * @author BrianX
 * @date 2022/09/19 11:47
 **/
public class Metrics {
    private Map<String, List<Double>> reponseTimes = new HashMap<>();
    private Map<String, List<Double>> timestamps = new HashMap<>();
    private ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();

    public void recordResponseTime(String apiName, double responseTime) {
        reponseTimes.putIfAbsent(apiName,new ArrayList<>());
        reponseTimes.get(apiName).add(responseTime);
    }

    public void recordTimestamps(String apiName, double timestamp){
        timestamps.putIfAbsent(apiName,new ArrayList<>());
        timestamps.get(apiName).add(timestamp);
    }

    public void startRepeatedReport(long period, TimeUnit unit){
        executor.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                Gson gson = new Gson();
                Map<String, Map<String, Double>> stats = new HashMap<>();
                for(Map.Entry<String, List<Double>> entry : reponseTimes.entrySet()) {
                    String apiName = entry.getKey();
                    List<Double> apiRespTimes = entry.getValue();
                    stats.putIfAbsent(apiName, new HashMap<>());
                    stats.get(apiName).put("max",max(apiRespTimes));
                    stats.get(apiName).put("avg",avg(apiRespTimes));
                }

                for(Map.Entry<String, List<Double>> entry : timestamps.entrySet()){
                    String apiName = entry.getKey();
                    List<Double> apiTimestamps = entry.getValue();
                    stats.putIfAbsent(apiName,new HashMap<>());
                    stats.get(apiName).put("count",(double)apiTimestamps.size());
                }
                System.out.println(gson.toJson(stats));
            }
        },0,period,unit);
    }

    private double max(List<Double> dataset){ return 0;}

    private double avg(List<Double> dataset){ return 0;}

}
