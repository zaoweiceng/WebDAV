package cn.webdav.service;

import cn.webdav.common.result.WebDAVResult;
import cn.webdav.pojo.entity.WebDAVHttpEntity;
import cn.webdav.pojo.entity.WebDAVResponse;

public interface WebDAVService {

    boolean authWebDAV(String s);

    boolean authWebDAVPath(String path);

    WebDAVResponse PropFind(WebDAVHttpEntity webDAVHttpEntity);

    WebDAVResponse PropPatch(WebDAVHttpEntity webDAVHttpEntity);

    WebDAVResponse MkCol(WebDAVHttpEntity webDAVHttpEntity);

    WebDAVResponse Get(WebDAVHttpEntity webDAVHttpEntity);

    WebDAVResponse Head(WebDAVHttpEntity webDAVHttpEntity);

    WebDAVResponse Post(WebDAVHttpEntity webDAVHttpEntity);

    WebDAVResponse Delete(WebDAVHttpEntity webDAVHttpEntity);

    WebDAVResponse Put(WebDAVHttpEntity webDAVHttpEntity);

    WebDAVResponse Copy(WebDAVHttpEntity webDAVHttpEntity);

    WebDAVResponse Move(WebDAVHttpEntity webDAVHttpEntity);

    WebDAVResponse Lock(WebDAVHttpEntity webDAVHttpEntity);

    WebDAVResponse Unlock(WebDAVHttpEntity webDAVHttpEntity);
}
