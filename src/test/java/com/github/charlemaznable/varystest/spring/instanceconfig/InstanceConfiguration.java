package com.github.charlemaznable.varystest.spring.instanceconfig;

import com.github.charlemaznable.httpclient.ohclient.OhScan;
import com.github.charlemaznable.varys.spring.VarysImport;
import com.github.charlemaznable.varystest.proxy.TestVarysScanAnchor;
import org.springframework.context.annotation.ComponentScan;

import javax.annotation.PostConstruct;

import static com.github.charlemaznable.httpclient.ohclient.OhFactory.springOhLoader;
import static com.github.charlemaznable.miner.MinerFactory.springMinerLoader;
import static org.joor.Reflect.on;

@ComponentScan
@VarysImport
@OhScan(basePackageClasses = TestVarysScanAnchor.class)
public class InstanceConfiguration {

    @PostConstruct
    public void postConstruct() {
        on(springMinerLoader()).field("minerCache").call("invalidateAll");
        on(springOhLoader()).field("ohCache").call("invalidateAll");
    }
}
