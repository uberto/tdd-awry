package com.gamasoft.examples.highcoupling;

public class SimpleBusSource implements BusSource {
    private BusListener listener;

    public SimpleBusSource() {
    }

    @Override
    public Publisher createActivePublisher(String topic, DataFetcher dataFetcher) {
        //associate dataFetcher with topic
        return new Publisher(){};
    }

    @Override
    public void notify(MessageData myMsg) {
      this.listener.refresh(myMsg);
    }

    @Override
    public void addTopicListener(BusListener listener) {

        this.listener = listener;
    }
}
