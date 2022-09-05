package com.brian.dp.apialert.domain;

import com.brian.dp.apialert.constants.NotificationEmergencyLevel;
import com.brian.dp.apialert.rule.AlertRule;
import com.brian.dp.apialert.service.Notification;

/**
 * API alert domain
 *
 * @author BrianX
 * @date 2022/09/02 18:09
 **/
public class Alert {
    private AlertRule rule;
    private Notification notification;

    public Alert(AlertRule rule,Notification notification){
        this.rule = rule;
        this.notification = notification;
    }

    public void check(String api, long requestCount, long errorCount,long timeoutCount, long durationOfSeconds){
        long tps = requestCount / durationOfSeconds;
        if(tps > rule.getMatchedRule(api).getMaxTps()){
            notification.notify(NotificationEmergencyLevel.URGENCY,"...");
        }

        if(errorCount > rule.getMatchedRule(api).getMaxErrorCount()){
            notification.notify(NotificationEmergencyLevel.SEVERE,"...");
        }

        long timeoutTps = timeoutCount / durationOfSeconds;
        if(timeoutTps > rule.getMatchedRule(api).getMaxTimeoutTps()){
            notification.notify(NotificationEmergencyLevel.URGENCY,"...");
        }
    }
}
