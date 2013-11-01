package com.gamasoft.examples.mocks;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class TradeTest {

    private Trade trade;
    private TradeBean tradeBean;

    @Test
    public void useSwapTypeIfTradingIsSwap() {
        //split test in two
        //construct concrete bean using a fluent builder
        tradeBean = SimpleTradeBean.prepare().currencies("EURGBP", "0.67").swapType("SPOTFWD").tradingType("SWAP");

        //next step: maybe we can get rid of mutable trade bean at all...
        trade = new Trade(tradeBean);

        //next step: better using a matcher on an expected Trade rather than a field at time
        assertEquals(TradeType.SPOTFWD, trade.getType());
    }

    @Test
    public void useSwapTypeIfTradingIsFwdSwap() {
        tradeBean = SimpleTradeBean.prepare().currencies("USDGBP", "1.2").swapType("SPOTFWD").tradingType("FWDSWAP");

        trade = new Trade(tradeBean);

        assertEquals(TradeType.SPOTFWD, trade.getType());

    }


    private static class SimpleTradeBean implements TradeBean {

        Map<String, String> fields = new HashMap<>();

        @Override
        public String getField(TradeBeanFields field) {
            return fields.get(field.name());
        }

        public static SimpleTradeBean prepare() {
            return new SimpleTradeBean();
        }

        public SimpleTradeBean swapType(String val) {
            fields.put(TradeBeanFields.SWAP_TYPE.name(), val);
            return this;
        }

        public SimpleTradeBean tradingType(String val) {
            fields.put(TradeBeanFields.TRADING_TYPE.name(), val);
            return this;
        }

        public SimpleTradeBean currencies(String pair, String price) {
            fields.put(TradeBeanFields.CURRENCY_PAIR.name(), pair);
            fields.put(TradeBeanFields.PRICE.name(), price);
            return this;
        }
    }
}
