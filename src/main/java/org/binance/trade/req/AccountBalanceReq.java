package org.binance.trade.req;


import org.binance.trade.BinanceActionStrategy;
import org.binance.trade.rep.AccountBalanceRep;
import org.binance.trade.sdkstrategy.AccountBalanceStrategy;

public class AccountBalanceReq extends  BaseRequest<AccountBalanceRep>{

    @Override
    public BinanceActionStrategy getStrategy() {
        return AccountBalanceStrategy.INSTANCE;
    }

    @Override
    public Class<AccountBalanceRep> getResponseType() {
        return AccountBalanceRep.class;
    }

}
