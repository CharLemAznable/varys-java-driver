package com.github.charlemaznable.varys.config;

import com.github.charlemaznable.configservice.Config;

@Config(keyset = "Varys", key = "${varys-config:-default}")
public interface VarysConfig {

    String address();

    @Config(defaultValue = "0")
    long callTimeout();

    @Config(defaultValue = "10000")
    long connectTimeout();

    @Config(defaultValue = "10000")
    long readTimeout();

    @Config(defaultValue = "10000")
    long writeTimeout();
}
