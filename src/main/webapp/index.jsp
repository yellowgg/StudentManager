<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
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
    <title>学生信息管理系统登录</title>
    <script type="text/javascript">
        // 禁止为空
        function validate() {
            if (document.getElementById("userName").value == "") {
                alert("用户名不能为空");
                document.getElementById("userName").focus();
                return false;
            } else if (document.getElementById("password").value == "") {
                alert("密码不能为空");
                document.getElementById("password").focus();
                return false;
            }
            return true;
        }
    </script>
</head>
<body background="images/body.jpg"
      style=" background-repeat:no-repeat;background-size:100% 100%;background-attachment: fixed;">
<div align="center" style="padding-top: 50px;">
    <form action="${pageContext.request.contextPath}/user" method="post"
          onsubmit="return validate()">
        <input type="hidden" name="method" value="login">
        <table width="740" height="500">
            <tr height="150"></tr>
            <tr height="30">
                <td></td>
                <td colspan="2" style="font-size: 30px;text-align: center">
                    学生信息管理系统
                </td>
            </tr>
            <tr height="10">
                <td width="40%"></td>
                <td width="10%">用户名：</td>
                <td><input type="text" name="userName" id="userName"/></td>
                <td width="30%"></td>
            </tr>
            <tr height="10">
                <td width="40%"></td>
                <td width="10%">密&nbsp;&nbsp;&nbsp;码：</td>
                <td><input type="password" name="password" id="password"/></td>
                <td width="30%"></td>
            </tr>
            <tr height="10">
                <td width="40%"></td>
                <td width="10%"></td>
                <td width="10%">
                    <input class="easyui-linkbutton" type="submit" value="登录"/>
                    <input class="easyui-linkbutton" type="reset" value="重置"/>
                </td>
                <td width="30%"></td>
            </tr>
            <tr height="10">
                <td width="40%"></td>
                <td width="10%"></td>
                <td colspan="3">
                    <span style="color: red">${msg}</span>
                </td>
            </tr>
            <tr>
                <td></td>
            </tr>
        </table>
    </form>
</div>
</body>
</html>