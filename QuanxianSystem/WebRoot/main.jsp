<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'main.jsp' starting page</title>
    
	<meta http-equiv="Content-type" content="text/html;charset='utf-8'">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
    <h1 align="center">${user.username}:&nbsp欢迎您</h1> <br>
    <ul>
    <li><a href="${pageContext.request.contextPath }/fage/DeleteTest">删除商品</a></li>
    <li><a href="${pageContext.request.contextPath }/fage/AddTest">增加商品</a></li>
    <li><a href="${pageContext.request.contextPath }/fage/UpdateTest">修改商品</a></li>
    </ul>
  </body>
</html>
