package com.gamasoft.examples.assertions;

import com.gamasoft.examples.highcoupling.Quote;
import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class StructuredAssertionsTest {

    @Test
    public void testValue() throws Exception {

        Quote expected = new Quote("USDGBP", "0.62");
        Quote different = new Quote("USDGBB", "0,62");

        Quote calculated = getQuote("USD", "GBP");

     //no very good because if one fail we don't know the value of the other check
        assertEquals(expected.getSubject(), calculated.getSubject());
        assertEquals(expected.getValue(), calculated.getValue());

    //better because it compare all fields every time
        assertThat(expected, sameQuote(calculated));

     //   assertThat(different, sameQuote(calculated));
    }

    private Matcher<Quote> sameQuote(Quote quote) {
        return new QuoteMatcher(quote);
    }

    private Quote getQuote(String cur1, String cur2) {
        return new Quote(cur1+cur2, "0.62");
    }


    private class QuoteMatcher extends BaseMatcher<Quote> {
        private Quote theExpected;

        private QuoteMatcher(Quote theExpected) {
            this.theExpected = theExpected;
        }

        @Override
        public boolean matches(Object o) {
            return theExpected.equals(o);
        }

        @Override
        public void describeTo(Description description) {
            description.appendText(theExpected.toString());
        }
    }
}
