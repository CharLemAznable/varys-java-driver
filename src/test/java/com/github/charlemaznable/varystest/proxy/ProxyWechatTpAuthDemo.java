package com.github.charlemaznable.varystest.proxy;

import com.github.charlemaznable.httpclient.common.Header;
import com.github.charlemaznable.httpclient.common.Mapping;
import com.github.charlemaznable.httpclient.common.PathVar;
import com.github.charlemaznable.httpclient.common.RequestBodyRaw;
import com.github.charlemaznable.httpclient.common.RequestMethod;
import com.github.charlemaznable.varys.impl.ProxyWechatTpAuth;

import static com.github.charlemaznable.httpclient.common.HttpMethod.POST;

@ProxyWechatTpAuth
public interface ProxyWechatTpAuthDemo {

    @Mapping("/{codeName}/{authorizerAppId}/wechatTpAuth")
    String wechatTpAuth(@PathVar("codeName") String codeName,
                        @PathVar("authorizerAppId") String authorizerAppId,
                        @Header("a") String a);

    @RequestMethod(POST)
    @Mapping("/{codeName}/{authorizerAppId}/wechatTpAuthParam/{testParam}")
    String wechatTpAuthParam(@PathVar("codeName") String codeName,
                             @PathVar("authorizerAppId") String authorizerAppId,
                             @PathVar("testParam") String testParam,
                             @RequestBodyRaw String requestBodyRaw);
}
