package com.github.charlemaznable.varystest.proxy;

import com.github.charlemaznable.core.net.ohclient.OhMapping;
import com.github.charlemaznable.core.net.ohclient.config.OhConfigRequestMethod;
import com.github.charlemaznable.core.net.ohclient.param.OhParameter;
import com.github.charlemaznable.core.net.ohclient.param.OhPathVar;
import com.github.charlemaznable.varys.ProxyCorp;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

@ProxyCorp
public interface ProxyCorpDemo {

    @OhMapping("/{codeName}/wechatCorp")
    String wechatCorp(@OhPathVar("codeName") String codeName,
                      @OhParameter("a") String a);

    @OhConfigRequestMethod(POST)
    @OhMapping("/{codeName}/wechatCorpParam/{testParam}")
    String wechatCorpParam(@OhPathVar("codeName") String codeName,
                           @OhPathVar("testParam") String testParam,
                           @OhParameter("a") String a);
}
