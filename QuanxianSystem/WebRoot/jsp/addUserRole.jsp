<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>为用户增加角色</title>
    
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
  	<h3>用户授角</h3>
  <br>
  <form action="${pageContext.request.contextPath }/servlet/AddUserRole" method="post">
  	<table border="1" width="500px">
  		<tr>
  			<td>用户名</td>
  			<th>${user.username }</th>
  		</tr>
  		<tr>
  			<td>用户当前角色</td>
  			<th>
  			<c:forEach var="role" items="${user_roles}">
  			${role.name }&nbsp<a href="${pageContext.request.contextPath }/servlet/RemoveUserRole?user_id=${user.id}&role_id=${role.id}">消除当前角色</a><br>
  			</c:forEach>
  			</th>
  		</tr>
  		<tr>
  			<td>还未拥有的角色</td>
  			<th>
  			<c:forEach var="sysrole" items="${hasNot}">
  			${sysrole.name }<input type="checkbox" name="role_id" value="${sysrole.id }"/><br>
  			</c:forEach>
  			<br>
  			<input type="hidden" name="user_id" value="${user.id }">
  			<input type="submit" value="确定授予">
  			</th>
  		</tr>
  	
  	</table>
  	</form>
  </body>
</html>
