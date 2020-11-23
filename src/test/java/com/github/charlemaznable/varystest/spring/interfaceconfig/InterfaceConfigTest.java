package com.github.charlemaznable.varystest.spring.interfaceconfig;

import com.github.charlemaznable.varys.impl.Query;
import com.github.charlemaznable.varystest.proxy.ProxyFengniaoAppDemo;
import com.github.charlemaznable.varystest.proxy.ProxyWechatAppDemo;
import com.github.charlemaznable.varystest.proxy.ProxyWechatCorpDemo;
import com.github.charlemaznable.varystest.proxy.ProxyWechatTpAuthDemo;
import com.github.charlemaznable.varystest.proxy.ProxyWechatTpDemo;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static com.github.charlemaznable.varystest.mock.InterfaceConfigMock.proxyInterfaceConfig;
import static com.github.charlemaznable.varystest.mock.InterfaceConfigMock.queryInterfaceConfig;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = InterfaceConfiguration.class)
public class InterfaceConfigTest {

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
    public void testInterfaceConfigQuery() {
        assertDoesNotThrow(() -> queryInterfaceConfig(query));
    }

    @SneakyThrows
    @Test
    public void testInterfaceConfigProxy() {
        assertDoesNotThrow(() -> proxyInterfaceConfig(
                proxyWechatApp,
                proxyWechatTp,
                proxyWechatTpAuth,
                proxyWechatCorp,
                proxyFengniaoApp));
    }
}
