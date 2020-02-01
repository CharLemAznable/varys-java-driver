package com.github.charlemaznable.varystest.proxy;

import com.github.charlemaznable.core.net.ohclient.OhClient;
import com.github.charlemaznable.core.net.ohclient.OhMapping;
import com.github.charlemaznable.core.net.ohclient.config.OhConfigRequestMethod;
import com.github.charlemaznable.core.net.ohclient.param.OhHeader;
import com.github.charlemaznable.core.net.ohclient.param.OhParameter;
import com.github.charlemaznable.core.net.ohclient.param.OhPathVar;
import com.github.charlemaznable.core.net.ohclient.param.OhRequestBodyRaw;
import com.github.charlemaznable.varys.VarysMapping;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

@OhClient
@VarysMapping
public interface ProxyDemo {

    @OhMapping("/{proxyWechatApp}/{codeName}/wechatApp")
    String wechatApp(@OhPathVar("codeName") String codeName,
                     @OhHeader("a") String a);

    @OhConfigRequestMethod(POST)
    @OhMapping("/{proxyWechatApp}/{codeName}/wechatAppParam/{testParam}")
    String wechatAppParam(@OhPathVar("codeName") String codeName,
                          @OhPathVar("testParam") String testParam,
                          @OhRequestBodyRaw String requestBodyRaw);

    @OhMapping("/{proxyWechatCorp}/{codeName}/wechatCorp")
    String wechatCorp(@OhPathVar("codeName") String codeName,
                      @OhParameter("a") String a);

    @OhConfigRequestMethod(POST)
    @OhMapping("/{proxyWechatCorp}/{codeName}/wechatCorpParam/{testParam}")
    String wechatCorpParam(@OhPathVar("codeName") String codeName,
                           @OhPathVar("testParam") String testParam,
                           @OhParameter("a") String a);
}
