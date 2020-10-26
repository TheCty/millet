package com.millet.dao;

import java.util.List;

public interface utilDao<T> {
    //添加
     boolean insert(T pojo);
    //修改
    boolean update(T pojo);
    //删除
    boolean delete(T pojo);
    //查询
    List<T> list(T pojo);
    //查单个
    T single(String name);
}
