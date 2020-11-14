package com.github.charlemaznable.varystest.proxy;

import com.github.charlemaznable.core.net.common.Mapping;
import com.github.charlemaznable.core.net.common.Parameter;
import com.github.charlemaznable.core.net.common.PathVar;
import com.github.charlemaznable.core.net.common.RequestMethod;
import com.github.charlemaznable.varys.impl.ProxyWechatCorp;

import static com.github.charlemaznable.core.net.common.HttpMethod.POST;

@ProxyWechatCorp
public interface ProxyWechatCorpDemo {

    @Mapping("/{codeName}/wechatCorp")
    String wechatCorp(@PathVar("codeName") String codeName,
                      @Parameter("a") String a);

    @RequestMethod(POST)
    @Mapping("/{codeName}/wechatCorpParam/{testParam}")
    String wechatCorpParam(@PathVar("codeName") String codeName,
                           @PathVar("testParam") String testParam,
                           @Parameter("a") String a);
}
