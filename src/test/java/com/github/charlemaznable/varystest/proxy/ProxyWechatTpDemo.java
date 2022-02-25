package com.github.charlemaznable.varystest.proxy;

import com.github.charlemaznable.httpclient.common.Header;
import com.github.charlemaznable.httpclient.common.Mapping;
import com.github.charlemaznable.httpclient.common.PathVar;
import com.github.charlemaznable.httpclient.common.RequestBodyRaw;
import com.github.charlemaznable.httpclient.common.RequestMethod;
import com.github.charlemaznable.varys.impl.ProxyWechatTp;

import static com.github.charlemaznable.httpclient.common.HttpMethod.POST;

@ProxyWechatTp
public interface ProxyWechatTpDemo {

    @Mapping("/{codeName}/wechatTp")
    String wechatTp(@PathVar("codeName") String codeName,
                    @Header("a") String a);

    @RequestMethod(POST)
    @Mapping("/{codeName}/wechatTpParam/{testParam}")
    String wechatTpParam(@PathVar("codeName") String codeName,
                         @PathVar("testParam") String testParam,
                         @RequestBodyRaw String requestBodyRaw);
}
