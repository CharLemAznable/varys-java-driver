package com.github.charlemaznable.varystest.proxy;

import com.github.charlemaznable.core.net.common.Header;
import com.github.charlemaznable.core.net.common.Mapping;
import com.github.charlemaznable.core.net.common.PathVar;
import com.github.charlemaznable.core.net.common.RequestBodyRaw;
import com.github.charlemaznable.core.net.common.RequestMethod;
import com.github.charlemaznable.varys.impl.ProxyShansongAppDeveloper;

import static com.github.charlemaznable.core.net.common.HttpMethod.POST;

@ProxyShansongAppDeveloper
public interface ProxyShansongAppDeveloperDemo {

    @Mapping("/{codeName}/{merchantCode}/shansongApp")
    String shansongApp(@PathVar("codeName") String codeName,
                       @PathVar("merchantCode") String merchantCode,
                       @Header("a") String a);

    @RequestMethod(POST)
    @Mapping("/{codeName}/{merchantCode}/shansongAppParam/{testParam}")
    String shansongAppParam(@PathVar("codeName") String codeName,
                            @PathVar("merchantCode") String merchantCode,
                            @PathVar("testParam") String testParam,
                            @RequestBodyRaw String requestBodyRaw);
}
