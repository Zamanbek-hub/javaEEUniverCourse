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
<% Footballer f = (Footballer) request.getAttribute("footballler");
    if(f!=null){
%>



<h2 style='color:darkred; border:1px solid grey; border-radius:3px;'><strong><%=f.getId()%>)<%=f.getName()%>   <%=f.getSurname()%> </strong></h2>
<h4> Club: <%=f.getClub()%>  </h4>
<h4> Salary:  <%=f.getSalary()%> M$</h4>
<h4> Transfer Price:  <%=f.getTransferPrice()%>  M$</h4>
<form action="/delete" method="POST">
    <input type='hidden' name="delete_id" value='<%=f.getId()%>'>
    <button>Delete</button>
</form>
<%

    }
%>


</body>
</html>
