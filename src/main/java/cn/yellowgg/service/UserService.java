package cn.yellowgg.service;

import cn.yellowgg.entity.User;

/**
 * @Author:黄广
 * @Description:用户的service接口
 * @Date: Created in 19-3-18 下午4:46
 */
public interface UserService {
    /**
     * 登录
     *
     * @param userName
     * @param password
     * @return 存在的user
     * @throws Exception
     */
    User login(String userName, String password) throws Exception;
}
