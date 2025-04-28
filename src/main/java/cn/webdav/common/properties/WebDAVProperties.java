package cn.webdav.common.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "webdav")
@Data
public class WebDAVProperties {

    Integer depth;
}
