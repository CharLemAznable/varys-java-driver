package com.github.charlemaznable.varys.impl;

import com.github.charlemaznable.httpclient.ohclient.annotation.ClientTimeout.TimeoutProvider;
import com.github.charlemaznable.varys.config.VarysConfig;

import javax.annotation.Nullable;

import static com.github.charlemaznable.configservice.ConfigFactory.getConfig;
import static com.github.charlemaznable.core.lang.Condition.nullThen;

public final class VarysWriteTimeoutProvider implements TimeoutProvider {

    private final VarysConfig varysConfig;

    public VarysWriteTimeoutProvider(@Nullable VarysConfig varysConfig) {
        this.varysConfig = nullThen(varysConfig, () -> getConfig(VarysConfig.class));
    }

    @Override
    public long timeout(Class<?> clazz) {
        return varysConfig.writeTimeout();
    }
}
