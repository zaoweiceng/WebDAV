package cn.webdav.xmlparser;

import cn.webdav.pojo.webdav.Href;
import cn.webdav.pojo.webdav.Owner;
import cn.webdav.pojo.webdav.Prop;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.junit.Test;

public class PropTest {
    @Test
    public void test(){
        Prop prop = Prop.builder()
                .displayName("displayName")
                .getcontentlength(100)
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
    public void test1(){
        String xml = "<prop><displayname>displayName</displayname><getcontentlength>100</getcontentlength><author/></prop>";
        XmlMapper xmlMapper = new XmlMapper();
        try {
            Prop prop = xmlMapper.readValue(xml, Prop.class);
            System.out.println(prop);
            System.out.println("".equals(prop.getAuthor()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
