package cn.webdav.pojo.webdav;

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
@ApiModel(description = "Href XML的实体类")
@JacksonXmlRootElement(localName = "href", namespace = "DAV:")
public class Href {
    @JacksonXmlProperty(localName = "href", namespace = "DAV:")
    private String href;

    public static Href of(String href){
        return Href.builder().href(href).build();
    }
}
