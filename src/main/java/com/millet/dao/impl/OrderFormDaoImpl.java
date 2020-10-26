package com.millet.dao.impl;

import com.millet.dao.utilDao;
import com.millet.pojo.Address;
import com.millet.pojo.Orderform;
import com.millet.pojo.Shopping;
import com.millet.pojo.User;
import com.millet.util.baseDao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderFormDaoImpl extends baseDao implements utilDao<Orderform> {
    public boolean insert(Orderform pojo) {
        String sql="INSERT INTO orderForm VALUES(?,?,?,?,?,DEFAULT)";
        return super.update(sql,new Object[]{pojo.getName(),pojo.getUserName().getName(),pojo.getAddressId(),pojo.getCommodityIds(),pojo.getCounts()});
    }

    public boolean update(Orderform pojo) {
        return false;
    }

    public boolean delete(Orderform pojo) {
        return false;
    }

    public List<Orderform> list(Orderform pojo) {
        List<Orderform> list=new ArrayList<Orderform>();
        String sql="select * from Orderform where 1=1 ";
        Object[] objects=null;
        if (pojo != null) {
            sql+="and username = ? ";
            objects=new Object[]{pojo.getUserName().getName()};
        }
        ResultSet rs=super.query(sql,objects);
        try {
            while (rs.next()) {
                Orderform orderform = new Orderform(
                        rs.getString("name"),
                        new User(rs.getString("username"), null),
                        new Address(rs.getInt("id"), null, null, null, null),
                        rs.getString("commodityIds"),
                        rs.getString("counts"),
                        rs.getInt("tag")
                );
                list.add(orderform);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return list;
    }

    public Orderform single(String name) {
        List<Orderform> list=new ArrayList<Orderform>();
        String sql="select * from Orderform where 1=1 and name = ?  ";
        ResultSet rs=super.query(sql,new Object[]{name});
        Orderform orderform =null;
        try {
            while (rs.next()) {
                 orderform = new Orderform(
                        rs.getString("name"),
                        new User(rs.getString("username"), null),
                        new Address(rs.getInt("id"), null, null, null, null),
                        rs.getString("commodityIds"),
                        rs.getString("counts"),
                        rs.getInt("tag")
                );
                list.add(orderform);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return orderform ;
    }
}
