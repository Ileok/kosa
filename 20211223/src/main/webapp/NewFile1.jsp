<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script>
   var sum=0;
   for (var i=0; i<=100; i++){
      sum+=i;
   }
   document.write("javascript : 1~100까지의 합은" + sum + "</br>");
</script>
</head>
<body>
10 + 5 = <%= calculator(10,5,"+") %> <br/>
10 - 5 = <%= calculator(10,5,"-") %> <br/>
10 * 5 = <%= calculator(10,5,"*") %> <br/>
10 / 5 = <%= calculator(10,5,"/") %> <br/>
<%
   int sum=0;
   for (int i=1; i<=100; i++){
      sum+=i;
   }
%>
<%= "jsp : 1~100까지의 합은" + sum + "</br>" %>

<% sum=0; %>
<%for (int i=1; i<=100; i++){%>
<%   sum+=i;%>
<% } %>
<%= "jsp : 1~100까지의 합은" + sum + "</br>" %>

<%!
   int y;
   public void test(){
      System.out.println("테스트");
   }
   public double calculator(double num1, double num2, String operator){
      double result = 0.0;
      if (operator.equals("+")) result = num1 + num2 ;
      else if (operator.equals("-")) result = num1 - num2 ;
      else if (operator.equals("*")) result = num1 * num2 ;
      else if (operator.equals("/")) result = num1 / num2 ;
      return result;
   }

%>

</body>
</html>