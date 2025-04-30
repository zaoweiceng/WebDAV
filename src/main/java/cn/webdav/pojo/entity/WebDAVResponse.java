package cn.webdav.pojo.entity;

import io.swagger.annotations.ApiModel;
import lombok.*;

@Data
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "WevDAV的实体类")
public class WebDAVResponse {
    private Integer statusCode;
    private Object body;
}
