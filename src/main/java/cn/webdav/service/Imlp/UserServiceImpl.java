package cn.webdav.service.Imlp;

import cn.hutool.jwt.JWT;
import cn.hutool.jwt.JWTHeader;
import cn.hutool.jwt.JWTUtil;
import cn.webdav.common.properties.JwtProperties;
import cn.webdav.common.utils.BaseContext;
import cn.webdav.exception.LoginFailedException;
import cn.webdav.mapper.UserMapper;
import cn.webdav.pojo.entity.User;
import cn.webdav.pojo.vo.UserLoginVO;
import cn.webdav.service.UserService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.Date;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private JwtProperties jwtProperties;

    @Override
    public UserLoginVO login(User userEntity) {
        // 根据用户名和密码查询是否存在
        User user = getOne(new LambdaQueryWrapper<User>()
                .select(User::getId, User::getUsername)
                .eq(User::getUsername, userEntity.getUsername())
                .eq(User::getPassword, userEntity.getPassword())
        );
        if (user != null) {
            // 登录成功
            String token = JWT.create()
                    .setHeader(JWTHeader.TYPE, "JWT")
                    .setHeader(JWTHeader.ALGORITHM, "HS256")
                    .setPayload("userId", user.getId())
                    .setPayload("username", user.getUsername())
                    .setIssuedAt(new Date())
                    .setExpiresAt(new Date(System.currentTimeMillis() + jwtProperties.getExpire()))
                    .setKey(jwtProperties.getUserSecret().getBytes(StandardCharsets.UTF_8))
                    .sign();
            return UserLoginVO.builder()
                    .id(user.getId())
                    .username(user.getUsername())
                    .token(token)
                    .build();
        }
        throw new LoginFailedException("用户名或密码错误");
    }

    @Override
    public String getGroupById() {
        Long userId = BaseContext.getCurrentId();
        if (userId == null) {
            throw new LoginFailedException("用户未登录");
        }
        User user = getById(userId);
        return user.getUserGroup();
    }

    @Override
    public String assignWebDAVToken() {
        Long userId = BaseContext.getCurrentId();
        if (userId == null) {
            throw new LoginFailedException("用户未登录");
        }
        User user = getById(userId);
        String token = JWT.create()
                .setHeader(JWTHeader.TYPE, "JWT")
                .setHeader(JWTHeader.ALGORITHM, "HS256")
                .setPayload("userId", user.getId())
                .setPayload("username", user.getUsername())
                .setPayload("salt", LocalDateTime.now())
                .setIssuedAt(new Date())
                .setKey(jwtProperties.getUserSecret().getBytes(StandardCharsets.UTF_8))
                .sign();
        user.setWebdavToken(token);
        user.setUpdatedAt(LocalDateTime.now());
        update(user, new LambdaQueryWrapper<User>().eq(User::getId, userId));
        return token;
    }
}