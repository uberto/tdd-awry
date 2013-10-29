package com.gamasoft.examples.mocks;

import com.gamasoft.examples.mocks.Trade;
import com.gamasoft.examples.mocks.TradeBean;
import com.gamasoft.examples.mocks.TradeBeanFields;
import com.gamasoft.examples.mocks.TradeType;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class MockHappyTradeTest {

    private static final String ACCOUNT_VAL = "1000";
    private static final String CURRENCY_PAIR_VAL = "1.20";
    private TradeBean tradeBean = mock(TradeBean.class);
    private Trade trade;



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
        when(tradeBean.getField(TradeBeanFields.CURRENCY_PAIR)).thenReturn(CURRENCY_PAIR_VAL);
        when(tradeBean.getField(TradeBeanFields.TRADING_TYPE)).thenReturn(tradingType);
        trade = new Trade(tradeBean);
    }
}
