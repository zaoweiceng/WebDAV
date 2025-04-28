package cn.webdav.common.utils.webdav;

import cn.webdav.pojo.webdav.*;

public class ResponseUtil {
    public static Response UnAuthorizedResponse(String path){
        return Response.builder()
                .href(Href.of(path))
                .status(Status.of(401))
                .build();
    }

    public static Response ForbiddenResponse(String path){
        return Response.builder()
                .href(Href.of(path))
                .status(Status.of(403))
                .build();
    }

    public static Response NotFoundResponse(String path){
        return Response.builder()
                .href(Href.of(path))
                .status(Status.of(404))
                .build();
    }

    public static Response buildResponse(String path, int statusCode){
        return Response.builder()
                .href(Href.of(path))
                .status(Status.of(statusCode))
                .build();
    }

    public static Response buildResponse(String path){
        return Response.builder()
                .href(Href.of(path))
                .status(Status.of(200))
                .build();
    }

    public static Response buildResponse(String path, PropStat propStat){
        return Response.builder()
                .href(Href.of(path))
                .propstat(propStat)
                .build();
    }

    public static Response buildResponse(String path, Prop prop){
        return Response.builder()
                .href(Href.of(path))
                .propstat(PropStat.builder()
                        .prop(prop)
                        .status(Status.of(200))
                        .build())
                .build();
    }
}
