package cn.webdav.common.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "webdav.jwt")
@Data
public class JwtProperties {
    private String userSecret;
    private long expire;

    private String webdavSecret;
}
