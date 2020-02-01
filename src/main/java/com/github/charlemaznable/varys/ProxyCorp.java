package com.github.charlemaznable.varys;

import com.github.charlemaznable.core.net.ohclient.OhClient;
import com.github.charlemaznable.core.net.ohclient.OhMapping;
import com.github.charlemaznable.varys.config.VarysProxyCorpUrlProvider;

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
@OhMapping(urlProvider = VarysProxyCorpUrlProvider.class)
public @interface ProxyCorp {
}
