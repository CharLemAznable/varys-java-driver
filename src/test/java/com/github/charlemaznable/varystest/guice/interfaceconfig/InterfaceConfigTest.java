package com.github.charlemaznable.varystest.guice.interfaceconfig;

import com.github.charlemaznable.varys.guice.VarysModular;
import com.github.charlemaznable.varys.impl.Query;
import com.github.charlemaznable.varystest.proxy.ProxyFengniaoAppDemo;
import com.github.charlemaznable.varystest.proxy.ProxyWechatAppDemo;
import com.github.charlemaznable.varystest.proxy.ProxyWechatCorpDemo;
import com.github.charlemaznable.varystest.proxy.ProxyWechatTpAuthDemo;
import com.github.charlemaznable.varystest.proxy.ProxyWechatTpDemo;
import com.github.charlemaznable.varystest.proxy.TestVarysScanAnchor;
import com.google.inject.Guice;
import com.google.inject.Injector;
import lombok.SneakyThrows;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.n3r.diamond.client.impl.MockDiamondServer;

import static com.github.charlemaznable.varystest.mock.InterfaceConfigMock.proxyInterfaceConfig;
import static com.github.charlemaznable.varystest.mock.InterfaceConfigMock.queryInterfaceConfig;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class InterfaceConfigTest {

    private static VarysModular varysModular;
    private static Injector injector;

    @BeforeAll
    public static void beforeAll() {
        varysModular = new VarysModular(InterfaceConfig.class);
        injector = Guice.createInjector(varysModular.scanOtherClientPackageClasses(
                TestVarysScanAnchor.class).createModule());
        MockDiamondServer.setUpMockServer();
        MockDiamondServer.setConfigInfo("Varys", "test",
                "InterfaceAddress=http://127.0.0.1:4236/varys\n");
    }

    @AfterAll
    public static void afterAll() {
        MockDiamondServer.tearDownMockServer();
    }

    @SneakyThrows
    @Test
    public void testInterfaceConfigQuery() {
        assertDoesNotThrow(() -> queryInterfaceConfig(varysModular.getClient(Query.class)));
    }

    @SneakyThrows
    @Test
    public void testInterfaceConfigProxy() {
        assertDoesNotThrow(() -> proxyInterfaceConfig(
                injector.getInstance(ProxyWechatAppDemo.class),
                injector.getInstance(ProxyWechatTpDemo.class),
                injector.getInstance(ProxyWechatTpAuthDemo.class),
                injector.getInstance(ProxyWechatCorpDemo.class),
                injector.getInstance(ProxyFengniaoAppDemo.class)));
    }
}
