package com.millet.service.user;

import com.millet.pojo.User;

import java.util.List;

public interface UserService {
    //注册
    boolean insert(User ur);
    //修改
    boolean update(User ur);
    //删除
    boolean delete(User ur);
    //查询
    List<User> list(User ur);
    //查单个
    User single(String name);
    //登入
    User enter(User ur);
}
