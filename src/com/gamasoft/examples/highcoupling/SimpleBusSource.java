package com.gamasoft.examples.highcoupling;

import java.util.HashMap;
import java.util.Map;

public class SimpleBusSource implements BusSource {
    private Map<String, BusListener> listeners = new HashMap<>();

    public SimpleBusSource() {
    }

    @Override
    public Publisher createActivePublisher(String topic, DataFetcher dataFetcher) {
        //associate dataFetcher with topic
        return new Publisher(){};
    }

    @Override
    public void notify(MessageData myMsg) {
        for (BusListener busListener : listeners.values()) {
            //if msg topic is relevant for the listener
            busListener.refresh(myMsg);
        }

    }

    @Override
    public void addTopicListener(String topic, BusListener listener) {
        listeners.put(topic, listener);
    }
}
