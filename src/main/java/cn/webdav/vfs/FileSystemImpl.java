package cn.webdav.vfs;

import cn.webdav.common.constant.LockConstant;
import cn.webdav.pojo.entity.DAVLock;
import cn.webdav.pojo.webdav.Href;
import cn.webdav.pojo.webdav.lock.ActiveLock;
import cn.webdav.pojo.webdav.lock.LockScope;
import cn.webdav.pojo.webdav.lock.LockType;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


@Component
public class FileSystemImpl implements FileSystem {

    private static final DateTimeFormatter DATE_TIME_FORMATTER =
            DateTimeFormatter.ofPattern("EEE, dd MMM yyyy HH:mm:ss", Locale.US);


    // TODO: 获取文件输入流
    public InputStream getFileDownloadStream(String path) {
        // 假设文件内容为 "hello world"
        // 通过接口获取文件流（示例：HTTP 请求获取文件）
        // URL fileUrl = new URL("https://example.com/api/get-file");
        // HttpURLConnection connection = (HttpURLConnection) fileUrl.openConnection();
        // InputStream inputStream = connection.getInputStream();
        // 具体实现时使用websocket向bwtfs获取文件流
        String content = "hello world!!!!!!!!";
        return new ByteArrayInputStream(content.getBytes());
    }

    // TODO: 获取文件大小
    @Override
    public Long getFileSize(String path) {
        String content = "hello world!!!!!!!!";
        return (long) content.getBytes().length;
    }

    // TODO: 获取文件列表
    @Override
    public List<String> getFileList(String path) {
        if(path.contains("ddd")){
            return List.of("aaa.txt", "h.png", "c.txt", "ccc");
        }
        if (path.contains("ccc")){
            return List.of("d.txt", "h", "c.txt");
        }
        return new ArrayList<String>();
    }

    // TODO: 获取文件作者
    @Override
    public String getAuthor(String path) {
        return "ccc";
    }

    // TODO: 获取文件创建时间
    @Override
    public String getCreationDate(String path) {
        LocalDateTime now = LocalDateTime.now();
        return now.format(DATE_TIME_FORMATTER);
    }

    // TODO: 获取文件最后修改时间
    @Override
    public String getLastModifiedDate(String path) {
        LocalDateTime now = LocalDateTime.now();
        return now.format(DATE_TIME_FORMATTER);
    }

    // TODO: 获取文件ETag
    // ETag 是一个用于标识文件内容的唯一标识符，通常使用 MD5 或 SHA-1 哈希算法计算得到。
    // 直接使用文件token的md5 hash作为文件的ETag
    @Override
    public String getETag(String path) {
        return "0123456789";
    }

    // TODO: 获取文件锁定信息
    @Override
    public DAVLock getLock(String path) {
        DAVLock activeLock = DAVLock.builder()
                .lockScope(LockConstant.LOCK_SCOPE_EXCLUSIVE)
                .lockType(LockConstant.LOCK_SCOPE_SHARED)
                .depth("infinity")
                .timeout("Second-3600")
                .lockToken("122")
                .lockRoot(path)
                .owner("ccc")
                .build();
        if (path.contains("aaa")){
            return activeLock;
        }
        return null;
    }

    // TODO: 创建文件锁
    @Override
    public boolean createLock(String path, ActiveLock activeLock) {
        return false;
    }

    // TODO: 创建文件
    @Override
    public boolean createFile(String path, String content, OutputStream outputStream) {
        return false;
    }


    // TODO: 删除文件
    @Override
    public boolean deleteFile(String path) {
        return false;
    }

    // TODO: 删除文件锁
    @Override
    public boolean deleteLock(String path, ActiveLock activeLock) {
        return false;
    }

    // TODO: 判断路径是否指向的文件
    public boolean isFile(String path) {
        String fileName = path.substring(path.lastIndexOf("/") + 1);
        if ("ddd".equals(fileName)){
            return false;
        }
        if ("ccc".equals(fileName)){
            return false;
        }
        return true;
    }

    // TODO: 判断路径是否指向的目录
    @Override
    public boolean isDirectory(String path) {
        String fileName = path.substring(path.lastIndexOf("/") + 1);
        if ("ddd".equals(fileName)){
            return true;
        }
        if ("ccc".equals(fileName)){
            return true;
        }
        return false;
    }

    // TODO: 判断路径是否指向的资源是否被锁定
    @Override
    public boolean isLocked(String path) {
//        System.out.println(path);
        if (path.contains("aaa")){
            return true;
        }
        return false;
    }


}
