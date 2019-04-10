package cn.yellowgg.dao;

import cn.yellowgg.entity.PageBean;
import cn.yellowgg.entity.Student;
import cn.yellowgg.utils.DataSourceUtils;
import cn.yellowgg.utils.DateUtil;
import cn.yellowgg.utils.StringUtil;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.util.List;

/**
 * @Author:黄广
 * @Description:学生模块的dao
 * @Date: Created in 19-3-19 上午9:24
 */
public class StudentDao {

    /**
     * 获取学生数量
     *
     * @param student 搜索条件
     * @return
     * @throws Exception
     */
    public int stuCount(Student student, String bbirthday, String ebirthday) throws Exception {
        //不使用where=1 这种效率不够高
        QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
        StringBuffer sb = new StringBuffer("select count(*) as total from " +
                "t_student s,t_grade g where s.gradeId=g.gradeId");
        //搜索条件
        if (StringUtil.isNotEmpty(student.getStuNo())) {
            sb.append(" and s.stuNo like '%" + student.getStuNo() + "%'");
        }
        if (StringUtil.isNotEmpty(student.getStuName())) {
            sb.append(" and s.stuName like '%" + student.getStuName() + "%'");
        }
        if (StringUtil.isNotEmpty(student.getSex())) {
            sb.append(" and s.sex ='" + student.getSex() + "'");
        }
        if (student.getGradeId() != -1) {
            sb.append(" and s.gradeId ='" + student.getGradeId() + "'");
        }
        if (StringUtil.isNotEmpty(bbirthday)) {
            sb.append(" and TO_DAYS(s.birthday)>=TO_DAYS('" + bbirthday + "')");
        }
        if (StringUtil.isNotEmpty(ebirthday)) {
            sb.append(" and TO_DAYS(s.birthday)<=TO_DAYS('" + ebirthday + "')");
        }

        String count = qr.query(sb.toString(), new ScalarHandler()).toString();
        return Integer.parseInt(count);
    }

    /**
     * 获取学生列表
     *
     * @param pageBean
     * @param student
     * @param bbirthday
     * @param ebirthday
     * @return
     * @throws Exception
     */
    public List<Student> stuList(PageBean pageBean, Student student,
                                 String bbirthday,
                                 String ebirthday) throws Exception {
        QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
        StringBuffer sb = new StringBuffer("select * from t_student s,t_grade " +
                "g where s.gradeId=g.gradeId");
        if (StringUtil.isNotEmpty(student.getStuNo())) {
            sb.append(" and s.stuNo like '%" + student.getStuNo() + "%'");
        }
        if (StringUtil.isNotEmpty(student.getStuName())) {
            sb.append(" and s.stuName like '%" + student.getStuName() + "%'");
        }
        if (StringUtil.isNotEmpty(student.getSex())) {
            sb.append(" and s.sex ='" + student.getSex() + "'");
        }
        if (student.getGradeId() != -1) {
            sb.append(" and s.gradeId ='" + student.getGradeId() + "'");
        }
        if (StringUtil.isNotEmpty(bbirthday)) {
            sb.append(" and TO_DAYS(s.birthday)>=TO_DAYS('" + bbirthday + "')");
        }
        if (StringUtil.isNotEmpty(ebirthday)) {
            sb.append(" and TO_DAYS(s.birthday)<=TO_DAYS('" + ebirthday + "')");
        }
        if (pageBean != null) {
            sb.append(" limit " + pageBean.getStart() + "," + pageBean.getRows());
        }
        List<Student> stuList = qr.query(sb.toString(), new BeanListHandler<Student>(Student.class));
        return stuList;
    }

    /**
     * 添加学生
     *
     * @param student
     * @return 受影响的行数
     * @throws Exception
     */
    public int stuAdd(Student student) throws Exception {
        QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
        String sql = "insert into t_student values (null,?,?,?,?,?,?,?)";
        return qr.update(sql, student.getStuNo(), student.getStuName(),
                student.getSex(), DateUtil.formatDate(student.getBirthday(),
                        "yyyy-MM-dd"), student.getGradeId(), student.getEmail(), student.getStuDesc());
    }

    /**
     * 删除学生
     *
     * @param delIds 例如： 65,67
     * @return 删除成功的记录数
     * @throws Exception
     */
    public int del(String delIds) throws Exception {
        QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
        String sql = "delete from t_student where stuId in(" + delIds + ")";
        return qr.update(sql);
    }

    /**
     * 修改学生信息
     *
     * @param student
     * @return 修改成功的记录数
     * @throws Exception
     */
    public int update(Student student) throws Exception {
        QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
        String sql = "update t_student set stuNo=?,stuName=?,sex=?,birthday=?," +
                "gradeId=?,email=?,stuDesc=? where " +
                "stuId=?";
        return qr.update(sql, student.getStuNo(), student.getStuName(),
                student.getSex(), DateUtil.formatDate(student.getBirthday(),
                        "yyyy-MM-dd"), student.getGradeId(),
                student.getEmail(), student.getStuDesc(), student.getStuId());
    }
}
