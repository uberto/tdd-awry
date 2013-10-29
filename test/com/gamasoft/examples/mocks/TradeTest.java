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
        tradeBean = SimpleTradeBean.prepare().swapType("SPOTFWD").tradingType("SWAP");

        trade = new Trade(tradeBean);

        assertEquals(TradeType.SPOTFWD, trade.getType());
    }

    @Test
    public void useSwapTypeIfTradingIsFwdSwap() {
        tradeBean = SimpleTradeBean.prepare().swapType("SPOTFWD").tradingType("FWDSWAP");

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
    }
}
