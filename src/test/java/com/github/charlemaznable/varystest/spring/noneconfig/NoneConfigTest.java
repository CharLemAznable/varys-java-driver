package com.github.charlemaznable.varystest.spring.noneconfig;

import com.github.charlemaznable.core.spring.SpringContext;
import com.github.charlemaznable.varys.impl.Query;
import com.github.charlemaznable.varystest.proxy.ProxyWechatAppDemo;
import com.github.charlemaznable.varystest.proxy.ProxyWechatCorpDemo;
import com.github.charlemaznable.varystest.proxy.ProxyWechatMpDemo;
import com.github.charlemaznable.varystest.proxy.ProxyWechatTpDemo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static com.github.charlemaznable.core.net.ohclient.OhFactory.getClient;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SuppressWarnings({"SpringJavaInjectionPointsAutowiringInspection", "Duplicates"})
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = NoneConfiguration.class)
public class NoneConfigTest {

    @Test
    public void testNoneConfigQuery() {
        try {
            SpringContext.getBean(Query.class);
        } catch (Exception e) {
            assertTrue(e.getCause() instanceof NullPointerException);
        }
        assertThrows(NullPointerException.class, () -> getClient(Query.class));
    }

    @Test
    public void testNoneConfigProxy() {
        try {
            SpringContext.getBean(ProxyWechatAppDemo.class);
        } catch (Exception e) {
            assertTrue(e.getCause() instanceof NullPointerException);
        }
        assertThrows(NullPointerException.class, () -> getClient(ProxyWechatAppDemo.class));

        try {
            SpringContext.getBean(ProxyWechatMpDemo.class);
        } catch (Exception e) {
            assertTrue(e.getCause() instanceof NullPointerException);
        }
        assertThrows(NullPointerException.class, () -> getClient(ProxyWechatMpDemo.class));

        try {
            SpringContext.getBean(ProxyWechatTpDemo.class);
        } catch (Exception e) {
            assertTrue(e.getCause() instanceof NullPointerException);
        }
        assertThrows(NullPointerException.class, () -> getClient(ProxyWechatTpDemo.class));

        try {
            SpringContext.getBean(ProxyWechatCorpDemo.class);
        } catch (Exception e) {
            assertTrue(e.getCause() instanceof NullPointerException);
        }
        assertThrows(NullPointerException.class, () -> getClient(ProxyWechatCorpDemo.class));
    }
}
