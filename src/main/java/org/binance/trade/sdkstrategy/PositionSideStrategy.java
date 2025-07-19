package org.binance.trade.sdkstrategy;

import com.binance.connector.futures.client.impl.UMFuturesClientImpl;

import org.binance.trade.BinanceActionStrategy;
import org.binance.trade.BinanceSDKConts;
import org.binance.trade.UMFuturesClientFactory;
import org.binance.trade.req.PositionSideReq;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedHashMap;

public class PositionSideStrategy implements BinanceActionStrategy<PositionSideReq> {
    private static final Logger logger = LoggerFactory.getLogger(PositionSideStrategy.class);

    public static final PositionSideStrategy INSTANCE = new PositionSideStrategy();

    private PositionSideStrategy(){}
    /**
     * //https://developers.binance.com/docs/zh-CN/derivatives/usds-margined-futures/trade/rest-api/Change-Position-Mode
     * 变换用户在 所有symbol 合约上的持仓模式：双向持仓或单向持仓。
     * dualSidePosition	STRING	YES	"true": 双向持仓模式；"false": 单向持仓模式
     *
     * 注意：有订单的时候是无法修改
     * */
    @Override
    public String execute(PositionSideReq positionSideReq) {
        LinkedHashMap<String, Object> params = new LinkedHashMap<>();
        params.put("dualSidePosition",positionSideReq.getDualSidePosition());
        params.put("recvWindow", BinanceSDKConts.recvWindow);
        params.put("timestamp", System.currentTimeMillis());
        try {
            logger.debug("api: {} {}" , positionSideReq.getApiKey(),positionSideReq.getSecretKey());
            UMFuturesClientImpl client = UMFuturesClientFactory.getClient(positionSideReq.getApiKey(),positionSideReq.getSecretKey());
            String result = client.account().changePositionModeTrade(params);
            return result;
        } catch (Exception e) {
            logger.error("PositionSideStrategy error:{}",e.getMessage());
        }
        return null;
    }
}