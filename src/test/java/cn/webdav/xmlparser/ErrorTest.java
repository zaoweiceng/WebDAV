package cn.webdav.xmlparser;

import cn.webdav.pojo.webdav.Allprop;
import cn.webdav.pojo.webdav.Error;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.junit.Test;

public class ErrorTest {

    @Test
    public void test(){
        Error error = new Error();
        XmlMapper xmlMapper = new XmlMapper();
        try {
            String xml = xmlMapper.writeValueAsString(error);
            System.out.println(xml);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
