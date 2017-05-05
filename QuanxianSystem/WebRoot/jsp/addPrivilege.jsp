<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>增加权限</title>
    <style type="text/css">
    	table{
			margin:100px 0px 0px 260px;
		}
		#caozuo{
			margin:10px 0px 0px 250px;
		}
    </style>
	
	<meta http-equiv="content-type" content="text/html;charset='utf-8'">

  </head>
  
  <body style="text-align:center;">
  	<form action="${pageContext.request.contextPath }/servlet/AddPrivilegeServlet" method="post">
  	<table border="1">
  		<tr>
  			<th>权限名称：</th>
  			<td><input type="text" name="privilegeName"/></td>
  		</tr>
  		<tr>
  			<th>权限描述：</th>
  			<th><textarea rows="10" cols="50" name="discription"></textarea></th>
  		</tr>
  	</table>
  	<div id="caozuo">
  		<input type="submit" value="添加"/>
  		<input type="reset" value="重置"/>
  	</div>
  	</form>
  </body>
</html>
