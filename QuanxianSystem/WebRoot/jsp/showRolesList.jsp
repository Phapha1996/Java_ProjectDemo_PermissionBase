<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>角色显示</title>
	<meta http-equiv="content-type" content="text/html;charset='utf-8'">
	<style type="text/css" >
		#table{
			margin:10px 0px 0px 260px;
		}
		a:link,a:visited{
			text-decoration:none;
			color:#004a87;
		}
		a:hover{
			color:#CCC;
			text-decoration:underline;
			color:red;
		}
		#addlink{
			width:100%;
			margin:50px 0px 0px 0px;
			padding:0px 0px 0px 130px;
		}
		h3{
			margin:0px;
			padding:0px 70px 0px 0px;
		}
	</style>

  </head>
  
  
  <body style="text-align:center;">
	
	<br><br>
	<h3>角色管理界面</h3>
	<div id="addlink"><a href="${pageContext.request.contextPath }/servlet/AddRoleUI">添加角色</a></div>
	<table id="table" align="center" border="1" width="500px">
		<tr>
			<th>角色名称</th>
			<th>描述</th>
			<th>操作</th>
		</tr>
		<c:forEach var="rol" items="${roles }">
		<tr>
			
				<th>${rol.name }</th>
				<th>${rol.discription }</th>
				<th>
					<a href="${pageContext.request.contextPath }/servlet/AddRolePrivilegeUI?id=${rol.id}">授权</a>
					<a href="#">修改</a>
					<a href="#">删除</a>
				</th>
		</tr>
		</c:forEach>
	</table> 	
	</body>
</html>
