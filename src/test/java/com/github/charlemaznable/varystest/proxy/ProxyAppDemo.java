package com.github.charlemaznable.varystest.proxy;

import com.github.charlemaznable.core.net.ohclient.OhMapping;
import com.github.charlemaznable.core.net.ohclient.config.OhConfigRequestMethod;
import com.github.charlemaznable.core.net.ohclient.param.OhHeader;
import com.github.charlemaznable.core.net.ohclient.param.OhPathVar;
import com.github.charlemaznable.core.net.ohclient.param.OhRequestBodyRaw;
import com.github.charlemaznable.varys.ProxyApp;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

@ProxyApp
public interface ProxyAppDemo {

    @OhMapping("/{codeName}/wechatApp")
    String wechatApp(@OhPathVar("codeName") String codeName,
                     @OhHeader("a") String a);

    @OhConfigRequestMethod(POST)
    @OhMapping("/{codeName}/wechatAppParam/{testParam}")
    String wechatAppParam(@OhPathVar("codeName") String codeName,
                          @OhPathVar("testParam") String testParam,
                          @OhRequestBodyRaw String requestBodyRaw);
}
