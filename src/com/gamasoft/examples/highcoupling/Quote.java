package com.gamasoft.examples.highcoupling;

public class Quote implements MessageItem {
    private final String subject;
    private final String value;

    public Quote(String currencyPair, String value) {
        this.subject = currencyPair;
        this.value = value;
    }

    public String getSubject() {
        return subject;
    }

    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Quote quote = (Quote) o;

        if (!subject.equals(quote.subject)) return false;
        if (!value.equals(quote.value)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = subject.hashCode();
        result = 31 * result + value.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Quote{" +
                "subject='" + subject + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}
