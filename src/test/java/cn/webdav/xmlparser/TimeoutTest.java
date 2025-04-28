package cn.webdav.xmlparser;

import cn.webdav.pojo.webdav.ResponseDescription;
import cn.webdav.pojo.webdav.Timeout;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.junit.Test;

public class TimeoutTest {
    @Test
    public void test(){
        Timeout prop = new Timeout("Second-3600");
        XmlMapper xmlMapper = new XmlMapper();
        try {
            String xml = xmlMapper.writeValueAsString(prop);
            System.out.println(xml);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test1() {
        String xml = "<timeout><timeout>Second-3600</timeout></timeout>";
        XmlMapper xmlMapper = new XmlMapper();
        try {
            Timeout prop = xmlMapper.readValue(xml, Timeout.class);
            System.out.println(prop);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
