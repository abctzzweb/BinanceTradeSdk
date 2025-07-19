package org.binance.trade.req;


import org.binance.trade.BinanceActionStrategy;
import org.binance.trade.rep.LeverageRep;
import org.binance.trade.sdkstrategy.LeverageStrategy;

public class LeverageReq extends  BaseRequest<LeverageRep>{

    //交易对
    private String symbol;

    //目标杠杆倍数：1 到 125 整数
    private Integer leverage;


    @Override
    public BinanceActionStrategy getStrategy() {
        return LeverageStrategy.INSTANCE;
    }

    @Override
    public Class<LeverageRep> getResponseType() {
        return LeverageRep.class;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public Integer getLeverage() {
        return leverage;
    }

    public void setLeverage(Integer leverage) {
        this.leverage = leverage;
    }
}
