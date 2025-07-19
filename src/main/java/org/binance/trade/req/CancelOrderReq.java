package org.binance.trade.req;


import org.binance.trade.BinanceActionStrategy;
import org.binance.trade.rep.CancelOrderRep;
import org.binance.trade.sdkstrategy.CancelOrderStrategy;

public class CancelOrderReq extends  BaseRequest<CancelOrderRep>{

    //交易对
    private String symbol;

    //系统订单编号
    private Long orderId;

    //用户自定义的订单号
    private String origClientOrderId;

    @Override
    public BinanceActionStrategy getStrategy() {
        return CancelOrderStrategy.INSTANCE;
    }

    @Override
    public Class<CancelOrderRep> getResponseType() {
        return CancelOrderRep.class;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getOrigClientOrderId() {
        return origClientOrderId;
    }

    public void setOrigClientOrderId(String origClientOrderId) {
        this.origClientOrderId = origClientOrderId;
    }
}
