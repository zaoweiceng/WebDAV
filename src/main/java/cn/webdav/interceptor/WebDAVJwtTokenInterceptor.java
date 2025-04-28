package cn.webdav.interceptor;

import cn.hutool.core.codec.Base64;
import cn.hutool.jwt.JWT;
import cn.hutool.jwt.JWTUtil;
import cn.webdav.common.constant.HttpHeaderConstant;
import cn.webdav.common.properties.JwtProperties;
import cn.webdav.common.utils.BaseContext;
import cn.webdav.pojo.entity.User;
import cn.webdav.service.WebDAVService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.nio.charset.StandardCharsets;

@Component
@Slf4j
public class WebDAVJwtTokenInterceptor implements HandlerInterceptor {

    @Autowired
    private WebDAVService webDAVService;

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (request.getHeader(HttpHeaderConstant.AUTHORIZATION) == null) {
            response.setHeader(HttpHeaderConstant.WWW_AUTHENTICATE, "Basic realm=\"Need Authorization\"");
            response.setStatus(401);
            return false;
        }
        String token = request.getHeader(HttpHeaderConstant.AUTHORIZATION);

        if (!token.startsWith("Basic")) {
            response.setStatus(401);
            response.getWriter().write("token is not start with Basic");
            return false;
        }
        boolean b = webDAVService.authWebDAV(Base64.decodeStr(token.substring(6)));
        if (b) {
            return true;
        } else {
            response.setStatus(401);
            return false;
        }
    }
}
