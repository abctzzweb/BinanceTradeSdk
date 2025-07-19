package org.binance.trade.rep;

import com.alibaba.fastjson.annotation.JSONField;
import org.binance.trade.req.CloseOrderReq;

public class CloseOrderRep extends BaseResponse<CloseOrderReq> {

    private String clientOrderId;// "testOrder", // 用户自定义的订单号
    private String cumQty;// "0",
    private String cumQuote;// "0", // 成交金额
    private String executedQty;// "0", // 成交量
    private String orderId;// 22542179, // 系统订单号
    private String avgPrice;// "0.00000",	// 平均成交价
    private String origQty;// "10", // 原始委托数量
    private String price;// "0", // 委托价格
    private Boolean reduceOnly;// false, // 仅减仓
    private String side;// "SELL", // 买卖方向
    private String positionSide;// "SHORT", // 持仓方向
    private String status;// "NEW", // 订单状态
    private String stopPrice;// "0", // 触发价，对`TRAILING_STOP_MARKET`无效
    private String closePosition;// false,   // 是否条件全平仓
    private String symbol;// "BTCUSDT", // 交易对
    private String timeInForce;// "GTD", // 有效方法
    private String type;// "TRAILING_STOP_MARKET", // 订单类型
    private String origType;// "TRAILING_STOP_MARKET",  // 触发前订单类型
    private String activatePrice;// "9020", // 跟踪止损激活价格, 仅`TRAILING_STOP_MARKET` 订单返回此字段
    private String priceRate;// "0.3",	// 跟踪止损回调比例, 仅`TRAILING_STOP_MARKET` 订单返回此字段
    private String updateTime;// 1566818724722, // 更新时间
    private String workingType;// "CONTRACT_PRICE", // 条件价格触发类型
    private Boolean priceProtect;// false,            // 是否开启条件单触发保护
    private String priceMatch;// "NONE",              //盘口价格下单模式
    private String selfTradePreventionMode;// "NONE", //订单自成交保护模式
    private Long goodTillDate;// 1693207680000      //订单TIF为GTD时的自动取消时间

    @JSONField(serialize = false)
    @Override
    public Class<CloseOrderReq> getRequestType() {
        return CloseOrderReq.class;
    }


    public String getClientOrderId() {
        return clientOrderId;
    }

    public void setClientOrderId(String clientOrderId) {
        this.clientOrderId = clientOrderId;
    }

    public String getCumQty() {
        return cumQty;
    }

    public void setCumQty(String cumQty) {
        this.cumQty = cumQty;
    }

    public String getCumQuote() {
        return cumQuote;
    }

    public void setCumQuote(String cumQuote) {
        this.cumQuote = cumQuote;
    }

    public String getExecutedQty() {
        return executedQty;
    }

    public void setExecutedQty(String executedQty) {
        this.executedQty = executedQty;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getAvgPrice() {
        return avgPrice;
    }

    public void setAvgPrice(String avgPrice) {
        this.avgPrice = avgPrice;
    }

    public String getOrigQty() {
        return origQty;
    }

    public void setOrigQty(String origQty) {
        this.origQty = origQty;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public Boolean getReduceOnly() {
        return reduceOnly;
    }

    public void setReduceOnly(Boolean reduceOnly) {
        this.reduceOnly = reduceOnly;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStopPrice() {
        return stopPrice;
    }

    public void setStopPrice(String stopPrice) {
        this.stopPrice = stopPrice;
    }

    public String getClosePosition() {
        return closePosition;
    }

    public void setClosePosition(String closePosition) {
        this.closePosition = closePosition;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getTimeInForce() {
        return timeInForce;
    }

    public void setTimeInForce(String timeInForce) {
        this.timeInForce = timeInForce;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getOrigType() {
        return origType;
    }

    public void setOrigType(String origType) {
        this.origType = origType;
    }

    public String getActivatePrice() {
        return activatePrice;
    }

    public void setActivatePrice(String activatePrice) {
        this.activatePrice = activatePrice;
    }

    public String getPriceRate() {
        return priceRate;
    }

    public void setPriceRate(String priceRate) {
        this.priceRate = priceRate;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getWorkingType() {
        return workingType;
    }

    public void setWorkingType(String workingType) {
        this.workingType = workingType;
    }

    public Boolean getPriceProtect() {
        return priceProtect;
    }

    public void setPriceProtect(Boolean priceProtect) {
        this.priceProtect = priceProtect;
    }

    public String getPriceMatch() {
        return priceMatch;
    }

    public void setPriceMatch(String priceMatch) {
        this.priceMatch = priceMatch;
    }

    public String getSelfTradePreventionMode() {
        return selfTradePreventionMode;
    }

    public void setSelfTradePreventionMode(String selfTradePreventionMode) {
        this.selfTradePreventionMode = selfTradePreventionMode;
    }

    public Long getGoodTillDate() {
        return goodTillDate;
    }

    public void setGoodTillDate(Long goodTillDate) {
        this.goodTillDate = goodTillDate;
    }
}
