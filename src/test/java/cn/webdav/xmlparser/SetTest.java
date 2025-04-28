package cn.webdav.xmlparser;

import cn.webdav.pojo.webdav.Prop;
import cn.webdav.pojo.webdav.Remove;
import cn.webdav.pojo.webdav.Set;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.dataformat.xml.ser.ToXmlGenerator;
import org.junit.Test;

public class SetTest {
    @Test
    public void test() {
        Set r = Set.builder()
                .prop(Prop.builder()
                        .author("aaa")
                        .build())
                .build();
        XmlMapper xmlMapper = new XmlMapper();

        xmlMapper.enable(ToXmlGenerator.Feature.WRITE_XML_DECLARATION);
        try {
            String xml = xmlMapper.writeValueAsString(r);
            System.out.println(xml);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test2() {
        String xml = "<set>\n" +
                "    <prop>\n" +
                "      <displayname>新名称</displayname>\n" +
                "    </prop>\n" +
                "  </set>";
        XmlMapper xmlMapper = new XmlMapper();
        try {
            Set r = xmlMapper.readValue(xml, Set.class);
            System.out.println(r);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
