package cn.yellowgg.service.impl;

import cn.yellowgg.dao.UserDao;
import cn.yellowgg.entity.User;
import cn.yellowgg.service.UserService;

/**
 * @Author:黄广
 * @Description:用户service接口的实现类
 * @Date: Created in 19-3-18 下午4:46
 */
public class UserServiceImpl implements UserService {
    UserDao ud = new UserDao();

    /**
     * 登录
     *
     * @param userName
     * @param password
     * @return 存在的user
     * @throws Exception
     */
    @Override
    public User login(String userName, String password) throws Exception {
        return ud.getByUsernameAndPwd(userName, password);
    }
}
