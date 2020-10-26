package com.millet.service.classify;

import com.millet.dao.impl.ClassifyDaoImpl;
import com.millet.dao.utilDao;
import com.millet.pojo.Classify;

import java.util.List;

public class ClassifyServiceImpl implements ClassifyService{
    private utilDao classifyService;

    public ClassifyServiceImpl() {
        this.classifyService = new ClassifyDaoImpl();
    }

    public boolean insert(Classify pojo) {
        return classifyService.insert(pojo);
    }

    public boolean update(Classify pojo) {
        return classifyService.update(pojo);
    }

    public boolean delete(Classify pojo) {
        return classifyService.delete(pojo);
    }

    public List<Classify> list(Classify pojo) {
        return classifyService.list(pojo);
    }

    public Classify single(String name) {
        return (Classify) classifyService.single(name);
    }
}
