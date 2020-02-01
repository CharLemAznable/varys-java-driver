package com.github.charlemaznable.varys.config;

import com.github.charlemaznable.core.net.ohclient.param.OhFixedValueProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.github.charlemaznable.core.lang.Condition.checkNotNull;
import static com.github.charlemaznable.core.miner.MinerFactory.getMiner;

@Component
public class VarysPathProvider implements OhFixedValueProvider {

    private final VarysConfig varysConfig;

    @Autowired(required = false)
    public VarysPathProvider() {
        this(getMiner(VarysConfig.class));
    }

    @Autowired(required = false)
    public VarysPathProvider(VarysConfig varysConfig) {
        checkNotNull(varysConfig);
        this.varysConfig = varysConfig;
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
            default:
                throw new IllegalArgumentException("Illegal Fixed Value Name: " + name);
        }
    }
}
