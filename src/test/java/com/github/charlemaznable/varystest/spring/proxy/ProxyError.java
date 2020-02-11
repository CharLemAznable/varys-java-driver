package com.github.charlemaznable.varystest.spring.proxy;

import com.github.charlemaznable.core.net.common.FixedPathVar;
import com.github.charlemaznable.core.net.common.Mapping;
import com.github.charlemaznable.core.net.common.PathVar;
import com.github.charlemaznable.varys.spring.ProxyApp;
import com.github.charlemaznable.varys.spring.provider.VarysPathProvider;

@ProxyApp
@FixedPathVar(name = "proxyWechatError", valueProvider = VarysPathProvider.class)
public interface ProxyError {

    @Mapping("/{proxyWechatError}/{codeName}/wechatApp")
    String proxyError(@PathVar("codeName") String codeName);
}
