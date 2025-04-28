package cn.webdav.pojo.webdav;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import io.swagger.annotations.ApiModel;
import lombok.*;

import java.util.List;

@Data
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JacksonXmlRootElement(localName = "multistatus", namespace = "DAV:")
public class MultiStatus {

    @JacksonXmlElementWrapper(useWrapping = false, localName = "response", namespace = "DAV:")
    @JacksonXmlProperty(localName = "response", namespace = "DAV:")
    private List<Response> response;
}
