package com.github.charlemaznable.varys.impl;

import com.github.charlemaznable.core.net.common.Mapping.UrlProvider;
import com.github.charlemaznable.varys.config.VarysConfig;
import com.google.inject.Inject;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Nullable;

import static com.github.charlemaznable.core.lang.Condition.checkNotNull;
import static com.github.charlemaznable.core.lang.Condition.nullThen;
import static com.github.charlemaznable.core.miner.MinerFactory.getMiner;
import static org.apache.commons.lang3.StringUtils.appendIfMissing;

@Component
public final class VarysProxyWechatMpUrlProvider implements UrlProvider {

    private final VarysConfig varysConfig;

    public VarysProxyWechatMpUrlProvider() {
        this(null);
    }

    @Inject
    @Autowired
    public VarysProxyWechatMpUrlProvider(@Nullable VarysConfig varysConfig) {
        this.varysConfig = nullThen(varysConfig, () -> getMiner(VarysConfig.class));
    }

    @Override
    public String url(Class<?> clazz) {
        val address = checkNotNull(varysConfig.address());
        return appendIfMissing(address, "/") + "proxy-wechat-mp";
    }
}
