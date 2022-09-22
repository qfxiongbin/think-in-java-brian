package com.brian.dp.test;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RangeLimiterTest {

    @Test
    public void testBetweenRange(){
        RangeLimiter rangeLimiter = new RangeLimiter();
        assertTrue(rangeLimiter.move(1));
        assertTrue(rangeLimiter.move(7));
        assertTrue(rangeLimiter.move(-5));
    }

    @Test
    public void testMove_exceedRange(){
        RangeLimiter rangeLimiter = new RangeLimiter();
        assertFalse(rangeLimiter.move(6));
    }

}