package cn.webdav.xmlparser;

import cn.webdav.pojo.webdav.Href;
import cn.webdav.pojo.webdav.Owner;
import cn.webdav.pojo.webdav.Status;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.junit.Test;

public class OwnerTest {
    @Test
    public void test(){
        Owner owner = Owner.builder()
                .href(Href.of("http://localhost:8080/webdav/test"))
                .name("test")
                .build();
        XmlMapper xmlMapper = new XmlMapper();
        try {
            String xml = xmlMapper.writeValueAsString(owner);
            System.out.println(xml);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
