package cn.webdav.pojo.webdav.lock;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.*;

@Data
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JacksonXmlRootElement(localName  = "lockentry", namespace = "DAV:")
public class LockEntry {
    @JacksonXmlProperty(localName = "lockscope", namespace = "DAV:")
    private LockScope lockscope;

    @JacksonXmlProperty(localName = "locktype", namespace = "DAV:")
    private LockType locktype;

}
