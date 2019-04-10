package cn.yellowgg.service;

import cn.yellowgg.entity.PageBean;
import cn.yellowgg.entity.Student;

import java.util.List;

/**
 * @Author:黄广
 * @Description:学生的service接口
 * @Date: Created in 19-3-19 上午9:23
 */
public interface StudentService {

    /**
     * 查询记录数
     *
     * @param student
     * @param bbirthday
     * @param ebirthday
     * @return
     * @throws Exception
     */
    int stuCount(Student student, String bbirthday, String ebirthday) throws Exception;


    /**
     * 查询学生列表
     *
     * @param pageBean
     * @param student
     * @param bbirthday
     * @param ebirthday
     * @return
     * @throws Exception
     */
    List<Student> stuList(PageBean pageBean, Student student,
                          String bbirthday,
                          String ebirthday) throws Exception;

    /**
     * 添加学生
     *
     * @param student
     * @return
     * @throws Exception
     */
    int stuAdd(Student student) throws Exception;

    /**
     * 删除学生
     *
     * @param delIds
     * @return
     * @throws Exception
     */
    int del(String delIds) throws Exception;

    /**
     * 修改学生信息
     *
     * @param student
     * @return
     * @throws Exception
     */
    int update(Student student) throws Exception;
}
