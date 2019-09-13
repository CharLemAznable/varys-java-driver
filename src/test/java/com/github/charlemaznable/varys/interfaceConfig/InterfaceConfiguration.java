package com.github.charlemaznable.varys.interfaceConfig;

import com.github.charlemaznable.core.miner.MinerScan;
import com.github.charlemaznable.varys.spring.VarysImport;
import org.n3r.diamond.client.impl.MockDiamondServer;
import org.springframework.context.annotation.ComponentScan;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@ComponentScan
@VarysImport
@MinerScan
public class InterfaceConfiguration {

    @PostConstruct
    public void postConstruct() {
        MockDiamondServer.setUpMockServer();
        MockDiamondServer.setConfigInfo("VARYS", "test",
                "address=http://127.0.0.1:4236/varys\n");
    }

    @PreDestroy
    public void preDestroy() {
        MockDiamondServer.tearDownMockServer();
    }
}
