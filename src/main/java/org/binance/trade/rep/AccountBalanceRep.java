package org.binance.trade.rep;


import org.binance.trade.BinanceSDKConts;
import org.binance.trade.rep.ben.AccountBalanceBen;
import org.binance.trade.req.AccountBalanceReq;

import java.util.List;

public class AccountBalanceRep extends BaseResponse<AccountBalanceReq> {

    private List<AccountBalanceBen> data;

    @Override
    public Class<AccountBalanceReq> getRequestType() {
        return AccountBalanceReq.class;
    }

    public List<AccountBalanceBen> getData() {
        return data;
    }

    public void setData(List<AccountBalanceBen> data) {
        this.data = data;
    }

    public String getUsdtAvailableBalance() {
        for (int i = 0; i < this.data.size(); i++) {
            AccountBalanceBen accountBalanceBen =  this.data.get(i);
            if(BinanceSDKConts.USDT.equals(accountBalanceBen.getAsset())){
                return accountBalanceBen.getAvailableBalance();
            }
        }
        return "0.0";
    }
}
