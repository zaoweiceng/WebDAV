package cn.webdav.vfs;

import cn.webdav.pojo.entity.DAVLock;
import cn.webdav.pojo.webdav.lock.ActiveLock;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

public interface FileSystem {

    public InputStream getFileDownloadStream(String path);

    public Long getFileSize(String path);

    public List<String> getFileList(String path);

    public String getAuthor(String path);

    public String getCreationDate(String path);

    public String getLastModifiedDate(String path);

    public String getETag(String path);

    public DAVLock getLock(String path);

    public boolean createLock(String path, ActiveLock activeLock);

    public boolean createFile(String path, String content, OutputStream outputStream);

    public boolean deleteFile(String path);

    public boolean deleteLock(String path, ActiveLock activeLock);

    public boolean isFile(String path);

    public boolean isDirectory(String path);

    public boolean isLocked(String path);


}
