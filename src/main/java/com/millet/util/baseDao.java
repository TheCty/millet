package com.millet.util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class baseDao {
    private static String driver;
    private static String url;
    private static String username;
    private static String password;
    static {
        //通过类加载器实现，类加载的初始化
        Properties properties = new Properties();
        InputStream in = baseDao.class.getClassLoader().getResourceAsStream("db.properties");
        try {
            properties.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
        driver=properties.getProperty("driver");
        url=properties.getProperty("url");
        username=properties.getProperty("username");
        password=properties.getProperty("password");
        //加载驱动
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    private Connection getConnection(){
        Connection conn=null;
        try {
            conn= DriverManager.getConnection(url,username,password);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return conn;
    }
    private void CloseAll(Connection conn, PreparedStatement pstmt, ResultSet rs){
        try {
            if (rs != null) {
                rs.close();
            }
            if (pstmt != null) {
                pstmt.close();
            }
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    public boolean update(String sql,Object[] objects){
        Connection conn=getConnection();
        PreparedStatement pstmt=null;
        int row=0;
        try {
            pstmt=conn.prepareStatement(sql);
            if (objects != null) {
                for (int i = 0; i < objects.length; i++) {
                    pstmt.setObject(i+1,objects[i]);
                }
            }
            row=pstmt.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            CloseAll(conn,pstmt,null);
        }
        return row>0;
    }
    public ResultSet query(String sql,Object[] objects){
        Connection conn=getConnection();
        PreparedStatement pstmt=null;
        ResultSet rs=null;
        try {
            pstmt=conn.prepareStatement(sql);
            if (objects != null) {
                for (int i = 0; i < objects.length; i++) {
                    pstmt.setObject(i+1,objects[i]);
                }
            }
            rs=pstmt.executeQuery();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return rs;
    }
}
