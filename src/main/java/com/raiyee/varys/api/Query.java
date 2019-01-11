package com.raiyee.varys.api;

import com.github.charlemaznable.net.HttpReq;
import com.google.common.base.Preconditions;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.raiyee.varys.Config;
import com.raiyee.varys.resp.AppAuthorizerTokenResp;
import com.raiyee.varys.resp.AppTokenResp;
import com.raiyee.varys.resp.CorpAuthorizerTokenResp;
import com.raiyee.varys.resp.CorpTokenResp;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.tuple.Pair;

import javax.annotation.Nonnull;

import static com.github.charlemaznable.codec.Json.unJson;
import static com.github.charlemaznable.lang.LoadingCachee.get;

public class Query {

    private LoadingCache<String, AppTokenResp> appTokenCache;
    private LoadingCache<Pair<String, String>, AppAuthorizerTokenResp> appAuthorizerTokenCache;
    private LoadingCache<String, CorpTokenResp> corpTokenCache;
    private LoadingCache<Pair<String, String>, CorpAuthorizerTokenResp> corpAuthorizerTokenCache;

    public Query(Config config) {
        CacheBuilder<Object, Object> builder = CacheBuilder.newBuilder();

        this.appTokenCache = builder.expireAfterWrite
                (config.getAppTokenCacheDuration(), config.getAppTokenCacheUnit())
                .build(new QueryCacheLoader<String, AppTokenResp>(config) {
                    @Override
                    public AppTokenResp load(@Nonnull String codeName) {
                        return unJson(httpGet("/query-wechat-app-token/" +
                                codeName), AppTokenResp.class);
                    }
                });

        this.appAuthorizerTokenCache = builder.expireAfterWrite
                (config.getAppAuthorizerTokenCacheDuration(), config.getAppAuthorizerTokenCacheUnit())
                .build(new QueryCacheLoader<Pair<String, String>, AppAuthorizerTokenResp>(config) {
                    @Override
                    public AppAuthorizerTokenResp load(@Nonnull Pair<String, String> pair) {
                        String codeName = pair.getLeft();
                        String authorizerAppId = pair.getRight();

                        return unJson(httpGet("/query-wechat-app-authorizer-token/" +
                                codeName + "/" + authorizerAppId), AppAuthorizerTokenResp.class);
                    }
                });

        this.corpTokenCache = builder.expireAfterWrite
                (config.getCorpTokenCacheDuration(), config.getCorpTokenCacheUnit())
                .build(new QueryCacheLoader<String, CorpTokenResp>(config) {
                    @Override
                    public CorpTokenResp load(@Nonnull String codeName) {
                        return unJson(httpGet("/query-wechat-corp-token/" +
                                codeName), CorpTokenResp.class);
                    }
                });

        this.corpAuthorizerTokenCache = builder.expireAfterWrite
                (config.getCorpAuthorizerTokenCacheDuration(), config.getCorpAuthorizerTokenCacheUnit())
                .build(new QueryCacheLoader<Pair<String, String>, CorpAuthorizerTokenResp>(config) {
                    @Override
                    public CorpAuthorizerTokenResp load(@Nonnull Pair<String, String> pair) {
                        String codeName = pair.getLeft();
                        String corpId = pair.getRight();

                        return unJson(httpGet("/query-wechat-corp-authorizer-token/" +
                                codeName + "/" + corpId), CorpAuthorizerTokenResp.class);
                    }
                });
    }

    public AppTokenResp appToken(String codeName) {
        Preconditions.checkNotNull(codeName);
        return get(this.appTokenCache, codeName);
    }

    public AppAuthorizerTokenResp appAuthorizerToken(String codeName, String authorizerAppId) {
        Preconditions.checkNotNull(codeName);
        Preconditions.checkNotNull(authorizerAppId);
        return get(this.appAuthorizerTokenCache, Pair.of(codeName, authorizerAppId));
    }

    public CorpTokenResp corpToken(String codeName) {
        Preconditions.checkNotNull(codeName);
        return get(this.corpTokenCache, codeName);
    }

    public CorpAuthorizerTokenResp corpAuthorizerToken(String codeName, String corpId) {
        Preconditions.checkNotNull(codeName);
        Preconditions.checkNotNull(corpId);
        return get(this.corpAuthorizerTokenCache, Pair.of(codeName, corpId));
    }

    @AllArgsConstructor
    private static abstract
    class QueryCacheLoader<K, V> extends CacheLoader<K, V> {
        protected final Config config;

        protected String httpGet(String subpath) {
            return new HttpReq(config.getAddress() + subpath).get();
        }
    }
}
