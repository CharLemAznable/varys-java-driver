package com.github.charlemaznable.varys.impl;

import com.github.charlemaznable.httpclient.common.Mapping.UrlProvider;
import com.github.charlemaznable.varys.config.VarysConfig;
import lombok.AllArgsConstructor;
import lombok.val;

import static com.github.charlemaznable.core.lang.Condition.checkNotNull;
import static org.apache.commons.lang3.StringUtils.appendIfMissing;

@AllArgsConstructor
public final class VarysProxyWechatTpUrlProvider implements UrlProvider {

    private final VarysConfig varysConfig;

    @Override
    public String url(Class<?> clazz) {
        val address = checkNotNull(varysConfig.address());
        return appendIfMissing(address, "/") + "proxy-wechat-tp";
    }
}
