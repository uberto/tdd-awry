package com.gamasoft.examples.highcoupling;

import org.junit.Test;
import org.mockito.ArgumentMatcher;

import static org.mockito.Matchers.argThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class PublisherTest {
    private BusSource mySource = mock(BusSource.class);

    private DataFetcher myFetcher;

    private BusListener myListener = mock(BusListener.class);

    @Test
    public void testName() throws Exception {

        Quote eurusd = new Quote("EURUSD", "1.2");

        //Simplified BusSource. More complex versions can exist for reporting, clustering etc.
        mySource = new SimpleBusSource();

        myFetcher = new DataFetcher(mySource);

        //let's register a listener for all topics
        //this is the only mock, at the end of the chain of real objects working together
        mySource.addTopicListener("*", myListener);

        //when there is an update on data...
        myFetcher.updateData(eurusd);

        //we check same data arrives to interested listeners
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
