package com.github.charlemaznable.varys.api;

import com.github.charlemaznable.net.HttpReq;
import com.github.charlemaznable.varys.Config;
import com.github.charlemaznable.varys.resp.AppAuthorizerTokenResp;
import com.github.charlemaznable.varys.resp.AppTokenResp;
import com.github.charlemaznable.varys.resp.CorpAuthorizerTokenResp;
import com.github.charlemaznable.varys.resp.CorpTokenResp;
import com.google.common.base.Preconditions;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.tuple.Pair;

import javax.annotation.Nonnull;

import static com.github.charlemaznable.codec.Json.unJson;
import static com.github.charlemaznable.lang.LoadingCachee.get;
import static com.github.charlemaznable.lang.LoadingCachee.writeCache;

@NoArgsConstructor
public class Query {

    private LoadingCache<String, AppTokenResp> appTokenCache;
    private LoadingCache<Pair<String, String>, AppAuthorizerTokenResp> appAuthorizerTokenCache;
    private LoadingCache<String, CorpTokenResp> corpTokenCache;
    private LoadingCache<Pair<String, String>, CorpAuthorizerTokenResp> corpAuthorizerTokenCache;

    public Query init(Config config) {
        appTokenCache = writeCache(new QueryCacheLoader<String, AppTokenResp>(config) {
            @Override
            public AppTokenResp load(@Nonnull String codeName) {
                return unJson(httpGet("/query-wechat-app-token/" +
                        codeName), AppTokenResp.class);
            }
        }, config.getAppTokenCacheDuration(), config.getAppTokenCacheUnit());

        appAuthorizerTokenCache = writeCache(new QueryCacheLoader<Pair<String, String>, AppAuthorizerTokenResp>(config) {
            @Override
            public AppAuthorizerTokenResp load(@Nonnull Pair<String, String> pair) {
                String codeName = pair.getLeft();
                String authorizerAppId = pair.getRight();

                return unJson(httpGet("/query-wechat-app-authorizer-token/" +
                        codeName + "/" + authorizerAppId), AppAuthorizerTokenResp.class);
            }
        }, config.getAppAuthorizerTokenCacheDuration(), config.getAppAuthorizerTokenCacheUnit());

        corpTokenCache = writeCache(new QueryCacheLoader<String, CorpTokenResp>(config) {
            @Override
            public CorpTokenResp load(@Nonnull String codeName) {
                return unJson(httpGet("/query-wechat-corp-token/" +
                        codeName), CorpTokenResp.class);
            }
        }, config.getCorpTokenCacheDuration(), config.getCorpTokenCacheUnit());

        corpAuthorizerTokenCache = writeCache(new QueryCacheLoader<Pair<String, String>, CorpAuthorizerTokenResp>(config) {
            @Override
            public CorpAuthorizerTokenResp load(@Nonnull Pair<String, String> pair) {
                String codeName = pair.getLeft();
                String corpId = pair.getRight();

                return unJson(httpGet("/query-wechat-corp-authorizer-token/" +
                        codeName + "/" + corpId), CorpAuthorizerTokenResp.class);
            }
        }, config.getCorpAuthorizerTokenCacheDuration(), config.getCorpAuthorizerTokenCacheUnit());

        return this;
    }

    public AppTokenResp appToken(String codeName) {
        Preconditions.checkNotNull(appTokenCache);
        Preconditions.checkNotNull(codeName);
        return get(appTokenCache, codeName);
    }

    public AppAuthorizerTokenResp appAuthorizerToken(String codeName, String authorizerAppId) {
        Preconditions.checkNotNull(appAuthorizerTokenCache);
        Preconditions.checkNotNull(codeName);
        Preconditions.checkNotNull(authorizerAppId);
        return get(appAuthorizerTokenCache, Pair.of(codeName, authorizerAppId));
    }

    public CorpTokenResp corpToken(String codeName) {
        Preconditions.checkNotNull(corpTokenCache);
        Preconditions.checkNotNull(codeName);
        return get(corpTokenCache, codeName);
    }

    public CorpAuthorizerTokenResp corpAuthorizerToken(String codeName, String corpId) {
        Preconditions.checkNotNull(corpAuthorizerTokenCache);
        Preconditions.checkNotNull(codeName);
        Preconditions.checkNotNull(corpId);
        return get(corpAuthorizerTokenCache, Pair.of(codeName, corpId));
    }

    @AllArgsConstructor
    private static abstract class QueryCacheLoader<K, V> extends CacheLoader<K, V> {
        protected final Config config;

        protected String httpGet(String subpath) {
            return Preconditions.checkNotNull(new HttpReq(config.getAddress() + subpath).get());
        }
    }
}
