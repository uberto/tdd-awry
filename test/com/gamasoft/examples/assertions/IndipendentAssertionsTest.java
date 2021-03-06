package com.gamasoft.examples.assertions;

import org.junit.Test;

import static junit.framework.Assert.assertEquals;

public class IndipendentAssertionsTest {

    @Test
    public void commutativeProperty() throws Exception {

        //these three should be put on 3 different tests
        //or use some kind of data table assertions like spec
        //or maybe assertAndContinue()

        assertEquals(5, sum(3,2));
        assertEquals(8, sum(3,5));
        assertEquals(sum(3,sum(4,5)), sum(sum(3, 4),5));

    }

    private int sum(int a, int b) {
        return a + b;
    }
}
