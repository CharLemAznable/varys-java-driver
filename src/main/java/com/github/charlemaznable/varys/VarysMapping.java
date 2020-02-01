package com.github.charlemaznable.varys;

import com.github.charlemaznable.core.net.ohclient.OhMapping;
import com.github.charlemaznable.core.net.ohclient.param.OhFixedPathVar;

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
@OhMapping(urlProvider = VarysConfigProvider.class)
@OhFixedPathVar(name = "queryWechatAppToken", valueProvider = VarysConfigProvider.class)
@OhFixedPathVar(name = "queryWechatAppAuthorizerToken", valueProvider = VarysConfigProvider.class)
@OhFixedPathVar(name = "queryWechatCorpToken", valueProvider = VarysConfigProvider.class)
@OhFixedPathVar(name = "queryWechatCorpAuthorizerToken", valueProvider = VarysConfigProvider.class)
@OhFixedPathVar(name = "proxyWechatApp", valueProvider = VarysConfigProvider.class)
@OhFixedPathVar(name = "proxyWechatCorp", valueProvider = VarysConfigProvider.class)
public @interface VarysMapping {
}
