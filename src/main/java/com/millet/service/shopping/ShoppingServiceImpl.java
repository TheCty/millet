package com.millet.service.shopping;

import com.millet.dao.impl.ShoppingDaoImpl;
import com.millet.dao.utilDao;
import com.millet.pojo.Shopping;

import java.util.List;

public class ShoppingServiceImpl implements ShoppingService{
    private utilDao shoppingService;

    public ShoppingServiceImpl() {
        this.shoppingService = new ShoppingDaoImpl();
    }

    public boolean insert(Shopping pojo) {
        return shoppingService.insert(pojo);
    }

    public boolean update(Shopping pojo) {
        return shoppingService.update(pojo);
    }

    public boolean delete(Shopping pojo) {
        return shoppingService.delete(pojo);
    }

    public List<Shopping> list(Shopping pojo) {
        return shoppingService.list(pojo);
    }

    public Shopping single(String name) {
        return (Shopping) shoppingService.single(name);
    }
}
