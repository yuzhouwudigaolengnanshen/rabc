package com.westos.rbac.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * jdbc工具类
 * @author yihang
 */
public class JdbcUtil {
    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("加载驱动失败");
        }
    }

//    static ThreadLocal<Connection> tl = new ThreadLocal<>();

    /**
     * 获取数据库连接
     * @return 连接对象
     */
    public static Connection getConnection() {
        try {
            Connection conn = conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/rabc?useSSL=false", "root", "01070511");
            return conn;
        } catch (SQLException e) {
            System.out.println("获取连接失败" + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    /**
     * 关闭数据库连接
     */
    /*public static void close() {
        try {
            Connection conn = tl.get();
            if (conn != null) {
                conn.close();
            }
            tl.remove();
        } catch (SQLException e) {
            System.out.println("关闭连接失败" + e.getMessage());
            throw new RuntimeException(e);
        }
    }*/

    /**
     * 关闭资源（包括ResultSet, Statement等）
     * @param resources 资源对象
     */
    public static void close(AutoCloseable... resources) {
        for (AutoCloseable resource : resources) {
            if(resource !=null) {
                try {
                    resource.close();
                } catch (Exception e) {
                    System.out.println("关闭资源失败" + e.getMessage());
                    throw new RuntimeException(e);
                }
            }
        }
    }

}
