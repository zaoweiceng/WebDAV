package cn.webdav.xmlparser;

import cn.webdav.common.constant.DepthConstant;
import cn.webdav.pojo.webdav.Href;
import cn.webdav.pojo.webdav.lock.ActiveLock;
import cn.webdav.pojo.webdav.lock.LockScope;
import cn.webdav.pojo.webdav.lock.LockType;
import cn.webdav.pojo.webdav.lock.Write;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.junit.Test;

public class ActivelockTest {

    @Test
    public void test() throws JsonProcessingException {
        ActiveLock activeLock = ActiveLock.builder()
                .lockscope(LockScope.Shared())
                .locktype(LockType.builder()
                        .write(Write.builder().build())
                        .build())
                .depth(DepthConstant.DEPTH_INFINITY)
                .timeout("timeout")
                .locktoken(Href.of("token"))
                .lockroot(new Href("root"))
                .build();
        XmlMapper xmlMapper = new XmlMapper();
        String xml = xmlMapper.writerWithDefaultPrettyPrinter().writeValueAsString(activeLock);
        System.out.println(xml);
    }

    @Test
    public void test1() throws JsonProcessingException {
        String xml = "<activeLock><locktype><write/></locktype><locktoken><href>token</href></locktoken><lockroot><href>root</href></lockroot><lockscope><shared/></lockscope><depth>infinity</depth><timeout>timeout</timeout></activeLock>";
        XmlMapper xmlMapper = new XmlMapper();
        ActiveLock activeLock = xmlMapper.readValue(xml, ActiveLock.class);
        System.out.println(activeLock.getDepth());
        System.out.println(activeLock.getLocktoken().getHref());
//        System.out.println(activeLock.getLockType());
//        System.out.println(activeLock.getLockScope());
        System.out.println(activeLock.getLockroot().getHref());
    }
}
