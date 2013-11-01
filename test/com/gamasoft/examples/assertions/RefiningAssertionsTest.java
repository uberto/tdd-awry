package com.gamasoft.examples.assertions;

import org.junit.Test;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;

public class RefiningAssertionsTest {

    @Test
    public void testName() throws Exception {

        String text = "To be, or not to be: that is the question";
        String[] words = getWords(text);

        //these assertion are increasing the precision, if one fails it makes no sense to run the next
        assertNotNull(words);
        assertEquals(10, words.length);

        assertEquals("To", words[0]);

    }

    private String[] getWords(String s) {
        return s.split(" ");
    }
}
