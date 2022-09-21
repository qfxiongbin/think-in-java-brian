package com.brian.dp.test;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TextTest {

    @Test
    public void testToNumber(){
        Text text = new Text(" 123");
        Assertions.assertEquals(123,text.toNumber());
    }
}