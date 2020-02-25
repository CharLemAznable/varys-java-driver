package com.github.charlemaznable.varystest.spring.interfaceconfig;

import com.github.charlemaznable.core.miner.MinerConfig;
import com.github.charlemaznable.varys.config.VarysConfig;

@MinerConfig(group = "Varys", dataId = "test")
public interface InterfaceConfig extends VarysConfig {

    @MinerConfig(dataId = "InterfaceAddress")
    String address();
}
