package cn.webdav.pojo.webdav;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.*;

@Data
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JacksonXmlRootElement(localName  = "propstat", namespace = "DAV:")
public class PropStat {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JacksonXmlProperty(localName = "prop", namespace = "DAV:")
    private Prop prop;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonUnwrapped
    @JacksonXmlProperty(localName = "status", namespace = "DAV:")
    private Status status;
}
