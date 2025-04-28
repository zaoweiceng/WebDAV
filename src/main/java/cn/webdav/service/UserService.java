package cn.webdav.service;

import cn.webdav.pojo.entity.User;
import cn.webdav.pojo.vo.UserLoginVO;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.stereotype.Service;

public interface UserService extends IService<User> {

    public UserLoginVO login(User user);

    String getGroupById();

    String assignWebDAVToken();
}
