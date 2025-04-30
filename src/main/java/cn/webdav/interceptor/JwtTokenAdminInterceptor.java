package cn.webdav.interceptor;

import cn.hutool.jwt.JWT;
import cn.hutool.jwt.JWTHeader;
import cn.hutool.jwt.JWTUtil;
import cn.webdav.common.constant.UserConstant;
import cn.webdav.common.properties.JwtProperties;
import cn.webdav.common.utils.BaseContext;
import cn.webdav.exception.LoginFailedException;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.nio.charset.StandardCharsets;

@Component
@Slf4j
public class JwtTokenAdminInterceptor implements HandlerInterceptor {

    @Autowired
    private JwtProperties jwtProperties;

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //判断当前拦截到的是Controller的方法还是其他资源
        if (!(handler instanceof HandlerMethod)) {
            //当前拦截到的不是动态方法，直接放行
            return true;
        }

        //1、从请求头中获取令牌
        String token = request.getHeader(UserConstant.USER_JWT_HEADER);

        //2、校验令牌
        try {
            boolean validate = JWTUtil.verify(token, jwtProperties.getUserSecret().getBytes(StandardCharsets.UTF_8));
            if (!validate) {
                throw new LoginFailedException(UserConstant.USER_JWT_INVALIDATED);
            }

            JWT jwt = JWTUtil.parseToken(token);
            boolean expired = jwt.setKey(jwtProperties.getUserSecret().getBytes(StandardCharsets.UTF_8)).validate(0);
            if (!expired) {
                throw new LoginFailedException(UserConstant.USER_JWT_EXPIRED);
            }
            Long userId =  Long.valueOf(jwt.getPayload(UserConstant.USER_WEBDAV_JWT_PAYLOAD_ID).toString());
            // ThreadLocal设置当前登录用户id
            BaseContext.setCurrentId(userId);
            //3、通过，放行
            return true;
        } catch (Exception ex) {
            //4、不通过，响应401状态码
            response.setStatus(401);
            log.error("[JwtTokenAdminInterceptor] preHandle error:{}", ex.getMessage());
            return false;
        }
    }
}
