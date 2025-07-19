package org.binance.trade.sdkstrategy;

import com.binance.connector.futures.client.impl.UMFuturesClientImpl;

import org.binance.trade.BinanceActionStrategy;
import org.binance.trade.BinanceSDKConts;
import org.binance.trade.UMFuturesClientFactory;
import org.binance.trade.req.LeverageReq;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedHashMap;

public class LeverageStrategy implements BinanceActionStrategy<LeverageReq> {
    private static final Logger logger = LoggerFactory.getLogger(LeverageStrategy.class);

    public static final LeverageStrategy INSTANCE = new LeverageStrategy();

    private LeverageStrategy(){}
    /**
     * https://developers.binance.com/docs/zh-CN/derivatives/usds-margined-futures/trade/rest-api/Change-Initial-Leverage
     * 调整用户在指定symbol合约的开仓杠杆。
     * */
    @Override
    public String execute(LeverageReq leverageReq) {
        LinkedHashMap<String, Object> params = new LinkedHashMap<>();
        params.put("symbol",leverageReq.getSymbol());
        params.put("leverage",leverageReq.getLeverage());
        params.put("recvWindow", BinanceSDKConts.recvWindow);
        params.put("timestamp", System.currentTimeMillis());
        try {
            logger.debug("api: {} {}" , leverageReq.getApiKey(),leverageReq.getSecretKey());
            UMFuturesClientImpl client = UMFuturesClientFactory.getClient(leverageReq.getApiKey(),leverageReq.getSecretKey());
            String result = client.account().changeInitialLeverage(params);
            return result;
        } catch (Exception e) {
            logger.error("LeverageStrategy error:{}",e.getMessage());
        }
        return null;
    }
}