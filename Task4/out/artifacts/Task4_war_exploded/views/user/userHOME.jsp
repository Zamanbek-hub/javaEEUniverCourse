<%@ page import="db.classes.News" %><%--
  Created by IntelliJ IDEA.
  User: zaman
  Date: 9/30/2020
  Time: 1:15 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <%@include file="./templates/cdns.jsp" %>
    <% String style = (String)request.getAttribute("style");%>
    <%if(style.equals("1")){
        System.out.println("We are here 1");%>
    <%@include file="./css/DefaultStyle.jsp" %>
    <%}%>
    <%if(style.equals("2")){%>
    <%@include file="./css/LingtStyle.jsp" %>
    <%}%>
    <%if(style.equals("3")){%>
    <%@include file="./css/DarkStyle.jsp" %>
    <%}%>

</head>
<body>
<%@include file="./templates/navbar.jsp" %>

<div class="container">
    <header>
        <div class="header_div">
            <h1 class="header_title">All World News</h1>
            <p class="header_description">You can read all news in different languages in world</p>
        </div>
    </header>

    <div class="row">
        <%
            ArrayList<News> news = (ArrayList<News> )request.getAttribute("news");

            if(news!=null){
                for(News n: news){

        %>
        <div class="card col-5">
            <div class="card-body">
                <h4 class="card-title">First Java EE</h4>
                <p class="card-text">I created</p>
                <a href="" class="btn btn-outline-primary">Read More</a>
            </div>
            <div class="card-footer">

            </div>
        </div>
        <%
                }
            }

        %>
    </div>

</div>


</body>
</html>
