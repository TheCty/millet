package com.millet.servlet;



import com.millet.pojo.User;
import com.millet.service.user.UserService;
import com.millet.service.user.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class registerServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String newPassword = req.getParameter("newPassword");
        UserService userService=new UserServiceImpl();
        String msg="";

        if ((username==null || username.equals("")) || (password==null || password.equals("")) || (newPassword==null || newPassword.equals(""))){
            msg="用户名或密码不能为空";
            req.setAttribute("msg",msg);
            req.getRequestDispatcher("/register.jsp").forward(req,resp);
        }else  if (!newPassword.equals(password)){
            msg="密码不一致";
            req.setAttribute("msg",msg);
            req.getRequestDispatcher("/register.jsp").forward(req,resp);
        }else{
            User user = new User(username, password);
            boolean is=false;
            System.out.println(is);
            if ((is=userService.insert(user))) {
                resp.sendRedirect("/index.jsp");
            }
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
