package com.github.charlemaznable.varys.impl;

import com.github.charlemaznable.httpclient.common.Mapping.UrlProvider;
import com.github.charlemaznable.varys.config.VarysConfig;
import lombok.AllArgsConstructor;

import static com.github.charlemaznable.core.lang.Condition.checkNotNull;

@AllArgsConstructor
public final class VarysQueryUrlProvider implements UrlProvider {

    private final VarysConfig varysConfig;

    @Override
    public String url(Class<?> clazz) {
        return checkNotNull(varysConfig.address());
    }
}
