package com.github.charlemaznable.varystest.spring.interfacenoneconfig;

import com.github.charlemaznable.configservice.diamond.DiamondConfig;
import com.github.charlemaznable.varys.config.VarysConfig;

@DiamondConfig(group = "Varys", dataId = "empty")
public interface InterfaceNoneConfig extends VarysConfig {
}
