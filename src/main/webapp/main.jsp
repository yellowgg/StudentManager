<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>学生信息管理系统主界面</title>
    <%
        // 权限验证
        if (session.getAttribute("currentUser") == null) {
            response.sendRedirect("index.jsp");
            return;
        }
    %>
    <link rel="stylesheet" type="text/css"
          href="jquery-easyui-1.7.0/themes/icon.css">
    <link rel="stylesheet" type="text/css"
          href="jquery-easyui-1.7.0/themes/default/easyui.css">
    <script type="text/javascript"
            src="jquery-easyui-1.7.0/jquery.min.js"></script>
    <script type="text/javascript"
            src="jquery-easyui-1.7.0/jquery.easyui.min.js"></script>
    <script type="text/javascript"
            src="jquery-easyui-1.7.0/locale/easyui-lang-zh_CN.js"></script>
    <script type="text/javascript">
        $(function () {
            // 数据
            var treeData = [{
                text: "根",
                children: [{
                    text: "班级信息管理",
                    attributes: {
                        url: "gradeInfoManage.jsp"
                    }
                }, {
                    text: "学生信息管理",
                    attributes: {
                        url: "studentInfoManage.jsp"
                    }
                }]
            }];

            // 实例化树菜单
            $("#tree").tree({
                data: treeData,
                lines: true,
                onClick: function (node) {
                    if (node.attributes) {
                        openTab(node.text, node.attributes.url);
                    }
                }
            });

            // 新增Tab
            function openTab(text, url) {
                if ($("#tabs").tabs('exists', text)) {
                    $("#tabs").tabs('select', text);
                } else {
                    var content = "<iframe frameborder='0' scrolling='auto' style='width:100%;height:100%' src=" + url + "></iframe>";
                    $("#tabs").tabs('add', {
                        title: text,
                        closable: true,
                        content: content
                    });
                }
            }
        });
    </script>
</head>
<body class="easyui-layout">
<div region="north" style="height: 80px;background-color: #E0EDFF">
    <div align="left" style="width: 80%;float: left"><img src="images/main.jpg">
    </div>
    <div style="padding-top: 50px;padding-right: 20px;">当前用户：&nbsp;
        <span style="color: red">${currentUser.userName }</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        <a href="${pageContext.request.contextPath}/user?method=logout">退出</a>
    </div>

</div>
<div region="center">
    <div class="easyui-tabs" fit="true" border="false" id="tabs">
        <div title="首页">
            <div align="center" style="padding-top: 100px;">
                <span style="color: red;font-size: 80px">欢迎使用</span>
            </div>
        </div>
    </div>
</div>
<div region="west" style="width: 150px;" title="导航菜单" split="true">
    <ul id="tree"></ul>
</div>
<div region="south" style="height: 25px;" align="center">宇智波广坤
    <a href="https://github.com/yellowgg" target="_blank">https://github.com/yellowgg</a>
</div>
</body>
</html>