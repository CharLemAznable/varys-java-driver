package com.github.charlemaznable.varys.impl;

import com.github.charlemaznable.core.net.common.Mapping;
import com.github.charlemaznable.core.net.ohclient.OhClient;
import com.github.charlemaznable.core.net.ohclient.annotation.ClientTimeout;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Inherited
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@OhClient
@Mapping(urlProvider = VarysProxyAppUrlProvider.class)
@ClientTimeout(
        callTimeoutProvider = VarysCallTimeoutProvider.class,
        connectTimeoutProvider = VarysConnectTimeoutProvider.class,
        readTimeoutProvider = VarysReadTimeoutProvider.class,
        writeTimeoutProvider = VarysWriteTimeoutProvider.class
)
public @interface ProxyApp {
}
