package org.binance.trade.req;

import org.binance.trade.BinanceActionStrategy;
import org.binance.trade.rep.PositionSideRep;
import org.binance.trade.sdkstrategy.PositionSideStrategy;

public class PositionSideReq extends  BaseRequest<PositionSideRep>{

    //"true": 双向持仓模式；"false": 单向持仓模式
    private String dualSidePosition;


    @Override
    public BinanceActionStrategy getStrategy() {
        return PositionSideStrategy.INSTANCE;
    }

    @Override
    public Class<PositionSideRep> getResponseType() {
        return PositionSideRep.class;
    }

    public String getDualSidePosition() {
        return dualSidePosition;
    }

    public void setDualSidePosition(String dualSidePosition) {
        this.dualSidePosition = dualSidePosition;
    }
}
