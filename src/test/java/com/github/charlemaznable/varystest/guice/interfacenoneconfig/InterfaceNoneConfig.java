package com.github.charlemaznable.varystest.guice.interfacenoneconfig;

import com.github.charlemaznable.miner.MinerConfig;
import com.github.charlemaznable.varys.config.VarysConfig;

@MinerConfig(group = "Varys", dataId = "empty")
public interface InterfaceNoneConfig extends VarysConfig {
}
