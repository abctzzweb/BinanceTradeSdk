package org.binance.trade.rep;

import com.alibaba.fastjson.annotation.JSONField;
import org.binance.trade.req.LeverageReq;

public class LeverageRep extends BaseResponse<LeverageReq> {

    // 交易对
    private String symbol;

    // 杠杆倍数 1 到 125 整数
    private Integer leverage;

    //// 当前杠杆倍数下允许的最大名义价值
    private String maxNotionalValue;

    @JSONField(serialize = false)
    @Override
    public Class<LeverageReq> getRequestType() {
        return LeverageReq.class;
    }

}
