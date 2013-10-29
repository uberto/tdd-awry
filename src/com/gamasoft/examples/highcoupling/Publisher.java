package com.gamasoft.examples.highcoupling;



public class Publisher {
    public MsgFactory getMessageFactory() {
        return new MsgFactory() {
            @Override
            public MessageData createMessage(String topic) {
                return new MessageData(topic);
            }
        };
    }
}
