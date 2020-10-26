package com.millet.service.shopping;

import com.millet.pojo.Shopping;

import java.util.List;

public interface ShoppingService {
    //添加
    boolean insert(Shopping pojo);
    //修改
    boolean update(Shopping pojo);
    //删除
    boolean delete(Shopping pojo);
    //查询
    List<Shopping> list(Shopping pojo);
    //查单个
    Shopping single(String name);
}
