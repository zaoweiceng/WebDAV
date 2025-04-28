package cn.webdav.pojo.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

@Data
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "WevDAV中Http请求的实体类")
public class WebDAVHttpEntity {

    @ApiModelProperty("请求路径")
    private String path;

    @ApiModelProperty("请求方法")
    private String method;

    @ApiModelProperty("请求深度")
    private String depth;

    @ApiModelProperty("请求锁token")
    private String lockToken;

    @ApiModelProperty("请求If-Match")
    private String ifMatch;

    @ApiModelProperty("请求的目标地址")
    private String destination;

    @ApiModelProperty("是否覆盖目标文件")
    private String overwrite;

    @ApiModelProperty("锁的超时时间")
    private String timeout;

    @ApiModelProperty("请求内容")
    private String content;

    @ApiModelProperty("请求的Content-Length")
    private Long  contentLength;
}
