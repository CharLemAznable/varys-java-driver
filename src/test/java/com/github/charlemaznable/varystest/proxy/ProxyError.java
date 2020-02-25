package com.github.charlemaznable.varystest.proxy;

import com.github.charlemaznable.core.net.common.FixedPathVar;
import com.github.charlemaznable.core.net.common.Mapping;
import com.github.charlemaznable.core.net.common.PathVar;
import com.github.charlemaznable.varys.impl.ProxyApp;
import com.github.charlemaznable.varys.impl.VarysPathProvider;

@ProxyApp
@FixedPathVar(name = "proxyWechatError", valueProvider = VarysPathProvider.class)
public interface ProxyError {

    @Mapping("/{proxyWechatError}/{codeName}/wechatApp")
    String proxyError(@PathVar("codeName") String codeName);
}
