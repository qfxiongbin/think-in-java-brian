package com.brian.dp.test;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * test
 *
 * @author BrianX
 * @date 2022/09/22 18:02
 **/
public class RangeLimiter {
    private static AtomicInteger position = new AtomicInteger(0);
    public static final int MAX_LIMIT = 5;
    public static final int MIN_LIMIT = -5;

    public boolean move(int delta){
        int currentPos = position.addAndGet(delta);
        boolean betweenRange = (currentPos <= MAX_LIMIT && currentPos >= MIN_LIMIT);
        return betweenRange;
    }
}
