package cn.yellowgg.dao;

import cn.yellowgg.entity.Grade;
import cn.yellowgg.entity.PageBean;
import cn.yellowgg.utils.DataSourceUtils;
import cn.yellowgg.utils.StringUtil;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.util.List;

/**
 * @Author:黄广
 * @Description:班级模块的dao
 * @Date: Created in 19-3-19 上午9:23
 */
public class GradeDao {

    /**
     * 获取班级数量
     *
     * @param grade 搜索条件
     * @return
     * @throws Exception
     */
    public int gradeCount(Grade grade) throws Exception {
        //不使用where=1 这种效率不够高
        QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
        StringBuffer sb = new StringBuffer("select count(*) as total from t_grade");
        //班级的搜索条件只有班级名称
        if (StringUtil.isNotEmpty(grade.getGradeName())) {
            sb.append(" and gradeName like '%" + grade.getGradeName() + "%'");
        }

        String count = qr.query(sb.toString().replace("and", "where"),
                new ScalarHandler()).toString();
        return Integer.parseInt(count);
    }

    /**
     * 获取班级列表
     *
     * @param pageBean 分页条件
     * @param grade    搜索条件
     * @return
     * @throws Exception
     */
    public List<Grade> gradeList(PageBean pageBean, Grade grade) throws Exception {
        QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
        StringBuffer sb = new StringBuffer("select * from t_grade");
        if (grade != null && StringUtil.isNotEmpty(grade.getGradeName())) {
            sb.append(" and gradeName like '%" + grade.getGradeName() + "%'");
        }
        if (pageBean != null) {
            sb.append(" limit " + pageBean.getStart() + "," + pageBean.getRows());
        }
        List<Grade> gradeList = qr.query(sb.toString().replace("and", "where"),
                new BeanListHandler<Grade>(Grade.class));
        return gradeList;
    }

    /**
     * 添加班级
     *
     * @param grade
     * @return 受影响的行数
     * @throws Exception
     */
    public int gradeAdd(Grade grade) throws Exception {
        QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
        String sql = "insert into t_grade values (null,?,?)";
        return qr.update(sql, grade.getGradeName(), grade.getGradeDesc());
    }

    /**
     * 班级是否有学生，因为如果有学生的话，不能删除该班级
     *
     * @param gradeId 班级ID
     * @return true代表有学生
     * @throws Exception
     */
    public boolean IsHasStudent(String gradeId) throws Exception {
        QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
        String sql = "select count(*) from t_student where gradeId=?";
        String count = qr.query(sql, new ScalarHandler<>(), gradeId).toString();
        return Integer.parseInt(count) > 0 ? true : false;
    }

    /**
     * 批量删除班级
     *
     * @param delIds 例如： 65,67
     * @return 删除成功的记录数
     * @throws Exception
     */
    public int del(String delIds) throws Exception {
        QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
        String sql = "delete from t_grade where gradeId in(" + delIds + ")";
        return qr.update(sql);
    }

    /**
     * 修改班级信息
     *
     * @param grade
     * @return 修改成功的记录数
     * @throws Exception
     */
    public int update(Grade grade) throws Exception {
        QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
        String sql = "update t_grade set gradeName=?,gradeDesc=? where " +
                "gradeId=?";
        return qr.update(sql, grade.getGradeName(), grade.getGradeDesc(),
                grade.getGradeId());
    }
}
