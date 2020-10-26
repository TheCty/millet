package com.millet.service.user;

import com.millet.dao.impl.UserDaoImpl;
import com.millet.dao.utilDao;
import com.millet.pojo.User;
import org.junit.Test;

import java.util.List;

public class UserServiceImpl implements UserService{

    private utilDao userDao;

    public UserServiceImpl() {
        userDao = new UserDaoImpl();
    }

    public User enter(User ur) {
        UserDaoImpl udi = (UserDaoImpl)userDao;
        return udi.enter(ur);
    }

    public boolean insert(User ur) {
        return userDao.insert(ur);
    }

    public boolean update(User ur) {
        return userDao.update(ur);
    }

    public boolean delete(User ur) {
        return userDao.delete(ur);
    }

    public List<User> list(User ur) {
        return userDao.list(ur);
    }

    public User single(String name) {
        return (User) userDao.single(name);
    }

    @Test
    public void test(){
        UserService user=new UserServiceImpl();
        User admin = user.single("admin");
        System.out.println(admin);
    }
}
