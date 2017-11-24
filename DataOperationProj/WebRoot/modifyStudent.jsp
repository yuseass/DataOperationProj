<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'modifyStudent.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
    <form action="modifyStudentServlet" method="get">
  <table>
  	<tr>
    <td>id:</td>
    <td><input type="text" name="id" value="${sessionScope.student.id}"></td>
    </tr>
    
    <tr>
    <td>name:</td>
    <td><input type="text" name="name" value="${sessionScope.student.name}"></td>
    </tr>
    
    <tr>
    <td>sex:</td>
    <td><input type="text" name="sex" value="${sessionScope.student.sex}"></td>
    </tr>
    
    <tr>
    <td>major:</td>
    <td><input type="text" name="major" value="${sessionScope.student.major}"></td>
    </tr>
    
    <tr>
    <td>hometown:</td>
    <td><input type="text" name="hometown" value="${sessionScope.student.hometown}"></td>
    </tr>
    </table>
    
    <input type="submit" value="修改">
    </form>
  </body>
</html>
