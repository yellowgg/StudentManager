# 开发笔记
刚学习了一个jsp+servlet的学生信息管理系统，它是2013年写的，使用了EasyUI做前端，觉得可以试一下这个。但是它的后台开发是使用基本的东西，而且是一个url对应一个servlet
，这样很复杂，我将重构成反射的方法来对应url，这样就不用开发多个servlet了。总之就是用它的界面来练习一些技术。后台将完全重构和优化，因为写前端真的太蓝瘦了哈哈。所以想借这个项目来换成其它技术，以便学习。

感谢本系统的原作者www.java1234.com。

## dao层:Dbutils
在这里我打算使用Dbutils，因为它之前是使用JDBC来开发的。所以刚好可以学习巩固下Dbtuils。


## 前端:EasyUI
原来的版本是jquery-easyui-1.3.3，现在是jquery-easyui-1.7.0，下面记录的是所用到的内容
+ 表单验证 http://www.jeasyui.net/demo/526.html
>不推荐用在登录时的验证，因为这个表单验证提交到后台会返回数据，比如在本项目中登录成功后转发到main
.jsp，然后会返回整个网页的html内容而不会转发，填了半天的坑，果然作为刚使用EasyUI的新手来说还是太嫩了。

+ 布局 http://www.jeasyui.net/demo/529.html
>使用基础布局可满足需求，region="方位"

+ 树 http://www.jeasyui.net/demo/409.html
>树的用法有很多，可以参考官网教程使用

+ 选项卡 http://www.jeasyui.net/demo/557.html
>选项卡也有很多demo

+ 链接按钮 http://www.jeasyui.net/demo/532.html

+ 搜索框 http://www.jeasyui.net/demo/386.html

+ 数据表格 http://www.jeasyui.net/demo/331.html
>请求后台的json数据是json数组，单个对象都不行，必须是数组,并且可以加一个total（分页组件），所以后台传的是rows和total，具体可参见代码  
>{"rows":[{"gradeId":32,"gradeName":"1","gradeDesc":"2"}],"total":27}

+ 组合框 http://www.jeasyui.net/demo/488.html
>组合框主要是两个值，一个是valueField，一个是textField,json数据格式是json数组
 
## json转化:Gson
原系统是使用json-lib库的，这里我想尝试一下gson

## getter/setter:lombok
在项目中使用Lombok可以减少很多重复代码的书写。比如说getter/setter/toString等方法的编写。良心推荐！
