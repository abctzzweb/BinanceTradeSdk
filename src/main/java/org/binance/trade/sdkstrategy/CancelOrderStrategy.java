package org.binance.trade.sdkstrategy;

import com.binance.connector.futures.client.impl.UMFuturesClientImpl;

import org.binance.trade.BinanceActionStrategy;
import org.binance.trade.BinanceSDKConts;
import org.binance.trade.UMFuturesClientFactory;
import org.binance.trade.req.CancelOrderReq;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedHashMap;


//https://developers.binance.com/docs/zh-CN/derivatives/usds-margined-futures/trade/rest-api/Cancel-Order
public class CancelOrderStrategy implements BinanceActionStrategy<CancelOrderReq> {
    private static final Logger logger = LoggerFactory.getLogger(CancelOrderStrategy.class);

    public static final  CancelOrderStrategy INSTANCE = new CancelOrderStrategy();

    private CancelOrderStrategy(){}
    @Override
    public String execute(CancelOrderReq cancelOrderReq) {
        LinkedHashMap<String, Object> params = new LinkedHashMap<>();
        params.put("symbol",cancelOrderReq.getSymbol());
        params.put("orderId", cancelOrderReq.getOrderId());
        params.put("origClientOrderId",cancelOrderReq.getOrigClientOrderId());

        params.put("recvWindow", BinanceSDKConts.recvWindow);
        params.put("timestamp", System.currentTimeMillis());
        try {
            logger.debug("api: {} {}" , cancelOrderReq.getApiKey(),cancelOrderReq.getSecretKey());
            UMFuturesClientImpl client = UMFuturesClientFactory.getClient(cancelOrderReq.getApiKey(),cancelOrderReq.getSecretKey());
            String orderResult = client.account().cancelOrder(params);
            logger.info("CancelOrderStrategy: {}" , orderResult);
            return orderResult;
        } catch (Exception e) {
            logger.error("CancelOrderStrategy error:{}",e.getMessage());
        }
        return null;
    }
}