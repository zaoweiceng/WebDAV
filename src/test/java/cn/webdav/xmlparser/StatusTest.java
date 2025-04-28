package cn.webdav.xmlparser;

import cn.webdav.pojo.webdav.Allprop;
import cn.webdav.pojo.webdav.Href;
import cn.webdav.pojo.webdav.Status;
import com.alibaba.druid.support.spring.stat.annotation.Stat;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.junit.Test;

public class StatusTest {
    @Test
    public void test(){
        Status status = new Status(200);
        XmlMapper xmlMapper = new XmlMapper();
        try {
            String xml = xmlMapper.writeValueAsString(status);
            System.out.println(xml);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
