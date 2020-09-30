<%@ page import="db.classes.Language" %>
<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: zaman
  Date: 9/29/2020
  Time: 7:32 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <%@include file="./templates/cdns.jsp" %>
    <%@include file="./templates/styles.jsp" %>
</head>
<body>
<%@include file="./templates/navbar.jsp" %>

<div class="container-fluid">

    <%
        String table = (String)request.getAttribute("table");
        if(table.equals("languageTable")){
    %>
        <%@include file="./templates/tables/languageTable.jsp" %>
    <%
        } else if(table.equals("publicationTable")){
    %>
        <%@include file="./templates/tables/publicationTable.jsp" %>
    <%
        } else if(table.equals("newsTable")){
    %>
    <%@include file="./templates/tables/newsTable.jsp" %>
    <%  } %>



</div>



</body>
</html>
