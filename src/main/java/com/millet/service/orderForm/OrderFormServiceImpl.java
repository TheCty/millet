package com.millet.service.orderForm;

import com.millet.dao.impl.OrderFormDaoImpl;
import com.millet.dao.utilDao;
import com.millet.pojo.Orderform;

import java.util.List;

public class OrderFormServiceImpl implements OrderFormService{
    private utilDao orderformService;

    public OrderFormServiceImpl() {
        this.orderformService = new OrderFormDaoImpl();
    }


    public boolean insert(Orderform pojo) {
        return orderformService.insert(pojo);
    }

    public boolean update(Orderform pojo) {
        return orderformService.update(pojo);
    }

    public boolean delete(Orderform pojo) {
        return orderformService.delete(pojo);
    }

    public List<Orderform> list(Orderform pojo) {
        return orderformService.list(pojo);
    }

    public Orderform single(String name) {
        return (Orderform) orderformService.single(name);
    }
}
