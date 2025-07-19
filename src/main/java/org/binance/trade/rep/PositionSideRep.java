package org.binance.trade.rep;

import com.alibaba.fastjson.annotation.JSONField;
import org.binance.trade.req.PositionSideReq;

public class PositionSideRep extends BaseResponse<PositionSideReq> {


    @JSONField(serialize = false)
    @Override
    public Class<PositionSideReq> getRequestType() {
        return PositionSideReq.class;
    }

}
