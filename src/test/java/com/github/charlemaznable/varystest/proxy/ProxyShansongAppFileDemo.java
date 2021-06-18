package com.github.charlemaznable.varystest.proxy;

import com.github.charlemaznable.core.net.common.Header;
import com.github.charlemaznable.core.net.common.Mapping;
import com.github.charlemaznable.core.net.common.PathVar;
import com.github.charlemaznable.core.net.common.RequestBodyRaw;
import com.github.charlemaznable.core.net.common.RequestMethod;
import com.github.charlemaznable.varys.impl.ProxyShansongAppFile;

import static com.github.charlemaznable.core.net.common.HttpMethod.POST;

@ProxyShansongAppFile
public interface ProxyShansongAppFileDemo {

    @Mapping("/{codeName}/shansongApp")
    String shansongApp(@PathVar("codeName") String codeName,
                       @Header("a") String a);

    @RequestMethod(POST)
    @Mapping("/{codeName}/shansongAppParam/{testParam}")
    String shansongAppParam(@PathVar("codeName") String codeName,
                            @PathVar("testParam") String testParam,
                            @RequestBodyRaw String requestBodyRaw);
}
