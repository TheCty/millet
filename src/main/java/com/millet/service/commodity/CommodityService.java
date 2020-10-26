package com.millet.service.commodity;

import com.millet.pojo.Commodity;
import com.millet.util.page;

import java.util.List;

public interface CommodityService {
    //添加
    boolean insert(Commodity pojo);
    //修改
    boolean update(Commodity pojo);
    //删除
    boolean delete(Commodity pojo);
    //查询
    List<Commodity> list(Commodity pojo);
    //查单个
    Commodity single(String name);
    //条件查询
    List<Commodity> list(Commodity pojo, page p);
    //查询总条数
    int MaxCount(Commodity pojo);
}
