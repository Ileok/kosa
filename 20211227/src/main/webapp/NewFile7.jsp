<%@ page language="java" contentType="application/hwp; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	response.setHeader("Content-Disposition","attachment;filename=member.hwp");
	response.setHeader("Content-Description", "JSP Generate Data");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<table border=1 width="500">
	<tr><th>이름</th><th>주소</th><th>연락처</th></tr>
	<tr><th>이숭무</th><th>성남</th><th>031-1234-1234</th></tr>
	<tr><th>김차중</th><th>의정부</th><th>031-987-7894</th></tr>
</table>
</body>
</html>