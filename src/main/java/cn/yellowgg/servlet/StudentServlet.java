package cn.yellowgg.servlet;

import cn.yellowgg.entity.PageBean;
import cn.yellowgg.entity.Student;
import cn.yellowgg.service.StudentService;
import cn.yellowgg.service.impl.StudentServiceImpl;
import cn.yellowgg.utils.DateUtil;
import cn.yellowgg.utils.ResponseUtil;
import cn.yellowgg.utils.StringUtil;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @Author:黄广
 * @Description:学生模块
 * @Date: Created in 19-3-19 上午9:21
 */
public class StudentServlet extends BaseServlet {
    private static final long serialVersionUID = 1L;
    StudentService studentService = new StudentServiceImpl();

    /**
     * 获取学生列表
     *
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public String stuList(HttpServletRequest request,
                          HttpServletResponse response) throws Exception {
        //获取参数
        String stuNo = request.getParameter("stuNo");
        String stuName = request.getParameter("stuName");
        String sex = request.getParameter("sex");
        String bbirthday = request.getParameter("bbirthday");
        String ebirthday = request.getParameter("ebirthday");
        String gradeId = request.getParameter("gradeId");

        Student student = new Student();
        if (StringUtil.isNotEmpty(stuNo)) {
            student.setStuNo(stuNo);
        }
        if (StringUtil.isNotEmpty(stuName)) {
            student.setStuName(stuName);
        }
        if (StringUtil.isNotEmpty(sex)) {
            student.setSex(sex);
        }
        if (StringUtil.isNotEmpty(gradeId)) {
            student.setGradeId(Integer.parseInt(gradeId));
        }

        String page = request.getParameter("page");
        String rows = request.getParameter("rows");
        PageBean pageBean = new PageBean(Integer.parseInt(page), Integer.parseInt(rows));

        //获取列表的记录数
        int total = studentService.stuCount(student, bbirthday, ebirthday);

        List<Student> students = studentService.stuList(pageBean, student,
                bbirthday, ebirthday);

        Gson gson = new Gson();
        JsonObject result = new JsonObject();
        result.add("rows", gson.toJsonTree(students));
        result.add("total", gson.toJsonTree(total));
        ResponseUtil.write(response, result);
        return null;
    }

    /**
     * 添加学生
     *
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public String save(HttpServletRequest request,
                       HttpServletResponse response) throws Exception {
        String stuNo = request.getParameter("stuNo");
        String stuName = request.getParameter("stuName");
        String sex = request.getParameter("sex");
        String birthday = request.getParameter("birthday");
        String gradeId = request.getParameter("gradeId");
        String email = request.getParameter("email");
        String stuDesc = request.getParameter("stuDesc");
        String stuId = request.getParameter("stuId");
        Student student = null;
        student = new Student(stuNo, stuName, sex, DateUtil.formatString(birthday, "yyyy-MM-dd"),
                Integer.parseInt(gradeId), email, stuDesc);
        if (StringUtil.isNotEmpty(stuId)) {
            student.setStuId(Integer.parseInt(stuId));
        }

        //用于返回seccess回调函数的json
        Gson gson = new Gson();
        JsonObject result = new JsonObject();

        int saveNum = studentService.stuAdd(student);
        if (saveNum > 0) {
            result.addProperty("success", "true");
        } else {
            result.addProperty("errorMsg", "添加失败，请重试");
        }
        ResponseUtil.write(response, result);
        return null;
    }

    /**
     * 修改学生
     *
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public String update(HttpServletRequest request,
                         HttpServletResponse response) throws Exception {
        String stuNo = request.getParameter("stuNo");
        String stuName = request.getParameter("stuName");
        String sex = request.getParameter("sex");
        String birthday = request.getParameter("birthday");
        String gradeId = request.getParameter("gradeId");
        String email = request.getParameter("email");
        String stuDesc = request.getParameter("stuDesc");
        String stuId = request.getParameter("stuId");
        Student student = null;
        student = new Student(stuNo, stuName, sex, DateUtil.formatString(birthday, "yyyy-MM-dd"),
                Integer.parseInt(gradeId), email, stuDesc);
        if (StringUtil.isNotEmpty(stuId)) {
            student.setStuId(Integer.parseInt(stuId));
        }

        //用于返回seccess回调函数的json
        Gson gson = new Gson();
        JsonObject result = new JsonObject();

        int saveNum = studentService.update(student);
        if (saveNum > 0) {
            result.addProperty("success", "true");
        } else {
            result.addProperty("errorMsg", "保存失败，请重试");
        }
        ResponseUtil.write(response, result);
        return null;
    }

    /**
     * 删除学生
     *
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public String del(HttpServletRequest request,
                      HttpServletResponse response) throws Exception {
        //获取要删除的id,因为前台传过来的就是用,分割的
        String delIds = request.getParameter("delIds");
        String[] str = delIds.split(",");

        //用于返回seccess回调函数的json
        Gson gson = new Gson();
        JsonObject result = new JsonObject();

        int delNums = studentService.del(delIds);
        if (delNums > 0) {
            result.add("success", gson.toJsonTree("true"));
            result.add("delNums", gson.toJsonTree(delNums));
        } else {
            result.addProperty("errorMsg", "删除失败");
        }
        ResponseUtil.write(response, result);
        return null;
    }
}
