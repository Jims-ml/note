package com.jims.utils;

import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.Properties;

/**
 * jdbc工具类
 * @author JIMS
 * @create 2020-01-29-16:17
 */
public class JDBCUtils {
    private static String url;
    private static String user;
    private static String password;
    //private static String driver;

    //配置文件读取
    static {
        try {
            //创建properties集合
            Properties pro = new Properties();
            //加载配置文件
            ClassLoader classLoader = JDBCUtils.class.getClassLoader();
            URL res = classLoader.getResource("jdbc.properties");
            String path = res.getPath();
            pro.load(new FileReader(path));
            //3获取数据，赋值
            url = pro.getProperty("url");
            user = pro.getProperty("user");
            password = pro.getProperty("password");
            //driver = pro.getProperty("driver");
            //4注册驱动
            //Class.forName(driver);mysql驱动jar包 5.x以后自动注册驱动
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //获取连接
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url,user,password);
    }

    //关闭连接
    public static void close(ResultSet rs, PreparedStatement pstmt, Connection conn) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (pstmt != null) {
            try {
                pstmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
