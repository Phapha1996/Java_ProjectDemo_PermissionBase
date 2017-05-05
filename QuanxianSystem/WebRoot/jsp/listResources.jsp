<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>权限显示</title>
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
	<h3>资源管理页面</h3>
	<div id="addlink"><a href="${pageContext.request.contextPath }/servlet/AddResourceUI">添加资源</a></div>
	<table id="table" align="center" border="1" width="600px">
		<tr>
			<th>资源</th>
			<th>操作</th>
		</tr>
		<c:forEach var="res" items="${Resources }">
		<tr>
			
				<th>${res.uri }</th>
				<th>
					<a href="${pageContext.request.contextPath }/servlet/NeedPrivilegeUI?resource_id=${res.id}">管理所需权限</a>
					<a href="#">修改</a>
					<a href="${pageContext.request.contextPath }/servlet/DeleteResource?resource_id=${res.id}">删除</a>
				</th>
		</tr>
		</c:forEach>
	</table> 		
 	 	
  </body>
</html>
