package com.brian.dp.metrics.report;

import com.brian.dp.metrics.domain.RequestInfo;
import com.brian.dp.metrics.domain.RequestStat;
import com.brian.dp.metrics.email.EmailSender;
import com.brian.dp.metrics.repository.MetricsStorage;
import com.brian.dp.metrics.service.Aggregator;

import java.util.*;

/**
 * Email reporter
 *
 * @author BrianX
 * @date 2022/09/19 16:17
 **/
public class EmailReporter {

    private static final Long DAY_HOURS_IN_SECONDS = 86400L;

    private MetricsStorage metricsStorage;
    private EmailSender emailSender;
    private List<String> toAddress = new ArrayList<>();


    public EmailReporter(MetricsStorage metricsStorage){
        this(metricsStorage,new EmailSender());
    }

    public EmailReporter(MetricsStorage metricsStorage, EmailSender emailSender){
        this.metricsStorage = metricsStorage;
        this.emailSender = emailSender;
    }

    public void addToAddress(String address){
        toAddress.add(address);
    }

    public void startDailyReport() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE,1);
        calendar.set(Calendar.HOUR_OF_DAY,0);
        calendar.set(Calendar.MINUTE,0);
        calendar.set(Calendar.SECOND,0);
        Date firstTime = calendar.getTime();
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                long durationInMills = DAY_HOURS_IN_SECONDS * 1000;
                long endTimeInMillis = System.currentTimeMillis();
                long startTimeInMillis = endTimeInMillis - durationInMills;

                Map<String, List<RequestInfo>> requsstInfos = metricsStorage.getRequestInfos(startTimeInMillis,endTimeInMillis);
                Map<String, RequestStat> stats = new HashMap<>();
                for(Map.Entry<String, List<RequestInfo>> entry :  requsstInfos.entrySet()){
                    String apiName = entry.getKey();
                    List<RequestInfo> requestInfosPerApi = entry.getValue();
                    RequestStat requestStat = Aggregator.aggregate(requestInfosPerApi,durationInMills);
                    stats.put(apiName, requestStat);
                }
            }
        },firstTime,DAY_HOURS_IN_SECONDS * 1000);
    }



}
