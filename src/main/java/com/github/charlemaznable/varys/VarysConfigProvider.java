package com.github.charlemaznable.varys;

import com.github.charlemaznable.core.net.ohclient.OhMapping.UrlProvider;
import com.github.charlemaznable.core.net.ohclient.param.OhFixedValueProvider;
import com.github.charlemaznable.varys.config.VarysConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.github.charlemaznable.core.lang.Condition.checkNotNull;
import static com.github.charlemaznable.core.miner.MinerFactory.getMiner;

@Component
public final class VarysConfigProvider implements UrlProvider, OhFixedValueProvider {

    private final VarysConfig varysConfig;

    @Autowired(required = false)
    public VarysConfigProvider() {
        this(getMiner(VarysConfig.class));
    }

    @Autowired(required = false)
    public VarysConfigProvider(VarysConfig varysConfig) {
        checkNotNull(varysConfig);
        this.varysConfig = varysConfig;
    }

    @Override
    public String url(Class<?> clazz) {
        return checkNotNull(varysConfig.address());
    }

    @Override
    public String value(Class<?> clazz, String name) {
        switch (name) {
            case "queryWechatAppToken":
                return varysConfig.appTokenCachePath();
            case "queryWechatAppAuthorizerToken":
                return varysConfig.appAuthorizerTokenCachePath();
            case "queryWechatCorpToken":
                return varysConfig.corpTokenCachePath();
            case "queryWechatCorpAuthorizerToken":
                return varysConfig.corpAuthorizerTokenCachePath();
            case "proxyWechatApp":
                return varysConfig.proxyWechatAppPath();
            case "proxyWechatCorp":
                return varysConfig.proxyWechatCorpPath();
            default:
                throw new IllegalArgumentException("Illegal Fixed Value Name: " + name);
        }
    }
}
