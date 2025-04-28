package cn.webdav.pojo.webdav;

import cn.hutool.core.date.DateTime;
import cn.webdav.pojo.webdav.lock.ActiveLock;
import cn.webdav.pojo.webdav.lock.LockDiscovery;
import cn.webdav.pojo.webdav.lock.SupportedLock;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlText;
import lombok.*;

@Data
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JacksonXmlRootElement(localName = "prop", namespace = "DAV:")
public class Prop {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JacksonXmlProperty(localName = "displayname", namespace = "DAV:")
    private String displayName;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JacksonXmlProperty(localName = "getcontentlength", namespace = "DAV:")
    private Long getcontentlength;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JacksonXmlProperty(localName = "author", namespace = "DAV:")
    private String author;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JacksonXmlProperty(localName = "creationdate", namespace = "DAV:")
    private String creationdate;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JacksonXmlProperty(localName = "getcontentlanguage", namespace = "DAV:")
    private String getcontentlanguage;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JacksonXmlProperty(localName = "getcontenttype", namespace = "DAV:")
    private String getcontenttype;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JacksonXmlProperty(localName = "getetag", namespace = "DAV:")
    private String getetag;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JacksonXmlProperty(localName = "getlastmodified", namespace = "DAV:")
    private String getlastmodified;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JacksonXmlProperty(localName = "lockdiscovery", namespace = "DAV:")
    private LockDiscovery lockdiscovery;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JacksonXmlProperty(localName = "resourcetype", namespace = "DAV:")
    private ResourceType resourcetype;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JacksonXmlProperty(localName = "supportedlock", namespace = "DAV:")
    private SupportedLock supportedlock;

}
