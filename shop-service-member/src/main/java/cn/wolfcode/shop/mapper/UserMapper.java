package cn.wolfcode.shop.mapper;

import cn.wolfcode.shop.domain.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {
    int deleteByPrimaryKey(Long id);

    int insert(User record);

    User selectByPrimaryKey(Long id);

    List<User> selectAll();

    int updateByPrimaryKey(User record);

	User login(@Param("username") String username,@Param("password") String password);

	User exists(String username);
}