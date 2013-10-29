package com.gamasoft.examples.highcoupling;

public class DataFetcher {
    private final Publisher pub;
    private BusSource busSource;

    public DataFetcher(BusSource busSource) {
        this.busSource = busSource;

        pub = busSource.createActivePublisher("subj", this);
    }

    public void updateData(MessageItem item) {

        MessageData msg = pub.getMessageFactory().createMessage(item.getSubject());
        msg.setPayload(item);

        busSource.notify(msg);
    }


}
