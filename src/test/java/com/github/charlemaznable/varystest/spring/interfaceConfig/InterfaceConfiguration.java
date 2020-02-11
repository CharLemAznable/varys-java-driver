package com.github.charlemaznable.varystest.spring.interfaceConfig;

import com.github.charlemaznable.core.miner.MinerFactory;
import com.github.charlemaznable.core.miner.MinerScan;
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
@MinerScan
@OhScan("com.github.charlemaznable.varystest.spring.proxy")
public class InterfaceConfiguration {

    @PostConstruct
    public void postConstruct() {
        onClass(OhFactory.class).field("ohCache").call("invalidateAll");
        onClass(MinerFactory.class).field("minerCache").call("invalidateAll");
        MockDiamondServer.setUpMockServer();
        MockDiamondServer.setConfigInfo("Varys", "test",
                "InterfaceAddress=http://127.0.0.1:4236/varys\n");
    }

    @PreDestroy
    public void preDestroy() {
        MockDiamondServer.tearDownMockServer();
    }
}