package cn.webdav.xmlparser;

import cn.webdav.pojo.webdav.*;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.junit.Test;

public class ResponseTest {
    @Test
    public void test(){
        Response response = Response.builder()
                .href(Href.builder()
                        .href("/")
                        .build()
                )
                .propstat(PropStat.builder()
                        .prop(Prop.builder()
                                .displayName("test")
                                .build()
                        )
                        .build()
                )
                .status(Status.of(200))
                .build();
        XmlMapper xmlMapper = new XmlMapper();
        try {
            String xml = xmlMapper.writeValueAsString(response);
            System.out.println(xml);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test1(){
        String xml = "<response>\n" +
                "  <href>http://example.com/resource</href>\n" +
                "  <propstat>\n" +
                "    <prop>\n" +
                "      <displayname/>\n" +
                "    </prop>\n" +
                "    <status>HTTP/1.1 200 OK</status>\n" +
                "  </propstat>\n" +
                "</response>";
        XmlMapper xmlMapper = new XmlMapper();
        try {
            Response prop = xmlMapper.readValue(xml, Response.class);
            System.out.println(prop);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
