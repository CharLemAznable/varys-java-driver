package com.github.charlemaznable.varystest.spring.interfacenoneconfig;

import com.github.charlemaznable.core.spring.SpringContext;
import com.github.charlemaznable.varys.impl.Query;
import com.github.charlemaznable.varystest.proxy.ProxyAppDemo;
import com.github.charlemaznable.varystest.proxy.ProxyCorpDemo;
import com.github.charlemaznable.varystest.proxy.ProxyMpDemo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static com.github.charlemaznable.core.net.ohclient.OhFactory.getClient;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SuppressWarnings({"SpringJavaInjectionPointsAutowiringInspection", "Duplicates"})
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = InterfaceNoneConfiguration.class)
public class InterfaceNoneConfigTest {

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
            SpringContext.getBean(ProxyAppDemo.class);
        } catch (Exception e) {
            assertTrue(e.getCause() instanceof NullPointerException);
        }
        assertThrows(NullPointerException.class, () -> getClient(ProxyAppDemo.class));

        try {
            SpringContext.getBean(ProxyMpDemo.class);
        } catch (Exception e) {
            assertTrue(e.getCause() instanceof NullPointerException);
        }
        assertThrows(NullPointerException.class, () -> getClient(ProxyMpDemo.class));

        try {
            SpringContext.getBean(ProxyCorpDemo.class);
        } catch (Exception e) {
            assertTrue(e.getCause() instanceof NullPointerException);
        }
        assertThrows(NullPointerException.class, () -> getClient(ProxyCorpDemo.class));
    }
}
