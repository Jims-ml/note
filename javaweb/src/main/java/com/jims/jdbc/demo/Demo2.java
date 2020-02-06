package com.jims.jdbc.demo;

import com.jims.jdbc.entity.User;
import com.jims.jdbc.utils.JDBCUtils1;

import org.junit.Test;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * @author JIMS
 * @create 2020-01-29-19:49
 */
public class Demo2 {

    @Test
    public void test1() {
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = JDBCUtils1.getConnection();
            String sql = "insert into user values(?,?,?)";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, 4);
            pstmt.setString(2, "zhaoliu");
            pstmt.setString(3, "333");
            int count = pstmt.executeUpdate();
            System.out.println(count);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils1.close(pstmt, conn);
        }
    }

    /*
     * JdbcTemplate:spring对jdbc的简单封装
     * JdbcTemplate更新数据
     * */
    @Test
    public void test2() {
        JdbcTemplate template = new JdbcTemplate(JDBCUtils1.getDataSource());
        String sql = "update user set password = ? where id = ?";
        int count = template.update(sql, "444", 1);
        System.out.println(count);
    }

    //结果集封装为map queryForMap
    @Test
    public void test3() {
        JdbcTemplate template = new JdbcTemplate(JDBCUtils1.getDataSource());
        String sql = "select * from user where id = ?";
        Map<String, Object> map = template.queryForMap(sql, 1);
        System.out.println(map);
    }
    //结果集封装为list queryForList
    @Test
    public void test4() {
        JdbcTemplate template = new JdbcTemplate(JDBCUtils1.getDataSource());
        String sql = "select * from user";
        List<Map<String, Object>> list = template.queryForList(sql);
        for (Map<String, Object> map : list) {
            System.out.println(map);
        }
    }
    //结果集封装为user对象的list query
    @Test
    public void test5() {
        JdbcTemplate template = new JdbcTemplate(JDBCUtils1.getDataSource());
        String sql = "select * from user";
        List<User> list = template.query(sql,new BeanPropertyRowMapper<>(User.class));
        for (User user : list) {
            System.out.println(user);
        }
    }
    //查询总记录数 queryForObject
    @Test
    public void test6() {
        JdbcTemplate template = new JdbcTemplate(JDBCUtils1.getDataSource());
        String sql = "select count(id) from user";
        Long count = template.queryForObject(sql,Long.class);
        System.out.println(count);
    }
}
