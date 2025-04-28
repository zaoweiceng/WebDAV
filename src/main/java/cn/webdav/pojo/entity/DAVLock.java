package cn.webdav.pojo.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

@Data
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "WevDAV的文件锁的实体类")
public class DAVLock {

    @ApiModelProperty(value = "锁的范围")
    public String lockScope;

    @ApiModelProperty(value = "锁的类型")
    public String lockType;

    @ApiModelProperty(value = "锁的深度")
    public String depth;

    @ApiModelProperty(value = "锁的Token")
    public String lockToken;

    @ApiModelProperty(value = "锁的目录")
    public String lockRoot;

    @ApiModelProperty(value = "锁的owner")
    public String owner;

    @ApiModelProperty(value = "锁的过期时间")
    public String timeout;


}
