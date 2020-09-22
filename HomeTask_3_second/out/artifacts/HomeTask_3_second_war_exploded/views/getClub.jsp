<%@ page import="db.classes.Club" %><%--
  Created by IntelliJ IDEA.
  User: Zamanbek
  Date: 21.09.2020
  Time: 15:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <%@include file="./templates/cdns.jsp" %>
    <%@include file="./templates/style.jsp" %>
</head>
<body>
<%@include file="./templates/navbar.jsp" %>
<div class="container">
    <br>

    <%
        String path = (String)request.getAttribute("path");
    %>
    <div class="card" >
        <div class="card-body" style="font-weight: bold">
            <%=path%>
        </div>
    </div>

    <br>
    <%
        Club club = (Club)request.getAttribute("club");
        if(club!=null){
    %>
    <div class="card" style="background-color: white">
        <div class="card-header">
            <span style="font-weight: bolder"><%=club.getName()%></span>
        </div>

        <div class="card-body">
            <div class="container jumbotron">
                <h1 class="display-4"><%=club.getName()%></h1>
                <p class="lead"><%=club.getDescription()%></p>
                <hr class="my-4">
                <p>Founded on: <%=club.getFoundedYear()%></p>
            </div>
        </div>
    </div>

    <%
        }
    %>
</div>
</body>
</html>
