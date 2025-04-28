package cn.webdav.pojo.webdav;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.*;

@Data
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JacksonXmlRootElement(localName = "propfind")
public class PropFind {

    private Allprop allprop;

    private Include include;
}
