package org.binance.trade;

import com.alibaba.fastjson.JSONObject;
import org.binance.trade.rep.BaseResponse;
import org.binance.trade.req.BaseRequest;

public class BinanceSDKClient {
    public static  <T extends BaseResponse> T execute(BaseRequest<T> request){
        String ret = request.getStrategy().execute(request);
        return JSONObject.parseObject(ret,request.getResponseType());
    }


}
