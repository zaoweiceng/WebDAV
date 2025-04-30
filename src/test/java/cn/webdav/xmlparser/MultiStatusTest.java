package cn.webdav.xmlparser;

import cn.webdav.common.constant.LanguageConstant;
import cn.webdav.common.constant.MediaTypeConstant;
import cn.webdav.pojo.webdav.*;
import cn.webdav.pojo.webdav.lock.*;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.dataformat.xml.ser.ToXmlGenerator;
import com.fasterxml.jackson.dataformat.xml.util.DefaultXmlPrettyPrinter;
import org.junit.Test;

import java.util.List;

public class MultiStatusTest {

    @Test
    public void test(){
        MultiStatus status = MultiStatus.builder()
                .response(
                        List.of(
                                Response.builder()
                                        .href(Href.of("/"))
                                        .status(Status.of(200))
                                        .build(),
                                Response.builder()
                                        .href(Href.of("/demo"))
                                        .status(Status.of(400))
                                        .build(),
                                Response.builder()
                                        .href(Href.of("/demo/index.html"))
                                        .propstat(PropStat.builder()
                                                .prop(Prop.builder()
                                                        .displayName("index.html")
                                                        .getcontentlength(1024L)
                                                        .author("webdav")
                                                        .creationdate("2021-01-01T00:00:00Z")
                                                        .getcontentlanguage(LanguageConstant.CHINESE_CHINA)
                                                        .getcontenttype(MediaTypeConstant.TEXT_HTML)
                                                        .getetag("\"1234567890\"")
                                                        .getlastmodified("2021-01-01T00:00:00Z")
                                                        .lockdiscovery(LockDiscovery.builder()
                                                                .activeLock(ActiveLock.builder()
                                                                        .lockscope(LockScope.Exclusive())
                                                                        .locktype(LockType.builder()
                                                                                .write(new Write())
                                                                                .build())
                                                                        .depth("0")
                                                                        .timeout("Infinite")
                                                                        .locktoken(Href.of("urn:uuid:f81de2ad-7f3d-a1b2-4f3c-00a0c91a9d76"))
                                                                        .lockroot(Href.of("ss"))
                                                                        .owner("webdav")
                                                                        .build())
                                                                .build())
                                                        .build())
                                                .status(Status.of(200))
                                                .build())
                                        .build()
                        )
                )
                .build();
        XmlMapper xmlMapper = new XmlMapper();
        xmlMapper.enable(ToXmlGenerator.Feature.WRITE_XML_DECLARATION);
        xmlMapper.configure(ToXmlGenerator.Feature.WRITE_XML_DECLARATION, true);


        try {
            String xml = xmlMapper.writerWithDefaultPrettyPrinter()
            .writeValueAsString(status);
            System.out.println(xml);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test2(){
        String xml = "<?xml version=\"1.0\" encoding=\"utf-8\" ?>\n" +
                "<D:multistatus xmlns:D='DAV:'>\n" +
                "    <D:response>\n" +
                "        <D:href>http://www.example.com/container/</D:href>\n" +
                "        <D:propstat>\n" +
                "            <D:prop>\n" +
                "                <D:lockdiscovery>\n" +
                "                    <D:activelock>\n" +
                "                        <D:locktype><D:write/></D:locktype>\n" +
                "                        <D:lockscope><D:exclusive/></D:lockscope>\n" +
                "                        <D:depth>0</D:depth>\n" +
                "                        <D:owner>Jane Smith</D:owner>\n" +
                "                        <D:timeout>Infinite</D:timeout>\n" +
                "                        <D:locktoken>\n" +
                "                        \t<D:href\n" +
                "                        \t>urn:uuid:f81de2ad-7f3d-a1b2-4f3c-00a0c91a9d76</D:href>\n" +
                "                        </D:locktoken>\n" +
                "                        <D:lockroot>\n" +
                "                        \t<D:href>http://www.example.com/container/</D:href>\n" +
                "                        </D:lockroot>\n" +
                "                    </D:activelock>\n" +
                "                </D:lockdiscovery>\n" +
                "            </D:prop>\n" +
                "            <D:status>HTTP/1.1 200 OK</D:status>\n" +
                "        </D:propstat>\n" +
                "    </D:response>\n" +
                "</D:multistatus>";
        try {
            XmlMapper xmlMapper = new XmlMapper();
            MultiStatus multiStatus = xmlMapper.readValue(xml, MultiStatus.class);
            System.out.println(multiStatus);
            System.out.println(multiStatus.getResponse().get(0)
                    .getPropstat().getProp().getLockdiscovery().getActiveLock().getOwner());
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
