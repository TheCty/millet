package com.millet.service.address;

import com.millet.pojo.Address;
import java.util.List;

public interface AddressService {
    //添加
    boolean insert(Address address);
    //修改
    boolean update(Address address);
    //删除
    boolean delete(Address address);
    //查询
    List<Address> list(Address address);
    //查单个
    Address single(String name);

}
