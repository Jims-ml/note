package com.jims.utils;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

/**
 * jdbc工具类(Druid连接池)
 *
 * @author JIMS
 * @create 2020-01-29-19:30
 */
public class JDBCUtils1 {
    private static DataSource ds;

    static {
        try {
            //加载配置文件
            Properties pro = new Properties();
            InputStream ips = JDBCUtils1.class.getClassLoader().getResourceAsStream("druid.properties");
            pro.load(ips);
            ds = DruidDataSourceFactory.createDataSource(pro);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //获取连接
    public static Connection getConnection() throws SQLException {
        return ds.getConnection();
    }

    //归还连接
    public static void close(PreparedStatement pstmt, Connection conn) {

        close(null, pstmt, conn);
    }

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

    //获取连接池
    public static DataSource getDataSource(){
        return ds;
    }
}
