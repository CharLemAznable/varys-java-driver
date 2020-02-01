package com.github.charlemaznable.varystest.proxy;

import com.github.charlemaznable.core.net.ohclient.OhMapping;
import com.github.charlemaznable.core.net.ohclient.param.OhFixedPathVar;
import com.github.charlemaznable.core.net.ohclient.param.OhPathVar;
import com.github.charlemaznable.varys.ProxyApp;
import com.github.charlemaznable.varys.config.VarysPathProvider;

@ProxyApp
@OhFixedPathVar(name = "proxyWechatError", valueProvider = VarysPathProvider.class)
public interface ProxyError {

    @OhMapping("/{proxyWechatError}/{codeName}/wechatApp")
    String proxyError(@OhPathVar("codeName") String codeName);
}
