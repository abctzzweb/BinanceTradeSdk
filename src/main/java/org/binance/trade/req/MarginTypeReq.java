package org.binance.trade.req;


import org.binance.trade.BinanceActionStrategy;
import org.binance.trade.rep.MarginTypeRep;
import org.binance.trade.sdkstrategy.MarginTypeStrategy;

public class MarginTypeReq extends  BaseRequest<MarginTypeRep>{

    //交易对
    private String symbol;

    //保证金模式 ISOLATED(逐仓), CROSSED(全仓)
    private String marginType;


    @Override
    public BinanceActionStrategy getStrategy() {
        return MarginTypeStrategy.INSTANCE;
    }

    @Override
    public Class<MarginTypeRep> getResponseType() {
        return MarginTypeRep.class;
    }


    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getMarginType() {
        return marginType;
    }

    public void setMarginType(String marginType) {
        this.marginType = marginType;
    }
}
