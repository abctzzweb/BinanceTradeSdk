package org.binance.trade.sdkstrategy;

import com.binance.connector.futures.client.impl.UMFuturesClientImpl;

import org.binance.trade.BinanceActionStrategy;
import org.binance.trade.BinanceSDKConts;
import org.binance.trade.UMFuturesClientFactory;
import org.binance.trade.req.MarginTypeReq;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedHashMap;

public class MarginTypeStrategy implements BinanceActionStrategy<MarginTypeReq> {
    private static final Logger logger = LoggerFactory.getLogger(MarginTypeStrategy.class);

    public static final MarginTypeStrategy INSTANCE = new MarginTypeStrategy();

    private MarginTypeStrategy(){}
    /**
     * https://developers.binance.com/docs/zh-CN/derivatives/usds-margined-futures/trade/rest-api/Change-Margin-Type
     * 变换用户在指定symbol合约上的保证金模式：逐仓或全仓。
     * marginType 	保证金模式 ISOLATED(逐仓), CROSSED(全仓)
     * 注意：如果存在未结订单，则不能更改保证金类型
     * */
    @Override
    public String execute(MarginTypeReq marginTypeReq) {
        LinkedHashMap<String, Object> params = new LinkedHashMap<>();
        params.put("symbol",marginTypeReq.getSymbol());
        params.put("marginType",marginTypeReq.getMarginType());
        params.put("recvWindow", BinanceSDKConts.recvWindow);
        params.put("timestamp", System.currentTimeMillis());
        try {
            logger.debug("api: {} {}" , marginTypeReq.getApiKey(),marginTypeReq.getSecretKey());
            UMFuturesClientImpl client = UMFuturesClientFactory.getClient(marginTypeReq.getApiKey(),marginTypeReq.getSecretKey());
            String result = client.account().changeMarginType(params);
            return result;
        } catch (Exception e) {
            logger.error("MarginTypeStrategy error:{}",e.getMessage());
        }
        return null;
    }
}