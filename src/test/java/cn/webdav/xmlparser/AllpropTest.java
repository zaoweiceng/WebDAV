package cn.webdav.xmlparser;

import cn.webdav.pojo.webdav.Allprop;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.junit.Test;

public class AllpropTest {

    @Test
    public void test(){
        Allprop allprop = new Allprop();
        XmlMapper xmlMapper = new XmlMapper();
        try {
            String xml = xmlMapper.writeValueAsString(allprop);
            System.out.println(xml);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
