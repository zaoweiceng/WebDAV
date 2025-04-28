package cn.webdav.xmlparser;

import cn.webdav.pojo.webdav.Prop;
import cn.webdav.pojo.webdav.PropStat;
import cn.webdav.pojo.webdav.ResponseDescription;
import cn.webdav.pojo.webdav.Status;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.junit.Test;

public class ResponseDescriptionTest {
    @Test
    public void test(){
        ResponseDescription prop = new ResponseDescription("test");
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
        String xml = "<responsedescription><responsedescription>操作成功</responsedescription></responsedescription>";
        XmlMapper xmlMapper = new XmlMapper();
        try {
            ResponseDescription prop = xmlMapper.readValue(xml, ResponseDescription.class);
            System.out.println(prop);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
