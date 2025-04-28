package cn.webdav.pojo.webdav;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.*;

@Data
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JacksonXmlRootElement(localName  = "error")
public class Error {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonUnwrapped
    private Object anyError;
}
