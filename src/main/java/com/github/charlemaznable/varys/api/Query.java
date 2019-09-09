package com.github.charlemaznable.varys.api;

import com.github.charlemaznable.core.net.HttpReq;
import com.github.charlemaznable.varys.config.Config;
import com.github.charlemaznable.varys.resp.AppAuthorizerTokenResp;
import com.github.charlemaznable.varys.resp.AppTokenResp;
import com.github.charlemaznable.varys.resp.CorpAuthorizerTokenResp;
import com.github.charlemaznable.varys.resp.CorpTokenResp;
import com.google.common.base.Preconditions;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import lombok.AllArgsConstructor;
import lombok.val;
import org.apache.commons.lang3.tuple.Pair;

import javax.annotation.Nonnull;

import static com.github.charlemaznable.core.codec.Json.unJson;
import static com.github.charlemaznable.core.lang.LoadingCachee.get;
import static com.github.charlemaznable.core.lang.LoadingCachee.writeCache;
import static com.github.charlemaznable.varys.mock.MockVarysServer.getVarysResponse;
import static com.github.charlemaznable.varys.mock.MockVarysServer.isTestMode;

public class Query {

    private final static String appTokenCachePath = "/query-wechat-app-token/";
    private final static String appAuthorizerTokenCachePath = "/query-wechat-app-authorizer-token/";
    private final static String corpTokenCachePath = "/query-wechat-corp-token/";
    private final static String corpAuthorizerTokenCachePath = "/query-wechat-corp-authorizer-token/";

    private LoadingCache<String, AppTokenResp> appTokenCache;
    private LoadingCache<Pair<String, String>, AppAuthorizerTokenResp> appAuthorizerTokenCache;
    private LoadingCache<String, CorpTokenResp> corpTokenCache;
    private LoadingCache<Pair<String, String>, CorpAuthorizerTokenResp> corpAuthorizerTokenCache;

    public Query(Config config) {
        appTokenCache = writeCache(new QueryCacheLoader<String, AppTokenResp>(config) {
            @Override
            public AppTokenResp load(@Nonnull String codeName) {
                return unJson(httpGet(appTokenCachePath + codeName), AppTokenResp.class);
            }
        }, config.appTokenCacheDuration(), config.appTokenCacheTimeUnit());

        appAuthorizerTokenCache = writeCache(new QueryCacheLoader<Pair<String, String>, AppAuthorizerTokenResp>(config) {
            @Override
            public AppAuthorizerTokenResp load(@Nonnull Pair<String, String> pair) {
                val codeName = pair.getLeft();
                val authorizerAppId = pair.getRight();

                return unJson(httpGet(appAuthorizerTokenCachePath + codeName
                        + "/" + authorizerAppId), AppAuthorizerTokenResp.class);
            }
        }, config.appAuthorizerTokenCacheDuration(), config.appAuthorizerTokenCacheTimeUnit());

        corpTokenCache = writeCache(new QueryCacheLoader<String, CorpTokenResp>(config) {
            @Override
            public CorpTokenResp load(@Nonnull String codeName) {
                return unJson(httpGet(corpTokenCachePath + codeName), CorpTokenResp.class);
            }
        }, config.corpTokenCacheDuration(), config.corpTokenCacheTimeUnit());

        corpAuthorizerTokenCache = writeCache(new QueryCacheLoader<Pair<String, String>, CorpAuthorizerTokenResp>(config) {
            @Override
            public CorpAuthorizerTokenResp load(@Nonnull Pair<String, String> pair) {
                val codeName = pair.getLeft();
                val corpId = pair.getRight();

                return unJson(httpGet(corpAuthorizerTokenCachePath + codeName
                        + "/" + corpId), CorpAuthorizerTokenResp.class);
            }
        }, config.corpAuthorizerTokenCacheDuration(), config.corpAuthorizerTokenCacheTimeUnit());
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
            String path = Preconditions.checkNotNull(config.address()) + subpath;
            if (isTestMode()) {
                return Preconditions.checkNotNull(getVarysResponse(path));
            }
            return Preconditions.checkNotNull(new HttpReq(path).get());
        }
    }
}
