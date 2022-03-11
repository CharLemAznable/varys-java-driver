package com.github.charlemaznable.varystest.guice.interfaceconfig;

import com.github.charlemaznable.configservice.diamond.DiamondConfig;
import com.github.charlemaznable.varys.config.VarysConfig;

@DiamondConfig(group = "Varys", dataId = "test")
public interface InterfaceConfig extends VarysConfig {

    @DiamondConfig(dataId = "InterfaceAddress")
    String address();
}
