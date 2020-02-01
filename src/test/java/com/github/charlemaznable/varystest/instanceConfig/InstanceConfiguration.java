package com.github.charlemaznable.varystest.instanceConfig;

import com.github.charlemaznable.core.miner.MinerFactory;
import com.github.charlemaznable.core.net.ohclient.OhFactory;
import com.github.charlemaznable.core.net.ohclient.OhScan;
import com.github.charlemaznable.varys.VarysImport;
import org.springframework.context.annotation.ComponentScan;

import javax.annotation.PostConstruct;

import static org.joor.Reflect.onClass;

@ComponentScan
@VarysImport
@OhScan("com.github.charlemaznable.varystest.proxy")
public class InstanceConfiguration {

    @PostConstruct
    public void postConstruct() {
        onClass(OhFactory.class).field("ohCache").call("invalidateAll");
        onClass(MinerFactory.class).field("minerCache").call("invalidateAll");
    }
}
