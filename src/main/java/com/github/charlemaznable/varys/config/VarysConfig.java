package com.github.charlemaznable.varys.config;

import com.github.charlemaznable.core.miner.MinerConfig;

import java.util.concurrent.TimeUnit;

@MinerConfig(group = "Varys", dataId = "default")
public interface VarysConfig {

    String address();

    @MinerConfig(defaultValue = "/query-wechat-app-token/")
    String appTokenCachePath();

    @MinerConfig(defaultValue = "/query-wechat-app-authorizer-token/")
    String appAuthorizerTokenCachePath();

    @MinerConfig(defaultValue = "/query-wechat-corp-token/")
    String corpTokenCachePath();

    @MinerConfig(defaultValue = "/query-wechat-corp-authorizer-token/")
    String corpAuthorizerTokenCachePath();

    @MinerConfig(defaultValue = "/proxy-wechat-app/")
    String proxyWechatAppPath();

    @MinerConfig(defaultValue = "/proxy-wechat-corp/")
    String proxyWechatCorpPath();

    @MinerConfig(defaultValue = "8")
    int queryPoolMaxTotal();

    @MinerConfig(defaultValue = "8")
    int queryPoolMaxIdle();

    @MinerConfig(defaultValue = "0")
    int queryPoolMinIdle();

    @MinerConfig(defaultValue = "10")
    long appTokenCacheDuration();

    @MinerConfig(defaultValue = "MINUTES")
    String appTokenCacheUnit();

    default TimeUnit appTokenCacheTimeUnit() {
        return TimeUnit.valueOf(appTokenCacheUnit());
    }

    @MinerConfig(defaultValue = "10")
    long appAuthorizerTokenCacheDuration();

    @MinerConfig(defaultValue = "MINUTES")
    String appAuthorizerTokenCacheUnit();

    default TimeUnit appAuthorizerTokenCacheTimeUnit() {
        return TimeUnit.valueOf(appAuthorizerTokenCacheUnit());
    }

    @MinerConfig(defaultValue = "10")
    long corpTokenCacheDuration();

    @MinerConfig(defaultValue = "MINUTES")
    String corpTokenCacheUnit();

    default TimeUnit corpTokenCacheTimeUnit() {
        return TimeUnit.valueOf(corpTokenCacheUnit());
    }

    @MinerConfig(defaultValue = "10")
    long corpAuthorizerTokenCacheDuration();

    @MinerConfig(defaultValue = "MINUTES")
    String corpAuthorizerTokenCacheUnit();

    default TimeUnit corpAuthorizerTokenCacheTimeUnit() {
        return TimeUnit.valueOf(corpAuthorizerTokenCacheUnit());
    }

    @MinerConfig(defaultValue = "8")
    int proxyPoolMaxTotal();

    @MinerConfig(defaultValue = "8")
    int proxyPoolMaxIdle();

    @MinerConfig(defaultValue = "0")
    int proxyPoolMinIdle();
}
