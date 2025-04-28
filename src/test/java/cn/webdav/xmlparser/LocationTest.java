package cn.webdav.xmlparser;

import cn.webdav.pojo.webdav.Allprop;
import cn.webdav.pojo.webdav.Href;
import cn.webdav.pojo.webdav.Location;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.junit.Test;

public class LocationTest {
    @Test
    public void test(){
        Location location = Location.builder()
                .location(Href.of("http://localhost:8080/webdav/"))
                .build();
        XmlMapper xmlMapper = new XmlMapper();
        try {
            String xml = xmlMapper.writeValueAsString(location);
            System.out.println(xml);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
