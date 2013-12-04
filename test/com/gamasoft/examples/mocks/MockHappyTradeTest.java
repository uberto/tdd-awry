package com.gamasoft.examples.mocks;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class MockHappyTradeTest {

    private static final String ACCOUNT_VAL = "1000";
    private static final String PRICE = "1.20";
    public static final String CURRENCY_PAIR = "USDGBP";
    private TradeBean tradeBean = mock(TradeBean.class);
    private Trade trade;


    @Test
    public void testGetCurrencyPair_USDGBP() {
        when(tradeBean.getField(TradeBeanFields.CURRENCY_PAIR)).thenReturn(CURRENCY_PAIR);
        setUpTrade("FWD");
        assertEquals(CURRENCY_PAIR, trade.getCurrencyPair());
    }

    @Test
    public void testGetSwapType_SPOTFWD() {
        when(tradeBean.getField(TradeBeanFields.SWAP_TYPE)).thenReturn("SPOTFWD");
        setUpTrade("SWAP");
        assertEquals(TradeType.SPOTFWD, trade.getType());

        when(tradeBean.getField(TradeBeanFields.SWAP_TYPE)).thenReturn("SPOTFWD");
        setUpTrade("FWDFWDSWAP");
        assertEquals(TradeType.SPOTFWD, trade.getType());
    }



    private void setUpTrade(final String tradingType) {
        when(tradeBean.getField(TradeBeanFields.ACCOUNT)).thenReturn(ACCOUNT_VAL);
        when(tradeBean.getField(TradeBeanFields.PRICE)).thenReturn(PRICE);
        when(tradeBean.getField(TradeBeanFields.TRADING_TYPE)).thenReturn(tradingType);
        trade = new Trade(tradeBean);
    }


//split and inline
    @Test
    public void testGetSwapType_SPOTFWD_SWP() {
        when(tradeBean.getField(TradeBeanFields.SWAP_TYPE)).thenReturn("SPOTFWD");
        when(tradeBean.getField(TradeBeanFields.ACCOUNT)).thenReturn(ACCOUNT_VAL);
        when(tradeBean.getField(TradeBeanFields.PRICE)).thenReturn(PRICE);
        when(tradeBean.getField(TradeBeanFields.TRADING_TYPE)).thenReturn("SWAP");
        trade = new Trade(tradeBean);
        assertEquals(TradeType.SPOTFWD, trade.getType());
    }

    @Test
    public void testGetSwapType_SPOTFWD_FWDSWP() {
        when(tradeBean.getField(TradeBeanFields.SWAP_TYPE)).thenReturn("SPOTFWD");
        when(tradeBean.getField(TradeBeanFields.ACCOUNT)).thenReturn(ACCOUNT_VAL);
        when(tradeBean.getField(TradeBeanFields.PRICE)).thenReturn(PRICE);
        when(tradeBean.getField(TradeBeanFields.TRADING_TYPE)).thenReturn("FWDFWDSWAP");
        trade = new Trade(tradeBean);
        assertEquals(TradeType.SPOTFWD, trade.getType());
    }
}
