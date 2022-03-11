package com.github.charlemaznable.varys.config;

import com.github.charlemaznable.configservice.apollo.ApolloConfig;
import com.github.charlemaznable.configservice.diamond.DiamondConfig;

@ApolloConfig(namespace = "Varys", propertyName = "${varys-config:-default}")
@DiamondConfig(group = "Varys", dataId = "${varys-config:-default}")
public interface VarysConfig {

    String address();

    @ApolloConfig(defaultValue = "0")
    @DiamondConfig(defaultValue = "0")
    long callTimeout();

    @ApolloConfig(defaultValue = "10000")
    @DiamondConfig(defaultValue = "10000")
    long connectTimeout();

    @ApolloConfig(defaultValue = "10000")
    @DiamondConfig(defaultValue = "10000")
    long readTimeout();

    @ApolloConfig(defaultValue = "10000")
    @DiamondConfig(defaultValue = "10000")
    long writeTimeout();
}
