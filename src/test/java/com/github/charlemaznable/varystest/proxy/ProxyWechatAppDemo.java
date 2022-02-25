package com.github.charlemaznable.varystest.proxy;

import com.github.charlemaznable.httpclient.common.Header;
import com.github.charlemaznable.httpclient.common.Mapping;
import com.github.charlemaznable.httpclient.common.PathVar;
import com.github.charlemaznable.httpclient.common.RequestBodyRaw;
import com.github.charlemaznable.httpclient.common.RequestMethod;
import com.github.charlemaznable.varys.impl.ProxyWechatApp;

import static com.github.charlemaznable.httpclient.common.HttpMethod.POST;

@ProxyWechatApp
public interface ProxyWechatAppDemo {

    @Mapping("/{codeName}/wechatApp")
    String wechatApp(@PathVar("codeName") String codeName,
                     @Header("a") String a);

    @RequestMethod(POST)
    @Mapping("/{codeName}/wechatAppParam/{testParam}")
    String wechatAppParam(@PathVar("codeName") String codeName,
                          @PathVar("testParam") String testParam,
                          @RequestBodyRaw String requestBodyRaw);
}
