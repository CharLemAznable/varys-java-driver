package com.github.charlemaznable.varystest.guice.defaultconfig;

import com.github.charlemaznable.varys.config.VarysConfig;
import com.github.charlemaznable.varys.guice.VarysModular;
import com.github.charlemaznable.varys.impl.Query;
import com.github.charlemaznable.varys.impl.VarysCallTimeoutProvider;
import com.github.charlemaznable.varys.impl.VarysConnectTimeoutProvider;
import com.github.charlemaznable.varys.impl.VarysProxyFengniaoAppUrlProvider;
import com.github.charlemaznable.varys.impl.VarysProxyShansongAppDeveloperUrlProvider;
import com.github.charlemaznable.varys.impl.VarysProxyShansongAppFileUrlProvider;
import com.github.charlemaznable.varys.impl.VarysProxyShansongAppMerchantUrlProvider;
import com.github.charlemaznable.varys.impl.VarysProxyWechatAppUrlProvider;
import com.github.charlemaznable.varys.impl.VarysProxyWechatCorpUrlProvider;
import com.github.charlemaznable.varys.impl.VarysProxyWechatTpAuthUrlProvider;
import com.github.charlemaznable.varys.impl.VarysProxyWechatTpUrlProvider;
import com.github.charlemaznable.varys.impl.VarysQueryUrlProvider;
import com.github.charlemaznable.varys.impl.VarysReadTimeoutProvider;
import com.github.charlemaznable.varys.impl.VarysWriteTimeoutProvider;
import com.github.charlemaznable.varystest.proxy.ProxyFengniaoAppDemo;
import com.github.charlemaznable.varystest.proxy.ProxyShansongAppDeveloperDemo;
import com.github.charlemaznable.varystest.proxy.ProxyShansongAppFileDemo;
import com.github.charlemaznable.varystest.proxy.ProxyShansongAppMerchantDemo;
import com.github.charlemaznable.varystest.proxy.ProxyWechatAppDemo;
import com.github.charlemaznable.varystest.proxy.ProxyWechatCorpDemo;
import com.github.charlemaznable.varystest.proxy.ProxyWechatTpAuthDemo;
import com.github.charlemaznable.varystest.proxy.ProxyWechatTpDemo;
import com.google.inject.Guice;
import com.google.inject.Injector;
import lombok.SneakyThrows;
import lombok.val;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.n3r.diamond.client.impl.MockDiamondServer;

import static com.github.charlemaznable.configservice.ConfigFactory.getConfig;
import static com.github.charlemaznable.varystest.mock.DefaultConfigMock.proxyDefaultConfig;
import static com.github.charlemaznable.varystest.mock.DefaultConfigMock.queryDefaultConfig;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

public class DefaultConfigTest {

    private static VarysModular varysModular;
    private static Injector injector;

    @BeforeAll
    public static void beforeAll() {
        varysModular = new VarysModular();
        injector = Guice.createInjector(varysModular.bindOtherClients(
                ProxyWechatAppDemo.class,
                ProxyWechatTpDemo.class,
                ProxyWechatTpAuthDemo.class,
                ProxyWechatCorpDemo.class,
                ProxyFengniaoAppDemo.class,
                ProxyShansongAppDeveloperDemo.class,
                ProxyShansongAppMerchantDemo.class,
                ProxyShansongAppFileDemo.class).createModule());
        MockDiamondServer.setUpMockServer();
        MockDiamondServer.setConfigInfo("Varys", "default",
                "address=http://127.0.0.1:4236/varys\n");
    }

    @AfterAll
    public static void afterAll() {
        MockDiamondServer.tearDownMockServer();
    }

    @SneakyThrows
    @Test
    public void testDefaultConfigQuery() {
        assertDoesNotThrow(() -> queryDefaultConfig(varysModular.getClient(Query.class)));
    }

    @SneakyThrows
    @Test
    public void testDefaultConfigProxy() {
        assertDoesNotThrow(() -> proxyDefaultConfig(
                injector.getInstance(ProxyWechatAppDemo.class),
                injector.getInstance(ProxyWechatTpDemo.class),
                injector.getInstance(ProxyWechatTpAuthDemo.class),
                injector.getInstance(ProxyWechatCorpDemo.class),
                injector.getInstance(ProxyFengniaoAppDemo.class),
                injector.getInstance(ProxyShansongAppDeveloperDemo.class),
                injector.getInstance(ProxyShansongAppMerchantDemo.class),
                injector.getInstance(ProxyShansongAppFileDemo.class)));
    }

    @Test
    public void testProxyError() {
        assertNull(injector.getInstance(VarysConfig.class));
        assertNotNull(injector.getInstance(VarysQueryUrlProvider.class));
    }

