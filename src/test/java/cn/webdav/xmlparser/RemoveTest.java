package cn.webdav.xmlparser;

import cn.webdav.pojo.webdav.Prop;
import cn.webdav.pojo.webdav.Remove;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.junit.Test;

public class RemoveTest {
    @Test
    public void test() {
        Remove r = Remove.builder()
                .prop(Prop.builder()
                        .author("")
                        .build())
                .build();
        XmlMapper xmlMapper = new XmlMapper();
        try {
            String xml = xmlMapper.writeValueAsString(r);
            System.out.println(xml);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test2() {
        String xml = "<remove>\n" +
                "    <prop><author/></prop>\n" +
                "  </remove>";
        XmlMapper xmlMapper = new XmlMapper();
        try {
            Remove r = xmlMapper.readValue(xml, Remove.class);
            System.out.println(r);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
