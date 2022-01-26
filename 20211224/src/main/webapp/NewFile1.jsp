<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>include</title>
</head>
<body>
<table border=1 width="640">
<tr><th colspan=2><%@ include file="include/top.jsp" %> <br/></th></tr>
<tr><th width=40%><%@ include file = "include/left.jsp" %></th>
	<th width="30%"></th>
	<td><img src="images/img1.jpg"></td>
</tr>
<tr>
	<th colspan=2>
	<%@ include file = "include/footer.jsp" %>
	</th>
</tr>
</table>
</body>
</html>