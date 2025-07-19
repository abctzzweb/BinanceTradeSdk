package org.binance.trade;


import org.binance.trade.req.BaseRequest;

public interface BinanceActionStrategy<T extends BaseRequest<?>> {

    String execute(T request);
}
