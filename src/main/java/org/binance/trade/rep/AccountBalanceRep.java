package org.binance.trade.rep;


import org.binance.trade.BinanceSDKConts;
import org.binance.trade.rep.ben.AccountBalanceBen;
import org.binance.trade.req.AccountBalanceReq;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

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

    /**
     * 获取USDT可用余额
     */
    public String getUsdtAvailableBalance() {
        return Optional.ofNullable(data)
                .orElse(Collections.emptyList())
                .stream()
                .filter(balance -> BinanceSDKConts.USDT.equals(balance.getAsset()))
                .findFirst()
                .map(AccountBalanceBen::getAvailableBalance)
                .orElse("0.0");
    }

    /**
     * 获取指定资产的可用余额 - 通用方法
     */
    public String getAssetAvailableBalance(String asset) {
        return Optional.ofNullable(data)
                .orElse(Collections.emptyList())
                .stream()
                .filter(balance -> asset.equals(balance.getAsset()))
                .findFirst()
                .map(AccountBalanceBen::getAvailableBalance)
                .orElse("0.0");
    }
}
