package com.brian.dp.test;

/**
 * redis distributed lock
 *
 * @author BrianX
 * @date 2022/09/21 15:28
 **/
public class RedisDistributedLock {

    private static RedisDistributedLock instance;
    private RedisDistributedLock(){}

    public static RedisDistributedLock getSingletonInstance(){
        if(instance == null) {
            instance = new RedisDistributedLock();
        }
        return instance;
    }

    public boolean lockTransaction(String id){
        //TODO
        return true;
    }

    public void unlockTransaction(String id){
        //TODO
    }
}
