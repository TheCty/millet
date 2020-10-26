package com.millet.service.orderForm;

import com.millet.pojo.Orderform;

import java.util.List;

public interface OrderFormService {
    //添加
    boolean insert(Orderform pojo);
    //修改
    boolean update(Orderform pojo);
    //删除
    boolean delete(Orderform pojo);
    //查询
    List<Orderform> list(Orderform pojo);
    //查单个
    Orderform single(String name);
}
