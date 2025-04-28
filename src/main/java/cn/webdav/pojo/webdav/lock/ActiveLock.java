package cn.webdav.pojo.webdav.lock;

import cn.webdav.pojo.webdav.Href;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlText;
import io.swagger.annotations.ApiModel;
import lombok.*;


@Data
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "ActiveLock XML的实体类")
@JacksonXmlRootElement(localName  = "activeLock", namespace = "DAV:")
public class ActiveLock {

    @JacksonXmlProperty(localName = "lockscope", namespace = "DAV:")
    private LockScope lockscope;

    @JacksonXmlProperty(localName = "locktype", namespace = "DAV:")
    private LockType locktype;

    @JacksonXmlProperty(localName = "depth", namespace = "DAV:")
    private String depth;

    @JacksonXmlProperty(localName = "timeout", namespace = "DAV:")
    private String timeout;

    @JacksonXmlProperty(localName = "locktoken", namespace = "DAV:")
    private Href locktoken;

    @JacksonXmlProperty(localName = "lockroot", namespace = "DAV:")
    private Href lockroot;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JacksonXmlProperty(localName = "owner", namespace = "DAV:")
    private String owner;
}
