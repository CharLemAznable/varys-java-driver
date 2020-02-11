package com.github.charlemaznable.varystest.spring.noneConfig;

import com.github.charlemaznable.core.miner.MinerFactory;
import com.github.charlemaznable.core.net.ohclient.OhFactory;
import com.github.charlemaznable.core.net.ohclient.OhScan;
import com.github.charlemaznable.varys.spring.VarysImport;
import org.n3r.diamond.client.impl.MockDiamondServer;
import org.springframework.context.annotation.ComponentScan;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import static org.joor.Reflect.onClass;

@ComponentScan
@VarysImport
@OhScan("com.github.charlemaznable.varystest.spring.proxy")
public class NoneConfiguration {

    @PostConstruct
    public void postConstruct() {
        onClass(OhFactory.class).field("ohCache").call("invalidateAll");
        onClass(MinerFactory.class).field("minerCache").call("invalidateAll");
        MockDiamondServer.setUpMockServer();
        MockDiamondServer.setConfigInfo("Varys", "default", "");
    }

    @PreDestroy
    public void preDestroy() {
        MockDiamondServer.tearDownMockServer();
    }
}