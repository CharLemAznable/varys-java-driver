package com.github.charlemaznable.varys.impl;

import com.github.charlemaznable.httpclient.common.Mapping.UrlProvider;
import com.github.charlemaznable.varys.config.VarysConfig;
import com.google.inject.Inject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Nullable;

import static com.github.charlemaznable.configservice.ConfigFactory.getConfig;
import static com.github.charlemaznable.core.lang.Condition.checkNotNull;
import static com.github.charlemaznable.core.lang.Condition.nullThen;

@Component
public final class VarysQueryUrlProvider implements UrlProvider {

    private final VarysConfig varysConfig;

    public VarysQueryUrlProvider() {
        this(null);
    }

    @Inject
    @Autowired
    public VarysQueryUrlProvider(@Nullable VarysConfig varysConfig) {
        this.varysConfig = nullThen(varysConfig, () -> getConfig(VarysConfig.class));
    }

    @Override
    public String url(Class<?> clazz) {
        return checkNotNull(varysConfig.address());
    }
}
