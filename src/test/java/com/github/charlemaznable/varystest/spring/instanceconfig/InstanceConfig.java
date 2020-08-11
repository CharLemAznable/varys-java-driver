package com.github.charlemaznable.varystest.spring.instanceconfig;

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
        return "query-wechat-app-token";
    }

    @Override
    public String appAuthorizerTokenCachePath() {
        return "query-wechat-app-authorizer-token";
    }

    @Override
    public String corpTokenCachePath() {
        return "query-wechat-corp-token";
    }

    @Override
    public String corpAuthorizerTokenCachePath() {
        return "query-wechat-corp-authorizer-token";
    }

    @Override
    public String proxyWechatAppPath() {
        return "proxy-wechat-app";
    }

    @Override
    public String proxyWechatMpPath() {
        return "proxy-wechat-mp";
    }

    @Override
    public String proxyWechatCorpPath() {
        return "proxy-wechat-corp";
    }

    @Override
    public long callTimeout() {
        return 0;
    }

    @Override
    public long connectTimeout() {
        return 10_000;
    }

    @Override
    public long readTimeout() {
        return 10_000;
    }

    @Override
    public long writeTimeout() {
        return 10_000;
    }
}
