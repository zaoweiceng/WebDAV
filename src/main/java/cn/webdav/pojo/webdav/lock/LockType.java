package cn.webdav.pojo.webdav.lock;

import cn.webdav.common.constant.LockConstant;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.*;


@Data
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JacksonXmlRootElement(localName  = "locktype", namespace = "DAV:")
public class LockType {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JacksonXmlProperty(localName = "write", namespace = "DAV:")
    private Write write;

    public static LockType Write(){
        return new LockType(Write.builder().build());
    }
}
