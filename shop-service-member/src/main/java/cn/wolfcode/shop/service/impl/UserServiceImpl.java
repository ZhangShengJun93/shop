package cn.wolfcode.shop.service.impl;

import cn.wolfcode.shop.domain.User;
import cn.wolfcode.shop.mapper.UserMapper;
import cn.wolfcode.shop.service.IUserService;
import com.alibaba.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class UserServiceImpl implements IUserService {


    public static Map<String, Object> userCache = new ConcurrentHashMap<String,Object>();
    @Autowired
    private UserMapper userMapper;

    @Override
    public User register(User user) {

        if (exists(user.getUsername())) {

            return null;
        } else {
            userMapper.insert(user);
            return user;
        }
    }

    @Override
    public boolean exists(String username) {
        User user = userMapper.exists(username);
        return user != null ? true : false;
    }

    @Override
    public String login(String username, String password) {
        User user = userMapper.login(username, password);
        if (user == null) {
            throw new RuntimeException("账号或密码错误");
        } else {
            String token = creatToken();
            userCache.put(token, user);
            return token;
        }
    }

	@Override
	public void update(User user) {
		userMapper.updateByPrimaryKey(user);
	}

	private String creatToken() {
        return UUID.randomUUID().toString();
    }

}