    @Test
    public void testContext() {
        assertNull(injector.getInstance(VarysConfig.class));

        val varysConfig = getConfig(VarysConfig.class);

        val varysProxyAppUrlProvider = injector
                .getInstance(VarysProxyWechatAppUrlProvider.class);
        assertNotNull(varysProxyAppUrlProvider);
        assertEquals(new VarysProxyWechatAppUrlProvider(varysConfig).url(Query.class),
                varysProxyAppUrlProvider.url(Query.class));

        val varysProxyTpUrlProvider = injector
                .getInstance(VarysProxyWechatTpUrlProvider.class);
        assertNotNull(varysProxyTpUrlProvider);
        assertEquals(new VarysProxyWechatTpUrlProvider(varysConfig).url(Query.class),
                varysProxyTpUrlProvider.url(Query.class));

        val varysProxyTpAuthUrlProvider = injector
                .getInstance(VarysProxyWechatTpAuthUrlProvider.class);
        assertNotNull(varysProxyTpAuthUrlProvider);
        assertEquals(new VarysProxyWechatTpAuthUrlProvider(varysConfig).url(Query.class),
                varysProxyTpAuthUrlProvider.url(Query.class));

        val varysProxyCorpUrlProvider = injector
                .getInstance(VarysProxyWechatCorpUrlProvider.class);
        assertNotNull(varysProxyCorpUrlProvider);
        assertEquals(new VarysProxyWechatCorpUrlProvider(varysConfig).url(Query.class),
                varysProxyCorpUrlProvider.url(Query.class));

        val varysProxyFengniaoAppUrlProvider = injector
                .getInstance(VarysProxyFengniaoAppUrlProvider.class);
        assertNotNull(varysProxyFengniaoAppUrlProvider);
        assertEquals(new VarysProxyFengniaoAppUrlProvider(varysConfig).url(Query.class),
                varysProxyFengniaoAppUrlProvider.url(Query.class));

        val varysProxyShansongAppDeveloperUrlProvider = injector
                .getInstance(VarysProxyShansongAppDeveloperUrlProvider.class);
        assertNotNull(varysProxyShansongAppDeveloperUrlProvider);
        assertEquals(new VarysProxyShansongAppDeveloperUrlProvider(varysConfig).url(Query.class),
                varysProxyShansongAppDeveloperUrlProvider.url(Query.class));

        val varysProxyShansongAppMerchantUrlProvider = injector
                .getInstance(VarysProxyShansongAppMerchantUrlProvider.class);
        assertNotNull(varysProxyShansongAppMerchantUrlProvider);
        assertEquals(new VarysProxyShansongAppMerchantUrlProvider(varysConfig).url(Query.class),
                varysProxyShansongAppMerchantUrlProvider.url(Query.class));

        val varysProxyShansongAppFileUrlProvider = injector
                .getInstance(VarysProxyShansongAppFileUrlProvider.class);
        assertNotNull(varysProxyShansongAppFileUrlProvider);
        assertEquals(new VarysProxyShansongAppFileUrlProvider(varysConfig).url(Query.class),
                varysProxyShansongAppFileUrlProvider.url(Query.class));

        val varysQueryUrlProvider = injector
                .getInstance(VarysQueryUrlProvider.class);
        assertNotNull(varysQueryUrlProvider);
        assertEquals(new VarysQueryUrlProvider(varysConfig).url(Query.class),
                varysQueryUrlProvider.url(Query.class));

        val varysCallTimeoutProvider = injector
                .getInstance(VarysCallTimeoutProvider.class);
        assertNotNull(varysCallTimeoutProvider);
        assertEquals(new VarysCallTimeoutProvider(varysConfig).timeout(Query.class),
                varysCallTimeoutProvider.timeout(Query.class));

        val varysConnectTimeoutProvider = injector
                .getInstance(VarysConnectTimeoutProvider.class);
        assertNotNull(varysConnectTimeoutProvider);
        assertEquals(new VarysConnectTimeoutProvider(varysConfig).timeout(Query.class),
                varysConnectTimeoutProvider.timeout(Query.class));

        val varysReadTimeoutProvider = injector
                .getInstance(VarysReadTimeoutProvider.class);
        assertNotNull(varysReadTimeoutProvider);
        assertEquals(new VarysReadTimeoutProvider(varysConfig).timeout(Query.class),
                varysReadTimeoutProvider.timeout(Query.class));

        val varysWriteTimeoutProvider = injector
                .getInstance(VarysWriteTimeoutProvider.class);
        assertNotNull(varysWriteTimeoutProvider);
        assertEquals(new VarysWriteTimeoutProvider(varysConfig).timeout(Query.class),
                varysWriteTimeoutProvider.timeout(Query.class));
    }
}
