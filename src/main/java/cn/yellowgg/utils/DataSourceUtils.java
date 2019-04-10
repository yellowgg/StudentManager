package cn.yellowgg.utils;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.sql.DataSource;
import java.sql.*;

/**
 * @Author:黄广
 * @Description: 数据源工具类
 * @Date: Created in 19-3-18 下午5:15
 */
public class DataSourceUtils {
    // 获得c3p0连接池对象
    private static ComboPooledDataSource ds = new ComboPooledDataSource();

    /**
     * 获得数据库连接对象
     *
     * @return
     * @throws SQLException
     */
    public static Connection getConnection() throws SQLException {
        return ds.getConnection();
    }

    /**
     * 获得c3p0连接池对象
     *
     * @return
     */
    public static DataSource getDataSource() {
        return ds;
    }


    /**
     * 释放资源
     *
     * @param conn 连接
     * @param st   语句执行者
     * @param rs   结果集
     */
    public static void closeResource(Connection conn, Statement st, ResultSet rs) {
        closeResultSet(rs);
        closeStatement(st);
        closeConn(conn);
    }

    /**
     * 释放连接
     *
     * @param conn 连接
     */
    public static void closeConn(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            conn = null;
        }

    }

    /**
     * 释放语句执行者
     *
     * @param st 语句执行者
     */
    public static void closeStatement(Statement st) {
        if (st != null) {
            try {
                st.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            st = null;
        }

    }

    /**
     * 释放结果集
     *
     * @param rs 结果集
     */
    public static void closeResultSet(ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            rs = null;
        }

    }

    /**
     * 开始事务
     *
     * @throws SQLException
     */
    public static void startTransaction() throws SQLException {
        //1.获取连接
        Connection conn = getConnection();

        //2.开始
        conn.setAutoCommit(false);
    }

    /**
     * 事务提交
     */
    public static void commitAndClose() {
        try {
            //0.获取连接
            Connection conn = getConnection();

            //1.提交事务
            conn.commit();

            //2.关闭且移除
            closeConn(conn);
        } catch (SQLException e) {
        }

    }

    /**
     * 提交回滚
     */
    public static void rollbackAndClose() {
        try {
            //0.获取连接
            Connection conn = getConnection();

            //1.事务回滚
            conn.rollback();

            //2.关闭且移除
            closeConn(conn);
        } catch (SQLException e) {
        }

    }
}
