package com.github.charlemaznable.varystest.proxy;

import com.github.charlemaznable.core.net.ohclient.OhClient;
import com.github.charlemaznable.core.net.ohclient.OhMapping;
import com.github.charlemaznable.core.net.ohclient.param.OhFixedPathVar;
import com.github.charlemaznable.core.net.ohclient.param.OhPathVar;
import com.github.charlemaznable.varys.VarysConfigProvider;
import com.github.charlemaznable.varys.VarysMapping;

@OhClient
@VarysMapping
@OhFixedPathVar(name = "proxyWechatError", valueProvider = VarysConfigProvider.class)
public interface ProxyError {

    @OhMapping("/{proxyWechatError}/{codeName}/wechatApp")
    String proxyError(@OhPathVar("codeName") String codeName);
}
