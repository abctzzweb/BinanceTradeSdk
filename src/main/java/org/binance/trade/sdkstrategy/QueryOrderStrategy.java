package org.binance.trade.sdkstrategy;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.binance.connector.futures.client.impl.UMFuturesClientImpl;

import org.binance.trade.BinanceActionStrategy;
import org.binance.trade.BinanceSDKConts;
import org.binance.trade.UMFuturesClientFactory;
import org.binance.trade.rep.ben.QueryOrderBen;
import org.binance.trade.req.QueryOrderReq;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


//https://developers.binance.com/docs/zh-CN/derivatives/usds-margined-futures/trade/rest-api/All-Orders
public class QueryOrderStrategy implements BinanceActionStrategy<QueryOrderReq> {
    private static final Logger logger = LoggerFactory.getLogger(QueryOrderStrategy.class);

    public static final QueryOrderStrategy INSTANCE = new QueryOrderStrategy();

    private QueryOrderStrategy(){}
    /**
     * 查询时间范围最大不得超过7天
     * 默认查询最近7天内的数据
     *
     * */
    @Override
    public String execute(QueryOrderReq cancelOrderReq) {
        LinkedHashMap<String, Object> params = new LinkedHashMap<>();
        params.put("symbol",cancelOrderReq.getSymbol());
        if(null!= cancelOrderReq.getOrderId()){
            params.put("orderId", cancelOrderReq.getOrderId());
        }
        if(null!= cancelOrderReq.getLimit()){
            params.put("limit", cancelOrderReq.getLimit());
        }
        params.put("recvWindow", BinanceSDKConts.recvWindow);
        params.put("timestamp", System.currentTimeMillis());
        try {
            logger.debug("api: {} {}" , cancelOrderReq.getApiKey(),cancelOrderReq.getSecretKey());
            UMFuturesClientImpl client = UMFuturesClientFactory.getClient(cancelOrderReq.getApiKey(),cancelOrderReq.getSecretKey());
            String orderResult = client.account().allOrders(params);

            List<QueryOrderBen> orderResultList = JSON.parseObject(orderResult, new TypeReference<List<QueryOrderBen>>(){});
            Map<String,List<QueryOrderBen>> jsonMap = new HashMap<>();
            jsonMap.put("data",orderResultList);
            logger.info("QueryOrderStrategy: {}" , orderResult);
            return JSONObject.toJSONString(jsonMap);
        } catch (Exception e) {
            logger.error("QueryOrderStrategy error:{}",e.getMessage());
        }
        return null;
    }
}