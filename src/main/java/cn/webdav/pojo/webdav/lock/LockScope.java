package cn.webdav.pojo.webdav.lock;

import cn.webdav.common.constant.LockConstant;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import io.swagger.annotations.ApiModel;
import lombok.*;


@JsonSerialize
class Exclusive {}
@JsonSerialize
class Shared {}

@Data
@Builder
@Getter
@Setter
@NoArgsConstructor
@JacksonXmlRootElement(localName  = "lockscope", namespace = "DAV:")
public class LockScope {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JacksonXmlProperty(localName = "exclusive", namespace = "DAV:")
    private Exclusive exclusive;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JacksonXmlProperty(localName = "shared", namespace = "DAV:")
    private Shared shared;

    public LockScope(Exclusive exclusive, Shared shared) {
        this.exclusive = exclusive;
        this.shared = shared;
    }

    public static LockScope Exclusive() {
        return new LockScope(new Exclusive(), null);
    }

    public static LockScope Shared() {
        return new LockScope(null, new Shared());
    }
}
