package cn.webdav.xmlparser;

import cn.webdav.pojo.webdav.Prop;
import cn.webdav.pojo.webdav.PropStat;
import cn.webdav.pojo.webdav.Status;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.junit.Test;

public class PropStatTest {
    @Test
    public void test(){
        PropStat prop = PropStat.builder()
                .prop(Prop.builder()
                        .displayName("")
                        .build())
                .status(Status.of(200))
                .build();
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
        String xml = "<propstat>\n" +
                "  <prop>\n" +
                "    <displayname/>\n" +
                "  </prop>\n" +
                "  <status>HTTP/1.1 200 OK</status>\n" +
                "</propstat>";
        XmlMapper xmlMapper = new XmlMapper();
        try {
            PropStat prop = xmlMapper.readValue(xml, PropStat.class);
            System.out.println(prop);
            System.out.println(prop.getProp());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
