package com.github.charlemaznable.varystest.proxy;

import com.github.charlemaznable.httpclient.common.Mapping;
import com.github.charlemaznable.httpclient.common.Parameter;
import com.github.charlemaznable.httpclient.common.PathVar;
import com.github.charlemaznable.httpclient.common.RequestMethod;
import com.github.charlemaznable.varys.impl.ProxyWechatCorp;

import static com.github.charlemaznable.httpclient.common.HttpMethod.POST;

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
