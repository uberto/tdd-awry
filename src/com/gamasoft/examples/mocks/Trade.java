package com.gamasoft.examples.mocks;

public class Trade {
    private TradeBean tradeBean;

    public Trade(TradeBean tradeBean) {

        this.tradeBean = tradeBean;
    }

    public TradeType getType() {
        if (tradeBean.getField(TradeBeanFields.TRADING_TYPE).contains("SWAP"))
            return TradeType.valueOf(tradeBean.getField(TradeBeanFields.SWAP_TYPE));
       else //other trading types
            return TradeType.FWD;
    }

    public String getCurrencyPair() {
        return tradeBean.getField(TradeBeanFields.CURRENCY_PAIR);
    }
}
