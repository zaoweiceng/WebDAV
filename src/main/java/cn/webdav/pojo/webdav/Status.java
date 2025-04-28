package cn.webdav.pojo.webdav;

import cn.webdav.common.constant.StatusConstant;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@JacksonXmlRootElement(localName  = "status", namespace = "DAV:")
public class Status {

//    @JsonUnwrapped
    @JacksonXmlProperty(localName = "status", namespace = "DAV:")
    private String status;

    private Status(){}
    public Status(int status) {
        this.status = StatusConstant.getStatusLine(status);
    }

    public static Status of(int status){
        return new Status(status);
    }
}
