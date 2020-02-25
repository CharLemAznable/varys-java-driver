package com.github.charlemaznable.varystest.spring.instanceconfig;

import com.github.charlemaznable.core.net.ohclient.OhScan;
import com.github.charlemaznable.varys.spring.VarysImport;
import com.github.charlemaznable.varystest.proxy.TestSpringScanAnchor;
import org.springframework.context.annotation.ComponentScan;

import javax.annotation.PostConstruct;

import static com.github.charlemaznable.core.miner.MinerFactory.springMinerLoader;
import static com.github.charlemaznable.core.net.ohclient.OhFactory.springOhLoader;
import static org.joor.Reflect.on;

@ComponentScan
@VarysImport
@OhScan(basePackageClasses = TestSpringScanAnchor.class)
public class InstanceConfiguration {

    @PostConstruct
    public void postConstruct() {
        on(springMinerLoader()).field("minerCache").call("invalidateAll");
        on(springOhLoader()).field("ohCache").call("invalidateAll");
    }
}
