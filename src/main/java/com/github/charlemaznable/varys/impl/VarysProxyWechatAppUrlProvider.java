package com.github.charlemaznable.varys.impl;

import com.github.charlemaznable.httpclient.common.Mapping.UrlProvider;
import com.github.charlemaznable.varys.config.VarysConfig;
import com.google.inject.Inject;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Nullable;

import static com.github.charlemaznable.configservice.ConfigFactory.getConfig;
import static com.github.charlemaznable.core.lang.Condition.checkNotNull;
import static com.github.charlemaznable.core.lang.Condition.nullThen;
import static org.apache.commons.lang3.StringUtils.appendIfMissing;

@Component
public final class VarysProxyWechatAppUrlProvider implements UrlProvider {

    private final VarysConfig varysConfig;

    public VarysProxyWechatAppUrlProvider() {
        this(null);
    }

    @Inject
    @Autowired
    public VarysProxyWechatAppUrlProvider(@Nullable VarysConfig varysConfig) {
        this.varysConfig = nullThen(varysConfig, () -> getConfig(VarysConfig.class));
    }

    @Override
    public String url(Class<?> clazz) {
        val address = checkNotNull(varysConfig.address());
        return appendIfMissing(address, "/") + "proxy-wechat-app";
    }
}
