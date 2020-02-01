package com.github.charlemaznable.varystest.noneConfig;

import com.github.charlemaznable.varys.Query;
import com.github.charlemaznable.varystest.proxy.ProxyAppDemo;
import com.github.charlemaznable.varystest.proxy.ProxyCorpDemo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static com.github.charlemaznable.core.net.ohclient.OhFactory.getClient;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = NoneConfiguration.class)
public class NoneConfigTest {

    @Test
    public void testNoneConfigQuery() {
        assertThrows(NullPointerException.class, () ->
                getClient(Query.class).appToken("none"));
    }

    @Test
    public void testNoneConfigProxy() {
        assertThrows(NullPointerException.class, () ->
                getClient(ProxyAppDemo.class).wechatApp("none", "b"));
        assertThrows(NullPointerException.class, () ->
                getClient(ProxyCorpDemo.class).wechatCorp("none", "b"));
    }
}
