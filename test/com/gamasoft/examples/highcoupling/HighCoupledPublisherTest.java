package com.gamasoft.examples.highcoupling;

import org.junit.Test;

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

        when(mySource.createActivePublisher(any(String.class), any(DataFetcher.class))).thenReturn(myPub);

        when(myPub.getMessageFactory()).thenReturn(myMsgFactory);

        when(myMsgFactory.createMessage("EURUSD")).thenReturn(myMsg);

        myFetcher = new DataFetcher(mySource);

        mySource.createActivePublisher("quotes", myFetcher);

        MessageItem item = mock(MessageItem.class);

        when(item.getSubject()).thenReturn("EURUSD");


        myFetcher.updateData(item);

        verify(mySource).notify(myMsg);
    }
}
