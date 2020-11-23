package com.github.charlemaznable.varystest.guice.interfacenoneconfig;

import com.github.charlemaznable.varys.guice.VarysModular;
import com.github.charlemaznable.varys.impl.Query;
import com.github.charlemaznable.varystest.proxy.ProxyFengniaoAppDemo;
import com.github.charlemaznable.varystest.proxy.ProxyWechatAppDemo;
import com.github.charlemaznable.varystest.proxy.ProxyWechatCorpDemo;
import com.github.charlemaznable.varystest.proxy.ProxyWechatTpAuthDemo;
import com.github.charlemaznable.varystest.proxy.ProxyWechatTpDemo;
import com.google.inject.Guice;
import com.google.inject.Injector;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.n3r.diamond.client.impl.MockDiamondServer;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class InterfaceNoneConfigTest {

    private static VarysModular varysModular;
    private static Injector injector;

    @BeforeAll
    public static void beforeAll() {
        varysModular = new VarysModular(InterfaceNoneConfig.class);
        injector = Guice.createInjector(varysModular.bindOtherClients(
                ProxyWechatAppDemo.class, ProxyWechatTpDemo.class, ProxyWechatTpAuthDemo.class,
                ProxyWechatCorpDemo.class, ProxyFengniaoAppDemo.class).createModule());
        MockDiamondServer.setUpMockServer();
    }

    @AfterAll
    public static void afterAll() {
        MockDiamondServer.tearDownMockServer();
    }

    @Test
    public void testNoneConfigQuery() {
        try {
            injector.getInstance(Query.class);
        } catch (Exception e) {
            assertTrue(e.getCause() instanceof NullPointerException);
        }
        assertThrows(NullPointerException.class, () ->
                varysModular.getClient(Query.class));
    }

    @SuppressWarnings("Duplicates")
    @Test
    public void testNoneConfigProxy() {
        try {
            injector.getInstance(ProxyWechatAppDemo.class);
        } catch (Exception e) {
            assertTrue(e.getCause() instanceof NullPointerException);
        }
        assertThrows(NullPointerException.class, () ->
                varysModular.getClient(ProxyWechatAppDemo.class));

        try {
            injector.getInstance(ProxyWechatTpDemo.class);
        } catch (Exception e) {
            assertTrue(e.getCause() instanceof NullPointerException);
        }
        assertThrows(NullPointerException.class, () ->
                varysModular.getClient(ProxyWechatTpDemo.class));

        try {
            injector.getInstance(ProxyWechatTpAuthDemo.class);
        } catch (Exception e) {
            assertTrue(e.getCause() instanceof NullPointerException);
        }
        assertThrows(NullPointerException.class, () ->
                varysModular.getClient(ProxyWechatTpAuthDemo.class));

        try {
            injector.getInstance(ProxyWechatCorpDemo.class);
        } catch (Exception e) {
            assertTrue(e.getCause() instanceof NullPointerException);
        }
        assertThrows(NullPointerException.class, () ->
                varysModular.getClient(ProxyWechatCorpDemo.class));

        try {
            injector.getInstance(ProxyFengniaoAppDemo.class);
        } catch (Exception e) {
            assertTrue(e.getCause() instanceof NullPointerException);
        }
        assertThrows(NullPointerException.class, () ->
                varysModular.getClient(ProxyFengniaoAppDemo.class));
    }
}
