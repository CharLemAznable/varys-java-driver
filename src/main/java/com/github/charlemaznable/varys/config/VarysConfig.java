package com.github.charlemaznable.varys.config;

import com.github.charlemaznable.core.miner.MinerConfig;

@MinerConfig(group = "Varys", dataId = "default")
public interface VarysConfig {

    String address();

    @MinerConfig(defaultValue = "0")
    long callTimeout();

    @MinerConfig(defaultValue = "10000")
    long connectTimeout();

    @MinerConfig(defaultValue = "10000")
    long readTimeout();

    @MinerConfig(defaultValue = "10000")
    long writeTimeout();
}
