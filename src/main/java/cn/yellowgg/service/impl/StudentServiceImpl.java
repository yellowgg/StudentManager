package cn.yellowgg.service.impl;

import cn.yellowgg.dao.StudentDao;
import cn.yellowgg.entity.PageBean;
import cn.yellowgg.entity.Student;
import cn.yellowgg.service.StudentService;

import java.util.List;

/**
 * @Author:黄广
 * @Description:学生service接口的实现类
 * @Date: Created in 19-3-21 下午8:09
 */
public class StudentServiceImpl implements StudentService {
    StudentDao studentDao = new StudentDao();

    /**
     * 查询记录数
     *
     * @param student
     * @param bbirthday
     * @param ebirthday
     * @return
     * @throws Exception
     */
    @Override
    public int stuCount(Student student, String bbirthday, String ebirthday) throws Exception {
        return studentDao.stuCount(student, bbirthday, ebirthday);
    }

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
    @Override
    public List<Student> stuList(PageBean pageBean, Student student, String bbirthday, String ebirthday) throws Exception {
        return studentDao.stuList(pageBean, student, bbirthday, ebirthday);
    }

    /**
     * 添加学生
     *
     * @param student
     * @return
     * @throws Exception
     */
    @Override
    public int stuAdd(Student student) throws Exception {
        return studentDao.stuAdd(student);
    }

    /**
     * 删除学生
     *
     * @param delIds
     * @return
     * @throws Exception
     */
    @Override
    public int del(String delIds) throws Exception {
        return studentDao.del(delIds);
    }

    /**
     * 修改学生信息
     *
     * @param student
     * @return
     * @throws Exception
     */
    @Override
    public int update(Student student) throws Exception {
        return studentDao.update(student);
    }
}
