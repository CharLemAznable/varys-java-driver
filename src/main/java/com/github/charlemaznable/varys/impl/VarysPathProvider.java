package com.github.charlemaznable.varys.impl;

import com.github.charlemaznable.core.net.common.FixedValueProvider;
import com.github.charlemaznable.varys.config.VarysConfig;
import com.google.inject.Inject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Nullable;

import static com.github.charlemaznable.core.lang.Condition.nullThen;
import static com.github.charlemaznable.core.miner.MinerFactory.getMiner;

@Component
public final class VarysPathProvider implements FixedValueProvider {

    private final VarysConfig varysConfig;

    @Autowired(required = false)
    public VarysPathProvider() {
        this(null);
    }

    @Inject
    @Autowired(required = false)
    public VarysPathProvider(@Nullable VarysConfig varysConfig) {
        this.varysConfig = nullThen(varysConfig, () -> getMiner(VarysConfig.class));
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
