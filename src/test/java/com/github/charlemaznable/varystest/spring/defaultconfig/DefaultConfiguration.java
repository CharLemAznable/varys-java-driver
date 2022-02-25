package com.github.charlemaznable.varystest.spring.defaultconfig;

import com.github.charlemaznable.httpclient.ohclient.OhScan;
import com.github.charlemaznable.varys.spring.VarysImport;
import com.github.charlemaznable.varystest.proxy.TestVarysScanAnchor;
import org.n3r.diamond.client.impl.MockDiamondServer;
import org.springframework.context.annotation.ComponentScan;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import static com.github.charlemaznable.httpclient.ohclient.OhFactory.springOhLoader;
import static com.github.charlemaznable.miner.MinerFactory.springMinerLoader;
import static org.joor.Reflect.on;

@ComponentScan
@VarysImport
@OhScan(basePackageClasses = TestVarysScanAnchor.class)
public class DefaultConfiguration {

    @PostConstruct
    public void postConstruct() {
        on(springMinerLoader()).field("minerCache").call("invalidateAll");
        on(springOhLoader()).field("ohCache").call("invalidateAll");
        MockDiamondServer.setUpMockServer();
        MockDiamondServer.setConfigInfo("Varys", "default",
                "address=http://127.0.0.1:4236/varys\n");
    }

    @PreDestroy
    public void preDestroy() {
        MockDiamondServer.tearDownMockServer();
    }
}
