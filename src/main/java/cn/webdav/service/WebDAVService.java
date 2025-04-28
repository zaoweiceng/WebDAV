package cn.webdav.service;

import cn.webdav.common.result.WebDAVResult;
import cn.webdav.pojo.entity.WebDAVHttpEntity;

public interface WebDAVService {

    boolean authWebDAV(String s);

    boolean authWebDAVPath(String path);

    Object PropFind(WebDAVHttpEntity webDAVHttpEntity);

    Object PropPatch(WebDAVHttpEntity webDAVHttpEntity);

    Object MkCol(WebDAVHttpEntity webDAVHttpEntity);

    Object Get(WebDAVHttpEntity webDAVHttpEntity);

    Object Head(WebDAVHttpEntity webDAVHttpEntity);

    Object Post(WebDAVHttpEntity webDAVHttpEntity);

    Object Delete(WebDAVHttpEntity webDAVHttpEntity);

    Object Put(WebDAVHttpEntity webDAVHttpEntity);

    Object Copy(WebDAVHttpEntity webDAVHttpEntity);

    Object Move(WebDAVHttpEntity webDAVHttpEntity);

    Object Lock(WebDAVHttpEntity webDAVHttpEntity);

    Object Unlock(WebDAVHttpEntity webDAVHttpEntity);
}
