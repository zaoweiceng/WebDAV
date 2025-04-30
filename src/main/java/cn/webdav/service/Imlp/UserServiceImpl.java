package cn.webdav.service.Imlp;

import cn.hutool.jwt.JWT;
import cn.hutool.jwt.JWTHeader;
import cn.hutool.jwt.JWTUtil;
import cn.webdav.common.constant.UserConstant;
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
            throw new LoginFailedException(UserConstant.USER_UNLOGIN);
        }
        User user = getById(userId);
        return user.getUserGroup();
    }

    @Override
    public String assignWebDAVToken() {
        Long userId = BaseContext.getCurrentId();
        if (userId == null) {
            throw new LoginFailedException(UserConstant.USER_UNLOGIN);
        }
        User user = getById(userId);
        String token = JWT.create()
                .setHeader(JWTHeader.TYPE, UserConstant.USER_WEBDAV_JWT_HEADER_TYPE)
                .setHeader(JWTHeader.ALGORITHM, UserConstant.USER_WEBDAV_JWT_HEADER_ALGORITHM)
                .setPayload(UserConstant.USER_WEBDAV_JWT_PAYLOAD_ID, user.getId())
                .setPayload(UserConstant.USER_WEBDAV_JWT_PAYLOAD_USERNAME, user.getUsername())
                .setPayload(UserConstant.USER_WEBDAV_JWT_PAYLOAD_SALT, LocalDateTime.now())
                .setIssuedAt(new Date())
                .setKey(jwtProperties.getUserSecret().getBytes(StandardCharsets.UTF_8))
                .sign();
        user.setWebdavToken(token);
        user.setUpdatedAt(LocalDateTime.now());
        update(user, new LambdaQueryWrapper<User>().eq(User::getId, userId));
        return token;
    }

    @Override
    public Boolean setUserStatus(Integer status) {
        Long userId = BaseContext.getCurrentId();
        if (userId != null) {
            User user = getById(userId);
            if (user != null) {
                user.setStatus(status);
                user.setUpdatedAt(LocalDateTime.now());
                return update(user, new LambdaQueryWrapper<User>().eq(User::getId, userId));
            }
        }
        return false;
    }

    @Override
    public Boolean registerUser(User user) {
        Long userId = BaseContext.getCurrentId();
        if (userId != null) {
            User recommender = getById(userId);
            if (recommender != null) {
                if (UserConstant.USER_GROUP_ADMIN.equals(recommender.getUserGroup())){
                    if (user.getUserGroup() != null && !user.getUserGroup().trim().isEmpty()){
                        user.setUserGroup(user.getUserGroup().trim());
                    }else{
                        user.setUserGroup(UserConstant.USER_GROUP_DEFALUT);
                    }
                }
                user.setUpdatedAt(LocalDateTime.now());
                user.setCreatedAt(LocalDateTime.now());
                return save(user);
            }
        }
        return false;
    }

    @Override
    public Boolean deleteById(Long id) {
        // 需要管理员用户才能删除
        Long userId = BaseContext.getCurrentId();
        if (userId == null) {
            return false;
        }
        // 最后一个管理员不可删除
        return null;
    }
}