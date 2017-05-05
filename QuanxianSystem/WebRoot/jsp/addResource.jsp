<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>增加资源</title>
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
  	<form action="${pageContext.request.contextPath }/servlet/AddResourceServlet" method="post">
  	<table border="1">
  		<tr>
  			<th>资源URI：</th>
  		<th><textarea rows="5" cols="50" name="uri"></textarea></th>
  		</tr>
  		
  	</table>
  	<div id="caozuo">
  		<input type="submit" value="添加"/>
  		<input type="reset" value="重置"/>
  	</div>
  	</form>
  </body>
</html>
