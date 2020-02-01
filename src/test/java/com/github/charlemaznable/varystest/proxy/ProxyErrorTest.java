package com.github.charlemaznable.varystest.proxy;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static com.github.charlemaznable.core.net.ohclient.OhFactory.getClient;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
@ExtendWith({SpringExtension.class})
@ContextConfiguration(classes = ProxyErrorConfiguration.class)
public class ProxyErrorTest {

    @Test
    public void testProxyError() {
        assertThrows(IllegalArgumentException.class, () ->
                getClient(ProxyError.class).proxyError("test"));
    }
}
