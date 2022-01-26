<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	Integer count = (Integer)application.getAttribute("count");
	if (count == null) {
		application.setAttribute("count", 1);
	} else {
		application.setAttribute("count", count+1);
	}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>counter.jsp</title>
</head>
<body>

</body>
</html>