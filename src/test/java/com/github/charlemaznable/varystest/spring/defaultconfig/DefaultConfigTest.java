package com.github.charlemaznable.varystest.spring.defaultconfig;

import com.github.charlemaznable.core.spring.SpringContext;
import com.github.charlemaznable.varys.config.VarysConfig;
import com.github.charlemaznable.varys.impl.Query;
import com.github.charlemaznable.varys.impl.VarysCallTimeoutProvider;
import com.github.charlemaznable.varys.impl.VarysConnectTimeoutProvider;
import com.github.charlemaznable.varys.impl.VarysProxyFengniaoAppUrlProvider;
import com.github.charlemaznable.varys.impl.VarysProxyWechatAppUrlProvider;
import com.github.charlemaznable.varys.impl.VarysProxyWechatCorpUrlProvider;
import com.github.charlemaznable.varys.impl.VarysProxyWechatTpAuthUrlProvider;
import com.github.charlemaznable.varys.impl.VarysProxyWechatTpUrlProvider;
import com.github.charlemaznable.varys.impl.VarysQueryUrlProvider;
import com.github.charlemaznable.varys.impl.VarysReadTimeoutProvider;
import com.github.charlemaznable.varys.impl.VarysWriteTimeoutProvider;
import com.github.charlemaznable.varystest.proxy.ProxyFengniaoAppDemo;
import com.github.charlemaznable.varystest.proxy.ProxyWechatAppDemo;
import com.github.charlemaznable.varystest.proxy.ProxyWechatCorpDemo;
import com.github.charlemaznable.varystest.proxy.ProxyWechatTpAuthDemo;
import com.github.charlemaznable.varystest.proxy.ProxyWechatTpDemo;
import lombok.SneakyThrows;
import lombok.val;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static com.github.charlemaznable.varystest.mock.DefaultConfigMock.proxyDefaultConfig;
import static com.github.charlemaznable.varystest.mock.DefaultConfigMock.queryDefaultConfig;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = DefaultConfiguration.class)
public class DefaultConfigTest {

    @Autowired
    private Query query;
    @Autowired
    private ProxyWechatAppDemo proxyWechatApp;
    @Autowired
    private ProxyWechatTpDemo proxyWechatTp;
    @Autowired
    private ProxyWechatTpAuthDemo proxyWechatTpAuth;
    @Autowired
    private ProxyWechatCorpDemo proxyWechatCorp;
    @Autowired
    private ProxyFengniaoAppDemo proxyFengniaoApp;

    @SneakyThrows
    @Test
    public void testDefaultConfigQuery() {
        assertDoesNotThrow(() -> queryDefaultConfig(query));
    }

    @SneakyThrows
    @Test
    public void testDefaultConfigProxy() {
        assertDoesNotThrow(() -> proxyDefaultConfig(
                proxyWechatApp,
                proxyWechatTp,
                proxyWechatTpAuth,
                proxyWechatCorp,
                proxyFengniaoApp));
    }

    @Test
    public void testContext() {
        assertNull(SpringContext.getBean(VarysConfig.class));

        val varysProxyAppUrlProvider = SpringContext
                .getBean(VarysProxyWechatAppUrlProvider.class);
        assertNotNull(varysProxyAppUrlProvider);
        assertEquals(new VarysProxyWechatAppUrlProvider().url(Query.class),
                varysProxyAppUrlProvider.url(Query.class));

        val varysProxyTpUrlProvider = SpringContext
                .getBean(VarysProxyWechatTpUrlProvider.class);
        assertNotNull(varysProxyTpUrlProvider);
        assertEquals(new VarysProxyWechatTpUrlProvider().url(Query.class),
                varysProxyTpUrlProvider.url(Query.class));

        val varysProxyTpAuthUrlProvider = SpringContext
                .getBean(VarysProxyWechatTpAuthUrlProvider.class);
        assertNotNull(varysProxyTpAuthUrlProvider);
        assertEquals(new VarysProxyWechatTpAuthUrlProvider().url(Query.class),
                varysProxyTpAuthUrlProvider.url(Query.class));

        val varysProxyCorpUrlProvider = SpringContext
                .getBean(VarysProxyWechatCorpUrlProvider.class);
        assertNotNull(varysProxyCorpUrlProvider);
        assertEquals(new VarysProxyWechatCorpUrlProvider().url(Query.class),
                varysProxyCorpUrlProvider.url(Query.class));

        val varysProxyFengniaoAppUrlProvider = SpringContext
                .getBean(VarysProxyFengniaoAppUrlProvider.class);
        assertNotNull(varysProxyFengniaoAppUrlProvider);
        assertEquals(new VarysProxyFengniaoAppUrlProvider().url(Query.class),
                varysProxyFengniaoAppUrlProvider.url(Query.class));

        val varysQueryUrlProvider = SpringContext
                .getBean(VarysQueryUrlProvider.class);
        assertNotNull(varysQueryUrlProvider);
        assertEquals(new VarysQueryUrlProvider().url(Query.class),
                varysQueryUrlProvider.url(Query.class));

        val varysCallTimeoutProvider = SpringContext
                .getBean(VarysCallTimeoutProvider.class);
        assertNotNull(varysCallTimeoutProvider);
        assertEquals(new VarysCallTimeoutProvider().timeout(Query.class),
                varysCallTimeoutProvider.timeout(Query.class));

        val varysConnectTimeoutProvider = SpringContext
                .getBean(VarysConnectTimeoutProvider.class);
        assertNotNull(varysConnectTimeoutProvider);
        assertEquals(new VarysConnectTimeoutProvider().timeout(Query.class),
                varysConnectTimeoutProvider.timeout(Query.class));

        val varysReadTimeoutProvider = SpringContext
                .getBean(VarysReadTimeoutProvider.class);
        assertNotNull(varysReadTimeoutProvider);
        assertEquals(new VarysReadTimeoutProvider().timeout(Query.class),
                varysReadTimeoutProvider.timeout(Query.class));

        val varysWriteTimeoutProvider = SpringContext
                .getBean(VarysWriteTimeoutProvider.class);
        assertNotNull(varysWriteTimeoutProvider);
        assertEquals(new VarysWriteTimeoutProvider().timeout(Query.class),
                varysWriteTimeoutProvider.timeout(Query.class));
    }
}
