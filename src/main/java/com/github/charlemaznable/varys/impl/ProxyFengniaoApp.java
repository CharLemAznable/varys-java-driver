package com.github.charlemaznable.varys.impl;

import com.github.charlemaznable.httpclient.common.ConfigureWith;
import com.github.charlemaznable.httpclient.ohclient.OhClient;
import com.github.charlemaznable.varys.config.VarysConfig;

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
@ConfigureWith(VarysConfig.ProxyFengniaoAppConfig.class)
public @interface ProxyFengniaoApp {
}
