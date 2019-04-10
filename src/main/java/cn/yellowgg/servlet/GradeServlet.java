package cn.yellowgg.servlet;

import cn.yellowgg.entity.Grade;
import cn.yellowgg.entity.PageBean;
import cn.yellowgg.service.GradeService;
import cn.yellowgg.service.impl.GradeServiceImpl;
import cn.yellowgg.utils.ResponseUtil;
import com.google.gson.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author:黄广
 * @Description:班级模块
 * @Date: Created in 19-3-19 上午9:19
 */
public class GradeServlet extends BaseServlet {
    private static final long serialVersionUID = 1L;
    GradeService gradeService = new GradeServiceImpl();

    /**
     * 获取班级列表
     *
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public String gradeList(HttpServletRequest request,
                            HttpServletResponse response) throws Exception {
        //获取参数 前两个参数是分页组件默认传过来的
        String page = request.getParameter("page");
        String rows = request.getParameter("rows");
        String gradeName = request.getParameter("gradeName");
        //判断一下。因为是要做搜索条件
        if (gradeName == null) {
            gradeName = "";
        }

        Grade grade = new Grade();
        grade.setGradeName(gradeName);

        PageBean pageBean = new PageBean(Integer.parseInt(page), Integer.parseInt(rows));

        //获取列表的记录数
        int total = gradeService.gradeCount(grade);

        //获取班级列表
        List<Grade> grades = gradeService.gradeList(pageBean, grade);


        Gson gson = new Gson();
        JsonObject result = new JsonObject();
        //必须是rows和total，因为easyui源码，查文档
        result.add("rows", gson.toJsonTree(grades));
        result.add("total", gson.toJsonTree(total));

        ResponseUtil.write(response, result);
        return null;
    }

    /**
     * 添加班級
     *
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public String save(HttpServletRequest request,
                       HttpServletResponse response) throws Exception {
        String gradeName = request.getParameter("gradeName");
        String gradeDesc = request.getParameter("gradeDesc");
        Grade grade = new Grade(gradeName, gradeDesc);

        //用于返回seccess回调函数的json
        JsonObject result = new JsonObject();

        int saveNum = gradeService.gradeAdd(grade);

        if (saveNum > 0) {
            result.addProperty("success", "true");
        } else {
            result.addProperty("errorMsg", "添加失败，请重试");
        }
        ResponseUtil.write(response, result);
        return null;
    }

    /**
     * 批量删除班级
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


        //遍历所要删除的班级有没有学生
        for (int i = 0; i < str.length; i++) {
            boolean b = gradeService.IsHasStudent(str[i]);
            if (b) {
                result.add("errorIndex", gson.toJsonTree(i));
                result.add("errorMsg", gson.toJsonTree("有学生，不能删除，否则就无家可归噢"));
                ResponseUtil.write(response, result);
                return null;
            }
        }

        //没有就可以删除了
        int delNums = gradeService.del(delIds);
        if (delNums > 0) {
            result.add("success", gson.toJsonTree("true"));
            result.add("delNums", gson.toJsonTree(delNums));
        } else {
            result.addProperty("errorMsg", "删除失败");
        }
        ResponseUtil.write(response, result);
        return null;
    }

    /**
     * 修改班级信息
     *
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public String update(HttpServletRequest request,
                         HttpServletResponse response) throws Exception {
        //获取参数
        String gradeName = request.getParameter("gradeName");
        String gradeDesc = request.getParameter("gradeDesc");
        String gradeId = request.getParameter("gradeId");

        //构造修改的班级对象
        Grade grade = new Grade();
        grade.setGradeId(Integer.parseInt(gradeId));
        grade.setGradeName(gradeName);
        grade.setGradeDesc(gradeDesc);

        //用于返回seccess回调函数的json
        JsonObject result = new JsonObject();

        int saveNum = gradeService.update(grade);
        if (saveNum > 0) {
            result.addProperty("success", "true");
        } else {
            result.addProperty("errorMsg", "保存失败，请重试");
        }
        ResponseUtil.write(response, result);
        return null;
    }

    /**
     * 将班级列表填充到下拉框，键是班级名，值是班级id
     *
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public String gradeCbList(HttpServletRequest request,
                              HttpServletResponse response) throws Exception {
        //获取班级列表
        List<Grade> gradesBack = gradeService.gradeList(null, null);

        Gson gson = new Gson();

       /* //下拉框的数据是json数组
        JsonArray jsonArray = new JsonArray();
        //定义请选择的json对象
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("gradeId", "");
        jsonObject.addProperty("gradeName", "请选择...");

        //添加到json数组
        jsonArray.add(jsonObject);
        //添加班级列表json
        jsonArray.add(gons.toJsonTree(gradesBack))*/

        /**
         * 上面的方法不太行
         需求是[{"gradeId":"","gradeName":"请选择..."},{"gradeId":69,
         "gradeName":"16数媒班"}]
         实际是[{"gradeId":"","gradeName":"请选择..."},[{"gradeId":61,
         "gradeName":"16计科一班","gradeDesc":"很好噢"}]]
         所以要解决两个问题，
         一、多余的gradeDesc去掉（其实不去也没事）。
         方法：定义一个转json的方法，只将id和班级名转，过滤Desc
         二、list用son.toJsonTree()方法转出来的json是json数组， jsonArray.add就会把数组
         加进去形成上面的实际情况
         所以要将班级列表的json变成多个json对象而不是一个json数组
         设想1：遍历json数组取出其中的对象一个个的加到jsonArray
         设想2：直接在班级列表的json数组中第一个位置加上“请选择”的json对象
         设想3：根据设想2的方法，json不容易在第一个位置添加元素，那么在list中添加第一个再转成json
         设想4：设想3也不好添加，干脆用两个list，一个装“请选择”，一个装班级列表，再合并起来 （用这个）
         **/
        Grade please = new Grade();
        //设置为null的话前台不会显示，所以这里要保证班级的id不为0
        please.setGradeId(0);
        please.setGradeName("请选择...");
        please.setGradeDesc("");

        List<Grade> gradesFront = new ArrayList<Grade>();
        gradesFront.add(please);

        //合并list
        gradesFront.addAll(gradesBack);

        //转json
        JsonArray gradeJsonList = (JsonArray) gson.toJsonTree(gradesFront);


        ResponseUtil.write(response, gradeJsonList);
        return null;
    }
}
