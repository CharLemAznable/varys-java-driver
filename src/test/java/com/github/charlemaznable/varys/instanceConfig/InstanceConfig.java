package com.github.charlemaznable.varys.instanceConfig;

import com.github.charlemaznable.varys.config.VarysConfig;
import org.springframework.stereotype.Component;

@Component
public class InstanceConfig implements VarysConfig {

    @Override
    public String address() {
        return "http://127.0.0.1:4236/varys";
    }

    @Override
    public String appTokenCachePath() {
        return "/query-wechat-app-token/";
    }

    @Override
    public String appAuthorizerTokenCachePath() {
        return "/query-wechat-app-authorizer-token/";
    }

    @Override
    public String corpTokenCachePath() {
        return "/query-wechat-corp-token/";
    }

    @Override
    public String corpAuthorizerTokenCachePath() {
        return "/query-wechat-corp-authorizer-token/";
    }

    @Override
    public String proxyWechatAppPath() {
        return "/proxy-wechat-app/";
    }

    @Override
    public String proxyWechatCorpPath() {
        return "/proxy-wechat-corp/";
    }

    @Override
    public int queryPoolMaxTotal() {
        return 8;
    }

    @Override
    public int queryPoolMaxIdle() {
        return 8;
    }

    @Override
    public int queryPoolMinIdle() {
        return 0;
    }

    @Override
    public long appTokenCacheDuration() {
        return 10;
    }

    @Override
    public String appTokenCacheUnit() {
        return "MINUTES";
    }

    @Override
    public long appAuthorizerTokenCacheDuration() {
        return 10;
    }

    @Override
    public String appAuthorizerTokenCacheUnit() {
        return "MINUTES";
    }

    @Override
    public long corpTokenCacheDuration() {
        return 10;
    }

    @Override
    public String corpTokenCacheUnit() {
        return "MINUTES";
    }

    @Override
    public long corpAuthorizerTokenCacheDuration() {
        return 10;
    }

    @Override
    public String corpAuthorizerTokenCacheUnit() {
        return "MINUTES";
    }

    @Override
    public int proxyPoolMaxTotal() {
        return 8;
    }

    @Override
    public int proxyPoolMaxIdle() {
        return 8;
    }

    @Override
    public int proxyPoolMinIdle() {
        return 0;
    }
}
