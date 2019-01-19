package com.xingxue.rbac.test;

import com.xingxue.rbac.util.JdbcUtil;

import java.sql.Connection;
import java.sql.SQLException;

public class TestConnectDatebase {
    public static void main(String[] args) throws SQLException {
        Connection connection = JdbcUtil.getConnection();
        System.out.println(connection.isClosed());
    }
}
