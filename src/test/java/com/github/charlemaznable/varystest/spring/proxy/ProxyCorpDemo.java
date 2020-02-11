package com.github.charlemaznable.varystest.spring.proxy;

import com.github.charlemaznable.core.net.common.Mapping;
import com.github.charlemaznable.core.net.common.Parameter;
import com.github.charlemaznable.core.net.common.PathVar;
import com.github.charlemaznable.core.net.common.RequestMethod;
import com.github.charlemaznable.varys.spring.ProxyCorp;

import static com.github.charlemaznable.core.net.common.HttpMethod.POST;

@ProxyCorp
public interface ProxyCorpDemo {

    @Mapping("/{codeName}/wechatCorp")
    String wechatCorp(@PathVar("codeName") String codeName,
                      @Parameter("a") String a);

    @RequestMethod(POST)
    @Mapping("/{codeName}/wechatCorpParam/{testParam}")
    String wechatCorpParam(@PathVar("codeName") String codeName,
                           @PathVar("testParam") String testParam,
                           @Parameter("a") String a);
}
