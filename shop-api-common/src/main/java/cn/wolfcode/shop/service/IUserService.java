package cn.wolfcode.shop.service;

import cn.wolfcode.shop.domain.User;

public interface IUserService {
    /**
     * 注册
     *
     * @param user
     * @return
     */
    User register(User user);

    /**
     * 验证用户名
     *
     * @param username
     * @return
     */
    boolean exists(String username);

    /**
     * 登陆操作
     * @param username
     * @param password
     * @return 返回一个随机字符串  作为cokies中的id
     */
    String login(String username, String password);

	/**
	 * 修改User信息
	 * @param user
	 */
	void update(User user);
}
