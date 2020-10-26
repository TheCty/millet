package com.millet.dao.impl;

import com.millet.dao.utilDao;
import com.millet.pojo.Shopping;
import com.millet.pojo.User;
import com.millet.util.baseDao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ShoppingDaoImpl extends baseDao implements utilDao<Shopping> {
    public boolean insert(Shopping pojo) {

//        List<Shopping> list = list(pojo);
//
//        for (Shopping shopping : list) {
//            System.out.println(shopping);
//            if (shopping.getUserName().getName().equals(pojo.getUserName().getName())) {
//                if (shopping.getCommodityIds().equals(pojo.getCommodityIds())) {
//                    int row=Integer.parseInt(shopping.getCounts())+Integer.parseInt(pojo.getCounts());
//                    shopping.setCounts(row+"");
//                   return update(shopping);
//                }else{
//                    String sql="INSERT INTO shopping VALUES(DEFAULT,?,?,?)";
//                    return super.update(sql,new Object[]{pojo.getUserName().getName(),pojo.getCommodityIds(),pojo.getCounts()});
//
//                }
//            }else {
                String sql = "INSERT INTO shopping VALUES(DEFAULT,?,?,?)";
                return super.update(sql, new Object[]{pojo.getUserName().getName(), pojo.getCommodityIds(), pojo.getCounts()});
//            }

//            }



    }

    public boolean update(Shopping pojo) {
        String sql="update shopping set commodityIds = ? , counts = ? where id = ? ";
        return super.update(sql,new Object[]{pojo.getCommodityIds(),pojo.getCounts(),pojo.getId()});
    }

    public boolean delete(Shopping pojo) {
        String sql="delete from shopping where id = ? ";
        return super.update(sql,new Object[]{pojo.getId()});
    }

    public List<Shopping> list(Shopping pojo) {
        String sql="select * from shopping where username = ? ";
        List<Shopping> list=new ArrayList<Shopping>();
        ResultSet rs = super.query(sql, new Object[]{pojo.getUserName().getName()});

        try {
            while (rs.next()) {
                Shopping shopping = new Shopping(
                        rs.getInt("id"),
                        new User(rs.getString("username"), null),
                        rs.getString("commodityIds"),
                        rs.getString("counts")
                );
                list.add(shopping);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return list;
    }

    public Shopping single(String name) {
        return null;
    }
}
