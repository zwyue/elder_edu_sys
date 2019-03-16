<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 2018/12/1
  Time: 16:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
    pageContext.setAttribute("path",request.getContextPath());
%>
<html>
<head>
    <title>Title</title>
    <script src="https://cdn.bootcss.com/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://cdn.bootcss.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
    <script src="../../../js/term_manage.js"></script>
    <link href="https://cdn.bootcss.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet">
    <style>
        #role_form {
            display: none;
            position: absolute;
            z-index: 101;
        }
        #allocate_power {
            display: none;
            position: absolute;
            z-index: 101;
        }
        #term_form_update{
            display: none;
            position: absolute;
            z-index: 101;
        }
        /* 半透明的遮罩层 */
        #overlay {
            background: #000;
            filter: alpha(opacity=50); /* IE的透明度 */
            opacity: 0.5;  /* 透明度 */
            display: none;
            position: absolute;
            top: 0px;
            left: 0px;
            width: 100%;
            height: 100%;
            z-index: 100; /* 此处的图层要大于页面 */
            display:none;
        }
    </style>
</head>
<body>

<%--<a href="/user/download"></a>--%>
<div class="container">
    <div class="row">
        <div class="col-md-12">
            <h1>角色列表</h1>
        </div>
    </div>

    <div class="row">
        <div class="col-md-4 col-md-offset-8">
            <a class="btn btn-primary" onclick="showForm('role_form',300,400)">新增</a>
            <a class="btn btn-primary" onclick="deleteResource()">批量删除</a>
        </div>
    </div>

    <div class="row">
        <div class="col-md-12">
            <table class="table table-hover table-striped">
                <tr>
                    <th>选择</th>
                    <th>id</th>
                    <th>角色名</th>
                    <th>状态</th>
                    <th>描述</th>
                    <th>创建时间</th>
                    <th>操作</th>
                </tr>
                <c:forEach items="${list}" var="list">
                    <tr id="${list.id}">
                        <td><input type="checkbox" value="${list.id}" name="idList"></td>
                        <td>${list.id}</td>
                        <td>${list.rolename}</td>
                        <td>${list.statusTxt}</td>
                        <td>${list.description}</td>
                        <td>${list.crttime}</td>
                        <td>
                            <a type="button" onclick="editTerm('term_form_update',300,400,'${list.rolename}','${list.description}','${list.status}','${list.id}')" class="btn btn-info btn-sm">
                                <span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>
                                编辑</a>
                            <a type="button" onclick="deleteMethod(${list.id})" class="btn btn-danger btn-sm">
                                <span class="glyphicon glyphicon-trash" aria-hidden="true" ></span>
                                删除</a>
                            <a type="button" onclick="showForm('allocate_power',300,400)" class="btn btn-danger btn-sm">
                                <span class="glyphicon glyphicon-trash" aria-hidden="true" ></span>
                                分配权限</a>
                        </td>
                    </tr>

                </c:forEach>
            </table>
        </div>
    </div>
</div>
<div id="overlay"></div>
<div id="role_form">

    <form action="/role/createNewRole" method="post">
        <%--<div class="title">--%>
        <%--<h2>用户登录</h2><a onclick="layer.style.display=none"></a>--%>
        <%--</div>--%>
        <p><label>角色名: </label><input type="input" name="rolename" /></p>
        <p><label>描述: </label><input type="input" name="description" /></p>
        <input type="submit" value=" 提 交 " />
    </form>
    <button onclick="closeForm('role_form')">关闭</button>

</div>

<div id="term_form_update">

    <form action="/term/updateTerm" method="post">
        <%--<div class="title">--%>
        <%--<h2>用户登录</h2><a onclick="layer.style.display=none"></a>--%>
        <%--</div>--%>
        <p><label>角色名: </label><input type="input" name="term" /></p>
        <p><label>描述: </label><input type="input" name="starttime" /></p>
        <p><label>id: </label><input type="input" name="id" /></p>
        <input type="submit" value=" 提 交 " />
    </form>
    <button onclick="closeForm('term_form_update')">关闭</button>

</div>

<div id="allocate_power">

    <form action="/power/allocatePower" method="post">
        <%--<div class="title">--%>
        <%--<h2>用户登录</h2><a onclick="layer.style.display=none"></a>--%>
        <%--</div>--%>
        <p><label>角色id: </label><input type="input" name="roleId" /></p>
        <p><label>权限id: </label><input type="input" name="powerId" /></p>
        <input type="submit" value=" 提 交 " />
    </form>
    <button onclick="closeForm('allocate_power')">关闭</button>

</div>
</body>
</html>
