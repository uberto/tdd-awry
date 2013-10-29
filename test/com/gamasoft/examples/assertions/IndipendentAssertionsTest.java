package com.gamasoft.examples.assertions;

import org.junit.Test;

import static junit.framework.Assert.assertEquals;

public class IndipendentAssertionsTest {

    @Test
    public void testName() throws Exception {

        assertEquals(5, sum(3,2));
        assertEquals(8, sum(3,5));
        assertEquals(sum(3,sum(4,5)), sum(sum(3, 4),5));

    }

    private int sum(int a, int b) {
        return a + b;
    }
}
