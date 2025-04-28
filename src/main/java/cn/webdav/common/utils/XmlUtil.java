package cn.webdav.common.utils;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

public class XmlUtil {

    public static <T> T  parse(String xml, Class <T> clazz) {
        try {
            XmlMapper xmlMapper = new XmlMapper();
            return xmlMapper.readValue(xml, clazz);
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException("Failed to parse XML: " + e.getMessage(), e);
        }
    }
}
