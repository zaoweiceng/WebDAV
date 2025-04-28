package cn.webdav.common.constant;

public class MediaTypeConstant {
    private MediaTypeConstant() {
        // 防止实例化
    }

    // 通用字符集参数
    public static final String CHARSET_UTF8 = ";charset=utf-8";

    // 文本类型 - 不带字符集
    public static final String TEXT_PLAIN = "text/plain";
    public static final String TEXT_HTML = "text/html";
    public static final String TEXT_CSS = "text/css";
    public static final String TEXT_JAVASCRIPT = "text/javascript";
    public static final String TEXT_XML = "text/xml";
    public static final String TEXT_CSV = "text/csv";

    // 文本类型 - 带UTF-8字符集
    public static final String TEXT_PLAIN_UTF8 = TEXT_PLAIN + CHARSET_UTF8;
    public static final String TEXT_HTML_UTF8 = TEXT_HTML + CHARSET_UTF8;
    public static final String TEXT_CSS_UTF8 = TEXT_CSS + CHARSET_UTF8;
    public static final String TEXT_JAVASCRIPT_UTF8 = TEXT_JAVASCRIPT + CHARSET_UTF8;
    public static final String TEXT_XML_UTF8 = TEXT_XML + CHARSET_UTF8;
    public static final String TEXT_CSV_UTF8 = TEXT_CSV + CHARSET_UTF8;

    // 应用程序类型 - 可能需要字符集的
    public static final String APPLICATION_JSON = "application/json";
    public static final String APPLICATION_XML = "application/xml";
    public static final String APPLICATION_JSON_UTF8 = APPLICATION_JSON + CHARSET_UTF8;
    public static final String APPLICATION_XML_UTF8 = APPLICATION_XML + CHARSET_UTF8;

    // 不需要字符集的类型
    public static final String IMAGE_JPEG = "image/jpeg";
    public static final String IMAGE_PNG = "image/png";
    public static final String IMAGE_GIF = "image/gif";
    public static final String AUDIO_MPEG = "audio/mpeg";
    public static final String VIDEO_MP4 = "video/mp4";
    public static final String APPLICATION_PDF = "application/pdf";
    public static final String APPLICATION_ZIP = "application/zip";
    public static final String APPLICATION_OCTET_STREAM = "application/octet-stream";

    // 多部分类型
    public static final String MULTIPART_FORM_DATA = "multipart/form-data";
}
