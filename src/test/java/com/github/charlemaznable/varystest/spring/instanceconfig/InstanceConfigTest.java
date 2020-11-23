package com.github.charlemaznable.varystest.spring.instanceconfig;

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

import static com.github.charlemaznable.varystest.mock.InstanceConfigMock.proxyInstanceConfig;
import static com.github.charlemaznable.varystest.mock.InstanceConfigMock.queryInstanceConfig;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = InstanceConfiguration.class)
public class InstanceConfigTest {

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
    public void testInstanceConfigQuery() {
        assertDoesNotThrow(() -> queryInstanceConfig(query));
    }

    @SneakyThrows
    @Test
    public void testInstanceConfigProxy() {
        assertDoesNotThrow(() -> proxyInstanceConfig(
                proxyWechatApp,
                proxyWechatTp,
                proxyWechatTpAuth,
                proxyWechatCorp,
                proxyFengniaoApp));
    }
}
