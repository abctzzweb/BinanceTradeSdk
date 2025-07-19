package org.binance.trade;

import com.alibaba.fastjson.JSONObject;

import org.binance.trade.rep.*;
import org.binance.trade.req.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestSdk {
    private static final Logger logger = LoggerFactory.getLogger(TestSdk.class);

    //测试网 https://testnet.binancefuture.com/zh-CN/futures/BTCUSDT  863493567@qq.com
    private static  final  String apikey="2e6efc0ed1c62b68b109586e5ef089b950192dbcc50d3a268c689dd49c5dcda7";
    private static  final  String secretKey="1aa858d6e6f3353e49af9aa575867bcf88d18c1a0acb8b2d1442a8e86fc1f616";
    public static void main(String[] agrs){

        //China 需要科学上网
        System.setProperty("http.proxyHost", "127.0.0.1");
        System.setProperty("http.proxyPort", "10796");
        System.setProperty("https.proxyHost", "127.0.0.1");
        System.setProperty("https.proxyPort", "10796");

        //查用户对应USDT保证可用余额
        testUsdtavailableBalance();

        //开仓(空、多)
        //testNewOrder();

        //主动平仓(止盈，止损)
        //testCloseOrder();

        //撤单
        //testCancenOrder();

        //查询所有订单，包括委托的还有仓位订单
        //testQueryOrder();

        //设置持仓模式：双向持仓或单向持仓。
        //testPositionSide();

        //设置对应交易对杠杆倍数
        //testLeverage();

        //变换用户在指定symbol合约上的保证金模式
        //testMarginType();

    }

    private static void testNewOrder(){
        NewOrderReq newOrderReq = new NewOrderReq();
        //为什么每次都需要传apikey.secretKey，方便量化中多账号下单
        newOrderReq.setApiKey(apikey);
        newOrderReq.setSecretKey(secretKey);
        newOrderReq.setNewClientOrderId(String.valueOf(System.currentTimeMillis()));
        newOrderReq.setSymbol("BTCUSDT");
        newOrderReq.setType("LIMIT");

        //开空
        newOrderReq.setSide("SELL");
        newOrderReq.setPositionSide("SHORT");
        newOrderReq.setPrice("123456.7");

        //开多
        //newOrderReq.setSide("BUY");
        //newOrderReq.setPositionSide("LONG");
        //newOrderReq.setPrice("100000");

        //若想通过百分比控制仓位，需自行计算：
        //quantity = （账户余额 × 风险比例） / 标记价格 。
        newOrderReq.setQuantity("0.001");
        newOrderReq.setTimeInForce("GTC");
        NewOrderRep orderRep = BinanceSDKClient.execute(newOrderReq);
        logger.info("testNewOrder:{}",JSONObject.toJSONString(orderRep));
    }

    private static void testCloseOrder(){
        CloseOrderReq closeOrderReq = new CloseOrderReq();
        closeOrderReq.setApiKey(apikey);
        closeOrderReq.setSecretKey(secretKey);
        closeOrderReq.setSymbol("BTCUSDT");//交易对（必须）
        closeOrderReq.setSide("BUY");//平空仓用BUY，平多仓用SELL
        closeOrderReq.setType("MARKET");//订单类型（市价单无需价格）
        closeOrderReq.setPositionSide("SHORT"); //仓位方向（与原仓一致）
        closeOrderReq.setQuantity("0.001");//平仓数量（≤持仓量）

        //平仓
        CloseOrderRep closeOrderRep = BinanceSDKClient.execute(closeOrderReq);
        logger.info("testCloseOrder:{}",JSONObject.toJSONString(closeOrderRep));

    }

    private static void testCancenOrder(){
        CancelOrderReq cancelOrderReq = new CancelOrderReq();
        cancelOrderReq.setApiKey(apikey);
        cancelOrderReq.setSecretKey(secretKey);
        cancelOrderReq.setSymbol("BTCUSDT");//交易对（必须）
        cancelOrderReq.setOrderId(5366423038L);//系统订单编号
        cancelOrderReq.setOrigClientOrderId("1752845372695"); //用户自定义的订单号

        //撤单
        CancelOrderRep cancelOrderRep = BinanceSDKClient.execute(cancelOrderReq);
        logger.info("testCancenOrder:{}",JSONObject.toJSONString(cancelOrderRep));

    }

    private static void testQueryOrder(){
        QueryOrderReq queryOrderReq = new QueryOrderReq();
        queryOrderReq.setApiKey(apikey);
        queryOrderReq.setSecretKey(secretKey);
        queryOrderReq.setSymbol("ETHUSDT");//交易对（必须）
        queryOrderReq.setLimit(2);//可不传
        //查询所有订单，包括委托的还有仓位订单
        QueryOrderRep queryOrderRep = BinanceSDKClient.execute(queryOrderReq);
        logger.info("testQueryOrder:{}",JSONObject.toJSONString(queryOrderRep));

    }

    private static void testPositionSide(){
        PositionSideReq positionSideReq = new PositionSideReq();
        positionSideReq.setApiKey(apikey);
        positionSideReq.setSecretKey(secretKey);

        //设置持仓模式：双向持仓或单向持仓。
        //"true": 双向持仓模式；"false": 单向持仓模式
        positionSideReq.setDualSidePosition("true");
        PositionSideRep positionSideRep  = BinanceSDKClient.execute(positionSideReq);
        logger.info("testPositionSide:{}",JSONObject.toJSONString(positionSideRep));
    }

    private static void testLeverage(){
        LeverageReq leverageReq = new LeverageReq();
        leverageReq.setApiKey(apikey);
        leverageReq.setSecretKey(secretKey);
        leverageReq.setSymbol("BTCUSDT");
        //目标杠杆倍数：1 到 125 整数 有些币种最大支持75或者25，视具体情况设置
        leverageReq.setLeverage(25);
        LeverageRep leverageRep = BinanceSDKClient.execute(leverageReq);
        logger.info("testLeverage:{}",JSONObject.toJSONString(leverageRep));
    }

    private static void testMarginType(){
        MarginTypeReq marginTypeReq = new MarginTypeReq();
        marginTypeReq.setApiKey(apikey);
        marginTypeReq.setSecretKey(secretKey);
        marginTypeReq.setSymbol("BNBUSDT");
        //变换用户在指定symbol合约上的保证金模式
        //保证金模式 ISOLATED(逐仓), CROSSED(全仓)
        //如果存在未结订单，则不能更改保证金类型
        marginTypeReq.setMarginType("ISOLATED");
        MarginTypeRep marginTypeRep = BinanceSDKClient.execute(marginTypeReq);
        logger.info("testMarginType:{}",JSONObject.toJSONString(marginTypeRep));
    }

    private static void testUsdtavailableBalance(){
        AccountBalanceReq accountBalanceReq = new AccountBalanceReq();
        accountBalanceReq.setApiKey(apikey);
        accountBalanceReq.setSecretKey(secretKey);
        //获取其他币种全部余额
        //AccountBalanceRep allBalanceRet = BinanceSDKClient.execute(accountBalanceReq);
        //只获取合约USDT保证金可用余额
        String usdtavailableBalance = BinanceSDKClient.execute(accountBalanceReq).getUsdtAvailableBalance();
        logger.info("testUsdtavailableBalance:{}",usdtavailableBalance);

    }
}
