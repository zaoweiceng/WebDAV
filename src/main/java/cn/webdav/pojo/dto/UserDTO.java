package cn.webdav.pojo.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

@Data
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "用户实体类")
public class UserDTO {

    @ApiModelProperty("用户名")
    private String username;

    @ApiModelProperty("密码")
    private String password;

    @ApiModelProperty("用户组")
    private String userGroup;

}
