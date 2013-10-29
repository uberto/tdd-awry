package com.gamasoft.examples.highcoupling;

import org.junit.Test;
import org.mockito.ArgumentMatcher;

import static org.mockito.Matchers.argThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class PublisherTest {
    private BusSource mySource = mock(BusSource.class);

    private DataFetcher myFetcher;

    @Test
    public void testName() throws Exception {

        mySource = new SimpleBusSource();

        myFetcher = new DataFetcher(mySource);

        BusListener myListener = mock(BusListener.class);
        mySource.addTopicListener(myListener);

        Quote eurusd = new Quote("EURUSD", "1.2");
        myFetcher.updateData(eurusd);

        verify(myListener).refresh(argThat(new SamePayload(eurusd)) );
    }

    private class SamePayload extends ArgumentMatcher<MessageData> {
        private MessageItem item;

        public SamePayload(MessageItem item) {
            this.item = item;
        }


        @Override
        public boolean matches(Object o) {
            if (o instanceof MessageData){
                MessageData md = (MessageData) o;
                return item.equals(md.getPayload());
            }
            return false;
        }
    }
}
