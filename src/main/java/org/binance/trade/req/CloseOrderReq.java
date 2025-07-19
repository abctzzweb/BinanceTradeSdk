package org.binance.trade.req;

import org.binance.trade.BinanceActionStrategy;
import org.binance.trade.rep.CloseOrderRep;
import org.binance.trade.sdkstrategy.CloseOrderStrategy;

public class CloseOrderReq extends  BaseRequest<CloseOrderRep>{

    //交易对
    private String symbol;

    //买卖方向 SELL, BUY
    private String side;

    //持仓方向，单向持仓模式下非必填，默认且仅可填BOTH;在双向持仓模式下必填,且仅可选择 LONG 或 SHORT
    private String positionSide;

    //订单类型 LIMIT, MARKET, STOP, TAKE_PROFIT, STOP_MARKET, TAKE_PROFIT_MARKET, TRAILING_STOP_MARKET
    private String type;

    /**
     * 下单数量
     * 若想通过百分比控制仓位，需自行计算：
     * quantity = （账户余额 × 风险比例） / 标记价格 。
     * */
    private String quantity;



    @Override
    public BinanceActionStrategy getStrategy() {
        return CloseOrderStrategy.INSTANCE;
    }

    @Override
    public Class<CloseOrderRep> getResponseType() {
        return CloseOrderRep.class;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getSide() {
        return side;
    }

    public void setSide(String side) {
        this.side = side;
    }

    public String getPositionSide() {
        return positionSide;
    }

    public void setPositionSide(String positionSide) {
        this.positionSide = positionSide;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

}
