package com.github.charlemaznable.varystest.proxy;

import com.github.charlemaznable.core.net.common.Header;
import com.github.charlemaznable.core.net.common.Mapping;
import com.github.charlemaznable.core.net.common.PathVar;
import com.github.charlemaznable.core.net.common.RequestBodyRaw;
import com.github.charlemaznable.core.net.common.RequestMethod;
import com.github.charlemaznable.varys.impl.ProxyWechatTpAuth;

import static com.github.charlemaznable.core.net.common.HttpMethod.POST;

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
