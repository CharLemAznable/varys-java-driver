package com.github.charlemaznable.varys.config;

import com.github.charlemaznable.core.miner.MinerConfig;

@MinerConfig(group = "Varys", dataId = "default")
public interface VarysConfig {

    String address();

    @MinerConfig(defaultValue = "query-wechat-app-token")
    String appTokenCachePath();

    @MinerConfig(defaultValue = "query-wechat-app-authorizer-token")
    String appAuthorizerTokenCachePath();

    @MinerConfig(defaultValue = "query-wechat-corp-token")
    String corpTokenCachePath();

    @MinerConfig(defaultValue = "query-wechat-corp-authorizer-token")
    String corpAuthorizerTokenCachePath();

    @MinerConfig(defaultValue = "proxy-wechat-app")
    String proxyWechatAppPath();

    @MinerConfig(defaultValue = "proxy-wechat-corp")
    String proxyWechatCorpPath();

    @MinerConfig(defaultValue = "0")
    long callTimeout();

    @MinerConfig(defaultValue = "10000")
    long connectTimeout();

    @MinerConfig(defaultValue = "10000")
    long readTimeout();

    @MinerConfig(defaultValue = "10000")
    long writeTimeout();
}
