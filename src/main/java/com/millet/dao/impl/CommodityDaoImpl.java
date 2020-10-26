package com.millet.dao.impl;

import com.millet.dao.utilDao;
import com.millet.pojo.Classify;
import com.millet.pojo.Commodity;
import com.millet.pojo.User;
import com.millet.util.baseDao;
import com.millet.util.page;
import org.junit.Test;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CommodityDaoImpl extends baseDao implements utilDao<Commodity> {
    public boolean insert(Commodity pojo) {
        String sql="INSERT INTO commodity VALUES(DEFAULT,?,?,?,DEFAULT,?,?,DEFAULT)";
        return super.update(sql,new Object[]{pojo.getImg(),pojo.getName(),pojo.getClassifyId().getId(),pojo.getPrice(),pojo.getContext()});
    }

    public boolean update(Commodity pojo) {
        String sql="update commodity set img = ? , name = ? ,classifyId = ? ,count = ? , price = ? , context = ? , tag = ? where id = ?";
        return super.update(sql,new Object[]{pojo.getImg(),pojo.getName(),pojo.getClassifyId().getId(),pojo.getCount(),pojo.getPrice(),pojo.getContext(),pojo.getTag(),pojo.getId()});
    }

    public boolean delete(Commodity pojo) {
        String sql ="delete form commodity where id =?";
        return super.update(sql,new Object[]{pojo.getId()});
    }


    public List<Commodity> list(Commodity pojo ) {
        List<Commodity> list=new ArrayList<Commodity>();
        String sql="select * from Commodity where tag = 0 ";
        List<Object> array = new ArrayList<Object>();
        StringBuffer sbr=new StringBuffer(sql);
        if (pojo != null) {

            if (pojo.getName() != null) {
                sbr.append("and name like concat('%',?,'%') ");
                array.add(pojo.getName());
            }
            if (pojo.getClassifyId() != null && pojo.getClassifyId().getId()!=0) {
                sbr.append("and classifyId = ? ");
                array.add(pojo.getClassifyId().getId());
            }
        }
        Object[] objects = array.toArray();
        sql=sbr.toString();
        ResultSet rs=super.query(sql,objects);
        try {
            while (rs.next()) {
                Commodity commodity = new Commodity(
                        rs.getInt("id"),
                        rs.getString("img"),
                        rs.getString("name"),
                        new Classify(rs.getInt("classifyId"), null, 0),
                        rs.getByte("count"),
                        rs.getDouble("price"),
                        rs.getString("context"),
                        rs.getInt("tag")
                );
                list.add(commodity);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return list;
    }

    public Commodity single(String name) {
        String sql="select * from Commodity where id = ? ";
        ResultSet rs=super.query(sql,new Object[]{name});
        Commodity commodity=null;
        try {
            while (rs.next()) {
                 commodity = new Commodity(
                        rs.getInt("id"),
                        rs.getString("img"),
                        rs.getString("name"),
                        new Classify(rs.getInt("classifyId"), null, 0),
                        rs.getByte("count"),
                        rs.getDouble("price"),
                        rs.getString("context"),
                        rs.getInt("tag")
                );

            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return commodity ;
    }


    public int MaxCount(Commodity pojo) {
        String sql="select Count(id) from Commodity where tag = 0 ";
        List<Object> array = new ArrayList<Object>();
        StringBuffer sbr=new StringBuffer(sql);
        if (pojo != null) {

            if (pojo.getName() != null && !pojo.getName().equals("")) {
                sbr.append("and name like concat('%',?,'%') ");
                array.add(pojo.getName());
            }
            if (pojo.getClassifyId() != null && pojo.getClassifyId().getId()!=0) {
                sbr.append("and classifyId = ? ");
                array.add(pojo.getClassifyId().getId());
            }
        }
        sql=sbr.toString();
        Object[] objects = array.toArray();
        int count=0;
        ResultSet rs=super.query(sql,objects);
        try {
            if (rs.next()) {
                count=rs.getInt(1);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return count ;
    }

    public List<Commodity> list(Commodity pojo, page p) {
        List<Commodity> list=new ArrayList<Commodity>();
        String sql="select * from Commodity where tag = 0 ";
        List<Object> array = new ArrayList<Object>();
        StringBuffer sbr=new StringBuffer(sql);
        if (pojo != null) {
            if (pojo.getName() != null) {
                sbr.append("and name like concat('%',?,'%') ");
                array.add(pojo.getName());
            }
            if (pojo.getClassifyId() != null && pojo.getClassifyId().getId()!=0) {
                sbr.append("and classifyId = ? ");
                array.add(pojo.getClassifyId().getId());
            }
        }
        if (p != null) {
            sbr.append("limit ? , ? ");
            array.add((p.getPageNow()-1)*p.getPageSize());
            array.add(p.getPageSize());
        }
        sql=sbr.toString();
        Object[] objects = array.toArray();
        ResultSet rs=super.query(sql,objects);
        try {
            while (rs.next()) {
                Commodity commodity = new Commodity(
                        rs.getInt("id"),
                        rs.getString("img"),
                        rs.getString("name"),
                        new Classify(rs.getInt("classifyId"), null, 0),
                        rs.getByte("count"),
                        rs.getDouble("price"),
                        rs.getString("context"),
                        rs.getInt("tag")
                );
                list.add(commodity);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return list;
    }

}
