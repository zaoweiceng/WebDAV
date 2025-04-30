package cn.webdav.controller.user;

import cn.hutool.core.bean.BeanUtil;
import cn.webdav.common.result.Result;
import cn.webdav.pojo.dto.UserDTO;
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

    @GetMapping(value = "/assignWebDAVToken", produces = "application/json")
    @ApiOperation(value = "分配WebDAVToken")
    public Result<String> assignWebDAVToken() {
        return Result.success(userService.assignWebDAVToken());
    }

    @PutMapping(value = "/status/{status}", produces = "application/json")
    @ApiOperation(value = "设置当前用户webdav的可用状态")
    public Result<Boolean> setUserStatus(@PathVariable Integer status) {
        return Result.success(userService.setUserStatus(status));
    }

    @PostMapping(value = "/register", produces = "application/json")
    @ApiOperation(value = "用户注册")
    public Result<Boolean> register(@RequestBody UserDTO userDTO) {
        return Result.success(userService.registerUser(BeanUtil.copyProperties(userDTO, User.class)));
    }

    // TODO: 删除用户
    @DeleteMapping(value = "/delete/{id}", produces = "application/json")
    @ApiOperation(value = "删除用户")
    public Result<Boolean> deleteUser(@PathVariable Long id) {
        return Result.success(userService.deleteById(id));
    }
}
