package com.brian.dp.apialert.handler;

import com.brian.dp.apialert.constants.NotificationEmergencyLevel;
import com.brian.dp.apialert.domain.ApiStatInfo;
import com.brian.dp.apialert.rule.AlertRule;
import com.brian.dp.apialert.service.Notification;

/**
 * ErrorAlertHandler
 *
 * @author BrianX
 * @date 2022/09/08 16:46
 **/
public class ErrorAlertHandler extends AbstractAlertHandler {

    public ErrorAlertHandler(AlertRule rule, Notification notification){
        super(rule, notification);
    }

    @Override
    public void check(ApiStatInfo apiStatInfo) {
        if(apiStatInfo.getErrorCount() > rule.getMatchedRule(apiStatInfo.getApi()).getMaxErrorCount()){
            notification.notify(NotificationEmergencyLevel.SEVERE,"......");
        }
    }
}
