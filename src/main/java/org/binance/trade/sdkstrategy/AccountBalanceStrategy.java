package org.binance.trade.sdkstrategy;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.binance.connector.futures.client.impl.UMFuturesClientImpl;

import org.binance.trade.BinanceActionStrategy;
import org.binance.trade.BinanceSDKConts;
import org.binance.trade.UMFuturesClientFactory;
import org.binance.trade.rep.ben.AccountBalanceBen;
import org.binance.trade.req.AccountBalanceReq;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


//https://developers.binance.com/docs/zh-CN/derivatives/usds-margined-futures/account/rest-api/Futures-Account-Balance-V3
public class AccountBalanceStrategy implements BinanceActionStrategy<AccountBalanceReq> {

    private static final Logger logger = LoggerFactory.getLogger(AccountBalanceStrategy.class);

    public static final AccountBalanceStrategy INSTANCE = new AccountBalanceStrategy();

    private AccountBalanceStrategy() {}
    @Override
    public String execute(AccountBalanceReq accountBalanceReq) {
        LinkedHashMap<String, Object> params = new LinkedHashMap<>();
        params.put("recvWindow", BinanceSDKConts.recvWindow);
        params.put("timestamp", System.currentTimeMillis());
        try {
            logger.debug("api: {} {}" , accountBalanceReq.getApiKey(),accountBalanceReq.getSecretKey());
            UMFuturesClientImpl client = UMFuturesClientFactory.getClient(accountBalanceReq.getApiKey(),accountBalanceReq.getSecretKey());
            String balanceResult = client.account().futuresAccountBalance(params);
            List<AccountBalanceBen> balancesList = JSON.parseObject(balanceResult, new TypeReference<List<AccountBalanceBen>>(){});
            Map<String,List<AccountBalanceBen>> jsonMap = new HashMap<>();
            jsonMap.put("data",balancesList);
            return JSONObject.toJSONString(jsonMap);
        } catch (Exception e) {
            logger.error("AccountBalanceStrategy error:{},{}",e,e.getMessage());
        }
        return null;
    }
}