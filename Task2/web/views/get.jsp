<%@ page import="db.clasess.Ticket" %>
<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: Zamanbek
  Date: 09.09.2020
  Time: 10:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <%@include file="templates/cdns.jsp" %>
</head>
<body>
<div class="container">
    <%@include file="templates/navbar.jsp" %>
    <% Ticket ticket = (Ticket)request.getAttribute("ticket");
        if(ticket!=null){
    %>
    <div class="jumbotron">
        <h1 class="display-4"><%=ticket.getFromCity()%> - <%=ticket.getToCity()%></h1>
        <p class="lead">Price:<%=ticket.getPrice()%> KZT</p>
        <%--        <hr class="my-4">--%>
        <p>Duration:<%=ticket.getPrice()%> Hours</p>
        <form action="/delete" method="POST">
            <input type="hidden" name="id" value="<%=ticket.getId()%>">
            <button class="btn btn-danger btn-lg" >Delete</button>
        </form>
    </div>
    <%
        }
    %>
</div>
</body>
</html>
