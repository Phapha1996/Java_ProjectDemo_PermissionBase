<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>增加用户</title>
    
	
	<meta http-equiv="content-type" content="text/html;charset='utf-8'">

  </head>
  
  <body style="text-align:center;">
  	<form action="${pageContext.request.contextPath }/servlet/AddUserServlet" method="post">
	<br>
	<h3>后台注册用户</h3>
  	<br><br><br>
  	<table  align="center">
  		<tr>
  			<th>用户名：</th>
  			<td><input type="text" name="userName"/></td>
  			<td><font color="red">*可以是字符组成，可选中文</font></td>
  		</tr>
  		<tr>
  			<th>密码：</th>
			<td><input type="password" name="password"></td>
  			<td><font color="red">*6-20位的密码</font></td>
  		</tr>
  	</table>
  		<br>
  		<input type="submit" value="注册"/>
  		<input type="reset" value="重置"/>
  	
  	</form>
  </body>
</html>
