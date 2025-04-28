package cn.webdav.pojo.webdav;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.*;

@Data
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JacksonXmlRootElement(localName  = "response", namespace = "DAV:")
public class Response {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonUnwrapped
    @JacksonXmlProperty(localName = "href", namespace = "DAV:")
    private Href href;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JacksonXmlProperty(localName = "propstat", namespace = "DAV:")
    private PropStat propstat;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonUnwrapped
    @JacksonXmlProperty(localName = "status", namespace = "DAV:")
    private Status status;
}
