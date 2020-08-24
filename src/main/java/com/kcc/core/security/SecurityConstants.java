package com.kcc.core.security;

public final class SecurityConstants {
    public static final String TOKEN_USERNAME_HEADER = "UsernameAuthorization";
    public static final String TOKEN_HEADER = "Authorization";
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String TOKEN_TYPE = "JWT";

//    public static final String HEADER_AUTHORIZATION = "Authorization";
//    public static final String HEADER_AUTHORIZATION_BEARER = "Bearer ";

    public static final String AUTH_LOGIN_URL = "/api/token";

    // 声明
    private static final String CLAIM_KEY_USERNAME = "sub";
    private static final String CLAIM_KEY_ID = "id";
    private static final String CLAIM_KEY_CREATED = "created";
    private static final String CLAIM_KEY_ROLES = "roles";

    private SecurityConstants() {
        throw new IllegalStateException("不能创建静态工具类");
//        throw new IllegalStateException("Cannot create instance of static util class");
    }
}
