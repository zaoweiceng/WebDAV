package cn.webdav.common.constant;

public class WebDAVMethodsConstant {
    private WebDAVMethodsConstant() {
        // 防止实例化
    }

    public static final String GET = "GET";
    public static final String HEAD = "HEAD";
    public static final String POST = "POST";
    public static final String PUT = "PUT";
    public static final String DELETE = "DELETE";
    public static final String CONNECT = "CONNECT";
    public static final String OPTIONS = "OPTIONS";
    public static final String TRACE = "TRACE";
    public static final String PATCH = "PATCH";

    public static final String PROPFIND = "PROPFIND";
    public static final String PROPPATCH = "PROPPATCH";
    public static final String MKCOL = "MKCOL";
    public static final String COPY = "COPY";
    public static final String MOVE = "MOVE";
    public static final String LOCK = "LOCK";
    public static final String UNLOCK = "UNLOCK";

    public static boolean isWebDAVMethod(String method) {
        if (method == null) {
            return false;
        }
        return method.equals(PROPFIND) || method.equals(PROPPATCH) ||
                method.equals(MKCOL) || method.equals(COPY) ||
                method.equals(MOVE) || method.equals(LOCK) ||
                method.equals(UNLOCK);
    }

    /**
     * 检查是否是有效的HTTP方法
     * @param method HTTP方法
     * @return 如果是有效方法返回true
     */
    public static boolean isValidMethod(String method) {
        if (method == null) {
            return false;
        }
        return method.equals(GET) || method.equals(HEAD) ||
                method.equals(POST) || method.equals(PUT) ||
                method.equals(DELETE) || method.equals(CONNECT) ||
                method.equals(OPTIONS) || method.equals(TRACE) ||
                method.equals(PATCH) || isWebDAVMethod(method);
    }
}
