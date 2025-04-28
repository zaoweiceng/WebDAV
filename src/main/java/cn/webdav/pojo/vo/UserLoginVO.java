package cn.webdav.pojo.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

@Data
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "用户返回实体类")
public class UserLoginVO {
    @ApiModelProperty("主键值")
    private Integer id;

    @ApiModelProperty("用户名")
    private String username;

    @ApiModelProperty("jwt令牌")
    private String token;
}
