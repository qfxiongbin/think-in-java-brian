package com.brian.dp.metrics;

import com.brian.dp.metrics.domain.RequestInfo;
import com.brian.dp.metrics.email.EmailSender;
import com.brian.dp.metrics.report.ConsoleReporter;
import com.brian.dp.metrics.report.EmailReporter;
import com.brian.dp.metrics.repository.MetricsStorage;
import com.brian.dp.metrics.repository.impl.RedisMetricsStorage;
import com.brian.dp.metrics.service.MetricsCollector;

/**
 * main
 *
 * @author BrianX
 * @date 2022/09/19 16:36
 **/
public class DemoMain {
    public static void main(String[] args) {
        MetricsStorage metricsStorage = new RedisMetricsStorage();
        ConsoleReporter consoleReporter = new ConsoleReporter(metricsStorage);
        consoleReporter.startRepeatedReport(1, 1);

        EmailReporter emailReporter = new EmailReporter(metricsStorage);
        emailReporter.addToAddress("qfxiongbin@163.com");
        emailReporter.startDailyReport();

        MetricsCollector collector = new MetricsCollector(metricsStorage);
        collector.recordRequest(new RequestInfo("register",123,10234));
        collector.recordRequest(new RequestInfo("login",123,10234));
        collector.recordRequest(new RequestInfo("add",123,10234));
        collector.recordRequest(new RequestInfo("query",123,10234));

        try{
            Thread.sleep(1000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
