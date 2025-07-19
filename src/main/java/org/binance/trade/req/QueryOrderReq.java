package org.binance.trade.req;


import org.binance.trade.BinanceActionStrategy;
import org.binance.trade.rep.QueryOrderRep;
import org.binance.trade.sdkstrategy.QueryOrderStrategy;

public class QueryOrderReq extends  BaseRequest<QueryOrderRep>{

    //交易对
    private String symbol;

    //只返回此orderID及之后的订单，缺省返回最近的订单
    private Long orderId;

    //可不传返回的结果集数量 默认值:500 最大值:1000
    private Integer limit;


    @Override
    public BinanceActionStrategy getStrategy() {
        return QueryOrderStrategy.INSTANCE;
    }

    @Override
    public Class<QueryOrderRep> getResponseType() {
        return QueryOrderRep.class;
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

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }
}
