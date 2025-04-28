package cn.webdav.pojo.webdav;

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
@JacksonXmlRootElement(localName = "owner")
public class Owner {
    @JsonUnwrapped
    private Href href;
    @JacksonXmlProperty(localName = "name")
    private String name;
}
