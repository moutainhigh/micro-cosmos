package com.yichen.cosmos.cloud.platform.dto;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;

/**
 * 移动端认证授权返回对象
 * Created by Linq on 2017/3/24 17:12.
 */
public class AccessTokenDto implements Serializable {
    private static final long serialVersionUID = -3097182691507909117L;

    public AccessTokenDto() {
    }

    public AccessTokenDto(String accessToken, String tokenType, String refreshToken, long expiresIn) {
        this.accessToken = accessToken;
        this.tokenType = tokenType;
        this.refreshToken = refreshToken;
        this.expiresIn = expiresIn;
    }

    /**
     * 生成的token
     */
    @JSONField(name = "access_token")
    private String accessToken;

    /**
     * token的类型
     */
    @JSONField(name = "token_type")
    private String tokenType;

    /**
     * 刷新token时候用到的token
     */
    @JSONField(name = "refresh_token")
    private String refreshToken;

    /**
     * 过期时间
     */
    @JSONField(name = "expires_in")
    private long expiresIn;

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getTokenType() {
        return tokenType;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public long getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(long expiresIn) {
        this.expiresIn = expiresIn;
    }
}
