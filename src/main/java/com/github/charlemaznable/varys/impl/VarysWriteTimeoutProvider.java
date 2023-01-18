package com.github.charlemaznable.varys.impl;

import com.github.charlemaznable.httpclient.ohclient.annotation.ClientTimeout.TimeoutProvider;
import com.github.charlemaznable.varys.config.VarysConfig;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public final class VarysWriteTimeoutProvider implements TimeoutProvider {

    private final VarysConfig varysConfig;

    @Override
    public long timeout(Class<?> clazz) {
        return varysConfig.writeTimeout();
    }
}
