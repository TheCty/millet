package com.millet.servlet;

import com.millet.pojo.Commodity;
import com.millet.service.commodity.CommodityService;
import com.millet.service.commodity.CommodityServiceImpl;
import com.millet.util.page;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class selectServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CommodityService comm=new CommodityServiceImpl();

        String id = req.getParameter("id");

        Commodity commodity = comm.single(id);


        System.out.println(commodity);

        req.setAttribute("commodity",commodity);



       req.getRequestDispatcher("/forbid/commodity.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
