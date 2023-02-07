package com.github.charlemaznable.varystest.guice.defaultconfig;

import com.github.charlemaznable.varys.config.VarysConfig;
import com.github.charlemaznable.varys.guice.VarysModular;
import com.github.charlemaznable.varys.impl.Query;
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
    public void testContext() {
        assertNull(injector.getInstance(VarysConfig.class));

        val varysConfig = getConfig(VarysConfig.class);

        val proxyWechatAppConfig = injector
                .getInstance(VarysConfig.ProxyWechatAppConfig.class);
        assertNotNull(proxyWechatAppConfig);
        assertEquals(new VarysConfig.ProxyWechatAppConfig(varysConfig).urlsString(),
                proxyWechatAppConfig.urlsString());

        val proxyWechatTpConfig = injector
                .getInstance(VarysConfig.ProxyWechatTpConfig.class);
        assertNotNull(proxyWechatTpConfig);
        assertEquals(new VarysConfig.ProxyWechatTpConfig(varysConfig).urlsString(),
                proxyWechatTpConfig.urlsString());

        val proxyWechatTpAuthConfig = injector
                .getInstance(VarysConfig.ProxyWechatTpAuthConfig.class);
        assertNotNull(proxyWechatTpAuthConfig);
        assertEquals(new VarysConfig.ProxyWechatTpAuthConfig(varysConfig).urlsString(),
                proxyWechatTpAuthConfig.urlsString());

        val proxyWechatCorpConfig = injector
                .getInstance(VarysConfig.ProxyWechatCorpConfig.class);
        assertNotNull(proxyWechatCorpConfig);
        assertEquals(new VarysConfig.ProxyWechatCorpConfig(varysConfig).urlsString(),
                proxyWechatCorpConfig.urlsString());

        val proxyFengniaoAppConfig = injector
                .getInstance(VarysConfig.ProxyFengniaoAppConfig.class);
        assertNotNull(proxyFengniaoAppConfig);
        assertEquals(new VarysConfig.ProxyFengniaoAppConfig(varysConfig).urlsString(),
                proxyFengniaoAppConfig.urlsString());

        val proxyShansongAppDeveloperConfig = injector
                .getInstance(VarysConfig.ProxyShansongAppDeveloperConfig.class);
        assertNotNull(proxyShansongAppDeveloperConfig);
        assertEquals(new VarysConfig.ProxyShansongAppDeveloperConfig(varysConfig).urlsString(),
                proxyShansongAppDeveloperConfig.urlsString());

        val proxyShansongAppMerchantConfig = injector
                .getInstance(VarysConfig.ProxyShansongAppMerchantConfig.class);
        assertNotNull(proxyShansongAppMerchantConfig);
        assertEquals(new VarysConfig.ProxyShansongAppMerchantConfig(varysConfig).urlsString(),
                proxyShansongAppMerchantConfig.urlsString());

        val proxyShansongAppFileConfig = injector
                .getInstance(VarysConfig.ProxyShansongAppFileConfig.class);
        assertNotNull(proxyShansongAppFileConfig);
        assertEquals(new VarysConfig.ProxyShansongAppFileConfig(varysConfig).urlsString(),
                proxyShansongAppFileConfig.urlsString());
    }
}
