package org.binance.trade.rep;

import com.alibaba.fastjson.annotation.JSONField;
import org.binance.trade.req.MarginTypeReq;

public class MarginTypeRep extends BaseResponse<MarginTypeReq> {




    @JSONField(serialize = false)
    @Override
    public Class<MarginTypeReq> getRequestType() {
        return MarginTypeReq.class;
    }

}
