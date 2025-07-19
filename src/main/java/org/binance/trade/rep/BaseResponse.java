package org.binance.trade.rep;


import com.alibaba.fastjson.annotation.JSONField;
import org.binance.trade.req.BaseRequest;

public abstract class BaseResponse<T extends BaseRequest> {

    private int code;
    private String msg;

    private Long timestamp;

    @JSONField(serialize = false)
    public  abstract Class<T> getRequestType();


    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }


    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }
}
