<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String edu = request.getParameter("edu");
	String nara = request.getParameter("nara");
	String[] like = request.getParameterValues("like");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>selectform.jsp</title>
</head>
<body>
학력 : 
<%= edu %>
<br/>
나라 : 
<%= nara %>
<br/>
선호 :
<%
	for (String s : like) {
		if(s.equals(like[like.length-1])) {
			out.print(s);
		} else {
			out.print(s + ", ");
		}
	}
%>
</body>
</html>