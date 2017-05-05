<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>为资源添加所需授权</title>
    
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
  	<h3>增添资源所需权限</h3>
  <br>
  	<form action="${pageContext.request.contextPath }/servlet/AddResourcePrivilege" method="post">
  	<table  border="1" width="600px">
  		<tr>
  			<td>资源：</td>
  			<th>${resource.uri }</th>
  		</tr>
  		<tr>
  			<td>当前资源所需权限：</td>
  			<th>
  				<c:forEach var="resource_pri" items="${res_priList }">
  					${resource_pri.name }&nbsp<a href="${pageContext.request.contextPath }/servlet/RemoveResourcePrivilege?privilege_id=${resource_pri.id}&resource_id=${resource.id}">取消所需权限</a><br>
  				</c:forEach>
  			</th>
  		</tr>
  		<tr>
  			<td>还未需要的权限：</td>
  			<th>
 	 			<c:forEach var="pri" items="${priList }">
  					${pri.name }<input type="checkbox" name="privilege_id" value="${pri.id }"><br>
  				</c:forEach>
  				<br>
  				<input type="submit" value="确定增加">
  			</th>
  		</tr>
  	</table>
  	<input type="hidden" name="resource_id" value="${resource.id }">
  	<br>
  	</form>
  		
  </body>
</html>
