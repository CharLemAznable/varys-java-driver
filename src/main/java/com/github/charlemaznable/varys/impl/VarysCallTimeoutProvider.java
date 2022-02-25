package com.github.charlemaznable.varys.impl;

import com.github.charlemaznable.httpclient.ohclient.annotation.ClientTimeout.TimeoutProvider;
import com.github.charlemaznable.varys.config.VarysConfig;
import com.google.inject.Inject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Nullable;

import static com.github.charlemaznable.core.lang.Condition.nullThen;
import static com.github.charlemaznable.miner.MinerFactory.getMiner;

@Component
public final class VarysCallTimeoutProvider implements TimeoutProvider {

    private final VarysConfig varysConfig;

    public VarysCallTimeoutProvider() {
        this(null);
    }

    @Inject
    @Autowired
    public VarysCallTimeoutProvider(@Nullable VarysConfig varysConfig) {
        this.varysConfig = nullThen(varysConfig, () -> getMiner(VarysConfig.class));
    }

    @Override
    public long timeout(Class<?> clazz) {
        return varysConfig.callTimeout();
    }
}
