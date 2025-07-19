package org.binance.trade.sdkstrategy;

import com.binance.connector.futures.client.impl.UMFuturesClientImpl;

import org.binance.trade.BinanceActionStrategy;
import org.binance.trade.BinanceSDKConts;
import org.binance.trade.UMFuturesClientFactory;
import org.binance.trade.req.CloseOrderReq;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedHashMap;

//https://developers.binance.com/docs/zh-CN/derivatives/usds-margined-futures/trade/rest-api
public class CloseOrderStrategy implements BinanceActionStrategy<CloseOrderReq> {

    private static final Logger logger = LoggerFactory.getLogger(CloseOrderStrategy.class);

    public static final CloseOrderStrategy INSTANCE = new CloseOrderStrategy();

    private CloseOrderStrategy() {}
    @Override
    public String execute(CloseOrderReq closeOrderReq) {
        LinkedHashMap<String, Object> params = new LinkedHashMap<>();
        params.put("symbol",closeOrderReq.getSymbol());
        params.put("side", closeOrderReq.getSide());
        params.put("type",closeOrderReq.getType());
        params.put("quantity",closeOrderReq.getQuantity());
        params.put("positionSide",closeOrderReq.getPositionSide());
        //params.put("reduceOnly", true);
        params.put("recvWindow", BinanceSDKConts.recvWindow);
        params.put("timestamp", System.currentTimeMillis());
        try {
            logger.debug("api: {} {}" , closeOrderReq.getApiKey(),closeOrderReq.getSecretKey());
            UMFuturesClientImpl client = UMFuturesClientFactory.getClient(closeOrderReq.getApiKey(),closeOrderReq.getSecretKey());
            String orderResult = client.account().newOrder(params);
            logger.info("CloseOrderStrategy: {}" , orderResult);
            return orderResult;
        } catch (Exception e) {
            logger.error("CloseOrderStrategy error:{}",e.getMessage());
        }
        return null;
    }
}