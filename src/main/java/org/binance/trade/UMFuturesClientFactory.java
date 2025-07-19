package org.binance.trade;

import com.binance.connector.futures.client.impl.UMFuturesClientImpl;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class UMFuturesClientFactory {

    private static final Map<String, UMFuturesClientImpl> clientCache = new ConcurrentHashMap<>();

    public static UMFuturesClientImpl getClient(String apiKey, String secretKey) {
        String key = apiKey + ":" + secretKey;
        return clientCache.computeIfAbsent(key, k ->
                new UMFuturesClientImpl(apiKey, secretKey)
                //new UMFuturesClientImpl(apiKey, secretKey,"https://testnet.binancefuture.com")
        );
    }
}
