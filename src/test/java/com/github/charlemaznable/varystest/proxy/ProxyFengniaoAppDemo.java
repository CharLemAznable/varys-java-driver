package com.github.charlemaznable.varystest.proxy;

import com.github.charlemaznable.core.net.common.Header;
import com.github.charlemaznable.core.net.common.Mapping;
import com.github.charlemaznable.core.net.common.PathVar;
import com.github.charlemaznable.core.net.common.RequestBodyRaw;
import com.github.charlemaznable.core.net.common.RequestMethod;
import com.github.charlemaznable.varys.impl.ProxyFengniaoApp;

import static com.github.charlemaznable.core.net.common.HttpMethod.POST;

@ProxyFengniaoApp
public interface ProxyFengniaoAppDemo {

    @Mapping("/{codeName}/fengniaoApp")
    String fengniaoApp(@PathVar("codeName") String codeName,
                       @Header("a") String a);

    @RequestMethod(POST)
    @Mapping("/{codeName}/fengniaoAppParam/{testParam}")
    String fengniaoAppParam(@PathVar("codeName") String codeName,
                            @PathVar("testParam") String testParam,
                            @RequestBodyRaw String requestBodyRaw);
}
