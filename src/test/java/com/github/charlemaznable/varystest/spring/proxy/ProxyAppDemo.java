package com.github.charlemaznable.varystest.spring.proxy;

import com.github.charlemaznable.core.net.common.Header;
import com.github.charlemaznable.core.net.common.Mapping;
import com.github.charlemaznable.core.net.common.PathVar;
import com.github.charlemaznable.core.net.common.RequestBodyRaw;
import com.github.charlemaznable.core.net.common.RequestMethod;
import com.github.charlemaznable.varys.spring.ProxyApp;

import static com.github.charlemaznable.core.net.common.HttpMethod.POST;

@ProxyApp
public interface ProxyAppDemo {

    @Mapping("/{codeName}/wechatApp")
    String wechatApp(@PathVar("codeName") String codeName,
                     @Header("a") String a);

    @RequestMethod(POST)
    @Mapping("/{codeName}/wechatAppParam/{testParam}")
    String wechatAppParam(@PathVar("codeName") String codeName,
                          @PathVar("testParam") String testParam,
                          @RequestBodyRaw String requestBodyRaw);
}
