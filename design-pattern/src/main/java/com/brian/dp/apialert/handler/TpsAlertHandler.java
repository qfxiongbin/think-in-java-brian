package com.brian.dp.apialert.handler;

import com.brian.dp.apialert.domain.ApiStatInfo;
import com.brian.dp.apialert.rule.AlertRule;
import com.brian.dp.apialert.service.Notification;

/**
 * Tps alert handler
 *
 * @author BrianX
 * @date 2022/09/05 17:59
 **/
public class TpsAlertHandler extends AbstractAlertHandler {

    public TpsAlertHandler(AlertRule rule, Notification notification){
        super(rule, notification);
    }

    @Override
    public void check(ApiStatInfo apiStatInfo) {

    }
}
