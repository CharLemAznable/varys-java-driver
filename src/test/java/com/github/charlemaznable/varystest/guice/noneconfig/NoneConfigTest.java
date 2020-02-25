package com.github.charlemaznable.varystest.guice.noneconfig;

import com.github.charlemaznable.varys.guice.VarysInjector;
import com.github.charlemaznable.varys.impl.Query;
import com.github.charlemaznable.varystest.proxy.ProxyAppDemo;
import com.github.charlemaznable.varystest.proxy.ProxyCorpDemo;
import com.github.charlemaznable.varystest.proxy.ProxyError;
import com.google.inject.Guice;
import com.google.inject.Injector;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.n3r.diamond.client.impl.MockDiamondServer;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class NoneConfigTest {

    private static VarysInjector varysInjector;
    private static Injector injector;

    @BeforeAll
    public static void beforeAll() {
        varysInjector = new VarysInjector();
        injector = Guice.createInjector(varysInjector.createModule(
                ProxyAppDemo.class, ProxyCorpDemo.class, ProxyError.class));
        MockDiamondServer.setUpMockServer();
        MockDiamondServer.setConfigInfo("Varys", "default", "");
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
                varysInjector.getClient(Query.class));
    }

    @Test
    public void testNoneConfigProxy() {
        try {
            injector.getInstance(ProxyAppDemo.class);
        } catch (Exception e) {
            assertTrue(e.getCause() instanceof NullPointerException);
        }
        assertThrows(NullPointerException.class, () ->
                varysInjector.getClient(ProxyAppDemo.class));

        try {
            injector.getInstance(ProxyCorpDemo.class);
        } catch (Exception e) {
            assertTrue(e.getCause() instanceof NullPointerException);
        }
        assertThrows(NullPointerException.class, () ->
                varysInjector.getClient(ProxyCorpDemo.class));
    }
}
