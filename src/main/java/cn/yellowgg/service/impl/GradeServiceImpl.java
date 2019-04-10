package cn.yellowgg.service.impl;

import cn.yellowgg.dao.GradeDao;
import cn.yellowgg.entity.Grade;
import cn.yellowgg.entity.PageBean;
import cn.yellowgg.service.GradeService;

import java.util.List;

/**
 * @Author:黄广
 * @Description:班级service接口的实现类
 * @Date: Created in 19-3-19 下午2:18
 */
public class GradeServiceImpl implements GradeService {
    GradeDao gradeDao = new GradeDao();

    /**
     * 查询记录数
     *
     * @param grade 搜索条件
     * @return
     */
    @Override
    public int gradeCount(Grade grade) throws Exception {
        return gradeDao.gradeCount(grade);
    }

    /**
     * 获取班级列表
     *
     * @param pageBean 数量信息
     * @param grade    搜索条件
     * @return
     * @throws Exception
     */
    @Override
    public List<Grade> gradeList(PageBean pageBean, Grade grade) throws Exception {
        return gradeDao.gradeList(pageBean, grade);
    }

    /**
     * 添加班级
     *
     * @param grade
     * @return
     * @throws Exception
     */
    @Override
    public int gradeAdd(Grade grade) throws Exception {
        return gradeDao.gradeAdd(grade);
    }

    /**
     * 查询班级是否有学生
     *
     * @param gradeId
     * @return
     * @throws Exception
     */
    @Override
    public boolean IsHasStudent(String gradeId) throws Exception {
        return gradeDao.IsHasStudent(gradeId);
    }

    /**
     * 批量删除班级
     *
     * @param delIds
     * @return
     * @throws Exception
     */
    @Override
    public int del(String delIds) throws Exception {
        return gradeDao.del(delIds);
    }

    /**
     * 修改班级信息
     *
     * @param grade
     * @return
     * @throws Exception
     */
    @Override
    public int update(Grade grade) throws Exception {
        return gradeDao.update(grade);
    }
}
