package com.millet.dao.impl;

import com.millet.dao.utilDao;
import com.millet.pojo.Classify;
import com.millet.pojo.User;
import com.millet.util.baseDao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClassifyDaoImpl extends baseDao implements utilDao<Classify> {
    public boolean insert(Classify pojo) {
        String sql="INSERT INTO classify(name) VALUES (?)";
        return super.update(sql,new Object[]{pojo.getName()});
    }

    public boolean update(Classify pojo) {
        String sql="update classify set name = ? ,tag = ?";
        return super.update(sql,new Object[]{pojo.getName(),pojo.getTag()});
    }

    public boolean delete(Classify pojo) {
        String sql="delete form classify where id = ?";
        return super.update(sql,new Object[]{pojo.getId()});
    }

    public List<Classify> list(Classify pojo) {
        List<Classify> list=new ArrayList<Classify>();
        String sql="select * from Classify ";
        ResultSet rs=super.query(sql,null);
        try {
            while (rs.next()) {
                Classify Classify = new Classify(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getInt("tag")
                );
                list.add(Classify);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return list;
    }

    public Classify single(String name) {
        String sql="select * from Classify where name = ?";
        ResultSet rs=super.query(sql,new Object[]{name});
        Classify Classify=null;
        try {
            while (rs.next()) {
                 Classify = new Classify(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getInt("tag")
                );
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return Classify;
    }
}
