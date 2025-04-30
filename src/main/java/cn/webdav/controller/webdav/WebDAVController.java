package cn.webdav.controller.webdav;

import cn.hutool.core.codec.Base64;
import cn.webdav.common.constant.DepthConstant;
import cn.webdav.common.constant.HttpHeaderConstant;
import cn.webdav.common.constant.MediaTypeConstant;
import cn.webdav.common.constant.WebDAVMethodsConstant;
import cn.webdav.common.properties.WebDAVProperties;
import cn.webdav.common.result.WebDAVResult;
import cn.webdav.common.utils.webdav.ResponseUtil;
import cn.webdav.pojo.entity.WebDAVHttpEntity;
import cn.webdav.pojo.entity.WebDAVResponse;
import cn.webdav.service.WebDAVService;
import cn.webdav.vfs.FileSystem;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
@Slf4j
@Api("WebDAV相关接口")
public class WebDAVController {

    @Autowired
    private WebDAVService webDAVService;

    @Autowired
    private FileSystem fileSystem;

    @Autowired
    private WebDAVProperties webDAVProperties;

    @RequestMapping("/**")
    public String webdav(HttpServletRequest request, HttpServletResponse response) throws IOException {
        if (!authMethod(request, response)) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return WebDAVResult
                    .Response(ResponseUtil
                            .UnAuthorizedResponse(request.getRequestURI()));
        }
        return methodHandler(request, response);
    }

    @GetMapping("/**")
    public ResponseEntity<InputStreamResource> download(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String file = request.getRequestURI();
        if (fileSystem.isFile(file)){
            HttpHeaders headers = new HttpHeaders();
            String fileName = file.substring(file.lastIndexOf("/") + 1);
            headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\" " + fileName + "\"");
            headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_OCTET_STREAM_VALUE);
            InputStreamResource inputStream = new InputStreamResource(fileSystem.getFileDownloadStream(file));
            return ResponseEntity.ok()
                    .headers(headers)
                    .contentLength(fileSystem.getFileSize(file))
                    .body(inputStream);
        }
        response.setStatus(404);
        return null;
    }

    private String methodHandler(HttpServletRequest request, HttpServletResponse response) throws IOException {
        WebDAVHttpEntity webDAVHttpEntity = WebDAVHttpEntity.builder()
                .path(request.getRequestURI())
                .method(request.getMethod())
                .depth(request.getHeader(HttpHeaderConstant.DEPTH))
                .lockToken(request.getHeader(HttpHeaderConstant.LOCK_TOKEN))
                .ifMatch(request.getHeader(HttpHeaderConstant.IF_MATCh))
                .destination(request.getHeader(HttpHeaderConstant.DESTINATION))
                .overwrite(request.getHeader(HttpHeaderConstant.OVERWRITE))
                .timeout(request.getHeader(HttpHeaderConstant.TIMEOUT))
                .content(request.getReader()
                        .lines()
                        .collect(Collectors.joining()))
                .contentLength(request.getContentLengthLong())
                .build();
        if (webDAVHttpEntity.getDepth() == null){
            webDAVHttpEntity.setDepth(DepthConstant.DEPTH_INFINITY);
        }
        if (DepthConstant.DEPTH_INFINITY.equals(webDAVHttpEntity.getDepth())){
            webDAVHttpEntity.setDepth(String.valueOf(webDAVProperties.getDepth()));
        }
        response.setContentType(MediaTypeConstant.TEXT_XML_UTF8);
        WebDAVResponse webDAVResponse;
        switch (request.getMethod()) {
            case WebDAVMethodsConstant.PROPFIND:
                webDAVResponse = webDAVService.PropFind(webDAVHttpEntity);
                response.setStatus(webDAVResponse.getStatusCode());
                return WebDAVResult.Response(webDAVResponse.getBody());
            case WebDAVMethodsConstant.PROPPATCH:
                webDAVResponse = webDAVService.PropPatch(webDAVHttpEntity);
                response.setStatus(webDAVResponse.getStatusCode());
                return WebDAVResult.Response(webDAVResponse.getBody());
            case WebDAVMethodsConstant.MKCOL:
                webDAVResponse = webDAVService.MkCol(webDAVHttpEntity);
                response.setStatus(webDAVResponse.getStatusCode());
                return WebDAVResult.Response(webDAVResponse.getBody());
            case WebDAVMethodsConstant.GET:
                webDAVResponse = webDAVService.Get(webDAVHttpEntity);
                response.setStatus(webDAVResponse.getStatusCode());
                return WebDAVResult.Response(webDAVResponse.getBody());
            case WebDAVMethodsConstant.HEAD:
                webDAVResponse = webDAVService.Head(webDAVHttpEntity);
                response.setStatus(webDAVResponse.getStatusCode());
                return WebDAVResult.Response(webDAVResponse.getBody());
            case WebDAVMethodsConstant.POST:
                webDAVResponse = webDAVService.Post(webDAVHttpEntity);
                response.setStatus(webDAVResponse.getStatusCode());
                return WebDAVResult.Response(webDAVResponse.getBody());
            case WebDAVMethodsConstant.DELETE:
                webDAVResponse = webDAVService.Delete(webDAVHttpEntity);
                response.setStatus(webDAVResponse.getStatusCode());
                return WebDAVResult.Response(webDAVResponse.getBody());
            case WebDAVMethodsConstant.PUT:
                webDAVResponse = webDAVService.Put(webDAVHttpEntity);
                response.setStatus(webDAVResponse.getStatusCode());
                return WebDAVResult.Response(webDAVResponse.getBody());

            case WebDAVMethodsConstant.COPY:
                webDAVResponse = webDAVService.Copy(webDAVHttpEntity);
                response.setStatus(webDAVResponse.getStatusCode());
                return WebDAVResult.Response(webDAVResponse.getBody());
            case WebDAVMethodsConstant.MOVE:
                webDAVResponse = webDAVService.Move(webDAVHttpEntity);
                response.setStatus(webDAVResponse.getStatusCode());
                return WebDAVResult.Response(webDAVResponse.getBody());
            case WebDAVMethodsConstant.LOCK:
                webDAVResponse = webDAVService.Lock(webDAVHttpEntity);
                response.setStatus(webDAVResponse.getStatusCode());
                return WebDAVResult.Response(webDAVResponse.getBody());
            case WebDAVMethodsConstant.UNLOCK:
                webDAVResponse = webDAVService.Unlock(webDAVHttpEntity);
                response.setStatus(webDAVResponse.getStatusCode());
                return WebDAVResult.Response(webDAVResponse.getBody());
            default:
                response.setStatus(405);
                return "";
        }

    }

    private boolean authMethod(HttpServletRequest request, HttpServletResponse response){
        String method = request.getMethod();
        if (!WebDAVMethodsConstant.isValidMethod(method)){
            response.setStatus(405);
            return false;
        }
        return true;
    }
}
