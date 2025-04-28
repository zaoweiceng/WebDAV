package cn.webdav.pojo.webdav;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.*;

@Data
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JacksonXmlRootElement(localName  = "resourcetype", namespace = "DAV:")
public class ResourceType {
    @JacksonXmlProperty(localName = "collection", namespace = "DAV:")
    private String collection;
}
