package org.binance.trade.rep;

import com.alibaba.fastjson.annotation.JSONField;
import org.binance.trade.rep.ben.QueryOrderBen;
import org.binance.trade.req.QueryOrderReq;

import java.util.List;

public class QueryOrderRep extends BaseResponse<QueryOrderReq> {


    private List<QueryOrderBen> data;

    @JSONField(serialize = false)
    @Override
    public Class<QueryOrderReq> getRequestType() {
        return QueryOrderReq.class;
    }

    public List<QueryOrderBen> getData() {
        return data;
    }

    public void setData(List<QueryOrderBen> data) {
        this.data = data;
    }
}
