package com.github.charlemaznable.varystest.spring.defaultconfig;

import com.github.charlemaznable.core.spring.SpringContext;
import com.github.charlemaznable.varys.config.VarysConfig;
import com.github.charlemaznable.varys.impl.Query;
import com.github.charlemaznable.varys.impl.VarysCallTimeoutProvider;
import com.github.charlemaznable.varys.impl.VarysConnectTimeoutProvider;
import com.github.charlemaznable.varys.impl.VarysPathProvider;
import com.github.charlemaznable.varys.impl.VarysProxyAppUrlProvider;
import com.github.charlemaznable.varys.impl.VarysProxyCorpUrlProvider;
import com.github.charlemaznable.varys.impl.VarysProxyMpUrlProvider;
import com.github.charlemaznable.varys.impl.VarysQueryUrlProvider;
import com.github.charlemaznable.varys.impl.VarysReadTimeoutProvider;
import com.github.charlemaznable.varys.impl.VarysWriteTimeoutProvider;
import com.github.charlemaznable.varystest.proxy.ProxyAppDemo;
import com.github.charlemaznable.varystest.proxy.ProxyCorpDemo;
import com.github.charlemaznable.varystest.proxy.ProxyError;
import com.github.charlemaznable.varystest.proxy.ProxyMpDemo;
import lombok.SneakyThrows;
import lombok.val;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static com.github.charlemaznable.core.codec.Json.jsonOf;
import static com.github.charlemaznable.core.net.ohclient.OhFactory.getClient;
import static com.github.charlemaznable.varystest.mock.DefaultConfigMock.proxyDefaultConfig;
import static com.github.charlemaznable.varystest.mock.DefaultConfigMock.queryDefaultConfig;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = DefaultConfiguration.class)
public class DefaultConfigTest {

    @Autowired
    private Query query;
    @Autowired
    private ProxyAppDemo proxyApp;
    @Autowired
    private ProxyMpDemo proxyMp;
    @Autowired
    private ProxyCorpDemo proxyCorp;

    @SneakyThrows
    @Test
    public void testDefaultConfigQuery() {
        queryDefaultConfig(() -> {
            val appTokenResp = query.appToken("default");
            assertEquals("1000", appTokenResp.getAppId());
            assertEquals("defaultToken", appTokenResp.getToken());

            val appAuthorizerTokenResp = query.appAuthorizerToken("default", "abcd");
            assertEquals("1000", appAuthorizerTokenResp.getAppId());
            assertEquals("abcd", appAuthorizerTokenResp.getAuthorizerAppId());
            assertEquals("defaultToken", appAuthorizerTokenResp.getToken());

            val corpTokenResp = query.corpToken("default");
            assertEquals("10000", corpTokenResp.getCorpId());
            assertEquals("defaultToken", corpTokenResp.getToken());

            val corpAuthorizerTokenResp = query.corpAuthorizerToken("default", "xyz");
            assertEquals("10000", corpAuthorizerTokenResp.getCorpId());
            assertEquals("xyz", corpAuthorizerTokenResp.getSuiteId());
            assertEquals("defaultToken", corpAuthorizerTokenResp.getToken());

            val toutiaoAppTokenResp = query.toutiaoAppToken("default");
            assertEquals("2000", toutiaoAppTokenResp.getAppId());
            assertEquals("defaultToken", toutiaoAppTokenResp.getToken());
        });
    }

    @SneakyThrows
    @Test
    public void testDefaultConfigProxy() {
        proxyDefaultConfig(() -> {
            val wechatAppResp = proxyApp.wechatApp("default", "b");
            assertEquals("defaultWechatAppResp", wechatAppResp);

            val wechatAppParamResp = proxyApp.wechatAppParam("default", "testParam", jsonOf("a", "b"));
            assertEquals("defaultWechatAppParamResp", wechatAppParamResp);

            val wechatMpResp = proxyMp.wechatMp("default", "b");
            assertEquals("defaultWechatMpResp", wechatMpResp);

            val wechatMpParamResp = proxyMp.wechatMpParam("default", "testParam", jsonOf("a", "b"));
            assertEquals("defaultWechatMpParamResp", wechatMpParamResp);

            val wechatCorpResp = proxyCorp.wechatCorp("default", "b");
            assertEquals("defaultWechatCorpResp", wechatCorpResp);

            val wechatCorpParamResp = proxyCorp.wechatCorpParam("default", "testParam", "b");
            assertEquals("defaultWechatCorpParamResp", wechatCorpParamResp);
        });
    }

    @Test
    public void testProxyError() {
        try {
            SpringContext.getBean(ProxyError.class);
        } catch (Exception e) {
            assertTrue(e.getCause() instanceof IllegalArgumentException);
        }
        assertThrows(IllegalArgumentException.class,
                () -> getClient(ProxyError.class));
    }

    @Test
    public void testContext() {
        assertNull(SpringContext.getBean(VarysConfig.class));

        val varysPathProvider = SpringContext
                .getBean(VarysPathProvider.class);
        assertNotNull(varysPathProvider);
        assertEquals(new VarysPathProvider().value(Query.class, "queryWechatAppToken"),
                varysPathProvider.value(Query.class, "queryWechatAppToken"));

        val varysProxyAppUrlProvider = SpringContext
                .getBean(VarysProxyAppUrlProvider.class);
        assertNotNull(varysProxyAppUrlProvider);
        assertEquals(new VarysProxyAppUrlProvider().url(Query.class),
                varysProxyAppUrlProvider.url(Query.class));

        val varysProxyMpUrlProvider = SpringContext
                .getBean(VarysProxyMpUrlProvider.class);
        assertNotNull(varysProxyMpUrlProvider);
        assertEquals(new VarysProxyMpUrlProvider().url(Query.class),
                varysProxyMpUrlProvider.url(Query.class));

        val varysProxyCorpUrlProvider = SpringContext
                .getBean(VarysProxyCorpUrlProvider.class);
        assertNotNull(varysProxyCorpUrlProvider);
        assertEquals(new VarysProxyCorpUrlProvider().url(Query.class),
                varysProxyCorpUrlProvider.url(Query.class));

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
