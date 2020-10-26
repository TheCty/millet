package com.millet.dao.impl;

import com.millet.dao.utilDao;
import com.millet.pojo.Address;
import com.millet.pojo.User;
import com.millet.util.baseDao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AddressDaoImpl extends baseDao implements utilDao<Address> {
    public boolean insert(Address pojo) {
        String sql="INSERT INTO address VALUES(DEFAULT,?,?,?,?)";
        return super.update(sql,new Object[]{pojo.getUserName().getName(),pojo.getName(),pojo.getPhone(),pojo.getAddress()});
    }

    public boolean update(Address pojo) {
        String sql="update address set name = ? ,phone = ? , address = ? where id = ?";
        return super.update(sql,new Object[]{pojo.getName(),pojo.getPhone(),pojo.getAddress(),pojo.getId()});
    }

    public boolean delete(Address pojo) {
        String sql="delete form address where id = ?";
        return super.update(sql,new Object[]{pojo.getId()});
    }

    public List<Address> list(Address pojo) {
        List<Address> list=new ArrayList<Address>();
        String sql ="select * from address where username= ?";
        ResultSet rs = super.query(sql, new Object[]{pojo.getUserName().getName()});

        try {
            while (rs.next()) {
                Address address = new Address(
                        rs.getInt("id"),
                        new User(rs.getString("name"), null),
                        rs.getString("name"),
                        rs.getString("phone"),
                        rs.getString("address")
                );
                list.add(address);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return list;
    }

    public Address single(String name) {
        String sql ="select * from address where id= ?";
        ResultSet rs = super.query(sql, new Object[]{name});
        Address address=null;
        try {
            while (rs.next()) {
                 address = new Address(
                        rs.getInt("id"),
                        new User(rs.getString("name"), null),
                        rs.getString("name"),
                        rs.getString("phone"),
                        rs.getString("address")
                );
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return address ;
    }
}
