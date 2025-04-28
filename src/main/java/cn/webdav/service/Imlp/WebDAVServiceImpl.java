package cn.webdav.service.Imlp;

import cn.hutool.core.codec.Base64;
import cn.hutool.jwt.JWT;
import cn.hutool.jwt.JWTUtil;
import cn.webdav.common.properties.JwtProperties;
import cn.webdav.common.result.WebDAVResult;
import cn.webdav.common.utils.BaseContext;
import cn.webdav.common.utils.XmlUtil;
import cn.webdav.common.utils.webdav.PropUtil;
import cn.webdav.common.utils.webdav.ResponseUtil;
import cn.webdav.exception.LoginFailedException;
import cn.webdav.mapper.UserMapper;
import cn.webdav.pojo.entity.SelectedProp;
import cn.webdav.pojo.entity.User;
import cn.webdav.pojo.entity.WebDAVHttpEntity;
import cn.webdav.pojo.webdav.*;
import cn.webdav.service.WebDAVService;
import cn.webdav.vfs.FileSystem;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@Service
public class WebDAVServiceImpl extends ServiceImpl<UserMapper, User> implements WebDAVService {

    @Autowired
    private JwtProperties jwtProperties;

    @Autowired
    private FileSystem fs;

    @Autowired
    private PropUtil propUtil;

    @Override
    public Object PropFind(WebDAVHttpEntity webDAVHttpEntity) {
        if (!authWebDAVPath(webDAVHttpEntity.getPath())){
            return ResponseUtil.UnAuthorizedResponse(webDAVHttpEntity.getPath());
        }
        boolean allprop = true;
        PropFind parsed = null;
        SelectedProp selectedProp = null;
        if (webDAVHttpEntity.getContentLength() > 0) {
             parsed = XmlUtil.parse(webDAVHttpEntity.getContent(), PropFind.class);
            if (parsed.getAllprop() == null) {
                allprop = false;
            }
            if (parsed.getInclude() != null) {
                allprop = false;
                selectedProp = PropUtil.parseSelectedProp(parsed.getInclude());
            }
        }
        if (selectedProp == null){
            allprop = true;
            selectedProp = SelectedProp.allTrue();
        }
        if (fs.isDirectory(webDAVHttpEntity.getPath())){
            List<String> fileList = fs.getFileList(webDAVHttpEntity.getPath());
            if (fileList.size() <= 0){
                return ResponseUtil.NotFoundResponse(webDAVHttpEntity.getPath());
            }else{
                MultiStatus multiStatus = new MultiStatus();
                multiStatus.setResponse(new ArrayList<Response>());
                DepthPropFind(webDAVHttpEntity.getPath(),
                        Integer.valueOf(webDAVHttpEntity.getDepth()),
                        selectedProp,
                        multiStatus);
                return multiStatus;
            }

        }else if (fs.isFile(webDAVHttpEntity.getPath())){
            if (allprop){
                return ResponseUtil.buildResponse(
                        webDAVHttpEntity.getPath(),
                        propUtil.getAllProp(webDAVHttpEntity.getPath())
                        );
            }else{
                return ResponseUtil.buildResponse(
                        webDAVHttpEntity.getPath(),
                        propUtil.getProp(webDAVHttpEntity.getPath(), selectedProp)
                );
            }
        }
        return ResponseUtil.NotFoundResponse(webDAVHttpEntity.getPath());
    }

    @Override
    public Object PropPatch(WebDAVHttpEntity webDAVHttpEntity) {
        return null;
    }

    @Override
    public Object MkCol(WebDAVHttpEntity webDAVHttpEntity) {
        return null;
    }

    @Override
    public Object Get(WebDAVHttpEntity webDAVHttpEntity) {
        return null;
    }

    @Override
    public Object Head(WebDAVHttpEntity webDAVHttpEntity) {
        return null;
    }

    @Override
    public Object Post(WebDAVHttpEntity webDAVHttpEntity) {
        return null;
    }

    @Override
    public Object Delete(WebDAVHttpEntity webDAVHttpEntity) {
        return null;
    }

    @Override
    public Object Put(WebDAVHttpEntity webDAVHttpEntity) {
        return null;
    }

    @Override
    public Object Copy(WebDAVHttpEntity webDAVHttpEntity) {
        return null;
    }

    @Override
    public Object Move(WebDAVHttpEntity webDAVHttpEntity) {
        return null;
    }

    @Override
    public Object Lock(WebDAVHttpEntity webDAVHttpEntity) {
        return null;
    }

    @Override
    public Object Unlock(WebDAVHttpEntity webDAVHttpEntity) {
        return null;
    }

    @Override
    public boolean authWebDAV(String s) {
        String[] split = s.split(":");
        if (split.length == 2) {
            String username = split[0];
            String password = split[1];
            User user = getOne(new LambdaQueryWrapper<User>()
                    .select(User::getId, User::getUsername, User::getStatus, User::getWebdavToken)
                    .eq(User::getUsername, username));
            if(user != null && password != null && user.getStatus() == 0 && password.equals(user.getWebdavToken())){
                boolean validate = JWTUtil.verify(password, jwtProperties.getUserSecret().getBytes(StandardCharsets.UTF_8));
                if (!validate) {
                    return false;
                }
                JWT jwt = JWTUtil.parseToken(password);
                Long userId =  Long.valueOf(jwt.getPayload("userId").toString());
                BaseContext.setCurrentId(userId);
                return true;
            }
        }
        return false;
    }

    // TODO: 验证当前路径是否有权限访问
    @Override
    public boolean authWebDAVPath(String path) {
        Long userId = BaseContext.getCurrentId();
        if (userId != null) {
            User user = getById(userId);
            if (user != null) {
                // TODO: 验证当前用户是否拥有该路径的权限
            }else{
                return false;
            }
        }
        // TODO: 修改状态码
        return true;
    }


    public void DepthPropFind(String path, Integer depth, SelectedProp selectedProp, MultiStatus multiStatus){
        if (depth > 0){
            if (fs.isDirectory(path)){
                List<String> fileList = fs.getFileList(path);
                for (String file : fileList) {
                    if (path.endsWith("/")){
                        DepthPropFind(path + file, depth - 1, selectedProp, multiStatus);
                    }else{
                        DepthPropFind(path + "/" + file, depth - 1, selectedProp, multiStatus);
                    }
                }
                multiStatus.getResponse()
                        .add(ResponseUtil.buildResponse(
                                path,
                                propUtil.getProp(path, selectedProp)
                        ));
            }
            if (fs.isFile(path)){
                multiStatus.getResponse()
                        .add(ResponseUtil.buildResponse(
                                path,
                                propUtil.getProp(path, selectedProp)
                        ));
            }
        }
    }


}
