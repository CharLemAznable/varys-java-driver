package com.github.charlemaznable.varystest.spring.interfaceconfig;

import com.github.charlemaznable.configservice.diamond.DiamondScan;
import com.github.charlemaznable.httpclient.ohclient.OhScan;
import com.github.charlemaznable.varys.spring.VarysImport;
import com.github.charlemaznable.varystest.proxy.TestVarysScanAnchor;
import org.n3r.diamond.client.impl.MockDiamondServer;
import org.springframework.context.annotation.ComponentScan;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import static com.github.charlemaznable.configservice.diamond.DiamondFactory.diamondLoader;
import static com.github.charlemaznable.core.spring.SpringFactory.springFactory;
import static com.github.charlemaznable.httpclient.ohclient.OhFactory.springOhLoader;
import static org.joor.Reflect.on;

@ComponentScan
@VarysImport
@DiamondScan
@OhScan(basePackageClasses = TestVarysScanAnchor.class)
public class InterfaceConfiguration {

    @PostConstruct
    public void postConstruct() {
        on(diamondLoader(springFactory())).field("configCache").call("invalidateAll");
        on(springOhLoader()).field("ohCache").call("invalidateAll");
        MockDiamondServer.setUpMockServer();
        MockDiamondServer.setConfigInfo("Varys", "test",
                "InterfaceAddress=http://127.0.0.1:4236/varys\n");
    }

    @PreDestroy
    public void preDestroy() {
        MockDiamondServer.tearDownMockServer();
    }
}
