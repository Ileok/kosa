<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String search = request.getParameter("search");
	request.setAttribute("subject", "jsp는 서버 프로그램입니다.");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
검색 페이지 입니다.<br/>
<form action="NewFile3.jsp" method="post" name="frm">
	검색 : <input type="search" name="search" value="<%=search%>"/><br/>
	검색 결과 : <%= request.getAttribute("subject") %><br/>
	<input type="submit" value="검색"/>
</form>
</body>
</html>