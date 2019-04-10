package cn.yellowgg.service;

import cn.yellowgg.entity.Grade;
import cn.yellowgg.entity.PageBean;

import java.util.List;

/**
 * @Author:黄广
 * @Description:班级的service接口
 * @Date: Created in 19-3-19 上午9:22
 */
public interface GradeService {
    /**
     * 查询记录数
     *
     * @param grade 搜索条件
     * @return
     */
    int gradeCount(Grade grade) throws Exception;

    /**
     * 获取班级列表
     *
     * @param pageBean 数量信息
     * @param grade    搜索条件
     * @return
     * @throws Exception
     */
    List<Grade> gradeList(PageBean pageBean, Grade grade) throws Exception;

    /**
     * 添加班级
     *
     * @param grade
     * @return
     * @throws Exception
     */
    int gradeAdd(Grade grade) throws Exception;

    /**
     * 查询班级是否有学生
     *
     * @param gradeId
     * @return
     * @throws Exception
     */
    boolean IsHasStudent(String gradeId) throws Exception;

    /**
     * 批量删除班级
     *
     * @param delIds
     * @return
     * @throws Exception
     */
    int del(String delIds) throws Exception;

    /**
     * 修改班级信息
     *
     * @param grade
     * @return
     * @throws Exception
     */
    int update(Grade grade) throws Exception;
}
