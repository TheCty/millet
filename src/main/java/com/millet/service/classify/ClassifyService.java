package com.millet.service.classify;

import com.millet.pojo.Classify;

import java.util.List;

public interface ClassifyService {
    //添加
    boolean insert(Classify pojo);
    //修改
    boolean update(Classify pojo);
    //删除
    boolean delete(Classify pojo);
    //查询
    List<Classify> list(Classify pojo);
    //查单个
    Classify single(String name);
}
