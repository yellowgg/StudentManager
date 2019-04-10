package cn.yellowgg.entity;

import lombok.Data;

import java.util.Date;

/**
 * @Author:黄广
 * @Description:学生的实体类
 * @Date: Created in 19-3-18 下午4:10
 */
@Data
public class Student {
    private Integer stuId;
    private String stuNo;
    private String stuName;
    private String sex;
    private Date birthday;
    private Integer gradeId = -1;
    private String email;
    private String stuDesc;

    private String gradeName;

    public Student() {
        super();
    }

    public Student(String stuNo, String stuName,
                   String sex, Date birthday,
                   Integer gradeId, String email,
                   String stuDesc) {
        this.stuNo = stuNo;
        this.stuName = stuName;
        this.sex = sex;
        this.birthday = birthday;
        this.gradeId = gradeId;
        this.email = email;
        this.stuDesc = stuDesc;
    }
}
