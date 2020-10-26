package com.millet.servlet;

import com.millet.pojo.Classify;
import com.millet.pojo.Commodity;
import com.millet.pojo.User;
import com.millet.service.classify.ClassifyService;
import com.millet.service.classify.ClassifyServiceImpl;
import com.millet.service.commodity.CommodityService;
import com.millet.service.commodity.CommodityServiceImpl;
import com.millet.service.user.UserService;
import com.millet.service.user.UserServiceImpl;
import com.millet.util.Constant;
import com.millet.util.page;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class homepageServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //判断查询那一条
        String method = req.getParameter("method");
        //调的方法类
        UserService userService=new UserServiceImpl();
        CommodityService commService=new CommodityServiceImpl();
        ClassifyService classifyService=new ClassifyServiceImpl();
        //判断
        if (method.equals("enter")) {
            //获得网页输入的值
            String username = req.getParameter("username");
            String password = req.getParameter("password");

            //创建分页类
            page p=new page();
            //设置总条数
            p.setTotalCount(commService.MaxCount(null));
            //返回标志
            String msg="";
            User user=null;
            //判断用户是否登入成功
            if (username!=null && !username.equals("")){
                user=userService.single(username);
            }

            //判断用户名密码不能为空
            if (username==null || username.equals("") || password==null || password.equals("")){
                msg="用户名或密码不能为空";
                req.setAttribute("msg",msg);
                req.getRequestDispatcher("/index.jsp").forward(req,resp);
                //判断用户名或密码输入错误
            }else if(user==null && !user.getPassword().equals(password)){
                msg="用户名或密码错误";
                req.setAttribute("msg",msg);
                req.getRequestDispatcher("/index.jsp").forward(req,resp);
            }else{
                //查询商品和分类
                List<Commodity> list = commService.list(null, p);
                List<Classify> classifyList = classifyService.list(null);
                //存入session用户
                req.getSession().setAttribute(Constant.USER_SESSION,user);
                String url="";
                //判断是否为管理员

                if (user.getTag()==1) {
                    url="/forbid/homepage.jsp";
                }else{
                    url="/forbid/homepage.jsp";
                }


                //存入商品、分类、分页  类
                req.getSession().setAttribute("classifyList",classifyList);
                common(req, null, p, list,classifyList);
                resp.sendRedirect(url);
            }
        }else if(method.equals("select")){
            //判断当前页数
            if (!req.getParameter("pageNow").equals("") & req.getParameter("pageNow")!=null) {
                //获得名称、类别

                //创建商品
                Commodity comm=new Commodity();
                //添加查询条件
                if (req.getParameter("name")!=null && !req.getParameter("name").equals("")) {
                    comm.setName(req.getParameter("name"));
                }
                if (req.getParameter("classify")!=null && Integer.parseInt(req.getParameter("classify"))!=0) {
                    comm.setClassifyId(new Classify(Integer.parseInt(req.getParameter("classify")),null,0));
                }


                //添加分页类
                page p=new page();
                p.setTotalCount(commService.MaxCount(comm));
                p.setPageNow(Integer.parseInt(req.getParameter("pageNow")));
                //查询商品类
                List<Commodity> list = commService.list(comm, p);


                common(req, comm, p, list,null);
                resp.sendRedirect("/forbid/homepage.jsp");
            }


        }else if(method.equals("exit")){
            req.getSession().setAttribute(Constant.USER_SESSION,null);
            resp.sendRedirect("/index.jsp");
        }



    }

    private void common(HttpServletRequest req, Commodity comm, page p, List<Commodity> list,List<Classify> classifyList) {
        if (comm != null) {
            req.getSession().setAttribute("comm",comm);
        }
        if (classifyList != null) {
            req.getSession().setAttribute("classifyList",classifyList);
        }

        req.getSession().setAttribute("p",p);
        req.getSession().setAttribute("commList",list);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
