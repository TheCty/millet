package com.millet.service.commodity;

import com.millet.dao.impl.CommodityDaoImpl;
import com.millet.dao.utilDao;
import com.millet.pojo.Classify;
import com.millet.pojo.Commodity;
import com.millet.util.page;
import org.junit.Test;

import java.util.List;

public class CommodityServiceImpl implements CommodityService {
    private CommodityDaoImpl commodityService;

    public CommodityServiceImpl() {
        commodityService = new CommodityDaoImpl();
    }

    public boolean insert(Commodity pojo) {
        return commodityService.insert(pojo);
    }

    public boolean update(Commodity pojo) {
        return commodityService.update(pojo);
    }

    public boolean delete(Commodity pojo) {
        return commodityService.delete(pojo);
    }

    public List<Commodity> list(Commodity pojo) {
        return commodityService.list(pojo);
    }

    public Commodity single(String name) {
        return (Commodity) commodityService.single(name);
    }

    public List<Commodity> list(Commodity pojo, page p) {
        return commodityService.list(pojo,p);
    }

    public int MaxCount(Commodity pojo) {
        return commodityService.MaxCount(pojo);
    }
    @Test
    public void test(){
        page page = new page();
        Commodity comm=new Commodity();
        comm.setName("30");
        Classify ciy=new Classify();
        ciy.setId(5);
        comm.setClassifyId(ciy);
        page.setTotalCount(MaxCount(comm));

        List<Commodity> list = list(comm, page);
        for (Commodity commodity : list) {
            System.out.println(commodity);
        }
    }

}
