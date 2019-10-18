package com.github.charlemaznable.varys.resp;

import lombok.val;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AppAuthorizerTokenRespTest {

    @Test
    public void testAppAuthorizerTokenResp() {
        val resp1 = new AppAuthorizerTokenResp();
        resp1.setAppId("1000");
        resp1.setAuthorizerAppId("abcd");
        resp1.setToken("defaultToken");
        val resp2 = new AppAuthorizerTokenResp();
        resp2.setAppId("1000");
        resp2.setAuthorizerAppId("abcd");
        resp2.setToken("defaultToken");

        assertEquals(resp1.getAppId(), resp2.getAppId());
        assertEquals(resp1.getAuthorizerAppId(), resp2.getAuthorizerAppId());
        assertEquals(resp1.getToken(), resp2.getToken());
    }
}
