package cn.webdav.xmlparser;

import cn.webdav.pojo.webdav.Allprop;
import cn.webdav.pojo.webdav.Include;
import cn.webdav.pojo.webdav.Prop;
import cn.webdav.pojo.webdav.PropFind;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.junit.Test;

public class PropFindTest {
    @Test
    public void test(){
    PropFind prop = PropFind.builder()
            .allprop(new Allprop())
            .include(Include.builder()
                    .displayname("")
                    .build()
            )
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
        String xml = "<propfind>\n" +
                "  <allprop/>\n" +
                "  <include>\n" +
                "    <displayname/>\n" +
                "  </include>\n" +
                "</propfind>";
        XmlMapper xmlMapper = new XmlMapper();
        try {
            PropFind prop = xmlMapper.readValue(xml, PropFind.class);
            System.out.println(prop);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
