package cn.webdav.pojo.webdav.lock;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.*;

@Data
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JacksonXmlRootElement(localName  = "supportedlock", namespace = "DAV:")
public class SupportedLock {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JacksonXmlProperty(localName = "lockdiscovery", namespace = "DAV:")
    private LockDiscovery lockdiscovery;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JacksonXmlProperty(localName = "lockentry", namespace = "DAV:")
    private LockEntry lockentry;
}
