package cn.yellowgg.entity;

import lombok.Data;

/**
 * @Author:黄广
 * @Description:班级的实体类
 * @Date: Created in 19-3-18 下午4:13
 */
@Data
public class Grade {

    private Integer gradeId;
    private String gradeName;
    private String gradeDesc;

    public Grade() {
        super();
    }

    public Grade(String gradeName, String gradeDesc) {
        this.gradeName = gradeName;
        this.gradeDesc = gradeDesc;
    }
}
