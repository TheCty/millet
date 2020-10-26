package com.millet.servlet;

import com.millet.pojo.Shopping;
import com.millet.pojo.User;
import com.millet.service.commodity.CommodityService;
import com.millet.service.commodity.CommodityServiceImpl;
import com.millet.service.shopping.ShoppingService;
import com.millet.service.shopping.ShoppingServiceImpl;
import com.millet.util.Constant;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class shoppingServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String method = req.getParameter("method");

        CommodityService commodityService=new CommodityServiceImpl();
        ShoppingService shoppingService=new ShoppingServiceImpl();
        User user = (User) req.getSession().getAttribute(Constant.USER_SESSION);

        if (method.equals("insert")) {
            String id = req.getParameter("id");
            String count = req.getParameter("count");
//        String msg="";
//        if (Integer.parseInt(count)<0) {
//            msg="数量不能为负数";
//            req.setAttribute(msg,msg);
//            req.getRequestDispatcher("/forbid/commodity.jsp").forward(req,resp);
//        }


            Shopping shopping = new Shopping(0,user,id,count);

            List<Shopping> list = shoppingService.list(shopping);

            boolean is=false;

            for (Shopping shopping1 : list) {
                if (shopping1.getUserName().getName().equals(user.getName())) {
                    if (shopping1.getCommodityIds().equals(shopping.getCommodityIds())) {
                        is=true;
                        int row=Integer.parseInt(shopping1.getCounts())+Integer.parseInt(shopping.getCounts());
                        shopping1.setCounts(row+"");
                        shoppingService.update(shopping1);
                        break;
                    }
                }
            }

            if (!is){
                if (shoppingService.insert(shopping)) {
                    resp.getWriter().write("<script type=\"text/javascript\"> alert('加入购物车成功'); </script>");
                }else{
                    resp.getWriter().write("<script type=\"text/javascript\"> alert('加入购物车失败'); </script>");
                }

            }

            resp.sendRedirect("/commodity?id="+id);


        } else if(method.equals("show")){
            List<Shopping> shoppingList = shoppingService.list(new Shopping(0, user, null, null));

            req.getSession().setAttribute("shoppingList",shoppingList);

            resp.sendRedirect("/forbid/shopping.jsp");

        }else if(method.equals("delete")){
            String id = req.getParameter("id");
            Shopping shopping = new Shopping();
            shopping.setId(Integer.parseInt(id));
            if (shoppingService.delete(shopping)) {

                List<Shopping> shoppingList = shoppingService.list(new Shopping(0, user, null, null));

                req.getSession().setAttribute("shoppingList",shoppingList);


                resp.sendRedirect("/forbid/shopping.jsp");
            }
        }







    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
