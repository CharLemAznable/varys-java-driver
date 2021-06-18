package com.github.charlemaznable.varystest.guice.instanceconfig;

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
import com.github.charlemaznable.varystest.proxy.TestVarysScanAnchor;
import com.google.inject.Guice;
import com.google.inject.Injector;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.util.ClassUtils;

import static com.github.charlemaznable.varystest.mock.InstanceConfigMock.proxyInstanceConfig;
import static com.github.charlemaznable.varystest.mock.InstanceConfigMock.queryInstanceConfig;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class InstanceConfigTest {

    private static VarysModular varysModular;
    private static Injector injector;

    @BeforeAll
    public static void beforeAll() {
        varysModular = new VarysModular(new InstanceConfig());
        injector = Guice.createInjector(varysModular.scanOtherClientPackages(
                ClassUtils.getPackageName(TestVarysScanAnchor.class)).createModule());
    }

    @SneakyThrows
    @Test
    public void testInstanceConfigQuery() {
        assertDoesNotThrow(() -> queryInstanceConfig(varysModular.getClient(Query.class)));
    }

    @SneakyThrows
    @Test
    public void testInstanceConfigProxy() {
        assertDoesNotThrow(() -> proxyInstanceConfig(
                injector.getInstance(ProxyWechatAppDemo.class),
                injector.getInstance(ProxyWechatTpDemo.class),
                injector.getInstance(ProxyWechatTpAuthDemo.class),
                injector.getInstance(ProxyWechatCorpDemo.class),
                injector.getInstance(ProxyFengniaoAppDemo.class),
                injector.getInstance(ProxyShansongAppDeveloperDemo.class),
                injector.getInstance(ProxyShansongAppMerchantDemo.class),
                injector.getInstance(ProxyShansongAppFileDemo.class)));
    }
}
