package com.github.charlemaznable.varystest.spring.noneconfig;

import com.github.charlemaznable.core.net.ohclient.OhScan;
import com.github.charlemaznable.varys.spring.VarysImport;
import com.github.charlemaznable.varystest.proxy.TestSpringScanAnchor;
import org.n3r.diamond.client.impl.MockDiamondServer;
import org.springframework.context.annotation.ComponentScan;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import static com.github.charlemaznable.core.miner.MinerFactory.springMinerLoader;
import static com.github.charlemaznable.core.net.ohclient.OhFactory.springOhLoader;
import static org.joor.Reflect.on;

@ComponentScan
@VarysImport
@OhScan(basePackageClasses = TestSpringScanAnchor.class)
public class NoneConfiguration {

    @PostConstruct
    public void postConstruct() {
        on(springMinerLoader()).field("minerCache").call("invalidateAll");
        on(springOhLoader()).field("ohCache").call("invalidateAll");
        MockDiamondServer.setUpMockServer();
        MockDiamondServer.setConfigInfo("Varys", "default", "");
    }

    @PreDestroy
    public void preDestroy() {
        MockDiamondServer.tearDownMockServer();
    }
}
