package cn.webdav.common.utils.webdav;

import cn.webdav.common.constant.LanguageConstant;
import cn.webdav.pojo.entity.SelectedProp;
import cn.webdav.pojo.webdav.Include;
import cn.webdav.pojo.webdav.Prop;
import cn.webdav.pojo.webdav.ResourceType;
import cn.webdav.vfs.FileSystem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PropUtil {

    @Autowired
    private FileSystem fs;

    public Prop getProp(String path, SelectedProp selectedProp) {
        Prop p = new Prop();
        String fileName = path.substring(path.lastIndexOf("/") + 1);
        if (selectedProp.isAuthor()){
            p.setAuthor(fs.getAuthor(path));
        }
        if (selectedProp.isCreationdate()){
            p.setCreationdate(fs.getCreationDate(path));
        }
        if (selectedProp.isDisplayname()){
            p.setDisplayName(fileName);
        }
        if (selectedProp.isGetcontentlanguage()){
            p.setGetcontentlanguage(LanguageConstant.CHINESE_CHINA);
        }
        if (selectedProp.isGetcontentlength()){
            p.setGetcontentlength(fs.getFileSize(path));
        }
        if (selectedProp.isGetetag()){
            p.setGetetag(fs.getETag(path));
        }
        if (selectedProp.isGetlastmodified()){
            p.setGetlastmodified(fs.getLastModifiedDate(path));
        }
        if (fs.isDirectory(path)){
            p.setResourcetype(ResourceType.builder().build());
        } else if (fs.isFile(path)) {
            p.setGetcontentlength(fs.getFileSize(path));
        }
        if (fs.isLocked(path)){
            p.setSupportedlock(LockUtil.buildSupportedLock(fs.getLock(path)));
        }
        return p;
    }

    public Prop getAllProp(String path) {
        SelectedProp selectedProp = SelectedProp.allTrue();
        return getProp(path, selectedProp);
    }

    public static SelectedProp parseSelectedProp(Include include) {
        return SelectedProp.builder()
                .displayname(include.getDisplayName() != null)
                .getcontentlength(include.getGetcontentlength() != null)
                .author(include.getAuthor() != null)
                .creationdate(include.getCreationdate() != null)
                .getcontentlanguage(include.getGetcontentlanguage() != null)
                .getcontenttype(include.getGetcontenttype() != null)
                .getetag(include.getGetetag()!= null)
                .getlastmodified(include.getGetlastmodified() != null)
                .build();
    }
}
