package com.millet.dao.impl;

import com.millet.dao.utilDao;
import com.millet.pojo.User;
import com.millet.service.user.UserService;
import com.millet.service.user.UserServiceImpl;
import com.millet.util.baseDao;
import org.junit.Test;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl extends baseDao implements utilDao<User> {
    public boolean insert(User pojo) {
        String sql="INSERT INTO USER VALUES(?,?,DEFAULT)";
        return super.update(sql,new Object[]{pojo.getName(),pojo.getPassword()});
    }

    public boolean update(User pojo) {
        String sql="update user set password = ? where name = ?";
        return update(sql,new Object[]{pojo.getPassword(),pojo.getName()});
    }

    public boolean delete(User pojo) {
        String sql="delete form user where name = ?";
        return super.update(sql,new Object[]{pojo.getName()});
    }

    public List<User> list(User pojo) {
        List<User> list=new ArrayList<User>();
        String sql="select * from user where tag = 0";
        ResultSet rs=super.query(sql,null);
        try {
            while (rs.next()) {
                User user = new User(
                        rs.getString("name"),
                        rs.getString("password"),
                        rs.getInt("tag")
                );
                list.add(user);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return list;
    }

    public User single(String name) {
        String sql="select * from user where name = ? ";
        ResultSet rs=super.query(sql,new Object[]{name});
        User user=null;
        try {
            while (rs.next()) {
                 user = new User(
                        rs.getString("name"),
                        rs.getString("password"),
                        rs.getInt("tag")
                );
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return user;
    }

    public User enter(User ur) {
        String sql="select * from user where name = ? and password = ?";
        ResultSet rs=super.query(sql,new Object[]{ur.getName(),ur.getPassword()});
        User user=null;
        try {
            while (rs.next()) {
                user = new User(
                        rs.getString("name"),
                        rs.getString("password"),
                        rs.getInt("tag")
                );
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return user;
    }
    @Test
    public void test(){
        User admin = single("admin");
        System.out.println(admin);
    }
}
