package com.gamasoft.examples.highcoupling;

public interface BusSource {
    public Publisher createActivePublisher(String topic, DataFetcher dataFetcher);

    void notify(MessageData msg);

    void addTopicListener(BusListener listener);
}
