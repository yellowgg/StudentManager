package cn.yellowgg.dao;

import cn.yellowgg.entity.User;
import cn.yellowgg.utils.DataSourceUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;


/**
 * @Author:黄广
 * @Description:用户模块的dao
 * @Date: Created in 19-3-18 下午5:14
 */
public class UserDao {

    /**
     * 用户登录
     *
     * @param userName
     * @param password
     * @return
     * @throws Exception
     */
    public User getByUsernameAndPwd(String userName, String password) throws Exception {
        User user = null;
        QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
        String sql = "select * from t_user where userName = ? and password = " +
                "? limit 1;";
        user = qr.query(sql, new BeanHandler<>(User.class), userName,
                password);

        return user;
    }
}
