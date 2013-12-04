package com.gamasoft.examples.highcoupling;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

public class HighCoupledPublisherTest {

    private BusSource mySource = mock(BusSource.class);
    private Publisher myPub = mock(Publisher.class);
    private MsgFactory myMsgFactory = mock (MsgFactory.class);
    private MessageData myMsg = mock(MessageData.class);
    private DataFetcher myFetcher;

    @Test
    public void testBusSourceSendMessage() throws Exception {

        //complex setup, unclear design, high coupling
        when(mySource.createActivePublisher(any(String.class), any(DataFetcher.class))).thenReturn(myPub);
        when(myPub.getMessageFactory()).thenReturn(myMsgFactory);
        when(myMsgFactory.createMessage("EURUSD")).thenReturn(myMsg);

        myFetcher = new DataFetcher(mySource);

        mySource.createActivePublisher("quotes", myFetcher);

        //why mocking a immutable object?
        MessageItem item = mock(MessageItem.class);

        when(item.getSubject()).thenReturn("EURUSD");

        //7 lines of mocks to test 3 lines of code
        myFetcher.updateData(item);

        //hard to understand the goal of this test from the verify
        verify(mySource).notify(myMsg);
    }

    @Test
    public void simplifiedBusSourceSendMessage() throws Exception {
        //simplify the real class to use it in tests
        BusSource mySource = new SimpleBusSource();

        myFetcher = new DataFetcher(mySource);

        Publisher myPub = mySource.createActivePublisher("quotes", myFetcher);

        // myFetcher.updateData(item);
        //verify(mySource).notify(myMsg);
        //we cannot yet verify the fetcher, so we copied fetcher code here and test it

        myMsg = myPub.getMessageFactory().createMessage("EURGBP");

        assertEquals("EURGBP", myMsg.getId());

    }
}
