package cn.webdav.pojo.webdav;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.*;

@Data
@Builder
@Getter
@Setter
@NoArgsConstructor
@JacksonXmlRootElement(localName  = "propname")
public class PropName {
}
