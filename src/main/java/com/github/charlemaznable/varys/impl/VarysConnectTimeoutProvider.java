package com.github.charlemaznable.varys.impl;

import com.github.charlemaznable.httpclient.ohclient.annotation.ClientTimeout.TimeoutProvider;
import com.github.charlemaznable.varys.config.VarysConfig;
import com.google.inject.Inject;
import lombok.AllArgsConstructor;

@AllArgsConstructor(onConstructor_={@Inject})
public final class VarysConnectTimeoutProvider implements TimeoutProvider {

    private final VarysConfig varysConfig;

    @Override
    public long timeout(Class<?> clazz) {
        return varysConfig.connectTimeout();
    }
}
