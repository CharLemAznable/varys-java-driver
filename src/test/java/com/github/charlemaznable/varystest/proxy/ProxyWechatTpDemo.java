package com.github.charlemaznable.varystest.proxy;

import com.github.charlemaznable.core.net.common.Header;
import com.github.charlemaznable.core.net.common.Mapping;
import com.github.charlemaznable.core.net.common.PathVar;
import com.github.charlemaznable.core.net.common.RequestBodyRaw;
import com.github.charlemaznable.core.net.common.RequestMethod;
import com.github.charlemaznable.varys.impl.ProxyWechatTp;

import static com.github.charlemaznable.core.net.common.HttpMethod.POST;

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
