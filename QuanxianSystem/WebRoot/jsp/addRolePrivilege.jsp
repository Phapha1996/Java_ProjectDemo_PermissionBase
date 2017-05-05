<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>为某个角色授权</title>
    
	<meta http-equiv="content-type" content="text/html;charset='utf-8'">
	<style type="text/css">
	table{
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
		h3{
			margin:0px;
			padding:0px 70px 0px 0px;
		}
	
	</style>	

  </head>
  
  <body style="text-align:center">
  <br><br><br>
  	<h3>角色授权</h3>
  <br>
  	<form action="${pageContext.request.contextPath }/servlet/AddRolePrivilege" method="post">
  	<table  border="1" width="500px">
  		<tr>
  			<td>角色名称：</td>
  			<th>${role.name }</th>
  		</tr>
  		<tr>
  			<td>角色拥有权限：</td>
  			<th>
  				<c:forEach var="role_pri" items="${role_priList }">
  					${role_pri.name }&nbsp<a href="${pageContext.request.contextPath }/servlet/RemoveRolePrivilege?privilege_id=${role_pri.id}&role_id=${role.id}">取消权限</a><br>
  				</c:forEach>
  			</th>
  		</tr>
  		<tr>
  			<td>还未拥有的权限：</td>
  			<th>
 	 			<c:forEach var="pri" items="${priList }">
  					${pri.name }<input type="checkbox" name="privilege_id" value="${pri.id }"><br>
  				</c:forEach>
  				<br>
  				<input type="submit" value="确定授权">
  			</th>
  		</tr>
  	</table>
  	<input type="hidden" name="role_id" value="${role.id }">
  	<br>
  	</form>
  		
  </body>
</html>
