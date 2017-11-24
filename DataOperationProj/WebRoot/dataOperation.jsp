<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'dataOperation.jsp' starting page</title>
    
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
    <a href="DataInputServlet">从属性文件读取数据到数据库</a><br>
    <a href="addStudent.jsp">添加学生信息</a><br>
    
    <hr>
  	
  	<table border="1" align="center">
  	<tr>
  	<td>学号</td>
	<td>姓名</td>
	<td>性别</td>
	<td>专业</td>
	<td>籍贯</td>
  	</tr>
  	<c:forEach var="student" items="${sessionScope.studentlist}" varStatus="status">
  	<%--为奇数行和偶数行设置不同的背景颜色--%>
			<c:if test="${status.count%2==0}">
				<tr style="background:#eeeeff">
			</c:if>
			<c:if test="${status.count%2!=0}">
				<tr style="background:#dedeff">
			</c:if>
  	<td>${student.id}</td>
  	<td>${student.name}</td>
  	<td>${student.sex}</td>
  	<td>${student.major}</td>
  	<td>${student.hometown}</td>
	<td><a href="findModifyStudent?index=${status.index}">修改</a></td>
	<td><a href="deleteStudent?index=${status.index}">删除</a></td>
  	
  	</c:forEach>
	
  	</table>
  	
  </body>
</html>
