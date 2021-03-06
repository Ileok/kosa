<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.aspnetcdn.com/ajax/jQuery/jquery-1.8.1.min.js"></script>
<script type="text/javascript">
$(function () {
	$("#delete").click(function() {
		//location.href="../memberDelete?num=[[${memberCommand.memberNum}]]";
		$.ajax({
			type : "post",
			url : "../memberDelete",
			data : {"num":"${memberCommand.memberNum}"},
			dataType : "text",
			success : function(result){
				alert(result);
				if(result.trim() == '1'){
					alert("회원이 정상적으로 삭제 되었습니다.");
					location.href="../memList";
				}else{
					alert("회원이 삭제되지 않았습니다.");
					return false;
				}
			},
			error : function() {
				alert('에러가 나왔다 홀홀홀~');
				return;
			}
		});
	});
});
</script>
</head>
<body>
회원 3등급 이상만 볼 수 있습니다.
회원 아이디 : ${memberCommand.memberId}<br/>
회원 이름 : ${memberCommand.memberName}<br/>
회원 주소 : ${memberCommand.memberAddr}<br/>
등록일 :   <fmt:formatDate value="${memberCommand.memberRegist}" 
					pattern="yyyy-MM-dd"/>  <br/>
성별 :  <c:if test="${memberCommand.gender=='M' }" >남자</c:if>
      <c:if test="${memberCommand.gender=='F' }" >여자</c:if> <br />
연락처 :    ${memberCommand.memberPhone} <br/>
생년월일 :   <fmt:formatDate value="${memberCommand.memberBirth}" 
					pattern="yyyy-MM-dd"/></span> <br/>
회원 이메일 :  ${memberCommand.memberEmail}<br/>
<form action="../memberModify" method="get">
 <input type="hidden" name="memberNum" value="${memberCommand.memberNum}" ><br/>
<input type="submit" value="수정해봐요"/>
<input type="button" value="삭제" id="delete">
</body>
</html>