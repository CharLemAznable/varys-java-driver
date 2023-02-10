package com.github.charlemaznable.varystest.spring.defaultconfig;

import com.github.charlemaznable.core.spring.SpringContext;
import com.github.charlemaznable.varys.config.VarysConfig;
import com.github.charlemaznable.varys.impl.Query;
import com.github.charlemaznable.varystest.proxy.ProxyFengniaoAppDemo;
import com.github.charlemaznable.varystest.proxy.ProxyShansongAppDeveloperDemo;
import com.github.charlemaznable.varystest.proxy.ProxyShansongAppFileDemo;
import com.github.charlemaznable.varystest.proxy.ProxyShansongAppMerchantDemo;
import com.github.charlemaznable.varystest.proxy.ProxyWechatAppDemo;
import com.github.charlemaznable.varystest.proxy.ProxyWechatCorpDemo;
import com.github.charlemaznable.varystest.proxy.ProxyWechatTpAuthDemo;
import com.github.charlemaznable.varystest.proxy.ProxyWechatTpDemo;
import lombok.SneakyThrows;
import lombok.val;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import static com.github.charlemaznable.configservice.ConfigFactory.getConfig;
import static com.github.charlemaznable.varystest.mock.DefaultConfigMock.proxyDefaultConfig;
import static com.github.charlemaznable.varystest.mock.DefaultConfigMock.queryDefaultConfig;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
@SpringJUnitConfig(DefaultConfiguration.class)
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
    @Autowired
    private ProxyShansongAppDeveloperDemo proxyShansongAppDeveloper;
    @Autowired
    private ProxyShansongAppMerchantDemo proxyShansongAppMerchant;
    @Autowired
    private ProxyShansongAppFileDemo proxyShansongAppFile;

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
                proxyFengniaoApp,
                proxyShansongAppDeveloper,
                proxyShansongAppMerchant,
                proxyShansongAppFile));
    }

    @Test
    public void testContext() {
        assertNull(SpringContext.getBean(VarysConfig.class));

        val varysConfig = getConfig(VarysConfig.class);

        val proxyWechatAppConfig = SpringContext
                .getBean(VarysConfig.ProxyWechatAppConfig.class);
        assertNotNull(proxyWechatAppConfig);
        assertEquals(new VarysConfig.ProxyWechatAppConfig(varysConfig).urlsString(),
                proxyWechatAppConfig.urlsString());

        val proxyWechatTpConfig = SpringContext
                .getBean(VarysConfig.ProxyWechatTpConfig.class);
        assertNotNull(proxyWechatTpConfig);
        assertEquals(new VarysConfig.ProxyWechatTpConfig(varysConfig).urlsString(),
                proxyWechatTpConfig.urlsString());

        val proxyWechatTpAuthConfig = SpringContext
                .getBean(VarysConfig.ProxyWechatTpAuthConfig.class);
        assertNotNull(proxyWechatTpAuthConfig);
        assertEquals(new VarysConfig.ProxyWechatTpAuthConfig(varysConfig).urlsString(),
                proxyWechatTpAuthConfig.urlsString());

        val proxyWechatCorpConfig = SpringContext
                .getBean(VarysConfig.ProxyWechatCorpConfig.class);
        assertNotNull(proxyWechatCorpConfig);
        assertEquals(new VarysConfig.ProxyWechatCorpConfig(varysConfig).urlsString(),
                proxyWechatCorpConfig.urlsString());

        val proxyFengniaoAppConfig = SpringContext
                .getBean(VarysConfig.ProxyFengniaoAppConfig.class);
        assertNotNull(proxyFengniaoAppConfig);
        assertEquals(new VarysConfig.ProxyFengniaoAppConfig(varysConfig).urlsString(),
                proxyFengniaoAppConfig.urlsString());
    }
}
