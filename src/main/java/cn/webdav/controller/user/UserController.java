package cn.webdav.controller.user;

import cn.hutool.core.bean.BeanUtil;
import cn.webdav.common.result.Result;
import cn.webdav.pojo.dto.UserLoginDTO;
import cn.webdav.pojo.entity.User;
import cn.webdav.pojo.vo.UserLoginVO;
import cn.webdav.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@Slf4j
@Api("用户相关接口")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping(value = "/login", produces = "application/json")
    @ApiOperation(value = "用户登录")
    public Result<UserLoginVO> login(UserLoginDTO userLoginDTO) {
        return Result.success(userService.login(BeanUtil.copyProperties(userLoginDTO, User.class)));
    }

    @GetMapping(value = "/group", produces = "application/json")
    @ApiOperation(value = "获取用户组")
    public Result<String> getUserGroup() {
        return Result.success(userService.getGroupById());
    }

    @GetMapping(value = "/assignWebDAVToken/", produces = "application/json")
    @ApiOperation(value = "分配WebDAVToken")
    public Result<String> assignWebDAVToken() {
        return Result.success(userService.assignWebDAVToken());
    }

    // TODO: 设置当前用户webdav的可用状态，POST

    // TODO: 新增用户，仅当前用户为管理员的情况下可新增

    // TODO: 删除用户
}
