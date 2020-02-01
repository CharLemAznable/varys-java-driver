package com.github.charlemaznable.varys.config;

import com.github.charlemaznable.core.net.ohclient.OhMapping.UrlProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.github.charlemaznable.core.lang.Condition.checkNotNull;
import static com.github.charlemaznable.core.miner.MinerFactory.getMiner;

@Component
public final class VarysQueryUrlProvider implements UrlProvider {

    private final VarysConfig varysConfig;

    @Autowired(required = false)
    public VarysQueryUrlProvider() {
        this(getMiner(VarysConfig.class));
    }

    @Autowired(required = false)
    public VarysQueryUrlProvider(VarysConfig varysConfig) {
        checkNotNull(varysConfig);
        this.varysConfig = varysConfig;
    }

    @Override
    public String url(Class<?> clazz) {
        return checkNotNull(varysConfig.address());
    }
}
