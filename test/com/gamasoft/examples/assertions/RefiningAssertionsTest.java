package com.gamasoft.examples.assertions;

import org.junit.Test;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;

public class RefiningAssertionsTest {

    @Test
    public void testName() throws Exception {

        String[] words = getWords("To be, or not to be: that is the question");
        assertNotNull(words);
        assertEquals(10, words.length);

        assertEquals("To", words[0]);
        assertEquals("question", words[9]);

    }

    private String[] getWords(String s) {
        return s.split(" ");
    }
}
