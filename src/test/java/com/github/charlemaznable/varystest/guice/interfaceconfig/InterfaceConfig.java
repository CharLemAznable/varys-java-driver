package com.github.charlemaznable.varystest.guice.interfaceconfig;

import com.github.charlemaznable.miner.MinerConfig;
import com.github.charlemaznable.varys.config.VarysConfig;

@MinerConfig(group = "Varys", dataId = "test")
public interface InterfaceConfig extends VarysConfig {

    @MinerConfig(dataId = "InterfaceAddress")
    String address();
}
