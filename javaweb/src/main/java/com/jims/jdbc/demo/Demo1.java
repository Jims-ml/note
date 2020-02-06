package com.jims.jdbc.demo;

import com.jims.jdbc.utils.JDBCUtils;
import org.junit.Test;

import java.sql.*;
import java.util.Scanner;

/**
 * @author JIMS
 * @create 2020-01-29-16:38
 */
public class Demo1 {

    @Test
    public void test1() {
        //1键盘录入
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入用户名：");
        String username = sc.nextLine();
        System.out.println("请输入密码：");
        String password = sc.nextLine();
        //2调用方法
        boolean flag = new Demo1().login(username, password);
        //3判断结果
        if (flag) {
            System.out.println("登录成功");
        } else {
            System.out.println("用户名或密码错误");
        }

    }

    public boolean login(String username, String password) {
        if (username == null || password == null) {
            return false;
        }
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            //注册驱动，获取连接
            conn = JDBCUtils.getConnection();
            //定义sql
            String sql = "select * from user where username = ? and password = ? ";
            //获取sql对象
            pstmt = conn.prepareStatement(sql);
            //设置参数
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            //执行sql
            rs = pstmt.executeQuery();

            return rs.next();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.close(rs, pstmt, conn);
        }
        System.out.println("11111111");
        return false;
    }
}
