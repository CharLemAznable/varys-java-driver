package com.github.charlemaznable.varys.noneConfig;

import com.github.charlemaznable.varys.spring.Varys;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = NoneConfiguration.class)
public class NoneConfigTest {

    @Autowired
    private Varys varys;

    @Test
    public void testNoneConfigQuery() {
        assertThrows(NullPointerException.class, () ->
                varys.query().appToken("none"));
    }

    @Test
    public void testNoneConfigProxy() {
        assertThrows(NullPointerException.class, () ->
                varys.proxy().wechatApp("none", "/wechatApp").get());
    }
}
