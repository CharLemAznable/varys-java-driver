package com.github.charlemaznable.varys.api;

import com.github.charlemaznable.core.net.HttpReq;
import com.github.charlemaznable.varys.config.VarysConfig;
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

public class Query {

    private final static String appTokenCachePath = "/query-wechat-app-token/";
    private final static String appAuthorizerTokenCachePath = "/query-wechat-app-authorizer-token/";
    private final static String corpTokenCachePath = "/query-wechat-corp-token/";
    private final static String corpAuthorizerTokenCachePath = "/query-wechat-corp-authorizer-token/";

    private LoadingCache<String, AppTokenResp> appTokenCache;
    private LoadingCache<Pair<String, String>, AppAuthorizerTokenResp> appAuthorizerTokenCache;
    private LoadingCache<String, CorpTokenResp> corpTokenCache;
    private LoadingCache<Pair<String, String>, CorpAuthorizerTokenResp> corpAuthorizerTokenCache;

    public Query(VarysConfig varysConfig) {
        appTokenCache = writeCache(new QueryCacheLoader<String, AppTokenResp>(varysConfig) {
            @Override
            public AppTokenResp load(@Nonnull String codeName) {
                return unJson(httpGet(appTokenCachePath + codeName), AppTokenResp.class);
            }
        }, varysConfig.appTokenCacheDuration(), varysConfig.appTokenCacheTimeUnit());

        appAuthorizerTokenCache = writeCache(new QueryCacheLoader<Pair<String, String>, AppAuthorizerTokenResp>(varysConfig) {
            @Override
            public AppAuthorizerTokenResp load(@Nonnull Pair<String, String> pair) {
                val codeName = pair.getLeft();
                val authorizerAppId = pair.getRight();

                return unJson(httpGet(appAuthorizerTokenCachePath + codeName
                        + "/" + authorizerAppId), AppAuthorizerTokenResp.class);
            }
        }, varysConfig.appAuthorizerTokenCacheDuration(), varysConfig.appAuthorizerTokenCacheTimeUnit());

        corpTokenCache = writeCache(new QueryCacheLoader<String, CorpTokenResp>(varysConfig) {
            @Override
            public CorpTokenResp load(@Nonnull String codeName) {
                return unJson(httpGet(corpTokenCachePath + codeName), CorpTokenResp.class);
            }
        }, varysConfig.corpTokenCacheDuration(), varysConfig.corpTokenCacheTimeUnit());

        corpAuthorizerTokenCache = writeCache(new QueryCacheLoader<Pair<String, String>, CorpAuthorizerTokenResp>(varysConfig) {
            @Override
            public CorpAuthorizerTokenResp load(@Nonnull Pair<String, String> pair) {
                val codeName = pair.getLeft();
                val corpId = pair.getRight();

                return unJson(httpGet(corpAuthorizerTokenCachePath + codeName
                        + "/" + corpId), CorpAuthorizerTokenResp.class);
            }
        }, varysConfig.corpAuthorizerTokenCacheDuration(), varysConfig.corpAuthorizerTokenCacheTimeUnit());
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
        protected final VarysConfig varysConfig;

        protected String httpGet(String subpath) {
            val path = Preconditions.checkNotNull(varysConfig.address()) + subpath;
            return Preconditions.checkNotNull(new HttpReq(path).get());
        }
    }
}
