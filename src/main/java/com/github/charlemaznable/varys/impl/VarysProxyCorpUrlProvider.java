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
import static org.apache.commons.lang3.StringUtils.prependIfMissing;
import static org.apache.commons.lang3.StringUtils.removeEnd;

@Component
public final class VarysProxyCorpUrlProvider implements UrlProvider {

    private final VarysConfig varysConfig;

    @Autowired(required = false)
    public VarysProxyCorpUrlProvider() {
        this(null);
    }

    @Inject
    @Autowired(required = false)
    public VarysProxyCorpUrlProvider(@Nullable VarysConfig varysConfig) {
        this.varysConfig = nullThen(varysConfig, () -> getMiner(VarysConfig.class));
    }

    @Override
    public String url(Class<?> clazz) {
        val address = checkNotNull(varysConfig.address());
        val proxyPath = checkNotNull(varysConfig.proxyWechatCorpPath());
        return removeEnd(address, "/") + prependIfMissing(proxyPath, "/");
    }
}
