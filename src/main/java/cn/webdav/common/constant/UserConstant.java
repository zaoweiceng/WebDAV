package cn.webdav.common.constant;

public class UserConstant {
    public static final String USER_GROUP_ADMIN = "admin";

    public static final String USER_GROUP_DEFALUT = "user";


    public static final String USER_UNLOGIN = "用户未登录";


    public static final String USER_WEBDAV_JWT_HEADER_TYPE = "JWT";

    public static final String USER_WEBDAV_JWT_HEADER_ALGORITHM = "HS256";

    public static final String USER_WEBDAV_JWT_PAYLOAD_ID = "userId";

    public static final String USER_WEBDAV_JWT_PAYLOAD_USERNAME = "username";

    public static final String USER_WEBDAV_JWT_PAYLOAD_SALT = "salt";

    public static final String USER_JWT_HEADER = "token";

    public static final String USER_JWT_INVALIDATED = "登录令牌不合法,无效的token";

    public static final String USER_JWT_EXPIRED = "登录令牌已过期,请重新登录";
}
