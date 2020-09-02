<%@ page import="db.Footballer" %><%--
  Created by IntelliJ IDEA.
  User: Zamanbek
  Date: 02.09.2020
  Time: 11:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Get</title>
</head>
<body>
<h1>Hello</h1>
<%
    Footballer f = (Footballer) request.getAttribute("footballler");
    int this_id = (int) request.getAttribute("this_id");
    if(f!=null){
%>



<h2 style='color:#46A478; border:2px solid grey; border-radius:3px;'><strong><%=this_id%>)<%=f.getName()%>   <%=f.getSurname()%> </strong></h2>
<h4> Club: <big style="color: darkblue;"> <%=f.getClub()%>  </big> </h4>
<h4> Salary: <big style="color: darkblue;"> <strong> <%=f.getSalary()%> </strong> </big> M$</h4>
<h4> Transfer Price:  <big style="color: darkblue;"> <strong> <%=f.getTransferPrice()%> </strong> </big>M$</h4>
<form action="/delete" method="POST">
    <input type='hidden' name="delete_id" value='<%=this_id%>'>
    <button>Delete</button>
</form>
<%

    }
%>


</body>
</html>
