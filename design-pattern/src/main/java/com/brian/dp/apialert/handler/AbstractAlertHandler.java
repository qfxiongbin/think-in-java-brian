package com.brian.dp.apialert.handler;

import com.brian.dp.apialert.domain.ApiStatInfo;
import com.brian.dp.apialert.rule.AlertRule;
import com.brian.dp.apialert.service.Notification;

/**
 * AlertHandler
 *
 * @author BrianX
 * @date 2022/09/05 17:55
 **/
public abstract class AbstractAlertHandler {
    protected AlertRule rule;
    protected Notification notification;
    public AbstractAlertHandler(AlertRule rule, Notification notification){
        this.rule = rule;
        this.notification = notification;
    }

    public abstract void check(ApiStatInfo apiStatInfo);

}
