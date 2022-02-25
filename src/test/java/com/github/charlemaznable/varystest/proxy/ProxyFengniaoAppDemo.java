package com.github.charlemaznable.varystest.proxy;

import com.github.charlemaznable.httpclient.common.Header;
import com.github.charlemaznable.httpclient.common.Mapping;
import com.github.charlemaznable.httpclient.common.PathVar;
import com.github.charlemaznable.httpclient.common.RequestBodyRaw;
import com.github.charlemaznable.httpclient.common.RequestMethod;
import com.github.charlemaznable.varys.impl.ProxyFengniaoApp;

import static com.github.charlemaznable.httpclient.common.HttpMethod.POST;

@ProxyFengniaoApp
public interface ProxyFengniaoAppDemo {

    @Mapping("/{codeName}/{merchantId}/fengniaoApp")
    String fengniaoApp(@PathVar("codeName") String codeName,
                       @PathVar("merchantId") String merchantId,
                       @Header("a") String a);

    @RequestMethod(POST)
    @Mapping("/{codeName}/{merchantId}/fengniaoAppParam/{testParam}")
    String fengniaoAppParam(@PathVar("codeName") String codeName,
                            @PathVar("merchantId") String merchantId,
                            @PathVar("testParam") String testParam,
                            @RequestBodyRaw String requestBodyRaw);
}
