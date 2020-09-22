<%@ page import="db.classes.League" %>
<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: Zamanbek
  Date: 21.09.2020
  Time: 9:46
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
    <br>
    <div class="card" >
        <div class="card-body">
            Home Page
        </div>
    </div>


    <%
        ArrayList<League> leagues = (ArrayList<League> )request.getAttribute("leagues");

        if(leagues!=null){
            for(League league: leagues){
    %>
    <br>
    <div class="card">
        <h5 class="card-header"><%=league.getName()%></h5>
        <div class="card-body">
            <p class="card-text"><%=league.getDescription()%></p>
            <a href="${pageContext.request.contextPath}/leagueClubs?id=<%=league.getId()%>" class="btn btn-dark">Details</a>
        </div>
    </div>
    <%
            }
        }
    %>
</div>

</body>
</html>
