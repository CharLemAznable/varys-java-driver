package com.github.charlemaznable.varys.resp;

import lombok.val;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CorpTokenRespTest {

    @Test
    public void testCorpTokenResp() {
        val resp1 = new CorpTokenResp();
        resp1.setCorpId("10000");
        resp1.setToken("defaultToken");
        val resp2 = new CorpTokenResp();
        resp2.setCorpId("10000");
        resp2.setToken("defaultToken");

        assertEquals(resp1.toString(), resp2.toString());
        assertEquals(resp1, resp2);
        assertEquals(resp1.hashCode(), resp2.hashCode());
    }
}
