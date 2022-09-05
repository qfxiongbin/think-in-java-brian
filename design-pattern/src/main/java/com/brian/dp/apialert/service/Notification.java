package com.brian.dp.apialert.service;

public interface Notification {

    /**
     * alert notify
    * @param level:
    	 * @param message:
    *
    * @return void
    * @author BrianX
    * @description TODO
    * @date 2022/9/5 17:50
    */
    void notify(Enum level,String message);
}
