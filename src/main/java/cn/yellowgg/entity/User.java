package cn.yellowgg.entity;

import lombok.Data;

/**
 * @Author:黄广
 * @Description:用户（管理员）的实体类
 * @Date: Created in 19-3-18 下午3:59
 */
@Data
public class User {
    private Integer userId;
    private String userName;
    private String password;

    public User() {
        super();
    }

    public User(String userName, String password) {
        super();
        this.userName = userName;
        this.password = password;
    }
}
