package org.binance.trade.sdkstrategy;

import com.binance.connector.futures.client.impl.UMFuturesClientImpl;

import org.binance.trade.BinanceActionStrategy;
import org.binance.trade.BinanceSDKConts;
import org.binance.trade.UMFuturesClientFactory;
import org.binance.trade.req.NewOrderReq;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedHashMap;

//https://developers.binance.com/docs/zh-CN/derivatives/usds-margined-futures/trade/rest-api
public class NewOrderStrategy implements BinanceActionStrategy<NewOrderReq> {

    private static final Logger logger = LoggerFactory.getLogger(NewOrderStrategy.class);

    public static final NewOrderStrategy INSTANCE = new NewOrderStrategy();

    private NewOrderStrategy() {}
    @Override
    public String execute(NewOrderReq newOrderReq) {
        LinkedHashMap<String, Object> params = new LinkedHashMap<>();
        params.put("symbol", newOrderReq.getSymbol());
        params.put("side",newOrderReq.getSide());
        params.put("positionSide", newOrderReq.getPositionSide());
        params.put("type", newOrderReq.getType());
        params.put("quantity", newOrderReq.getQuantity());
        params.put("price",newOrderReq.getPrice());
        params.put("newClientOrderId", newOrderReq.getNewClientOrderId());
        params.put("timeInForce",newOrderReq.getTimeInForce());
        params.put("recvWindow", BinanceSDKConts.recvWindow);//接收窗口（毫秒），建议5000-60000
        params.put("timestamp", System.currentTimeMillis());
        try {
            logger.debug("api: {} {}" , newOrderReq.getApiKey(),newOrderReq.getSecretKey());
            UMFuturesClientImpl client = UMFuturesClientFactory.getClient(newOrderReq.getApiKey(),newOrderReq.getSecretKey());
            String orderResult = client.account().newOrder(params);
            logger.info("newOrder: {}" , orderResult);
            return orderResult;
        } catch (Exception e) {
            logger.error("NewOrderStrategy error:{},{}",e,e.getMessage());
        }
        return null;
    }
}