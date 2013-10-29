package com.gamasoft.examples.highcoupling;

public class MessageData {
    private MessageItem payload;
    private final String id;

    public MessageData(String id) {
        this.id = id;

    }

    public String getId() {
        return id;
    }

    public MessageItem getPayload() {
        return payload;
    }

    public void setPayload(MessageItem payload) {
        this.payload = payload;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MessageData that = (MessageData) o;

        if (!payload.equals(that.payload)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return payload.hashCode();
    }
}
